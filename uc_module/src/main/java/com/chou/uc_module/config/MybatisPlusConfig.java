package com.chou.uc_module.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisPlusConfig
 * @Description mybatisPlus 自动分页插件
 * @Author Axel
 * @Date 2021/3/28 11:43
 * @Version 1.0
 */

@Configuration
@MapperScan("com.chou.order_module.mapper")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor interceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return  paginationInterceptor;
    }
}
