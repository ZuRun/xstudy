package com.xstudy.test.thread.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zurun
 * @date 2019/7/2 12:48:05
 */
public class AtomicTest {
    private static final int poolSize = 10;
    private static CountDownLatch countDownLatch = new CountDownLatch(poolSize);
    private static AtomicTest atomicTest = new AtomicTest();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            executorService.execute(runnable);
        }
        countDownLatch.await();
        System.out.println(atomicTest.getSum());
    }

    private static Runnable runnable = () -> {
        for (int i = 0; i < 1001; i++) {
            atomicTest.increment();
        }
        System.out.println(Thread.currentThread().getName() + ":线程结束");
        countDownLatch.countDown();
    };

    private AtomicInteger atomic = new AtomicInteger(0);

    /**
     * 核心代码,使用循环CAS,不成功则一直重试.
     */
    private void increment() {
        while (true) {
            int oldValue = atomic.get();
            boolean suc = atomic.compareAndSet(oldValue, ++oldValue);
            if (suc) {
                break;
            }
        }
    }

    public int getSum() {
        return atomic.get();
    }
}
