package com.example.hellodocker.design.Factory.AbstractFactory;

/**
 * @author yjf
 * @date 2022/2/15
 * @description
 */
public class Bird implements Animal{
    public Bird(){
        System.out.println("这是鸟");
    }
    @Override
    public void price() {
        System.out.println("白送");
    }
}
