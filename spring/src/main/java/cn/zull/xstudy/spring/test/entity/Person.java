package cn.zull.xstudy.spring.test.entity;

import lombok.Data;

/**
 * @author zurun
 * @date 2020/1/6 20:25:22
 */
@Data
public class Person {
    private String name;
    private int age;



    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

}
