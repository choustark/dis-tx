package com.chou.common_module.context;

/**
 * @ClassName ResponseData
 * @Description api响应结果对象
 * @Author Axel
 * @Date 2021/3/25 10:25
 * @Version 1.0
 */

public class ResponseData<T> {
    /**
     * 响应码code默认正常
     */
    private String code = "0";
    /**
     * 响应信息
     */
    private String msg ="";
    /**
     * 详情是否成功 默认true 成功
     */
    private Boolean success = true;
    /**
     * 响应数据
     */
    private T Data;

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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
