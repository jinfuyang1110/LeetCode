package com.example.hellodocker.Jvm;

import javax.print.attribute.standard.PrinterMoreInfo;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * @author yjf
 * @date 2022/1/11
 * @description
 */
public class OomTest {
    public static void main(String[] args) {
        System.out.println("oom");
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        long init = heapMemoryUsage.getInit();
        long max = heapMemoryUsage.getMax();
        System.out.println(init/1024/1024);
        System.out.println(max/1024/1024);
        System.out.println(heapMemoryUsage);
        try {
            Thread.sleep(1000*60*60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
