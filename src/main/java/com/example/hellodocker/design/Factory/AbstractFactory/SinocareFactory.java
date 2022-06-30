package com.example.hellodocker.design.Factory.AbstractFactory;

/**
 * @author yjf
 * @date 2022/2/15
 * @description
 */
public class SinocareFactory implements AbstractFactory{
    @Override
    public Plant makePlant() {
        System.out.println("开始生成植物类");
        return new Fruits();
    }

    @Override
    public Animal makeAnimal() {
        System.out.println("开始生成动物类");
        return new Bird();
    }
}
