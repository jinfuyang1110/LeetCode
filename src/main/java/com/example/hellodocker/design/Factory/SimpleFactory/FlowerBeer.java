package com.example.hellodocker.design.Factory.SimpleFactory;

/**
 * @author yjf
 * @date 2022/2/14
 * @description
 */
public class FlowerBeer implements Beer {


    @Override
    public void price() {
        System.out.println("20元一瓶");
    }
}
