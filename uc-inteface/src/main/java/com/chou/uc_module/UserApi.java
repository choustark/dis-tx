package com.chou.uc_module;

import com.chou.common_module.context.ResponseData;
import com.chou.uc_module.req.AddUserReq;
import com.chou.uc_module.req.UpdateUseReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName CustomerService
 * @Description TODO
 * @Author Axel
 * @Date 2021/3/28 17:09
 * @Version 1.0
 */
@Component
@FeignClient(name = "user-service")
@RequestMapping("/uc/user")
public interface UserApi {
    @PostMapping("/update/deposit")
    ResponseData<Boolean> updateUserDeposit(@RequestBody UpdateUseReq req);

    @PostMapping("/add")
    ResponseData<Boolean> addCustomer(@RequestBody AddUserReq req);
}
