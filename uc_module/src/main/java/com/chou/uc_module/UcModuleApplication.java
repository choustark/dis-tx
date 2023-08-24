package com.chou.uc_module;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.chou.common_module.config")
@EnableDiscoveryClient
@MapperScan("com.chou") public class UcModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcModuleApplication.class, args);
    }

}
