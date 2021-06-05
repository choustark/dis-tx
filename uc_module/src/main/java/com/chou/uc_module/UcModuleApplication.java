package com.chou.uc_module;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.chou")
public class UcModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcModuleApplication.class, args);
    }

}
