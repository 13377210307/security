package com.jwt.controller;

import com.jwt.config.JwtAuthConfig;
import com.jwt.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    private JwtAuthConfig jwtAuthConfig;

    @PostMapping("/jwt")
    public String jwt(@RequestBody SysUser sysUser) {
        return this.jwtAuthConfig.login(sysUser);
    }
}
