package cn.zull.jvm.oom;

import lombok.Data;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 软引用测试
 *
 * @author zurun
 * @date 2019-05-26 20:23:28
 */

public class TestSoftReference {
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
     * 限制堆内存为10M，list集合每次增加1M byte，oom时，会进行回收软引用（前提是 没有强引用同时与之关联）
     * -Xmx10M -XX:+PrintGC
     *
     * @param args
     */
    public static void main(String[] args) {
        User u = new User("mark", 11);
        SoftReference<User> userSoftReference = new SoftReference<>(u);
        // 此时u置为null，没有强引用与此对象关联
        u = null;

        System.out.println(userSoftReference.get());
        // 软引用，gc不一定触发垃圾回收
        System.gc();
        System.out.println("AfterGC");
        System.out.println(userSoftReference.get());
        List<byte[]> list = new ArrayList<>();
        try {
            for (int i = 0; i < 15; i++) {
                System.out.println(userSoftReference.get());
                list.add(new byte[1024 * 1024 * 1]);
            }
        } catch (Throwable throwable) {
            // 在OOM之前，会触发软引用垃圾回收
            throwable.printStackTrace();
            System.out.println("throwable:" + userSoftReference.get());
        }


    }
}
