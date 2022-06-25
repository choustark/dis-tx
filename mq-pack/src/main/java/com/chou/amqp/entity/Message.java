package com.chou.amqp.entity;

import lombok.Data;

/**
 * @ClassName Message
 * @Description 消息体基类
 * @Author Axel
 * @Date 2021/6/28 23:55
 * @Version 1.0
 */
@Data
public abstract class Message {
    private Long objectId;
}
