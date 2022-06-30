package com.example.hellodocker.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eric
 * @date 2021/7/26
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMsg {
    private String Field;
    private String ObjectName;
    private String Message;
}
