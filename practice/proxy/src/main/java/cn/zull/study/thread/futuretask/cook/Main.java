package cn.zull.study.thread.futuretask.cook;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zurun
 * @date 2018/10/4 21:29:12
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureTask<Chuju> futureTask = new FutureTask<>(() -> {
            System.out.println("---");
            Thread.sleep(4000L);
            return new Chuju();

        });

        new Thread(futureTask).start();

        System.out.println("开始购买食材");
        Thread.sleep(3000L);
        System.out.println("购买食材完毕");

        if (!futureTask.isDone()) {
            System.out.println("第三步：厨具还没到，心情好就等着（心情不好就调用cancel方法取消订单）");
        }

        Chuju chuju = futureTask.get();
        System.out.println("第三步：厨具到位，开始展现厨艺");
    }

    static void cook() {

    }

    static class Chuju {
    }

    ;
}
