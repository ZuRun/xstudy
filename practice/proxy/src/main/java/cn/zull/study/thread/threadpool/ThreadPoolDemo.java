package cn.zull.study.thread.threadpool;

import cn.zull.study.basis.utils.InputUtils;
import cn.zull.study.basis.utils.UuidUtils;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

/**
 * @author zurun
 * @date 2018/10/6 00:31:47
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        MonitorHandler monitorHandler = new MonitorHandler() {
        };
        Thread monitorThread = new Thread(() -> {
            while (true) {
                try {
                    monitorHandler.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //        ExecutorService executorService = Executors.newFixedThreadPool(5, r -> new Thread(r,"thread-pool"));
        MonitorableThreadPoolExecutor threadPoolExecutor = new MonitorableThreadPoolExecutor(5, 5,
                5, TimeUnit.SECONDS, new LinkedBlockingQueue(), r -> new Thread(r, "线程名字"));

        threadPoolExecutor.addMonitorTask(monitorHandler);
        threadPoolExecutor.execute(new ThreadTest("aa"));
        threadPoolExecutor.execute(new ThreadTest("bb"));
        threadPoolExecutor.execute(new ThreadTest("cc"));
        threadPoolExecutor.execute(new ThreadTest("dd"));
        threadPoolExecutor.execute(new ThreadTest("ee"));
        threadPoolExecutor.execute(new ThreadTest("ff"));

//        threadPoolExecutor.shutdownNow();
        System.out.println("---");
    }

    static class ThreadTest implements Runnable {
        private String flag;

        public ThreadTest(String flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            System.out.println("flag:" + Thread.currentThread().getName() + "_" + flag);
            try {
                Thread.sleep(2000L);
//                Thread.currentThread().isInterrupted();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
