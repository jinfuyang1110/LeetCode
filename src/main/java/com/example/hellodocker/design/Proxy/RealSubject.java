package com.example.hellodocker.design.Proxy;



/**
 * @author yjf
 * @date 2022/2/17
 * @description
 */
public class RealSubject implements Subject{
    @Override
    public void say() {
        System.out.println("we are family");
    }
}
