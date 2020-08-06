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
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String login(SysUser sysUser, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(sysUser.getUsername(),sysUser.getPassword());
        Authentication authenticate = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        // 使用用户名加载用户信息
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(sysUser.getUsername());

        String token = this.jwtTokenUtils.generateToken(userDetails);

        //设置token到header中
        response.addHeader("Authorization", "Bearer " + token);

        // 生成token
        return token;
    }


    /**
     * 刷新token
     */
    public String refreshToken(String oldToken, HttpServletResponse response) {

        //从请求头中获取token
        /*String oldToken = request.getHeader("Authorization");
        if (StringUtils.isEmpty(oldToken)) {
            return null;
        }*/
        // 获取真正token
        String token = this.jwtTokenUtils.realToken(oldToken);

        // 判断token是否过期
        if (this.jwtTokenUtils.isTokenExpired(token)) {
            return null;
        }else {
            // 刷新令牌
            String refreshToken = this.jwtTokenUtils.refreshToken(token);
            response.addHeader("Authorization", "Bearer " + refreshToken);
            return refreshToken;
        }
    }
}
