package com.example.hellodocker.common;

import java.io.Serializable;

/**
 * @author Eric
 * @date 2021/7/23
 * @description
 */
public class Person implements Serializable {
    private static final long serialVersionUID = -9898998L;
    private String name;
    private int age;
    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
