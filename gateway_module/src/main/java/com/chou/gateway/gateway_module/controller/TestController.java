package com.chou.gateway.gateway_module.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author Axel
 * @Date 2021/5/15 17:42
 * @Version 1.0
 */

@RestController
@RequestMapping("/gateway")
@RefreshScope
public class TestController {

    @Value("${gateway.test.info}")
    private String value;
    @GetMapping("/get/info")
    public String getServiceInfo(){
        return value;
    }
}
