package com.xstudy.test.thread.pool;

import java.util.Random;

/**
 * @author zurun
 * @date 2019/6/30 20:41:16
 */
public class DbPoolTest {
    private static DbPool pool = new DbPool(10);

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(useConnection, "db-" + i);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    private static Runnable useConnection = () -> {
        DbConnection connection = pool.fetchConnection();
        System.out.println(connection.getNum());
        try {
            int nextInt = new Random().nextInt(1000);
            Thread.sleep(1000L + nextInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        connection.release();
    };
}
