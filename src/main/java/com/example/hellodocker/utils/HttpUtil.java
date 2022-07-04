package com.example.hellodocker.utils;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @author yjf
 * @date 2022/6/30
 * @description
 */
@Component
public class HttpUtil {
    public JSONObject directPost(String url, String reqJson, String cookie){
        HttpRequest request = HttpRequest.post(url)
                .body(reqJson)
                .cookie(cookie);
        return JSON.parseObject(request.execute().body());
    }
}
