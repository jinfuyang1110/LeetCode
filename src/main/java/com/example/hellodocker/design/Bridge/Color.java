package com.example.hellodocker.design.Bridge;

/**
 * @author yjf
 * @date 2022/2/17
 * @description
 */
public abstract class Color {
    protected Bag bag;

    public Color(Bag bag) {
        this.bag = bag;
    }

    abstract void color();
}
