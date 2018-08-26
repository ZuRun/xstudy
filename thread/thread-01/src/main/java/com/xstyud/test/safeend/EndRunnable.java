package com.xstyud.test.safeend;


/**
 * @author zurun
 * @date 2018/8/22 23:40:12
 */
public class EndRunnable {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Thread is start");
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(threadName + " is run");
            }
            System.out.println(threadName + " interrupt flag is " + Thread.currentThread().isInterrupted());
        }, "endThreadA");

        thread.start();
        Thread.sleep(100L);
        thread.interrupt();
        Thread.sleep(1000L);

    }
}
