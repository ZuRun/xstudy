package cn.zull.jvm.oom;

import lombok.Data;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 弱引用测试
 *
 * @author zurun
 * @date 2019-05-26 20:23:28
 */

@SuppressWarnings("Duplicates")
public class TestWeakReference {
    @Data
    public static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    /**
     * 弱引用在下次gc时，不管内存够不够，一定会回收（前提是 没有强引用同时与之关联）
     * -Xmx10M -XX:+PrintGC
     *
     * @param args
     */
    public static void main(String[] args) {
        test1();
        System.out.println("-------------");
        test2();
    }

    public static void test1() {
        User u = new User("mark", 11);
        WeakReference<User> userWeakReference = new WeakReference<>(u);
        // 此时u置为null，没有强引用与此对象关联
        u = null;

        System.out.println(userWeakReference.get());
        // 弱引用，gc一定会回收
        System.gc();
        System.out.println("AfterGC");
        System.out.println(userWeakReference.get());
    }

    /**
     * 虽然u被置为null，但是u2还与new出来的User对象，因此此时弱引用不会被回收
     */
    public static void test2() {
        User u = new User("mark", 11);
        User u2 = u;
        WeakReference<User> userWeakReference = new WeakReference<>(u);
        // 此时虽然u置为null，但u2还是与User对象有强引用关联
        u = null;
        System.out.println(userWeakReference.get());
        System.gc();
        System.out.println("AfterGC");
        System.out.println(userWeakReference.get());

        System.out.println("u:" + u);
        System.out.println("u2:" + u2);
    }
}
