package com.chou.order_module.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.Serializable;

/**
 * @ClassName CommodityOrderPo
 * @Description TODO
 * @Author Axel
 * @Date 2021/6/5 20:05
 * @Version 1.0
 */
@Data
@EqualsAndHashCode
@ToString
public class CommodityOrderPo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
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
