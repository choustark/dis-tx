package com.chou.uc_module.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chou.uc_module.po.SearchUserPo;
import com.chou.uc_module.po.UserPo;
import com.chou.uc_module.service.IUserService;
import com.chou.uc_module.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import com.chou.common_module.context.ResponseData;
import com.chou.common_module.context.ResponseDataBuilder;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Chou
 * @since 2021-05-16
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {
    @Autowired
    private IUserService iUserService;

    @Value("${test.info}")
    private String testInfo;

    /**
     * 分页查询 User
     *
     * @param po
     * @param pageNo
     * @param pageSize
     */
    @GetMapping("/page")
    public ResponseData<IPage<UserVO>> getUserPage(SearchUserPo po,
                                                   @RequestParam(name = "pagNo", defaultValue = "1") Integer pageNo,
                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        IPage<UserVO> voPage = iUserService.getPage(po, pageNo, pageSize);
        return ResponseDataBuilder.buildSuccessData(voPage);
    }

    /**
     * 新增 User
     *
     * @param po
     */
    @PostMapping("/add")
    public ResponseData<Boolean> addUser(@RequestBody UserPo po) {
        return ResponseDataBuilder.buildSuccessData(iUserService.addUser(po));
    }

    /**
     * 详情查询 User
     *
     * @param id
     */
    @GetMapping("/one")
    public ResponseData<UserVO> getOne(@RequestParam Long id) {
        return ResponseDataBuilder.buildSuccessData(iUserService.getOne(id));
    }

    /**
     * 修改操作 User
     *
     * @param po
     */
    @PostMapping("/update")
    public ResponseData<Boolean> updateUser(@RequestBody UserPo po) {
        return ResponseDataBuilder.buildSuccessData(iUserService.updateUser(po));
    }

    @PostMapping("/update/deposit")
    ResponseData<Boolean> updateUserDeposit(@RequestBody UserPo po) {
        log.info("修改的值={}",testInfo);
        iUserService.updateUserDeposit(po);
        return ResponseDataBuilder.buildSuccessData(true);
    }

    /**
     * 删除 User
     *
     * @param id
     */
    @DeleteMapping("/delete")
    public ResponseData<Boolean> deleteUser(@RequestParam Long id) {
        return ResponseDataBuilder.buildSuccessData(iUserService.delete(id));
    }


}

