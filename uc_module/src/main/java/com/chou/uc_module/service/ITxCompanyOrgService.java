package com.chou.uc_module.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chou.uc_module.domain.entity.TxCompanyOrg;
import com.chou.uc_module.vo.TxCompanyOrgVO;

import java.util.List;

/**
 * @author Axel
 * @version 1.0
 * @className TxCompanyOrgService
 * @description TODO
 * @date 2022/11/13 13:17
 */
public interface ITxCompanyOrgService extends IService<TxCompanyOrg> {


    Integer addOrg(TxCompanyOrgVO vo);

    List<TxCompanyOrgVO> getListOrg();

}
