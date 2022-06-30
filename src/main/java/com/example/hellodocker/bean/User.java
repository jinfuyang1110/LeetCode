package com.example.hellodocker.bean;


import lombok.Data;

import javax.validation.constraints.NotBlank;



/**
 * @author Eric
 * @date 2021/7/23
 * @description
 */
@Data
public class User {
    @NotBlank(message = "name is blank")
    private String name;
    private int age;
}

