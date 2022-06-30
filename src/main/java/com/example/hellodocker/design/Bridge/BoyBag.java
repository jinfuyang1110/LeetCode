package com.example.hellodocker.design.Bridge;

/**
 * @author yjf
 * @date 2022/2/17
 * @description
 */
public class BoyBag implements Bag{
    @Override
    public void name() {
        System.out.println("男士包");
    }
}
