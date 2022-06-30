package com.example.hellodocker.design.Factory.AbstractFactory;

/**
 * @author yjf
 * @date 2022/2/15
 * @description
 */
public  interface AbstractFactory {
    /** 制作产品
     * @return
     */
    Plant makePlant();

    /**制作产品
     * @return
     */
    Animal makeAnimal();
}
