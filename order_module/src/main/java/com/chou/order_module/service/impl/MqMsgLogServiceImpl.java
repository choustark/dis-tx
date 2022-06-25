package com.chou.order_module.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chou.order_module.domain.entity.MqMsgLog;
import com.chou.order_module.mapper.MqMsgLogMapper;
import com.chou.order_module.po.MsgLogPO;
import com.chou.order_module.service.MqMsgLogService;
import org.springframework.stereotype.Service;

/**
 * @ClassName MqMsgLogServiceImpl
 * @Description TODO
 * @Author Axel
 * @Date 2021/7/3 21:22
 * @Version 1.0
 */

@Service("mqMsgLogService")
public class MqMsgLogServiceImpl extends ServiceImpl<MqMsgLogMapper, MqMsgLog> implements MqMsgLogService {
    @Override
    public void addMsgLog(MqMsgLog log) {
        int insert = getBaseMapper().insert(log);
    }

    @Override
    public void updateMsgByCondition(MqMsgLog log) {

    }
}
