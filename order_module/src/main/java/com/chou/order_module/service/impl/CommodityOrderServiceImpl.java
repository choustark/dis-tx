package com.chou.order_module.service.impl;

import com.alibaba.fastjson.JSON;
import com.chou.amqp.constant.ExchangeConstant;
import com.chou.amqp.constant.QueueConstant;
import com.chou.amqp.entity.UpdateDepositMsg;
import com.chou.order_module.domain.entity.CommodityOrder;
import com.chou.order_module.domain.entity.MqMsgLog;
import com.chou.order_module.inf.enums.MsgStateConstant;
import com.chou.order_module.mapper.CommodityOrderMapper;
import com.chou.order_module.mq.MQOrderSendMsgService;
import com.chou.order_module.po.CommodityOrderPo;
import com.chou.order_module.po.SearchCommodityOrderPo;
import com.chou.order_module.service.ICommodityOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chou.order_module.service.MqMsgLogService;
import com.chou.order_module.vo.CommodityOrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private MQOrderSendMsgService mqOrderService;

    @Autowired
    private MqMsgLogService mqMsgLogService;

    public IPage<CommodityOrderVO> getPage(SearchCommodityOrderPo po, Integer pageNo, Integer pageSize) {
        return null;
    }

    public Boolean addCommodityOrder(CommodityOrderPo po) {
        CommodityOrder commodityOrder = new CommodityOrder();
        BeanUtils.copyProperties(po, commodityOrder);
        return this.save(commodityOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addDisCommodityOrder(CommodityOrderPo po) {
        // 保存订单信息
        CommodityOrder commodityOrder = new CommodityOrder();
        BeanUtils.copyProperties(po, commodityOrder);
        boolean save = this.save(commodityOrder);
        if (save) {
            // 采用消息通知的方式更新解决用户余额问题，解决分布式事务的带来的问题
            UpdateDepositMsg msg = new UpdateDepositMsg();
            msg.setObjectId(commodityOrder.getId());
            msg.setUserId(po.getUserId());
            msg.setUserName(po.getUserId());
            msg.setDeposit(po.getAmount());
            mqOrderService.updateUserDep(msg);
            // 插入消息记录表
            MqMsgLog entity = new MqMsgLog();
            entity.setState(MsgStateConstant.UN_CONFIRM.getState());
            entity.setBody(JSON.toJSONString(msg));
            entity.setObjectId(commodityOrder.getId());
            entity.setExchangeName(ExchangeConstant.ORDER_UPDATE_DEPOSIT_EXCHANGE);
            entity.setRoutingKeyName(QueueConstant.ORDER_UPDATE_DEPOSIT);
            entity.setNextTime(null);
            mqMsgLogService.addMsgLog(entity);
        }
        return save;
    }

    public CommodityOrderVO getOne(Long id) {
        return null;
    }

    public Boolean updateCommodityOrder(CommodityOrderPo po) {
        return null;
    }

    public Boolean delete(Long id) {
        return null;
    }
}
