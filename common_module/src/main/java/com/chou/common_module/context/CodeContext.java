package com.chou.common_module.context;

/**
 * @ClassName CodeContext
 * @Description TODO
 * @Author Axel
 * @Date 2021/5/16 0:00
 * @Version 1.0
 */
public interface CodeContext {
    /**
     * 错误码
     */
    String getCode();
    /**
     * 错误描述
     */
    String getMsg();
}
