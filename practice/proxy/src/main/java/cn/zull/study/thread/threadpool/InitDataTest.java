package cn.zull.study.thread.threadpool;

import cn.zull.study.basis.utils.NoArgsFunction;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zurun
 * @date 2019/1/31 15:09:22
 */
@Slf4j
public class InitDataTest {
    /**
     * 线程数
     */
    private static final Integer nThreads = 100;
    private static final Integer maxNumber = 10000;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        getThread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(atomicInteger.get());
        });

    }

    public static void getThread(NoArgsFunction noArgsFunction) {
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads, r -> new Thread(r, "thread-pool"));

        for (int i = 0; i < nThreads; i++) {
            int finalI = i;
            executorService.execute(() -> {
                while (true) {
                    safeCount();
                    noArgsFunction.todo();
                    int number = atomicInteger.get();
                    if (number > maxNumber) {
                        Thread thread = Thread.currentThread();
                        log.info("[线程关闭] tName:{} ", thread.getName() + ":" + finalI);
                        thread.interrupt();
                        break;
                    }
                    if (number % 100 == 0) {
                        log.info("[] 操作数量:{}", number);
                    }
                }
            });
        }
    }

    private static void safeCount() {
        for (; ; ) {
            int i = atomicInteger.get();
            boolean isSuccess = atomicInteger.compareAndSet(i, ++i);
            if (isSuccess) {
                break;
            }
        }
    }
}
