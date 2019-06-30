package com.xstudy.test.interview.question.design.patterns.singleton;

/**
 * @author zurun
 * @date 2019/6/30 11:31:57
 */
public class Singleton1 {
    private static Singleton1 ourInstance = new Singleton1();

    public static Singleton1 getInstance() {
        return ourInstance;
    }

    private Singleton1() {
    }
}
