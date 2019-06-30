package com.xstudy.test.interview.question.design.patterns.singleton;

/**
 * @author zurun
 * @date 2019/6/30 11:36:45
 */
public enum SingletonEnum {
    /**
     * 单例
     */
    INSTANCE;

    public void test() {

    }


    public static void main(String[] args) {
        SingletonEnum.INSTANCE.test();
    }
}
