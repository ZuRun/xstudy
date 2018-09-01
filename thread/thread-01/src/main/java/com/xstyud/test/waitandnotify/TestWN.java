package com.xstyud.test.waitandnotify;

/**
 * @author zurun
 * @date 2018/9/1 22:51:05
 */
public class TestWN {
    private static Express express = new Express(0, Express.CITY);


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> express.waitKm(), "checkKmThread" + i)
                    .start();
        }
        Thread.sleep(1000L);

        for (int i = 0; i < 110; i++) {
            express.changeKm(i);
        }

        System.out.println("主线程结束");
    }
}
