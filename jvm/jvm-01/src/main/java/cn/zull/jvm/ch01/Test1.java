package cn.zull.jvm.ch01;

import lombok.Data;

/**
 * @author zurun
 * @date 2019-05-25 20:39:52
 */
@Data
public class Test1 {

    private String name;
    private Integer age;
    private Integer aa;
    private Integer bb;

    public Test1 test() {
        return this;
    }

    public static void main(String[] args) {
        for (int i = 0, length = 1000000000; i < length; i++) {
            Test1 t = tt();
            t.setBb(11231);
        }
    }

    public static Test1 tt() {
        Test1 t = new Test1();
        t.setAge(111);
        t.setName("张三");
        t.test();
        return t;
    }
}
