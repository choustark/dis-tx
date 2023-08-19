package com.chou.uc_module.domain.entity;

import com.chou.common_module.BaseEntity;
import lombok.Data;

/**
 * @author Axel
 * @version 1.0
 * @className TxCompanyOrg
 * @description TODO
 * @date 2022/11/13 12:56
 */

@Data
public class TxCompanyOrg extends BaseEntity {
    /**
     * 主键集合
     */
    private Long id;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 组织编码
     */
    private String orgCode;

    /**
     * 序号
     */
    private Integer sortNumber;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 全路径
     */
    private String fullPath;

    /**
     * 中文全路径
     */
    private String cnFullPath;

}












