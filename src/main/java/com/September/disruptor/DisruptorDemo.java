package com.September.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * 高性能队列Disruptor
 *
 * 1. 内存分配更加合理，使用RingBuffer数据结构，数组元素在初始化时一次性全部创建，提升缓存命中率；
 * 对象循环利用，避免频繁GC。
 * 2. 能够避免伪共享，提升缓存利用率。
 * 3. 采用无锁算法，避免频繁加锁、解锁的性能消耗。
 * 4. 支持批量消费，消费者可以无锁方式消费多个消息。
 *
 * @author shijie.xu
 * @since 2019年09月02日
 */
public class DisruptorDemo {
    class LongEvent {
        private long value;

        public void setValue(long value) {
            this.value = value;
        }

    }

    int bufferSize = 1024;

    Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);

    public void init() {
//        注册事件处理器
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("E: " + event.value));
//        启动
        disruptor.start();
//        获取RingBuffer
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
//        生成Event
        ByteBuffer bb = ByteBuffer.allocate(8);
        for(long l = 0; true; l++) {
            bb.putLong(0, l);
            ringBuffer.publishEvent((event, sequence, arg0) -> event.setValue(arg0.getLong(0)), bb);
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new DisruptorDemo().init();
    }


}
