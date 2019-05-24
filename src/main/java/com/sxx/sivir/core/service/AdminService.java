package com.sxx.sivir.core.service;

import com.sxx.sivir.core.common.page.PageResult;
import com.sxx.sivir.core.common.request.PageRequestDTO;
import com.sxx.sivir.core.common.response.RegionInfo;
import com.sxx.sivir.core.common.response.RegionTransCar;
import com.sxx.sivir.core.dal.domain.Car;
import com.sxx.sivir.core.dal.domain.Sorder;
import com.sxx.sivir.core.dal.domain.User;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Package com.sxx.sivir.core.service
 * @Author: 尚星
 * @Date: 2019/4/9 16:30
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description: 管理员业务逻辑
 */


public interface AdminService {


    /*------                                   --------*/
    /*------        管理员接口 - 用户管理         --------*/
    /*------                                   --------*/
    /**
     * <p> 获取全部用户 </p>
     * @param pageRequestDTO 分页参数
     * @return PageResult<User>
     * @date 2019/4/9 16:35
     *
     */
    PageResult<User> getAllUser(PageRequestDTO pageRequestDTO);

    /**
     * <p> 新增用户 </p>
     * @param user 用户信息
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    Integer addUser(User user);

    /**
     * <p> 修改用户 </p>
     * @param user 用户信息
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    Integer updateUser(User user);

    /**
     * <p> 删除用户 </p>
     * @param user 用户信息
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    Integer deleteUser(User user);


    /**
     * <p> 根据id获取用户信息 </p>
     * @param user 用户信息
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    User getUserById(User user);



    /*------                                   --------*/
    /*------        管理员接口 - 订单管理         --------*/
    /*------                                   --------*/
    /**
     * <p> 获取全部订单 </p>
     * @param pageRequestDTO 分页参数
     * @return PageResult<Sorder>
     * @date 2019/4/9 16:35
     *
     */
    PageResult<Sorder> getAllSorder(PageRequestDTO pageRequestDTO);

    /**
     * <p> 修改订单 </p>
     * @param sorder 订单信息
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    Integer updateSorder(Sorder sorder);

    /**
     * <p> 删除订单 </p>
     * @param sorder 订单信息
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    Integer deleteSorder(Sorder sorder);

    /**
     * <p> 根据id获取订单信息 </p>
     * @param sorder 订单信息
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    Sorder getSorderById(Sorder sorder);



    /*------                                             --------*/
    /*------  管理员接口 - 区域管理 - 配置区域内的快递员和车辆   --------*/
    /*------                                             --------*/
    /**
     * <p> 新增区域信息 </p>
     * @param regionTransCar 区域信息
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    Integer addRegionTransCar(RegionTransCar regionTransCar);

    /**
     * <p> 删除区域信息 </p>
     * @param regionTransCar 区域信息
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    Integer deleteRegionTransCar(RegionTransCar regionTransCar);

    /**
     * <p> 获取全部区域信息 </p>
     * @param pageRequestDTO 分页参数
     * @return PageResult<RegionInfo>
     * @date 2019/4/9 16:35
     *
     */
    PageResult<RegionInfo> getAllRegionInfo(PageRequestDTO pageRequestDTO);


    /*------                                             --------*/
    /*------  管理员接口 - 存储管理 - 车辆管理   --------*/
    /*------                                             --------*/
    /**
     * <p> 新增车辆信息 </p>
     * @param car 车辆信息
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    Integer addCar(Car car);

    /**
     * <p> 删除车辆信息 </p>
     * @param car 订单车辆信息
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    Integer deleteCar(Car car);

    /**
     * <p> 修改车辆信息 </p>
     * @param car 订单车辆信息
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    Integer updateCar(Car car);

    /**
     * <p> 获取全部车辆信息 </p>
     * @param pageRequestDTO 分页参数
     * @return PageResult<car>
     * @date 2019/4/9 16:35
     *
     */
    PageResult<Car> getAllCar(PageRequestDTO pageRequestDTO);


    /*------                                             --------*/
    /*------  管理员接口 - 配送管理 - 安排订单的快递员和车辆   --------*/
    /*------                                             --------*/
    /**
     * <p> 安排订单快递员车辆信息 </p>
     * @param sorder 订单快递员车辆信息
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    Integer arrangeSorderTransCar(Sorder sorder);

    /**
     * <p> 修改订单快递员车辆信息 </p>
     * @param sorder 订单快递员车辆信息
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    Integer updateSorderTransCar(Sorder sorder);

    /**
     * <p> 获取全部订单快递员车辆信息 </p>
     * @param pageRequestDTO 分页参数
     * @return PageResult<sorder>
     * @date 2019/4/9 16:35
     *
     */
    PageResult<Sorder> getAllSorderTransCar(PageRequestDTO pageRequestDTO);



    /**
     * <p> 生成业务报表 </p>
     * @param pageRequestDTO 分页参数
     * @return XSSFWorkbook excel
     * @date 2019/4/9 16:35
     *
     */
    XSSFWorkbook getBusinessReport(PageRequestDTO pageRequestDTO);


    RegionInfo getRegionInfoById(Long id);
}
