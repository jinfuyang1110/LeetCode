package com.example.hellodocker.design.Bridge;

/**
 * @author yjf
 * @date 2022/2/17
 * @description
 */
public class BridgeTest {
    public static void main(String[] args) {
        Bag girl = new GirlBag();
        Bag boy = new BoyBag();
        Color redGirl = new Red(girl);
        Color redBoy = new Red(boy);
        Color blueGirl = new Blue(girl);
        Color blueBoy = new Blue(boy);
        redGirl.color();
        System.out.println("-------------");
        redBoy.color();
        System.out.println("--------------");
        blueBoy.color();
        System.out.println("--------------");
        blueGirl.color();
    }
}
