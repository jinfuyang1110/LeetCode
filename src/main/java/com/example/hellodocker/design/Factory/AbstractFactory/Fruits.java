package com.example.hellodocker.design.Factory.AbstractFactory;

/**
 * @author yjf
 * @date 2022/2/15
 * @description
 */
public class Fruits implements Plant {
    public  Fruits() {
        System.out.println("这是水果");
    }

    @Override
    public void price() {
        System.out.println("10元一斤");
    }
}
