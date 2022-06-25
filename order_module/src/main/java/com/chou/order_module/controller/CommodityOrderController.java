package com.chou.order_module.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chou.amqp.entity.UpdateDepositMsg;
import com.chou.order_module.po.CommodityOrderPo;
import com.chou.order_module.po.MsgLogPO;
import com.chou.order_module.po.SearchCommodityOrderPo;
import com.chou.order_module.service.ICommodityOrderService;
import com.chou.order_module.mq.MQOrderSendMsgService;
import com.chou.order_module.service.MqMsgLogService;
import com.chou.order_module.vo.CommodityOrderVO;

import com.chou.uc_module.UserApi;
import com.chou.uc_module.req.UpdateUseReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.chou.common_module.context.ResponseData;
import com.chou.common_module.context.ResponseDataBuilder;

import java.util.concurrent.Executors;


/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author Chou
 * @since 2021-06-05
 */
@RestController
@RequestMapping("/commodityOrder")
public class CommodityOrderController {
        @Autowired
        private ICommodityOrderService iCommodityOrderService;

        @Autowired
        private UserApi userApi;

        /**
        * 分页查询 CommodityOrder
        * @param po
        * @param pageNo
        * @param pageSize
        */
        @GetMapping("/page")
        public ResponseData<IPage<CommodityOrderVO>> getCommodityOrderPage(SearchCommodityOrderPo po,
                                                                           @RequestParam(name = "pagNo",defaultValue = "1") Integer pageNo,
                                                                           @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize){
           IPage<CommodityOrderVO> voPage =  iCommodityOrderService.getPage(po,pageNo,pageSize);
           return ResponseDataBuilder.buildSuccessData(voPage);
        }

        /**
         * 新增 CommodityOrder
         * @param po
         */
        @PostMapping("/add")
        @Transactional(rollbackFor = Exception.class)
        public ResponseData<Boolean> addCommodityOrder(@RequestBody CommodityOrderPo po){
            Boolean aBoolean = iCommodityOrderService.addCommodityOrder(po);
            if(aBoolean){
                // 减去对应用户余额
                UpdateUseReq req = new UpdateUseReq();
                req.setId(po.getUserId());
                req.setDeposit(po.getAmount());
                userApi.updateUserDeposit(req);
            }
            return ResponseDataBuilder.buildSuccessData(true);
        }

    /**
     * 新增 CommodityOrder
     * @param po
     */
    @PostMapping("/distributeAdd")
    public ResponseData<Boolean> disAddCommodityOrder(@RequestBody CommodityOrderPo po){
        iCommodityOrderService.addDisCommodityOrder(po);
        return ResponseDataBuilder.buildSuccessData(true);
    }

        /**
         * 详情查询 CommodityOrder
         * @param id
         */
        @GetMapping("/one")
        public ResponseData<CommodityOrderVO> getOne(@RequestParam Long id){
            return ResponseDataBuilder.buildSuccessData(iCommodityOrderService.getOne(id));
        }

        /**
         * 修改操作 CommodityOrder
         * @param po
         */
        @PostMapping("/update")
        public ResponseData<Boolean> updateCommodityOrder(@RequestBody CommodityOrderPo po){
            return ResponseDataBuilder.buildSuccessData(iCommodityOrderService.updateCommodityOrder(po));
        }

        /**
        * 删除 CommodityOrder
        * @param id
        */
        @DeleteMapping("/delete")
        public ResponseData<Boolean> deleteCommodityOrder(@RequestParam Long id){
            return ResponseDataBuilder.buildSuccessData(iCommodityOrderService.delete(id));
        }
}

