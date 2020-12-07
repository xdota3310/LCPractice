package com.lr.flink.datastream;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Random;

/**
 * @author xu.shijie
 * @since 12/7/20
 */
public class MyStreamingSource implements SourceFunction<MyStreamingSource.Item> {
    private Boolean isRunning = true;

    @Override
    public void run(SourceContext<Item> sourceContext) throws Exception {
        while(isRunning){
            Item item = generateItem();
            sourceContext.collect(item);
            //每秒产生一条数据
            Thread.sleep(1000);
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }

    private Item generateItem(){
        int i = new Random().nextInt(100);
        Item item = new Item();
        item.setName("name" + i);
        item.setId(i);
        return item;
    }

    class Item{
        private String name;
        private Integer id;
        Item() {
        }
        public String getName() {
            return name;
        }
        void setName(String name) {
            this.name = name;
        }
        private Integer getId() {
            return id;
        }
        void setId(Integer id) {
            this.id = id;
        }
        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<Item> source = env.addSource(new MyStreamingSource()).setParallelism(1);
//        SingleOutputStreamOperator<Object> itemDataStream = source.map(Item::getName);
        SingleOutputStreamOperator<Object> itemDataStream = source.map(Item::getName);
        itemDataStream.print().setParallelism(1);
        env.execute("user defined streaming source");
    }
}
