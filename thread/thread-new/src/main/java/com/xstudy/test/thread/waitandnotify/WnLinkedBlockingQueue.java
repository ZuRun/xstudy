package com.xstudy.test.thread.waitandnotify;

import java.util.LinkedList;

/**
 * @author zurun
 * @date 2019/7/28 16:41:23
 */
public class WnLinkedBlockingQueue<E> implements Queue<E> {

    final LinkedList<E> linkedList = new LinkedList<>();
    private final int limit;

    public WnLinkedBlockingQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public synchronized E teke() throws InterruptedException {
        while (linkedList.getFirst() == null) {
            wait();
        }
        E pop = linkedList.pop();
        notifyAll();
        return pop;
    }

    @Override
    public synchronized void put(E e) throws InterruptedException {
        while (linkedList.size() > limit) {
            wait();
        }
        linkedList.add(e);
        notifyAll();
    }


}
