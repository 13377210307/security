package com.form.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    // 访问资源管理
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()  // 跨站请求禁用
                .formLogin()
                .loginProcessingUrl("/login")  // 登录请求
                .successForwardUrl("/form/index")  // 验证成功跳转
                .failureForwardUrl("/form/failure")  //验证失败跳转
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()  // 对外开放接口，不需要进行身份验证
                .antMatchers("/form/admin").hasAnyRole("ADMIN")  // 只有admin角色用户可以进行访问
                .antMatchers("/form/user").hasAnyRole("ADMIN,USER"); // 具有admin以及user角色可以
    }

    // 用户授权认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 基于内存用户认证方式
        auth.inMemoryAuthentication()
                .withUser("user").password(new BCryptPasswordEncoder().encode("123456")).roles("USER")
                .and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");
    }

    // 静态资源管理
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 将项目中的静态资源开放出来
        web.ignoring().antMatchers("/css/**","fonts/**","img/**");
    }

    // java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"  报错：由于未注入加密方法
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
