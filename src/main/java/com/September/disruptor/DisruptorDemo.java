package com.September.disruptor;

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

}
