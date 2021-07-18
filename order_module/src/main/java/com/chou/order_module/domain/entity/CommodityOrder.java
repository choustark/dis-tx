package com.chou.order_module.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Chou
 * @since 2021-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommodityOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id" , type = IdType.ID_WORKER)
    private Long id;

    /**
     * 客户id
     */
    private Long userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 金额
     */
    private Integer amount;

}
