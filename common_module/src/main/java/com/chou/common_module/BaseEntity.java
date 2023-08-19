package com.chou.common_module;

import lombok.Data;

import java.util.Date;

/**
 * @author Axel
 * @version 1.0
 * @className BaseEntity
 * @description TODO
 * @date 2022/11/13 13:02
 */

@Data
public class BaseEntity {

    /**
     * 是否删除标识
     */
    private Integer isDel;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 创建人
     */
    private Long  createBy;

    /**
     * 创建时间
     */
    private Date  createDate;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private Date updateDate;

    public void initDefault(){
        this.setCreateBy(1L);
        this.setUpdateBy(1L);
        this.setCompanyId(1L);
        this.setIsDel(0);
        this.setCreateDate(new Date());
        this.setUpdateDate(new Date());
    }
}
