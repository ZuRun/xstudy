package com.xstudy.test.thread.aqs.test;

import com.xstudy.test.thread.aqs.MyReentrantSelfLock;

import java.util.concurrent.locks.Lock;

/**
 * @author zurun
 * @date 2019/7/9 20:29:47
 */
public class ConditionTest {
    public static void main(String[] args) {
        final Lock nonFairlock = new MyReentrantSelfLock();
        nonFairlock.newCondition();
     }
}
