package com.sxx.sivir.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Package com.sxx.sivir.web.controller
 * @Author: 尚星
 * @Date: 2019/4/12 10:53
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description:
 */

@Controller
public class ToController {

    @GetMapping("/api/to/login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/api/to/index")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/api/to/welcome")
    public String toWelcome() {
        return "welcome";
    }

    @RequestMapping("/api/to/customer-sorder")
    public String toCustomerSorder() {
        return "customer-sorder";
    }

    @RequestMapping("/api/to/customer-sorder-add")
    public String toCustomerSorderAdd() {
        return "customer-sorder-add";
    }



}
