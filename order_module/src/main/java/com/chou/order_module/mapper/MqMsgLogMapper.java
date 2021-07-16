package com.chou.order_module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chou.order_module.domain.entity.MqMsgLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName MqMsgLogMapper
 * @Description TODO
 * @Author Axel
 * @Date 2021/7/3 21:17
 * @Version 1.0
 */
@Mapper
public interface MqMsgLogMapper extends BaseMapper<MqMsgLog> {
}
