package com.wechat.demo1.wechatdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig1 extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //配置没有权限访问跳转自定义界面
        http.exceptionHandling().accessDeniedPage("/unauth.html");

        http.formLogin()     //自定义自己编写的登陆页面
            .loginPage("/login.html")           //登陆页面设置
            .loginProcessingUrl("/user/login")  //登陆访问的路径,controller不需要自己写，springsecurity处理过程。
            .defaultSuccessUrl("/test/index").permitAll()              //认证成功之后调转的路径
            .and().authorizeRequests()
                .antMatchers("/","/test/hello","/user/login","/K3WebApi").permitAll()//设置哪些不需要登陆验证。
                //当前登陆用户，只有具有admins权限才可以访问这个路径
                //第一种：hasAuthority方法
                //.antMatchers("/test/index").hasAuthority("admins")
                //第二种：hasAnyAuthority方法
                //.antMatchers("/test/index").hasAnyAuthority("admins,manager")
                //第三种方法：hasRole方法
                .antMatchers("/test/index").hasRole("sale")
                //第四种方法：hasAnyRole方法
                //.antMatchers("/test/index").hasAnyRole("sale");
            .anyRequest().authenticated()
            .and().csrf().disable();    //关闭csrf防护
    }
}
