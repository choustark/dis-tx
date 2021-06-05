package com.chou.common_module.exception;

/**
 * @ClassName RemotionCallException
 * @Description TODO
 * @Author Axel
 * @Date 2021/5/29 19:50
 * @Version 1.0
 */

public class RemoteCallException extends BizException{

    public RemoteCallException(String code, String msg) {
        super(code, msg);
    }
}
