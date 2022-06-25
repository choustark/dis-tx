package com.chou.mqconfig;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitTemplateConfig
 * @Description TODO
 * @Author Axel
 * @Date 2021/6/13 22:17
 * @Version 1.0
 */
@Configuration
public class RabbitTemplateConfig {
    @Bean
    public RabbitTemplate createRabbitTemplate(CachingConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 消息推送确认 配置文件yml 配置了可以不用
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setEncoding("utf-8");
        return rabbitTemplate;
    }
}
