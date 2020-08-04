package com.form.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/form")
public class FormController {

    @GetMapping("/index")
    public String index() {
        return "我是首页";
    }

    @GetMapping("/user")
    public String user() {
        return "我是用户访问接口";
    }

    @GetMapping("/admin")
    public String admin() {
        return "我是admin访问接口";
    }

    @GetMapping("failure")
    public String failure() {
        return "我是验证失败访问接口";
    }
}
