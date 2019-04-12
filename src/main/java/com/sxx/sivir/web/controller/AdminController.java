package com.sxx.sivir.web.controller;

import com.sxx.sivir.core.common.entity.APIResult;
import com.sxx.sivir.core.common.page.PageResult;
import com.sxx.sivir.core.common.request.PageRequestDTO;
import com.sxx.sivir.core.common.response.RegionInfo;
import com.sxx.sivir.core.common.response.RegionTransCar;
import com.sxx.sivir.core.dal.domain.Car;
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

    /*------       管理员接口 - 区域管理 - 配置区域内的快递员和车辆       --------*/

    @RequestMapping(value = ADMIN_GET_ALL_REGION_INFO, method = RequestMethod.POST)
    public APIResult<PageResult<RegionInfo>> getAllRegionInfo(@RequestBody PageRequestDTO pageRequestDTO) {
        return APIResult.ok(adminService.getAllRegionInfo(pageRequestDTO));
    }

    @RequestMapping(value = ADMIN_ADD_REGION_INFO, method = RequestMethod.POST)
    public APIResult<Integer> addRegionTransCar(@RequestBody RegionTransCar regionTransCar) {
        return APIResult.ok(adminService.addRegionTransCar(regionTransCar));
    }

    @RequestMapping(value = ADMIN_DELETE_REGION_INFO, method = RequestMethod.POST)
    public APIResult<Integer> deleteRegionTransCar(@RequestBody RegionTransCar regionTransCar) {
        return APIResult.ok(adminService.deleteRegionTransCar(regionTransCar));
    }

    /*------      管理员接口 - 存储管理 - 车辆管理      --------*/

    @RequestMapping(value = ADMIN_GET_ALL_CAR, method = RequestMethod.POST)
    public APIResult<PageResult<Car>> getAllCar(@RequestBody PageRequestDTO pageRequestDTO) {
        return APIResult.ok(adminService.getAllCar(pageRequestDTO));
    }

    @RequestMapping(value = ADMIN_ADD_CAR, method = RequestMethod.POST)
    public APIResult<Integer> addCar(@RequestBody Car car) {
        return APIResult.ok(adminService.addCar(car));
    }

    @RequestMapping(value = ADMIN_DELETE_CAR, method = RequestMethod.POST)
    public APIResult<Integer> deleteCar(@RequestBody Car car) {
        return APIResult.ok(adminService.deleteCar(car));
    }

    @RequestMapping(value = ADMIN_UPDATE_CAR, method = RequestMethod.POST)
    public APIResult<Integer> updateCar(@RequestBody Car car) {
        return APIResult.ok(adminService.updateCar(car));
    }


    /*------        管理员接口 - 配送管理 - 安排订单的快递员和车辆         --------*/

    @RequestMapping(value = ADMIN_GET_ALL_SORDER_INFO, method = RequestMethod.POST)
    public APIResult<PageResult<Sorder>> getAllSorderTransCar(@RequestBody PageRequestDTO pageRequestDTO) {
        return APIResult.ok(adminService.getAllSorderTransCar(pageRequestDTO));
    }

    @RequestMapping(value = ADMIN_ARRANGE_SORDER_INFO, method = RequestMethod.POST)
    public APIResult<Integer> arrangeSorderTransCar(@RequestBody Sorder sorder) {
        return APIResult.ok(adminService.arrangeSorderTransCar(sorder));
    }

    @RequestMapping(value = ADMIN_UPDATE_SORDER_INFO, method = RequestMethod.POST)
    public APIResult<Integer> updateSorderTransCar(@RequestBody Sorder sorder) {
        return APIResult.ok(adminService.updateSorderTransCar(sorder));
    }



    @RequestMapping(value = ADMIN_GET_BUSINESS_REPORT, method = RequestMethod.POST)
    public APIResult<PageResult<Sorder>> getBusinessReport(@RequestBody PageRequestDTO pageRequestDTO) {
        return APIResult.ok(adminService.getBusinessReport(pageRequestDTO));
    }
}
