package com.example.hellodocker.design.Bridge;

/**
 * @author yjf
 * @date 2022/2/17
 * @description
 */
public class Blue extends Color{
    public Blue(Bag bag) {
        super(bag);
    }

    @Override
    void color() {
        System.out.println("蓝色的");
        bag.name();
    }
}
