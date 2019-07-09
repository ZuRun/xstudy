package com.xstudy.test.thread.aqs.test;

import com.xstudy.test.thread.aqs.MyReentrantSelfLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

/**
 * @author zurun
 * @date 2019/7/9 19:39:40
 */
public class MyReentrantSelfLockTest {

    public static void main(String[] args) {
//        nonFairTest();
        reentrantTest();
    }

    private static final Lock nonFairlock = new MyReentrantSelfLock();

    private static Runnable nonFairTask = () -> {
        nonFairlock.lock();
        System.out.println(Thread.currentThread().getName() + " 获取到锁");
        try {
            System.out.println(Thread.currentThread().getName() + "------ ");
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            nonFairlock.unlock();
            System.out.println(Thread.currentThread().getName() + " 释放锁");
        }
    };

    /**
     * 验证lock是否可以保证锁(非公平锁)
     */
    private static void nonFairTest() {
        final int poolSize = 20;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            executorService.execute(nonFairTask);
        }
    }

    /**
     * 可重入测试
     */
    private static void reentrantTest() {
        nonFairlock.lock();
        printAndSleep(1);
        nonFairlock.lock();
        printAndSleep(2);
        new Thread(() -> {
            nonFairlock.lock();
            System.out.println(Thread.currentThread().getName() + "---- start");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            nonFairlock.unlock();
            System.out.println(Thread.currentThread().getName() + "---- end");

        }, "线程B").start();
        nonFairlock.lock();
        printAndSleep(3);
        nonFairlock.lock();
        printAndSleep(4);
        nonFairlock.unlock();
        printAndSleep(5);
        nonFairlock.unlock();
        printAndSleep(6);

        nonFairlock.unlock();
        printAndSleep(7);
        nonFairlock.unlock();
        printAndSleep(8);
    }

    private static void printAndSleep(int i) {
        System.out.println("------- start --- " + i);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------- e n d --- " + i);

    }

}
