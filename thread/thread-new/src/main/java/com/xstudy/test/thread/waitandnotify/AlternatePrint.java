package com.xstudy.test.thread.waitandnotify;


import java.util.Random;

/**
 * a,b线程交替打印
 *
 * @author zurun
 * @date 2019/6/30 23:11:29
 */
public class AlternatePrint {

    public static void main(String[] args) {
        useWaitNotify();
    }

    private static volatile boolean flag = true;

    public static void useWaitNotify() {
        final Object obj = new Object();

        new Thread(() -> {
            synchronized (obj) {
                while (true) {
                    try {
                        if (flag) {

                            Thread.sleep(new Random().nextInt(200));
                            System.out.println("-----A----");
                            flag = false;
                            obj.notifyAll();
                        }
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
        new Thread(() -> {
            synchronized (obj) {
                while (true) {
                    try {
                        if (!flag) {
                            Thread.sleep(new Random().nextInt(200));
                            System.out.println("-----B----");
                            flag = true;
                            obj.notifyAll();
                        }
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
