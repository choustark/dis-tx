package com.chou.common_module.config;//package com.chou.uc_module.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Axel
 * @version 1.0
 * @className WebSecurityConfig
 * @description TODO
 * @date 2022/6/4 0:00
 */

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/", "/uc/**","order/**");
        super.configure(web);
    }
}
