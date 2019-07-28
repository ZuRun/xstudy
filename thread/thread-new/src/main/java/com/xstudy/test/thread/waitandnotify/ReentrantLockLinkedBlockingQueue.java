package com.xstudy.test.thread.waitandnotify;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zurun
 * @date 2019/7/28 19:44:47
 */
public class ReentrantLockLinkedBlockingQueue<E> implements Queue<E> {
    final LinkedList<E> linkedList;
    private final int limit;
    /**
     * Main lock guarding all access
     */
    final ReentrantLock lock;

    /**
     * Condition for waiting takes
     */
    private final Condition notEmpty;

    /**
     * Condition for waiting puts
     */
    private final Condition notFull;

    public ReentrantLockLinkedBlockingQueue(int limit) {
        this.limit = limit;
        this.linkedList = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    @Override
    public E teke() throws InterruptedException {
        lock.lock();
        try {
            while (linkedList.getFirst() == null) {
                notEmpty.await();
            }
            E pop = linkedList.pop();
            notEmpty.signal();
            notFull.signal();
            return pop;
        } finally {
            lock.lock();
        }

    }

    @Override
    public void put(E e) throws InterruptedException {
        lock.lock();
        try {
            while (linkedList.size() > limit) {
                notFull.await();
            }
            linkedList.add(e);
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}
