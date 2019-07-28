package com.xstudy.test.thread.waitandnotify;

/**
 * @author zurun
 * @date 2019/7/28 16:41:36
 */
public interface Queue<V> {
    V teke() throws InterruptedException;

    void put(V v) throws InterruptedException;
}
