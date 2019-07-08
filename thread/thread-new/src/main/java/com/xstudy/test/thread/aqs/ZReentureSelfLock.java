package com.xstudy.test.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 可重入锁
 *
 * @author zurun
 * @date 2019/7/8 20:40:35
 */
public class ZReentureSelfLock implements Lock {
    private final Sync sync = new Sync();

    static final class Sync extends AbstractQueuedSynchronizer {

        /**
         * 锁是否被当前线程锁占有
         *
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        /**
         * 可重入锁,每次获取锁成功后,state+1
         *
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
            int state = getState();
            if (state == 0) {
                // 第一次获取锁

            }

            return super.tryAcquire(arg);
        }

        @Override
        protected boolean tryRelease(int arg) {
            return super.tryRelease(arg);
        }
    }

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
