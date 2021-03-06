package com.company.P2018_11_23;

/**
 * 1. 除了线程池这种池结构会用到队列排队请求，你还知道有哪些类似的池结构或者场景中会用到队列的排队请求呢？
 * 2. 今天讲到并发队列，关于如何实现无锁并发队列，网上有非常多的讨论。对这个问题，你怎么看呢？
 * 3. 循环队列是我们这节的重点。要想写出没有 bug 的循环队列实现代码，关键要确定好队空和队满的 判定条件，具体的代码你要能写出来。
 *
 * @author shijie.xu
 * @since 2018年11月23日
 *
 * 循环队列的数组实现，在代码中，入队时会空留出一个位置，而且我感觉不太好理解。 我定义一个记录队列大小的值size，
 * 当这个值与数组大小相等时，表示队列已满，当tail达到最底时 ，size不等于数组大小时，tail就指向数组第一个位置。
 * 当出队时，size—，入队时size++
 */
public class Queue {
    int size;
    int[] ret = new int[size];
    int head = 0;
    int tail = 0;

    public Queue(int size) {
        this.size = size;
    }

    public boolean input(int data) {
        if((tail + 1) % size == head) {
            return false;
        }
        ret[tail] = data;
        tail = (tail + 1) % size;
        return true;
    }

    public int output() {
        if(head == tail) {
            return -1;
        }
        int result = ret[head];
        head=(head+1)%size;
        return result;
    }

}
