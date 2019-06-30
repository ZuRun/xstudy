package com.xstudy.test;

import java.util.concurrent.ExecutionException;

/**
 * 守护线程
 *
 * @author zurun
 * @date 2018/8/26 18:06:15
 */
public class DaemonThread {
    private static class UseThread extends Thread {
        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    System.out.println(Thread.currentThread().getName()
                            + " I am extends Thread.");
                }
                System.out.println(Thread.currentThread().getName()
                        + " interrupt flag is " + isInterrupted());
            } finally {
                System.out.println("...........finally");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread useThread = new UseThread();
        // 设置为守护线程,需要再start()方法之前
        useThread.setDaemon(true);
        useThread.start();
        Thread.sleep(1);
//        useThread.interrupt();
    }
}
