package com.chou.order_module.service.impl;

import com.chou.order_module.domain.entity.CommodityOrder;
import com.chou.order_module.mapper.CommodityOrderMapper;
import com.chou.order_module.po.CommodityOrderPo;
import com.chou.order_module.po.SearchCommodityOrderPo;
import com.chou.order_module.service.ICommodityOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chou.order_module.vo.CommodityOrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Chou
 * @since 2021-06-05
 */
@Service("iCommodityOrderService")
public class CommodityOrderServiceImpl extends ServiceImpl<CommodityOrderMapper, CommodityOrder> implements ICommodityOrderService {

        public IPage<CommodityOrderVO> getPage(SearchCommodityOrderPo po, Integer pageNo, Integer pageSize){
            return null;
        }

        public Boolean addCommodityOrder(CommodityOrderPo po){
            CommodityOrder commodityOrder = new CommodityOrder();
            BeanUtils.copyProperties(po,commodityOrder);
            return this.save(commodityOrder);
        }

        public CommodityOrderVO getOne(Long id){
            return null;
        }

        public Boolean updateCommodityOrder(CommodityOrderPo po){
            return null;
        }

        public Boolean delete(Long id){
            return null;
        }
}
