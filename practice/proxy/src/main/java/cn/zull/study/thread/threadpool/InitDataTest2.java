package cn.zull.study.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zurun
 * @date 2019/1/31 15:09:22
 */
public class InitDataTest2 {
    /**
     * 线程数
     */
    private static final Integer nThreads = 100;
    private static ExecutorService executorService = Executors.newFixedThreadPool(nThreads, r -> new Thread(r, "thread-pool"));

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            test();
        }
    }

    public static void test() {
        executorService.execute(() -> {
            System.out.println("xxx");
        });
    }
}
