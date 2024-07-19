package com.chou.uc_module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chou.common_module.BaseEntity;
import com.chou.common_module.utils.SnowflakeIdWorker;
import com.chou.uc_module.domain.entity.TxCompanyOrg;
import com.chou.uc_module.mapper.TxCompanyOrgMapper;
import com.chou.uc_module.service.ITxCompanyOrgService;
import com.chou.uc_module.domain.vo.TxCompanyOrgVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Axel
 * @version 1.0
 * @className TxCompanyOrgServiceImpl
 * @description TODO
 * @date 2022/11/13 13:19
 */

@Service("iTxCompanyOrgService")
public class TxCompanyOrgServiceImpl extends ServiceImpl<TxCompanyOrgMapper, TxCompanyOrg> implements ITxCompanyOrgService {


    @Override
    public Integer addOrg(TxCompanyOrgVO vo) {
        SnowflakeIdWorker snowflakeIdWorker = new  SnowflakeIdWorker(1,1);
        StringBuilder fullPathBuffer = new StringBuilder();
        StringBuilder znFullPathBuffer = new StringBuilder();

        TxCompanyOrg entity = new TxCompanyOrg();
        vo.setId(snowflakeIdWorker.nextId());
        BeanUtils.copyProperties(vo,entity);
        entity.initDefault();
        if(vo.getParentId() != -1L){
            TxCompanyOrg parentOrg = this.baseMapper.selectById(vo.getParentId());
            fullPathBuffer.append(parentOrg.getId()).append("/").append(entity.getId());
            znFullPathBuffer.append(parentOrg.getName()).append("/").append(vo.getName());
        }
        entity.setFullPath(fullPathBuffer.toString());
        entity.setCnFullPath(znFullPathBuffer.toString());
        return this.baseMapper.insert(entity);
    }

    @Override
    public List<TxCompanyOrgVO> getListOrg() {
        List<TxCompanyOrgVO> vos = new ArrayList<>();
        LambdaQueryWrapper<TxCompanyOrg> queryWrapper = new LambdaQueryWrapper<TxCompanyOrg>().eq(BaseEntity::getCompanyId, 1).eq(BaseEntity::getIsDel, 0);
        List<TxCompanyOrg> txCompanyOrgs = this.getBaseMapper().selectList(queryWrapper);
        if(CollectionUtils.isNotEmpty(txCompanyOrgs)){
            for (TxCompanyOrg txCompanyOrg : txCompanyOrgs) {
                TxCompanyOrgVO vo = new TxCompanyOrgVO();
                BeanUtils.copyProperties(txCompanyOrg,vo);
                vos.add(vo);
            }
        }
        return vos;

    }
}
