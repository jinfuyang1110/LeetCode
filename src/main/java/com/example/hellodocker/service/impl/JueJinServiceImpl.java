package com.example.hellodocker.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.hellodocker.service.JueJinService;
import com.example.hellodocker.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author yjf
 * @date 2022/6/30
 * @description
 */
@Service
@RefreshScope
@Slf4j
public class JueJinServiceImpl implements JueJinService {
    @Value("${jueJin.check_in}")
    private String checkIn;

    @Value("${jueJin.draw}")
    private String draw;

    @Value("${jueJin.dip_luck}")
    private String dipLuck;

    @Value("${jueJin.cookie}")
    private String cookie;

    @Resource
    private HttpUtil httpUtil;

    @Override
    public String check() {
        try {
            JSONObject checkIn = httpUtil.directPost(this.checkIn, null, cookie);
            Thread.sleep(1000*3);
            JSONObject draw = httpUtil.directPost(this.draw, null, cookie);
            Thread.sleep(1000*3);
            JSONObject dipLuck = httpUtil.directPost(this.dipLuck, null, cookie);
            Thread.sleep(1000*3);
            return "签到:\t" + checkIn.toString() + "\n"
                    + "抽奖:\t" + draw.toString() + "\n"
                    + "沾福气:\t" + dipLuck.toString();
        }catch (Exception e){
            log.error(".......cookie失效");
        }
        return "12";
    }
}
