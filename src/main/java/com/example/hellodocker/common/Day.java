package com.example.hellodocker.common;

/**
 * @author Eric
 * @date 2021/7/22
 * @description
 */
public enum Day {

    /**
     * 周一
     */
    MONDAY("星期一"),
    /**
     * 星期二
     */
    TUESDAY("星期二")
    ;
   private String msg;

    Day(String msg) {
        this.msg = msg;
    }
    public String getMsg(){
        return msg;
    }

    public static void main(String[] args) {
        for(Day day: Day.values()){
            System.out.println(day.getMsg());
        }
    }
}
