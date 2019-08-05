package com.xstudy.test.thread.lock;

/**
 * @author zurun
 * @date 2019/8/4 16:35:23
 */
public class DeadLockTest {
    private static final String A = "A";
    private static final String B = "B";

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("a-a");
                synchronized (B) {
                    System.out.println("a-b");
                }
            }
        },"t1");

        Thread threadB = new Thread(() -> {
            synchronized (B) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("b-b");
                synchronized (A) {
                    System.out.println("b-a");
                }
            }
        },"t2");

        threadA.start();
        threadB.start();
    }
}
