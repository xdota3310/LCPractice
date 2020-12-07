package com.lr.flink.datastream;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @author xu.shijie
 * @since 12/7/20
 */
public class WordCount {
    public static class WordWithCount {
        public String word;
        public long count;

        public WordWithCount() {
        }

        public WordWithCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return word + " : " + count;
        }
    }

    public static void doCount() throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> dataStream = env.socketTextStream("127.0.0.1", 9000, "\n");
        DataStream<WordWithCount> windowCounts = dataStream.flatMap(new FlatMapFunction<String, WordWithCount>() {
            @Override
            public void flatMap(String s, Collector<WordWithCount> collector) throws Exception {
                for (String word : s.split("\\s")) {
                    collector.collect(new WordWithCount(word, 1L));
                }
            }
        }).keyBy("word").timeWindow(Time.seconds(6), Time.seconds(1))
                .reduce(new ReduceFunction<WordWithCount>() {
                    @Override
                    public WordWithCount reduce(WordWithCount w1, WordWithCount w2) throws Exception {
                        return new WordWithCount(w1.word, w1.count + w2.count);
                    }
                });
        windowCounts.print().setParallelism(1);
        env.execute("Socket Window WordCount");
    }

    public static void main(String[] args) throws Exception {
        doCount();
    }
}
