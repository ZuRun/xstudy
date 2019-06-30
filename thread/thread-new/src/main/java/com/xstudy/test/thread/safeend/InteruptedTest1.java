package com.xstudy.test.thread.safeend;

import java.util.Scanner;

/**
 * @author zurun
 * @date 2019/6/27 20:19:21
 */
public class InteruptedTest1 {

    public static void main(String[] args) {
        t();
//        Scanner scanner = new Scanner(System.in);
//        String next = scanner.next();
//        if ("stop".equals(next)) {
//
//        }
//        Thread.currentThread().isInterrupted();
    }

    public static void t() {
        Thread thread = new Thread(() -> {
            Thread subThread = Thread.currentThread();
            while (!subThread.isInterrupted()) {
                System.out.println("请输入文本:");
                Scanner scanner = new Scanner(System.in);
                String next = scanner.next();
                if ("stop".equals(next)) {
                    subThread.interrupt();
                }
                System.out.println(subThread.getName() + " " + next + " isInterrupted:" + subThread.isInterrupted());
            }
        }, "s");
        thread.start();
    }
}
