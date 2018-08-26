package com.xstyud.test;

import com.xstyud.test.utils.BaseTest;
import com.xstyud.test.utils.LogUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author zurun
 * @date 2018/8/25 22:46:43
 */
public class StartAndRun extends BaseTest {
    private static class ThreadRun extends Thread {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
                log(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 先执行run()方法,和执行一个普通方法一样,整个进程被sleep了
     * 两次输出时间差了1s
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread t = new ThreadRun();
        t.setName("BeCalled");
        // 输出"main"
        t.run();
        // 输出"BeCalled",两次输出时间间隔1s
        t.start();
    }
}