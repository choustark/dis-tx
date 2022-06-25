package com.chou.order_module.inf.enums;

import lombok.Getter;

/**
 * @author Axel
 * @version 1.0
 * @className MsgStateConstant
 * @description 消息状态常量 状态消息(0-未确认,1-已确认,-1-发送消息异常)
 * @date 2022/4/4 18:22
 */

@Getter
public enum MsgStateConstant {
    UN_CONFIRM(0,"未确认"),
    CONFIRM(1,"已确认"),
    ACK_FAILED(-1,"发送消息异常");

    public final  Integer state;
    public final String describe;

    MsgStateConstant(Integer state,String describe){
        this.state = state;
        this.describe = describe;

    }

}
