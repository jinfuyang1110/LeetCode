package com.example.hellodocker.design.Factory.AbstractFactory;

/**
 * @author yjf
 * @date 2022/2/15
 * @description
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        SinocareFactory sinocareFactory = new SinocareFactory();
        Animal animal = sinocareFactory.makeAnimal();
        animal.price();
        Plant plant = sinocareFactory.makePlant();
        plant.price();
    }
}
