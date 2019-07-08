package com.xstudy.test.thread.future.practice;

import java.util.concurrent.*;

/**
 * @author zurun
 * @date 2019/7/8 15:16:34
 */
public class MyFutureTask<V> implements Future<V>, Runnable {


    @Override
    public void run() {

    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
