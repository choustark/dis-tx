package com.chou.order_module.mapper;

import com.chou.order_module.domain.entity.CommodityOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Chou
 * @since 2021-06-05
 */
@Mapper
public interface CommodityOrderMapper extends BaseMapper<CommodityOrder> {

}
