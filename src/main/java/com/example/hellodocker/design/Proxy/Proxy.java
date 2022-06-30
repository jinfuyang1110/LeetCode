package com.example.hellodocker.design.Proxy;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yjf
 * @date 2022/2/17
 * @description
 */
public class Proxy implements Subject{

    private RealSubject realSubject;

    @Override
    public void say() {
        if (null==realSubject){
            realSubject=new RealSubject();
        }
        before();
        realSubject.say();
        after();
    }
    void before(){
        System.out.println("------began-----");
    }
    void after(){
        System.out.println("------end-----");
    }
}
