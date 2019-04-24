package com.sxx.sivir.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sxx.sivir.core.dal.domain.User;
import com.sxx.sivir.core.dal.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/api/to/trans-received")
    public String toTransReceived() {
        return "trans-send";
    }

    @RequestMapping("/api/to/trans-send")
    public String toTransSend() {
        return "trans-received";
    }


    @RequestMapping("/api/to/admin-user")
    public String toAdminUser() {
        return "admin-user";
    }
    @RequestMapping("/api/to/admin-user-add")
    public String toAdminAddUser() {
        return "admin-user-add";
    }
    @RequestMapping("/api/to/admin-user-update")
    public ModelAndView toAdminUpdateUser(@RequestParam Long userId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin-user-update");
        mv.addObject("userId", userId);
        return mv;
    }

    @RequestMapping("/api/to/admin-sorder")
    public String toAdminSorder() {
        return "admin-sorder";
    }
    @RequestMapping("/api/to/admin-sorder-update")
    public ModelAndView toAdminUpdateSorder(@RequestParam Long orderId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin-sorder-update");
        mv.addObject("orderId", orderId);
        return mv;
    }


    @RequestMapping("/api/to/admin-region")
    public String toAdminRegion() {
        return "admin-region";
    }

    @RequestMapping("/api/to/admin-car")
    public String toAdminCar() {
        return "admin-car";
    }

    @RequestMapping("/api/to/admin-arrange")
    public String toAdminArrange() {
        return "admin-arrange";
    }

    @RequestMapping("/api/to/admin-report")
    public String toAdminReport() {
        return "admin-report";
    }



}
