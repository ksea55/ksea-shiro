package com.ksea.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ksea on 2017/6/23.
 */
@Controller
@RequestMapping("home")
public class HomeController {
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "pages/home";
    }


    @RequiresRoles(value = "admin")
    @RequestMapping(value = "admin/index", method = RequestMethod.GET)
    public String admin() {
        return "pages/admin";
    }

    @RequiresRoles(value = "user")
    @RequestMapping(value = "user/index", method = RequestMethod.GET)
    public String user() {
        return "pages/user";
    }


}
