package com.xstudy.test.thread.safeend;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zurun
 * @date 2019/6/28 21:50:52
 */
public class VolatileTest {
    /**
     * 即使加了volatile,也不能保证线程安全
     */
    private static volatile long count = 0;

    private static AtomicLong atomicCount = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        Thread count1 = new Thread(countTask);
        Thread count2 = new Thread(countTask);
        count1.start();
        count2.start();

        count1.join();
        count2.join();
        System.out.println(count);
        System.out.println(atomicCount.get());
    }

    private static Runnable countTask = () -> {
        for (int i = 0; i < 10000; i++) {
            count++;
            atomicCount.addAndGet(1);
        }
    };
}
