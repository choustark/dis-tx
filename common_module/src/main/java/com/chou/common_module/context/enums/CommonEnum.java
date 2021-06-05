package com.chou.common_module.context.enums;

import com.chou.common_module.context.CodeContext;

/**
 * @ClassName CommonEnum
 * @Description TODO
 * @Author Axel
 * @Date 2021/5/16 0:15
 * @Version 1.0
 */

public enum CommonEnum implements CodeContext {
    // 数据操作错误定义
    SUCCESS("200", "成功!"),
    BODY_NOT_MATCH("400","请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙，请稍后再试!")
    ;

    private String code;
    private String msg;

    CommonEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
