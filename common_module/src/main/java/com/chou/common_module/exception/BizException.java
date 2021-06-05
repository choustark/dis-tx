package com.chou.common_module.exception;

import com.chou.common_module.context.enums.ResCodeEnum;

/**
 * @ClassName BizException
 * @Description 业务自定义异常处理类
 * @Author Axel
 * @Date 2021/5/16 0:20
 * @Version 1.0
 */

public class BizException extends RuntimeException{
    /** serialVersionUID */
    private static final long serialVersionUID = -1136824916931940285L;

    /**
     * 响应码枚举类
     */
    private ResCodeEnum resCodeEnum;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String msg;

    public Throwable throwable;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ResCodeEnum getResCodeEnum() {
        return resCodeEnum;
    }

    public void setResCodeEnum(ResCodeEnum resCodeEnum) {
        this.resCodeEnum = resCodeEnum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



    public BizException(ResCodeEnum resCodeEnum, String code, String msg) {
        super();
        this.resCodeEnum = resCodeEnum;
        this.code = code;
        this.msg = msg;
    }

    public BizException(ResCodeEnum resCodeEnum){
        super(resCodeEnum.getCode());
        this.resCodeEnum = resCodeEnum;
    }

    public BizException(String code,String msg){
        super(code);
        this.code = code;
        this.msg = msg;
    }

    public BizException(String code,Throwable throwable){
        super(code,throwable);
        this.code = code;
        this.throwable = throwable;
    }


}
