package com.chou.order_module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.chou.uc_module")
@EnableDiscoveryClient
public class OrderModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderModuleApplication.class, args);
    }
}
