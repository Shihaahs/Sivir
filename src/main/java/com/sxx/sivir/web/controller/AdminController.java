package com.sxx.sivir.web.controller;

import com.sxx.sivir.core.common.entity.APIResult;
import com.sxx.sivir.core.common.page.PageResult;
import com.sxx.sivir.core.common.request.PageRequestDTO;
import com.sxx.sivir.core.dal.domain.Sorder;
import com.sxx.sivir.core.dal.domain.User;
import com.sxx.sivir.core.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.sxx.sivir.core.common.constant.SivirURL.*;

/**
 * @Package com.sxx.sivir.web.controller
 * @Author: 尚星
 * @Date: 2019/4/9 16:21
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description: 管理员业务入口
 */

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    /*------        管理员接口 - 用户管理         --------*/

    @RequestMapping(value = ADMIN_GET_ALL_USER, method = RequestMethod.POST)
    public APIResult<PageResult<User>> getAllUser(@RequestBody PageRequestDTO pageRequestDTO) {
        return APIResult.ok(adminService.getAllUser(pageRequestDTO));
    }

    @RequestMapping(value = ADMIN_ADD_USER, method = RequestMethod.POST)
    public APIResult<Integer> addUser(@RequestBody User user) {
        return APIResult.ok(adminService.addUser(user));
    }

    @RequestMapping(value = ADMIN_DELETE_USER, method = RequestMethod.POST)
    public APIResult<Integer> deleteUser(@RequestBody User user) {
        return APIResult.ok(adminService.deleteUser(user));
    }

    @RequestMapping(value = ADMIN_UPDATE_USER, method = RequestMethod.POST)
    public APIResult<Integer> updateUser(@RequestBody User user) {
        return APIResult.ok(adminService.updateUser(user));
    }

    /*------        管理员接口 - 订单管理         --------*/

    @RequestMapping(value = ADMIN_GET_ALL_SORDER, method = RequestMethod.POST)
    public APIResult<PageResult<Sorder>> getAllSorder(@RequestBody PageRequestDTO pageRequestDTO) {
        return APIResult.ok(adminService.getAllSorder(pageRequestDTO));
    }

    @RequestMapping(value = ADMIN_DELETE_SORDER, method = RequestMethod.POST)
    public APIResult<Integer> deleteSorder(@RequestBody Sorder sorder) {
        return APIResult.ok(adminService.deleteSorder(sorder));
    }

    @RequestMapping(value = ADMIN_UPDATE_SORDER, method = RequestMethod.POST)
    public APIResult<Integer> updateSorder(@RequestBody Sorder sorder) {
        return APIResult.ok(adminService.updateSorder(sorder));
    }

}
