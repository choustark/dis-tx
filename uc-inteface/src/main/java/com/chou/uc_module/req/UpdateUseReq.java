package com.chou.uc_module.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @ClassName UpdateUseReq
 * @Description TODO
 * @Author Axel
 * @Date 2021/5/16 10:44
 * @Version 1.0
 */

@Data
@EqualsAndHashCode
@ToString
public class UpdateUseReq {
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
