package com.flink.dataset;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @author xu.shijie
 * @since 12/7/20
 */
public class WordCount {
    public static final class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) {
            String[] tokens = s.toLowerCase().split("\\W+");
            for (String token : tokens) {
                if (token.length() > 0) {
                    collector.collect(new Tuple2<>(token, 1));
                }
            }
        }
    }

    public void doExecute() throws Exception {
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<String> dataSet = env.fromElements(
                "Flink Spark Storm",
                "Flink Flink Flink",
                "Spark Spark Spark",
                "Storm Storm Storm"
        );

        DataSet<Tuple2<String, Integer>> counts = dataSet.flatMap(new LineSplitter()).groupBy(0).sum(1);
        counts.print();
    }

    public static void main(String[] args) throws Exception {
        new WordCount().doExecute();
    }
}
