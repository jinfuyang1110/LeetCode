package com.example.hellodocker.common;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author Eric
 * @date 2021/7/23
 * @description
 */
public class ReadPerson {
    public static void main(String[] args) {
        try {
            FileInputStream is = new FileInputStream("D://test.txt");
            ObjectInputStream inputStream = new ObjectInputStream(is);
            Object o = inputStream.readObject();
            inputStream.close();
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
