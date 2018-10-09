package cn.zull.study.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author zurun
 * @date 2018/10/9 14:28:13
 */
public class ArrayBlockingQueueDemo {
    private static ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println(arrayBlockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
        for (int i = 0; i < 10001; i++) {
            System.out.println(arrayBlockingQueue.offer("xxxx" + i));
        }
        System.out.println("_____");
    }


}
