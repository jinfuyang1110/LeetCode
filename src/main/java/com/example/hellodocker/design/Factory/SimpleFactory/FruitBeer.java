package com.example.hellodocker.design.Factory.SimpleFactory;

/**
 * @author yjf
 * @date 2022/2/14
 * @description
 */
public class FruitBeer implements Beer{

    @Override
    public void price() {
        System.out.println("100元一瓶");
    }
}
