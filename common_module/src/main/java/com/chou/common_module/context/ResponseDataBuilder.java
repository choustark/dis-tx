package com.chou.common_module.context;

import com.chou.common_module.context.enums.ResCodeEnum;
import com.chou.common_module.exception.BizException;

/**
 * @ClassName ResponseDataBuilder
 * @Description 响应结果构造者
 * @Author Axel
 * @Date 2021/3/25 10:47
 * @Version 1.0
 */

public class ResponseDataBuilder {

    public static<T> ResponseData<T> buildSuccessData(T data){
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode("0");
        responseData.setData(data);
        responseData.setMsg("请求成功");
        return responseData;
    }

    public static<T> ResponseData<T> buildFailData(T data){
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setData(data);
        responseData.setCode("-1");
        responseData.setMsg("服务繁忙, 请稍后再试");
        return responseData;
    }

    public static <T extends BizException> ResponseData<T> buildException(T data){
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(data.getCode());
        responseData.setMsg(data.getMsg());
        responseData.setData(data);
        return responseData;
    }

    public static <T extends CodeContext> ResponseData<T> buildException(T CodeContext){
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(CodeContext.getCode());
        responseData.setMsg(CodeContext.getMsg());
        responseData.setData(CodeContext);
        return responseData;
    }
}
