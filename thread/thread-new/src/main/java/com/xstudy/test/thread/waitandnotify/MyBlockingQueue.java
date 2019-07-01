package com.xstudy.test.thread.waitandnotify;

import java.util.LinkedList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用wait和notify实现阻塞队里
 *
 * @author zurun
 * @date 2019/7/1 12:08:31
 */
public class MyBlockingQueue<T> {
    private final LinkedList<T> queue = new LinkedList<>();
    private final int maxSize;
    private AtomicInteger currentSize = new AtomicInteger(0);

    public MyBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public int size() {
        return currentSize.get();
    }

    public T take() throws InterruptedException {
        synchronized (queue) {
            while (currentSize.get() == 0) {
                queue.wait();
            }
            T t = queue.removeFirst();
            currentSize.getAndDecrement();
            queue.notifyAll();
            return t;
        }
    }

    public void put(T t) throws InterruptedException {
        synchronized (queue) {
            while (currentSize.get() >= maxSize) {
                queue.wait();
            }
            currentSize.getAndIncrement();
            queue.addLast(t);
            queue.notifyAll();
        }
    }

    private static MyBlockingQueue<String> blockingQueue = new MyBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        new Thread(takeTask).start();
        new Thread(putTask).start();
        while (true) {
            Thread.sleep(500L);
            System.out.println("-----------:" + blockingQueue.size());
        }
    }

    private static Runnable takeTask = () -> {
        while (true) {
            try {
//                Thread.sleep(new Random().nextInt(500));

                String take = blockingQueue.take();
                System.out.println("take:" + take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private static Runnable putTask = () -> {
        while (true) {
            try {
                Thread.sleep(new Random().nextInt(30));

                String uuid = UUID.randomUUID().toString();
                blockingQueue.put(uuid);
                System.out.println("put:" + uuid);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
