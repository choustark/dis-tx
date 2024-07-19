package com.chou.uc_module.controller.login;


import com.chou.common_module.context.ResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Chou
 * @Description 登录
 * @ClassName LoginController
 * @Date 2023/8/27 21:59
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "account")
public class LoginController {

    public static final String LOGIN_MSG = "登录成功！";
    public static final String LOGIN_OUT_MSG = "操作成功！";


    /**
     * 登录成功
     * @return
     */
    @PostMapping("/login")
    public ResponseData login() {

        return ResponseData.builder().msg("登录成功！").build();
    }

    @GetMapping("/test")
    public ResponseData test(){
        return ResponseData.builder().Data("hello").build();
    }

    /**
     * 登出
     * @return
     */
    @PostMapping("/loginOut")
    public ResponseData loginOut() {

        return ResponseData.builder().msg("操作成功！").build();
    }

}
