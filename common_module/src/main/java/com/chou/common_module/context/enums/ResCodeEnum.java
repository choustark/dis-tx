package com.chou.common_module.context.enums;

import com.chou.common_module.context.CodeContext;

/**
 * @ClassName ResCodeEnum
 * @Description TODO
 * @Author Axel
 * @Date 2021/5/16 0:21
 * @Version 1.0
 */

public enum  ResCodeEnum {
    REMOTE_CALL_EXCEPTION("100","远程调用异常")
    ;
    private String code;
    private String msg;

    ResCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return null;
    }

    public String getMsg() {
        return null;
    }
}
