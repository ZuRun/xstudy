package safeend;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义变量标示是否中断线程CustomizedInterruptFlag
 *
 * @author ZuRun
 * @date 2018/08/24 00:02:11
 */
public class CustomizedInterruptFlag {
    private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public static void log(String message) {
        System.out.println(formatter.format(new Date()) + " - " + message);
    }

    /**
     * 自定义变量标示是否中断线程
     */
    public static class CustomizedInterruptThread extends Thread {
        /**
         * 是否中断线程
         */
        private boolean cancel = false;

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
                    log(threadName + " is run,flag is " + cancel);
                } catch (InterruptedException e) {
                    log(threadName + " throw InterruptedException,flag is " + cancel);
                    e.printStackTrace();
                }
            }
            log(threadName + " cancel flag is " + cancel);
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
                    log(threadName + " is run,flag is " + Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    log(threadName + " throw InterruptedException,flag is " + Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
            }
            log(threadName + " cancel flag is " + Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread flagInterruptThread = new CustomizedInterruptThread("FlagInterruptThread");
        flagInterruptThread.start();
        Thread.sleep(100L);
        flagInterruptThread.interrupt();
        log("giving instructions to interrupt ");


//        Thread t = new Thread(new InterruptThread());
//        t.start();
//        t.interrupt();
//        log("giving instructions to interrupt ");

    }
}
