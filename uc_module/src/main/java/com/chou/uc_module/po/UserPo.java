package com.chou.uc_module.po;

import lombok.Data;

/**
 * @ClassName UserPo
 * @Description user 持久层对象
 * @Author Axel
 * @Date 2021/5/16 19:29
 * @Version 1.0
 */

@Data
public class UserPo {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户余额
     */
    private Integer deposit;
}
