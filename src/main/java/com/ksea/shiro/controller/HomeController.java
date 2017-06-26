package com.ksea.shiro.controller;

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
}
