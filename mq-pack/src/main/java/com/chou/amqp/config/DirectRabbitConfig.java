package com.chou.amqp.config;

import com.chou.amqp.constant.ExchangeConstant;
import com.chou.amqp.constant.QueueConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitDirectConfig
 * @Description TODO
 * @Author Axel
 * @Date 2021/6/13 22:11
 * @Version 1.0
 */

@Configuration
public class DirectRabbitConfig {

        @Bean
        public Queue updateDepositQueue(){
            return new Queue(QueueConstant.ORDER_UPDATE_DEPOSIT,true,false,false);
        }

        @Bean
        public Exchange updateDepositExchange(){
            return new DirectExchange(ExchangeConstant.ORDER_UPDATE_DEPOSIT_EXCHANGE,true,false);
        }

        @Bean
        public Binding updateDepositBinding(){
            return BindingBuilder.bind(updateDepositQueue()).to(updateDepositExchange()).with(QueueConstant.ORDER_UPDATE_DEPOSIT).noargs();
        }
}
