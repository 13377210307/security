package com.session.config;

import com.session.auth.MyExpireSessionStrategy;
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

    // 资源认证
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .formLogin()
                .and()
                .authorizeRequests().antMatchers("/session/session").hasAnyRole("ADMIN") //所有资源都需认证
                .and()
                .sessionManagement()
                .invalidSessionUrl("/login")  // session过期跳转登录页
                .maximumSessions(1) //只允许一台设备进行登录
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(new MyExpireSessionStrategy());  // session过期或非法策略
    }

    // 用户授权认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");
    }

    // 静态资源访问
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
