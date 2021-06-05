package com.chou.uc_module.service;

import com.chou.uc_module.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chou.uc_module.po.SearchUserPo;
import com.chou.uc_module.po.UserPo;
import com.chou.uc_module.vo.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Chou
 * @since 2021-05-16
 */
public interface IUserService extends IService<User> {

        IPage<UserVO> getPage(SearchUserPo po, Integer pageNo, Integer pageSize);

        Boolean addUser(UserPo po);

        UserVO getOne(Long id);

        Boolean updateUser(UserPo po);

        Boolean delete(Long id);

        void updateUserDeposit(UserPo req);
}
