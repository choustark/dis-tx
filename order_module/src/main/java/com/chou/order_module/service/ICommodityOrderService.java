package com.chou.order_module.service;

import com.chou.order_module.domain.entity.CommodityOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chou.order_module.po.CommodityOrderPo;
import com.chou.order_module.po.SearchCommodityOrderPo;
import com.chou.order_module.vo.CommodityOrderVO;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Chou
 * @since 2021-06-05
 */
public interface ICommodityOrderService extends IService<CommodityOrder> {

        IPage<CommodityOrderVO> getPage(SearchCommodityOrderPo po, Integer pageNo, Integer pageSize);

        Boolean addCommodityOrder(CommodityOrderPo po);

        CommodityOrderVO getOne(Long id);

        Boolean updateCommodityOrder(CommodityOrderPo po);

        Boolean delete(Long id);

        Boolean  addDisCommodityOrder(CommodityOrderPo po);
}
