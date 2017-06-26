package com.ksea.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ksea on 2017/6/26.
 */
@Controller
@RequestMapping("login")
public class LoginController {
    //登录首页
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "login/login";
    }
}
