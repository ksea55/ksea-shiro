package com.ksea.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ksea on 2017/6/26.
 */
@Controller
@RequestMapping("auth")
public class AuthorizedController {
    //无权限访问页面
    @RequestMapping(value = "/unauth",method = RequestMethod.GET)
    public String unauthIndex(){
        return "";
    }
}
