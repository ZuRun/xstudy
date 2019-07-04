package com.xstudy.test.thread.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zurun
 * @date 2019/7/3 20:10:07
 */
public class ReentrantLockTest {
    private static Lock lock = new ReentrantLock();
    private static long sum = 0;
    private static final int poolSize = 10;
    private static final CountDownLatch countDownLatch = new CountDownLatch(poolSize);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            executorService.execute(runnable);
        }
        countDownLatch.await();
        System.out.println(sum);
    }

    private static Runnable runnable = () -> {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            run1();
        }
        countDownLatch.countDown();
    };

    public static void run1() {
        lock.lock();
        try {
            sum++;
            System.out.println(Thread.currentThread().getName() + "   ----- run1");
            run2();
        } finally {
            lock.unlock();
        }
    }

    public static void run2() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "   ----- run2");
            sum++;
        } finally {
            lock.unlock();
        }
    }

}
