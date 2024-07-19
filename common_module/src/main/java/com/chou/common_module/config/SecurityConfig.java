package com.chou.common_module.config;//package com.chou.uc_module.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Axel
 * @version 1.0
 * @className SecurityConfig
 * @description Security鉴权、授权、安全配置
 * @date 2022/6/4 0:00
 */

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        /*http.csrf().disable().httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/uc/user/**")
                //.hasRole("ADMIN")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .disable();*/
        http.csrf().disable().httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/account/**")
                //.hasRole("ADMIN")
                .permitAll()
                //.anyRequest().authenticated()
                .and()
                .formLogin();
    }

    /**
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        //web.ignoring().antMatchers("/resources/public/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication().withUser("zhangSan").password("123456").roles("ADMIN");
        super.configure(auth);
    }
}
