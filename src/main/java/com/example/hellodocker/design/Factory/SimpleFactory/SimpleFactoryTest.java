package com.example.hellodocker.design.Factory.SimpleFactory;

/**
 * @author yjf
 * @date 2022/2/14
 * @description
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        Beer beer = BeerFactory.makeBeer(1);
        beer.price();
        Beer beer1 = BeerFactory.makeBeer(2);
        beer1.price();
    }
}
