package com.jwt.config;

import com.jwt.entity.SysUser;
import com.jwt.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@Configuration
public class JwtAuthConfig {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    /**
     * 登录认证换取token令牌
     */
    public String login(SysUser sysUser) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(sysUser.getUsername(),sysUser.getPassword());
        Authentication authenticate = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        // 使用用户名加载用户信息
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(sysUser.getUsername());

        // 生成token
        return this.jwtTokenUtils.generateToken(userDetails);
    }


}
