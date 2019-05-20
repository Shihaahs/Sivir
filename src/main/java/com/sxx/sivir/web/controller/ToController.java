package com.sxx.sivir.web.controller;

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

    /**
     *     客户 - 订单管理
     */

    @RequestMapping("/api/to/customer-sorder")
    public String toCustomerSorder() {
        return "customer-sorder";
    }

    @RequestMapping("/api/to/customer-sorder-add")
    public String toCustomerAddSorder() {
        return "customer-sorder-add";
    }

    /**
     *     快递员 - 订单管理
     */


    @RequestMapping("/api/to/trans-received")
    public String toTransReceived() {
        return "trans-send";
    }

    @RequestMapping("/api/to/trans-send")
    public String toTransSend() {
        return "trans-received";
    }


    /**
     *     管理员 - 人员管理
     */
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


    /**
     *     管理员 - 订单管理
     */
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

    /**
     *     管理员 - 区域存储管理
     */
    @RequestMapping("/api/to/admin-region")
    public String toAdminRegion() {
        return "admin-region";
    }

    @RequestMapping("/api/to/admin-region-add")
    public String toAdminAddRegion() {
        return "admin-region-add";
    }

    @RequestMapping("/api/to/admin-region-update")
    public String toAdminUpdateRegion() {
        return "admin-region-add";
    }


    /**
     *     管理员 - 车辆管理
     */
    @RequestMapping("/api/to/admin-car")
    public String toAdminCar() {
        return "admin-car";
    }

    @RequestMapping("/api/to/admin-car-add")
    public String toAdminAddCar() {
        return "admin-car-add";
    }

    /**
     *     管理员 - 配送管理
     */
    @RequestMapping("/api/to/admin-arrange")
    public String toAdminArrange() {
        return "admin-arrange";
    }
    @RequestMapping("/api/to/admin-arrange-update")
    public ModelAndView toAdminUpdateArrange(@RequestParam Long orderId, @RequestParam Long orderRegionId) {
        System.out.println(orderId);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin-arrange-update");
        mv.addObject("orderId", orderId);
        mv.addObject("orderRegionId", orderRegionId);
        return mv;
    }

    /**
     *     管理员 - 报表管理
     */
    @RequestMapping("/api/to/admin-report")
    public String toAdminReport() {
        return "admin-report";
    }



}
