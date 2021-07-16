package com.chou.amqp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @ClassName UpdateDepositMsg
 * @Description TODO
 * @Author Axel
 * @Date 2021/6/28 23:56
 * @Version 1.0
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UpdateDepositMsg extends Message {
    /**
     * 用户id
     */
    private  Long id;
    /**
     * 用户名称
     */
    private Long usrName;

    /**
     * 价格
     */
    private  Integer deposit;

}
