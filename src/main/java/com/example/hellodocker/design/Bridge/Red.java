package com.example.hellodocker.design.Bridge;

/**
 * @author yjf
 * @date 2022/2/17
 * @description
 */
public class Red extends Color {
    public Red(Bag bag) {
        super(bag);
    }

    @Override
    public void color() {
        System.out.println("红色的");
        bag.name();
    }
}
