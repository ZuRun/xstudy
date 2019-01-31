package cn.zull.study.thread.threadpool;

import java.util.concurrent.*;

/**
 * @author zurun
 * @date 2018/10/6 00:31:47
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        newFixedThreadPool();
    }

    public static void monitorableThreadPoolExecutor() {
        MonitorHandler monitorHandler = new MonitorHandler() {
        };
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

    public static void newFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(5, r -> new Thread(r, "thread-pool"));

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.execute(() -> {
                System.out.println(finalI);
            });
        }
        System.out.println("end");

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
