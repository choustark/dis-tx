package com.chou.order_module.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @ClassName MsgLogPO
 * @Description TODO
 * @Author Axel
 * @Date 2021/7/3 21:39
 * @Version 1.0
 */
@Data
@EqualsAndHashCode
@ToString
public class MsgLogPO {
    /**
     * id
     */
    private Long id;

    /**
     * 存储的对象id
     */
    private Long objectId;

    /**
     * 交换机名称
     */
    private String exchangeName;

    /**
     * 路由名称
     */
    private String routingKeyName;

    /**
     * 状态消息(0-未确认,1-已确认,-1-发送消息异常)
     */
    private Integer state;

    /**
     * 消息主要内容 json 格式
     */
    private String body;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 下一次执行时间
     */
    private Date nextTime;
}
