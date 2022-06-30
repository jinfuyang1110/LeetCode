package com.example.hellodocker.controller;

import com.example.hellodocker.bean.User;
import com.example.hellodocker.handler.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author Eric
 * @date 2021/7/21
 * @description
 */
@RestController
@RequestMapping("/admin")
@RefreshScope
public class DockerController {
    @Value("${userName}")
    private String userName;
    @GetMapping("/docker")
    public String hello(){
        return userName;
    }
    @PostMapping("/user")
    public Result<?> user(@Validated @RequestBody  User user){
        return Result.success(user);
    }

}
