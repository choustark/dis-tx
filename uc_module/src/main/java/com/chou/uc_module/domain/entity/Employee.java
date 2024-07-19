package com.chou.uc_module.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 员工表
 * </p>
 *
 * @author Chou
 * @since 2023-10-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableField("主键")
    private Long id;

    /**
     * 用户id
     */
    @TableField("用户id")
    private Long userId;

    /**
     * 名称
     */
    @TableField("名称")
    private String name;

    /**
     * 员工编号
     */
    @TableField("员工编号")
    private String empNo;

    /**
     * 所属组织id
     */
    @TableField("所属组织id")
    private Long orgId;

    /**
     * 性别
     */
    @TableField("性别")
    private Integer gender;

    /**
     * 邮箱
     */
    @TableField("邮箱")
    private String email;

    /**
     * 身份证
     */
    @TableField("身份证")
    private String identityCard;

    /**
     * 公司id
     */
    @TableField("公司id")
    private Long companyId;

    /**
     * 创建人
     */
    @TableField("创建人")
    private Long createdBy;

    /**
     * 创建时间
     */
    @TableField("创建时间")
    private Date createdDate;

    /**
     * 更新人
     */
    @TableField("更新人")
    private Long updatedBy;

    /**
     * 更新时间
     */
    @TableField("更新时间")
    private Long updatedDate;

    /**
     * 是否删除标识 1：是，0否
     */
    @TableField("是否删除标识 1：是，0否")
    private Integer isDel;


}
