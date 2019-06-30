package cn.enjoyedu.ch1.vola;

import cn.enjoyedu.tools.SleepTools;
import org.apache.commons.lang.time.StopWatch;

/**
 * 类说明：演示Volatile的提供的可见性
 */
public class VolatileCase {
    private static boolean ready;
//        private volatile static boolean ready;
    private static int number;

    private static class PrintThread extends Thread {
        @Override
        public void run() {
            System.out.println("PrintThread is running.......");
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (!ready) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println(System.currentTimeMillis() + " ready:" + ready);
                ;
            }
            stopWatch.stop();
            System.out.println("time=  " + stopWatch.getTime() + " number = " + number);
        }
    }

    public static void main(String[] args) {
        new PrintThread().start();
        SleepTools.second(1);
        number = 51;
        ready = true;
//        SleepTools.second(5);
        System.out.println("main is ended!");
    }
}
