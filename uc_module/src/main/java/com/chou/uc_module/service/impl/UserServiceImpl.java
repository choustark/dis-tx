package com.chou.uc_module.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chou.uc_module.domain.entity.User;
import com.chou.uc_module.mapper.UserMapper;
import com.chou.uc_module.domain.vo.SearchUserVO;
import com.chou.uc_module.domain.po.UserPo;
import com.chou.uc_module.service.IUserService;
import com.chou.uc_module.domain.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Chou
 * @since 2021-05-16
 */
@Service("iUserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    public IPage<UserVO> getPage(SearchUserVO po, Integer pageNo, Integer pageSize) {
        return null;
    }

    public Boolean addUser(UserPo po) {
        User entity = new User();
        BeanUtils.copyProperties(po, entity);
        return this.save(entity);

    }

    public UserVO getOne(Long id) {
        return null;
    }

    public Boolean updateUser(UserPo po) {
        return null;
    }

    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public void updateUserDeposit(UserPo po) {
        baseMapper.updateUserDeposit(po);
    }
}
