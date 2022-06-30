package com.example.hellodocker.common;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * @author Eric
 * @date 2021/7/23
 * @description
 */
public class WritePerson {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = WritePerson.class.getClassLoader();
        Class<?> clazz = classLoader.loadClass("com.example.hellodocker.common.Person");
        System.out.println(clazz);
//        Person person = (Person) clazz.getConstructor().newInstance();
//        person.setName("eric");
//        person.setAge(12);
//        System.out.println(person);
        Method setName = clazz.getMethod("setName", String.class);
        Method setAge = clazz.getMethod("setAge", int.class);
        Object o = clazz.getConstructor().newInstance();
        setName.invoke(o,"eric");
        setAge.invoke(o,18);
        System.out.println(o);
        OutputStream os = new FileOutputStream("D://test.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
        objectOutputStream.writeObject(o);
        objectOutputStream.close();
    }
}
