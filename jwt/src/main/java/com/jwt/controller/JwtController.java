package com.jwt.controller;

import com.jwt.config.JwtAuthConfig;
import com.jwt.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    private JwtAuthConfig jwtAuthConfig;

    /**
     * 登录认证以及设置请求头
     */
    @PostMapping("/jwt")
    public String jwt(@RequestBody SysUser sysUser, HttpServletResponse response) {
        return this.jwtAuthConfig.login(sysUser,response);
    }

    /**
     * 刷新令牌以及设置请求头
     */
    @GetMapping("/refresh")
    public String refreshToken(@RequestHeader("Authorization") String token,HttpServletResponse response) {
        return this.jwtAuthConfig.refreshToken(token,response);
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin测试成功";
    }


}
