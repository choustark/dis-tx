package com.chou.common_module.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ResponseData
 * @Description api响应结果对象
 * @Author Axel
 * @Date 2021/3/25 10:25
 * @Version 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
