package com.lr.flink.flink.beaver;

import cn.yottabyte.flink.beaver.indexfilter.IndexFilter;
import cn.yottabyte.flink.beaver.indexfilter.TimeAndIndexPrefixFilter;
import cn.yottabyte.search.IndexName;
import com.google.common.collect.Sets;
import org.apache.flink.api.common.io.InputFormat;
import org.apache.flink.api.common.io.statistics.BaseStatistics;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.io.InputSplitAssigner;
import org.elasticsearch.common.unit.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author chengtao
 * @date 26/11/2020 - 10:54
 */
public class BeaverInputFormat implements InputFormat<Row, BeaverInputSplit> {

    private final Logger logger = LoggerFactory.getLogger(BeaverInputFormat.class);
    private final BeaverConfig beaverConfig;
    private final SearchBundle searchBundle;
    private transient ScrollQuery scrollQuery;
    private transient BeaverService beaverService;

    public BeaverInputFormat(BeaverConfig beaverConfig,
                             SearchBundle searchBundle) {
        super();
        this.beaverConfig = beaverConfig;
        this.searchBundle = searchBundle;
    }

    @Override
    public void configure(Configuration parameters) {

    }

    @Override
    public BaseStatistics getStatistics(BaseStatistics cachedStatistics) throws IOException {
        return cachedStatistics;
    }

    @Override
    public BeaverInputSplit[] createInputSplits(int minNumSplits) throws IOException {
        if (beaverService == null) {
            this.beaverService = new BeaverService(beaverConfig);
        }

        // filter indices
        Set<String> indices = beaverService.getIndices();
        IndexFilter indexFilter = new TimeAndIndexPrefixFilter(
                searchBundle.getIndexPrefix(), searchBundle.getEarliest(), searchBundle.getLatest());
        Set<String> searchIndices = Sets.newHashSet();
        for (String index : indices) {
            IndexName indexName = IndexName.parse(index);
            if (indexFilter.filterIndex(indexName)) {
                searchIndices.add(index);
            }
        }
        logger.info("search indices: {}", searchIndices);
        if (searchIndices.isEmpty()) {
            return new BeaverInputSplit[0];
        }

        List<PartitionDef> partitions = beaverService.listPartitionDef(searchIndices);
        logger.info("partitions {}", partitions);
        BeaverInputSplit[] splits = new BeaverInputSplit[partitions.size()];
        int index = 0;
        for (PartitionDef partition : partitions) {
            BeaverShard shard = (BeaverShard)partition;
            BeaverInputSplit split = new BeaverInputSplit(index, shard);
            splits[index++] = split;
        }
        return splits;
    }

    @Override
    public InputSplitAssigner getInputSplitAssigner(BeaverInputSplit[] inputSplits) {
        return new ShuffleInputSplitAssigner(inputSplits);
    }

    @Override
    public void open(BeaverInputSplit split) throws IOException {
        if (beaverService == null) {
            beaverService = new BeaverService(beaverConfig);
        }
        BeaverScrollRequestBuilder bsrb = new BeaverScrollRequestBuilder(beaverService.getClient());

        bsrb.setEarliest(searchBundle.getEarliest());
        bsrb.setLatest(searchBundle.getLatest());
        bsrb.setQueryString(searchBundle.getQueryString());
        bsrb.setFields(searchBundle.getFields());
        bsrb.setBeaverShard(split.getBeaverShard());

        BeaverScrollQuery beaverScrollQuery = beaverService.executeScroll(
                bsrb, TimeValue.timeValueMinutes(5), TimeValue.timeValueMinutes(5));

        this.scrollQuery = new ScrollQueryWrapper(beaverScrollQuery);

    }

    @Override
    public void close() throws IOException {
        if (scrollQuery != null) {
            scrollQuery.close();
            scrollQuery = null;
        }
        if (beaverService != null) {
            beaverService.close();
        }
    }

    @Override
    public boolean reachedEnd() throws IOException {
        return scrollQuery == null || !scrollQuery.hasNext();
    }

    @Override
    public Row nextRecord(Row reuse) throws IOException {
        return scrollQuery.next();
    }

}
