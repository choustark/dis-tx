package com.chou.order_module.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chou.order_module.domain.entity.MqMsgLog;

/**
 * @ClassName MqMsgLogService
 * @Description TODO
 * @Author Axel
 * @Date 2021/7/3 21:22
 * @Version 1.0
 */
public interface MqMsgLogService extends IService<MqMsgLog> {

    void addMsgLog(MqMsgLog log );

    void updateMsgByCondition(MqMsgLog log);
}
