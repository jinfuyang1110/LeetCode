package com.example.hellodocker.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Eric
 * @date 2021/7/26
 * @description
 */
@MapperScan("com.example.hellodocker.mapper")
@Configuration
public class MyConfig {
}
