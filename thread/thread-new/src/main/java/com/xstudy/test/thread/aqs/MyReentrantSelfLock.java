package com.xstudy.test.thread.aqs;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class MyReentrantSelfLock implements Lock {
    private final Sync sync;

    abstract static class Sync extends AbstractQueuedSynchronizer {

        public abstract void lock();

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
            final Thread currentThread = Thread.currentThread();
            if (state == 0) {
                // 锁未被持有,如果只调用lock方法,那么此条件不应该被执行(独占锁)
                if (compareAndSetState(0, arg)) {
                    setExclusiveOwnerThread(currentThread);
                    return true;
                }
                System.out.println("[cas失败] " + currentThread.getName());
            } else {
                // 此锁已经被持有过
                if (currentThread == getExclusiveOwnerThread()) {
                    int nextState = state + arg;
                    if (nextState < 0) {
                        throw new Error("Maximum lock count exceeded");
                    }
                    setState(nextState);
                    return true;
                }
                System.out.println("[其他线程抢夺锁] " + currentThread.getName());
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            final Thread currentThread = Thread.currentThread();
            int state = getState();
            if (getExclusiveOwnerThread() != currentThread) {
                throw new IllegalMonitorStateException();
            }
            int nextState = state - arg;
            if (nextState == 0) {
                setExclusiveOwnerThread(null);
                setState(nextState);
                return true;
            }
            setState(nextState);
            return false;
        }
    }

    final static class NonFairSync extends Sync {
        /**
         * 阻塞获取锁
         */
        @Override
        public void lock() {
            // 假设第一次获取锁
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
            } else {
                // 当锁已被持有时
                acquire(1);
            }
        }
    }

    final static class FairSync extends Sync {

        @Override
        public void lock() {

        }


    }

    public MyReentrantSelfLock() {
        sync = new NonFairSync();
    }

    public MyReentrantSelfLock(boolean fair) {
        sync = fair ? new FairSync() : new NonFairSync();
    }

    @Override
    public void lock() {
        sync.lock();
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
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }


}
