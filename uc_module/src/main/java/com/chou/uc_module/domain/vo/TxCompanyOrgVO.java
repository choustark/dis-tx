package com.chou.uc_module.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Axel
 * @version 1.0
 * @className TxCompanyOrgVO
 * @description TODO
 * @date 2022/11/13 14:16
 */
@Data
@ApiModel("组织展示对象")
public class TxCompanyOrgVO {
    /**
     * 主键集合
     */
    @ApiModelProperty(value = "id",example = "32132131")
    private Long id;

    /**
     * 组织名称
     */
    @ApiModelProperty(value = "name",example = "组织名称")
    private String name;

    /**
     * 组织编码
     */
    @ApiModelProperty(value = "组织编码")
    private String orgCode;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer sortNumber;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private Long parentId;

    /**
     * 全路径
     */
    @ApiModelProperty(value = "全路径")
    private String fullPath;

    /**
     * 中文全路径
     */
    @ApiModelProperty(value = "中文全路径")
    private String cnFullPath;
}
