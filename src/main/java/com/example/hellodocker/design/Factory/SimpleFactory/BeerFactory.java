package com.example.hellodocker.design.Factory.SimpleFactory;

/**
 * @author yjf
 * @date 2022/2/14
 * @description
 */
public class BeerFactory {
  public static   Beer makeBeer(int a){
        switch (a){
            case 1:{
                System.out.println("开始生成花蜜");
                return new FlowerBeer();
            }
            case 2:{
                System.out.println("开始生成果蜜");
                return new FruitBeer();
            }
            default:return null;
        }
    }
}
