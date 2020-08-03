package com.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/content")
    public String basic() {
        System.out.println("简单密码模式测试");
        return "简单密码模式测试";
    }
}
