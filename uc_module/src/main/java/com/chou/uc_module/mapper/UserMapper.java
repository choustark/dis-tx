package com.chou.uc_module.mapper;

import com.chou.uc_module.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chou.uc_module.po.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Chou
 * @since 2021-05-16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * @param req
     */
    void updateUserDeposit(@Param("po") UserPo req);
}
