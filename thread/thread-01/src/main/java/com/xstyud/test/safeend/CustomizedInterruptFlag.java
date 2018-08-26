package com.xstyud.test.safeend;


import com.xstyud.test.utils.LogUtils;

/**
 * 自定义变量标示是否中断线程CustomizedInterruptFlag
 *
 * @author ZuRun
 * @date 2018/08/24 00:02:11
 */
public class CustomizedInterruptFlag {

    /**
     * 自定义变量标示是否中断线程
     */
    public static class CustomizedInterruptThread extends Thread {
        /**
         * 是否中断线程
         */
        private volatile boolean cancel = false;

        @Override
        public void interrupt() {
            this.cancel = true;
        }

        public CustomizedInterruptThread(String threadName) {
            super(threadName);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (!cancel) {
                try {
                    Thread.sleep(2000L);
                    LogUtils.log(threadName + " is run,flag is " + cancel);
                } catch (InterruptedException e) {
                    LogUtils.log(threadName + " throw InterruptedException,flag is " + cancel);
                    e.printStackTrace();
                }
            }
            LogUtils.log(threadName + " cancel flag is " + cancel);
        }
    }

    /**
     * interrupt()方法标示 中断
     */
    public static class InterruptThread implements Runnable {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000L);
                    LogUtils.log(threadName + " is run,flag is " + Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    // 根据业务需要,决定是否重新标识中断标识
                    Thread.currentThread().interrupt();
                    LogUtils.log(threadName + " throw InterruptedException,flag is " + Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
            }
            LogUtils.log(threadName + " cancel flag is " + Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        Thread flagInterruptThread = new CustomizedInterruptThread("FlagInterruptThread");
//        flagInterruptThread.start();
//        Thread.sleep(100L);
//        flagInterruptThread.interrupt();
//        LogUtils.log("giving instructions to interrupt ");


        Thread t = new Thread(new InterruptThread(),"InterruptThread");
        t.start();
        Thread.sleep(100L);
        // 当子线程在休眠的时候,调用中断方法,会抛出InterruptedException
        t.interrupt();
        LogUtils.log("giving instructions to interrupt2 ");

    }
}
