package com.chou.order_module.service.impl;

import com.chou.order_module.po.CommodityOrderPo;
import com.chou.order_module.service.ICommodityOrderService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName MQOrderService
 * @Description TODO
 * @Author Axel
 * @Date 2021/6/14 21:15
 * @Version 1.0
 */
@Service("mQOrderService")
public class MQOrderService implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private ICommodityOrderService commodityOrderService;

    public void addCommodityOrder(CommodityOrderPo po) {

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

    }
}
