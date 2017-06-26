package com.ksea.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    //执行登录
    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    public String doLogin(@RequestParam("userName") String userName,
                          @RequestParam("password") String password) {

        try {
            //获取Subject
            Subject subject = SecurityUtils.getSubject();
            //组装token
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            System.out.println("token:"+token.hashCode());
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }


        return "redirect:home/index";
    }
}
