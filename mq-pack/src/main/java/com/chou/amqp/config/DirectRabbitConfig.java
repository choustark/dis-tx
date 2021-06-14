package com.chou.amqp.config;

import org.springframework.amqp.core.*;
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

        public Queue updateDepositQueue(){
            return new Queue("order.update.deposit",true,false,false);
        }

        public Exchange updateDepositExchange(){
            return new DirectExchange("order.update.deposit.exchange",true,false);
        }

        public Binding updateDepositBinding(){
            return BindingBuilder.bind(updateDepositQueue()).to(updateDepositExchange()).with("order.update.deposit").noargs();
        }
}
