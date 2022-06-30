package com.example.hellodocker.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * power_user
 * @author 
 */
@Data
public class PowerUser implements Serializable {
    private String userId;

    private String userName;

    private String userPhoneNumber;

    private static final long serialVersionUID = 1L;
}