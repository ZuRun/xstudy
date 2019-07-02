package com.xstudy.test.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zurun
 * @date 2019/7/1 21:11:26
 */
public class FutureTaskTest {

    private static Callable<Integer> remoteQeq = () -> {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(2L);
            sum++;
        }
        System.out.println("----远程调用----");
        return sum;
    };

    public static void main(String[] args) {

        FutureTask<Integer> futureTask = new FutureTask<>(remoteQeq);

        new Thread(futureTask).start();

        System.out.println("task是否取消:" + futureTask.isCancelled());

//        futureTask.cancel(false);
        System.out.println("task是否取消:" + futureTask.isCancelled());
        try {
            Integer result = futureTask.get();
            System.out.println("task结果:" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("task是否取消:" + futureTask.isCancelled());


    }
}
