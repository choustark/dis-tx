package com.chou.order_module.mq;

import com.chou.amqp.constant.ExchangeConstant;
import com.chou.amqp.constant.QueueConstant;
import com.chou.amqp.entity.UpdateDepositMsg;
import com.chou.common_module.utils.SnowflakeIdWorker;
import com.chou.order_module.po.CommodityOrderPo;
import com.chou.order_module.service.ICommodityOrderService;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import java.util.UUID;

/**
 * @ClassName MQOrderService
 * @Description
 * @Author Axel
 * @Date 2021/6/14 21:15
 * @Version 1.0
 */
@Service("mQOrderService")
public class MQOrderSendMsgService implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void updateUserDep(UpdateDepositMsg msg) {
        // 消息的唯一标识
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1,1);
        UpdateDepositMsg updateDepositMsg = new UpdateDepositMsg();
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(String.valueOf(idWorker.nextId()));
        rabbitTemplate.convertAndSend(ExchangeConstant.ORDER_UPDATE_DEPOSIT_EXCHANGE,
                QueueConstant.ORDER_UPDATE_DEPOSIT,updateDepositMsg,correlationData);
    }

    // 重写消息确认送达接口
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            // 将存的消息库中的消息状态改为已发送
        }
        // 如果推送失败 报错
    }
}
