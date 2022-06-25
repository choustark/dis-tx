package com.chou.order_module.mq;

import com.chou.amqp.constant.ExchangeConstant;
import com.chou.amqp.constant.QueueConstant;
import com.chou.amqp.entity.UpdateDepositMsg;
import com.chou.order_module.domain.entity.MqMsgLog;
import com.chou.order_module.inf.enums.MsgStateConstant;
import com.chou.order_module.service.MqMsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName MQOrderService
 * @Description
 * @Author Axel
 * @Date 2021/6/14 21:15
 * @Version 1.0
 */
@Service("mQOrderService")
@Slf4j
public class MQOrderSendMsgService implements RabbitTemplate.ConfirmCallback {


    @Autowired
    private MqMsgLogService mqMsgLogService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void updateUserDep(UpdateDepositMsg msg) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(String.valueOf(msg.getObjectId()));
        rabbitTemplate.convertAndSend(ExchangeConstant.ORDER_UPDATE_DEPOSIT_EXCHANGE,
                QueueConstant.ORDER_UPDATE_DEPOSIT,msg,correlationData);
    }

    // 重写消息确认送达接口
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        try {
            if (ack) {
                // 将存的消息库中的消息状态改为已发送
                MqMsgLog log = new MqMsgLog();
                log.setObjectId(Long.valueOf(correlationData.getId()));
                log.setState(MsgStateConstant.CONFIRM.getState());
                mqMsgLogService.updateMsgByCondition(log);
            }
            // 如果推送失败 报错
        } catch (Exception e) {
            log.info("ack is failed {}",correlationData.getId());
        }

    }
}
