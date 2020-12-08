package com.lr.flink.flink;

import cn.yottabyte.OriginalLog;
import cn.yottabyte.flink.beaver.BeaverInputFormat;
import cn.yottabyte.flink.beaver.Row;
import cn.yottabyte.flink.beaver.SearchBundle;
import cn.yottabyte.flink.mongo.MongoOutputFormat;
import cn.yottabyte.model.Model;
import cn.yottabyte.spark.function.ModelMergeFunctions;
import cn.yottabyte.spark.serializable.SettingsSeri;
import cn.yottabyte.util.LoggerFactory;
import com.google.common.collect.Sets;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.util.CollectionUtil;
import org.apache.flink.util.Collector;
import org.apache.flink.util.Preconditions;
import org.apache.hadoop.util.hash.Hash;
import org.apache.hadoop.util.hash.MurmurHash;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static cn.yottabyte.flink.beaver.FieldConst.*;

/**
 * @author chengtao
 * @date 23/11/2020 - 19:58
 */
public class FlinkTrainJob {

    private static Logger logger = LoggerFactory.getLogger(FlinkTrainJob.class);
    private static Set<String> FIELD_SET = Sets.newHashSet(RAW_MESSAGE, TIMESTAMP, ID);
    private final FlinkJobArgs args;

    public FlinkTrainJob(FlinkJobArgs flinkJobArgs) {
        this.args = flinkJobArgs;
    }

    public void run() throws Exception {
        logger.info("run train job for {} ", args);

        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(args.getTrainJobBundle().getTrainJobParallelism());
        SearchBundle searchBundle = buildSearchBundle();
        TrainJobBundle trainJobBundle = args.getTrainJobBundle();
        SettingsSeri settingsSeri = SettingsSeri.buildFromSettings(args.getSettings());

        DataSet<OriginalLog> raw = env.createInput(
                new BeaverInputFormat(args.getBeaverConfig(), searchBundle)).map(
                new MapFunction<Row, OriginalLog>() {
                    @Override
                    public OriginalLog map(Row r) throws Exception {
                        return new OriginalLog((String)r.getField(ID),
                                (Long) r.getField(TIMESTAMP), (String) r.getField(RAW_MESSAGE));
                    }
                }
        );

        final long count = raw.count();
        final long partitionCount = computePartitionNumber(count, args.getTrainJobBundle().getMaxRowCountPerPartition());
        Preconditions.checkState(partitionCount != 0, "partition count can not be zero");

        logger.info("partition for job {} count {} partition {} ",
                args.getTrainJobBundle().getName(), count, partitionCount);

        UnsortedGrouping<OriginalLog> groupRows = raw.groupBy(
                (OriginalLog value) -> {
                    final Hash murmurHash = MurmurHash.getInstance();
                    String key = value.getId();
                    Preconditions.checkNotNull(key);
                    return murmurHash.hash(key.getBytes()) % partitionCount;
                }
        );

        List<Model> modelList = groupRows.reduceGroup(
                new GroupReduceFunction<OriginalLog, Model>() {

                    @Override
                    public void reduce(Iterable<OriginalLog> values, Collector<Model> out) throws Exception {
                        Model model = ModelMergeFunctions.trainPattern(
                                trainJobBundle.getName(),
                                settingsSeri,
                                trainJobBundle.isStopWordMask(),
                                trainJobBundle.isPunctuationMask(),
                                trainJobBundle.getRegexConfigList(),
                                new Iterator<String>() {
                                    Iterator<OriginalLog> iter = values.iterator();
                                    @Override
                                    public boolean hasNext() {
                                        return iter.hasNext();
                                    }

                                    @Override
                                    public String next() {
                                        return iter.next().getLogBody();
                                    }
                                }
                        );
                        out.collect(model);
                    }
                }
        ).reduce((v1, v2) -> ModelMergeFunctions.mergePatterns(v1, v2, settingsSeri)).collect();

        logger.info("train job {}, total row count {}, partition count {}, row count per parititon {}, model list: {}",
                trainJobBundle, count, partitionCount, count / partitionCount, modelList.size());

        if (CollectionUtil.isNullOrEmpty(modelList)) {
            logger.error("no model trained for {}", trainJobBundle);
        } else {
            Preconditions.checkState(modelList.size() == 1);
            groupRows.reduceGroup(new GroupReduceFunction<OriginalLog, Model>() {
                @Override
                public void reduce(Iterable<OriginalLog> values, Collector<Model> out) throws Exception {
                    Model model = ModelMergeFunctions.trainParameter(values.iterator(), modelList.get(0));
                    out.collect(model);
                }
            }).reduce((v1, v2) -> ModelMergeFunctions.mergeParameters(v1, v2, settingsSeri))
                    .output(new MongoOutputFormat(args.getMongoConfig(), settingsSeri));
        }

        env.execute();
    }

    private SearchBundle buildSearchBundle() {
        TrainJobBundle bundle = args.getTrainJobBundle();
        SearchBundle sb = new SearchBundle();
        sb.setEarliest(bundle.getEarliest())
                .setLatest(bundle.getLatest())
                .setFields(FIELD_SET)
                .setIndexPrefix(bundle.getIndexPrefix())
                .setQueryString(bundle.getQueryString());
        return sb;
    }

    private long computePartitionNumber(long logCount, int maxCountPerPartition) {
        Preconditions.checkNotNull(maxCountPerPartition);
        Preconditions.checkArgument(maxCountPerPartition != 0,
                "max count per partition can not be zero");
        // logCount % maxCountPerPartition != 0 then plus 1
        return (logCount + maxCountPerPartition - 1) / maxCountPerPartition;
    }
}
