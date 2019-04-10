package com.sxx.sivir.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sxx.sivir.core.common.page.PageResult;
import com.sxx.sivir.core.common.request.PageRequestDTO;
import com.sxx.sivir.core.common.response.RegionInfo;
import com.sxx.sivir.core.common.response.RegionTransCar;
import com.sxx.sivir.core.dal.dao.RegionDao;
import com.sxx.sivir.core.dal.domain.Car;
import com.sxx.sivir.core.dal.domain.Region;
import com.sxx.sivir.core.dal.domain.Sorder;
import com.sxx.sivir.core.dal.domain.User;
import com.sxx.sivir.core.dal.manager.CarManager;
import com.sxx.sivir.core.dal.manager.RegionManager;
import com.sxx.sivir.core.dal.manager.SorderManager;
import com.sxx.sivir.core.dal.manager.UserManager;
import com.sxx.sivir.core.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sxx.sivir.core.common.page.PageQuery.conditionAdapter;
import static com.sxx.sivir.core.common.page.PageQuery.initPage;

/**
 * @Package com.sxx.sivir.core.service.impl
 * @Author: 尚星
 * @Date: 2019/4/9 16:31
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description:
 */

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserManager userManager;
    @Autowired
    private CarManager carManager;
    @Autowired
    private SorderManager sorderManager;
    @Autowired
    private RegionManager regionManager;


    /**
     * 管理员接口 - 用户管理
     */
    @Override
    public PageResult<User> getAllUser(PageRequestDTO pageRequestDTO) {
        //分页条件查询
        Page<User> userPage = userManager.selectPage(
                initPage(pageRequestDTO),
                conditionAdapter(pageRequestDTO));

        return new PageResult<>(pageRequestDTO.getPageSize(),
                pageRequestDTO.getPageCurrent(),
                (int) userPage.getTotal(),
                userPage.getRecords());
    }

    @Override
    public Integer addUser(User user) {
        return userManager.insert(user);
    }

    @Override
    public Integer updateUser(User user) {
        return userManager.updateById(user);
    }

    @Override
    public Integer deleteUser(User user) {
        return userManager.deleteById(user.getUserId());
    }

    /**
     * 管理员接口 - 订单管理
     */
    @Override
    public PageResult<Sorder> getAllSorder(PageRequestDTO pageRequestDTO) {
        //分页条件查询
        Page<Sorder> sorderPage = sorderManager.selectPage(
                initPage(pageRequestDTO),
                conditionAdapter(pageRequestDTO));

        return new PageResult<>(pageRequestDTO.getPageSize(),
                pageRequestDTO.getPageCurrent(),
                (int) sorderPage.getTotal(),
                sorderPage.getRecords());
    }

    @Override
    public Integer updateSorder(Sorder sorder) {
        return sorderManager.updateById(sorder);
    }

    @Override
    public Integer deleteSorder(Sorder sorder) {
        return sorderManager.deleteById(sorder.getOrderId());
    }


    /**
     * 管理员接口 - 区域管理 - 配置区域内的快递员和车辆
     */
    @Override
    public Integer addRegionTransCar(RegionTransCar regionTransCar) {
        if (null == regionTransCar.getRegionId() || 0L == regionTransCar.getRegionId()) {
            log.error("AdminServiceImpl - addRegionTransCar -> 区域管理的区域id为空");
            return null;
        }
        int row = 0;
        if (null != regionTransCar.getRegionCarId() && 0L == regionTransCar.getRegionCarId()) {
            Car car = new Car();
            car.setCarId(regionTransCar.getRegionCarId());
            car.setCarRegionId(regionTransCar.getRegionId());
            row = carManager.updateById(car);

            if (1 != row) {
                log.error("AdminServiceImpl - addRegionTransCar -> 区域管理的新增车辆失败");
                return null;
            }
        }
        if (null != regionTransCar.getRegionTransId() && 0L == regionTransCar.getRegionTransId()) {
            User trans = new User();
            trans.setUserId(regionTransCar.getRegionTransId());
            trans.setTransRegionId(regionTransCar.getRegionId());
            row = userManager.updateById(trans);

            if (1 != row) {
                log.error("AdminServiceImpl - addRegionTransCar -> 区域管理的新增快递员失败");
                return null;
            }
        }
        return row;
    }

    @Override
    public Integer deleteRegionTransCar(RegionTransCar regionTransCar) {
        if (null == regionTransCar.getRegionId() || 0L == regionTransCar.getRegionId()) {
            log.error("AdminServiceImpl - deleteRegionTransCar -> 区域管理的区域id为空");
            return null;
        }
        int row = 0;
        if (null != regionTransCar.getRegionCarId() && 0L == regionTransCar.getRegionCarId()) {
            Car car = new Car();
            car.setCarId(regionTransCar.getRegionCarId());
            car.setCarRegionId(0L);
            row = carManager.updateById(car);

            if (1 != row) {
                log.error("AdminServiceImpl - deleteRegionTransCar -> 区域管理的删除车辆失败");
                return null;
            }
        }
        if (null != regionTransCar.getRegionTransId() && 0L == regionTransCar.getRegionTransId()) {
            User trans = new User();
            trans.setUserId(regionTransCar.getRegionTransId());
            trans.setTransRegionId(0L);
            row = userManager.updateById(trans);

            if (1 != row) {
                log.error("AdminServiceImpl - deleteRegionTransCar -> 区域管理的删除快递员失败");
                return null;
            }
        }
        return row;
    }

    @Override
    public PageResult<RegionInfo> getAllRegionInfo(PageRequestDTO pageRequestDTO) {

        Pagination page = new Page<Region>(pageRequestDTO.getPageCurrent(), pageRequestDTO.getPageSize());
        List<Long> regionIds = regionManager.getRegionIdWithCarOrTrans(page);
        //分页获取有车或人的region
        Map<Long, String> regionList = regionManager.selectList(
                new EntityWrapper<Region>()
                        .in("region_id", regionIds))
                .stream().collect(Collectors.toMap(Region::getRegionId, Region::getRegionName));

        List<RegionInfo> regionInfoList = new ArrayList<>();
        regionList.forEach((regionId, regionName) -> {
            List<Car> carList = carManager.selectList(new EntityWrapper<Car>().eq("car_region_id", regionId));
            List<User> transList = userManager.selectList(new EntityWrapper<User>().eq("trans_region_id", regionId));
            //只要分配了车或者人 就加载出来
            if (0 != carList.size() || 0 != transList.size()) {
                RegionInfo regionInfo = new RegionInfo();
                regionInfo.setRegionId(regionId);
                regionInfo.setRegionName(regionName);
                if (0 != carList.size()) {
                    regionInfo.setRegionCarList(carList);
                }
                if (0 != transList.size()) {
                    regionInfo.setRegionTransList(transList);
                }
                regionInfoList.add(regionInfo);
            }
        });

        return new PageResult<>(
                pageRequestDTO.getPageSize(),
                pageRequestDTO.getPageCurrent(),
                regionInfoList.size(),
                regionInfoList);
    }


    /**
     * 管理员接口 - 存储管理 - 对存储的车辆进行管理
     */
    @Override
    public Integer addCar(Car car) {
        return carManager.insert(car);
    }

    @Override
    public Integer deleteCar(Car car) {
        return carManager.deleteById(car.getCarId());
    }

    @Override
    public Integer updateCar(Car car) {
        return carManager.updateById(car);
    }

    @Override
    public PageResult<Car> getAllCar(PageRequestDTO pageRequestDTO) {
        //分页条件查询
        Page<Car> carPage = carManager.selectPage(
                initPage(pageRequestDTO),
                conditionAdapter(pageRequestDTO));

        return new PageResult<>(pageRequestDTO.getPageSize(),
                pageRequestDTO.getPageCurrent(),
                (int) carPage.getTotal(),
                carPage.getRecords());
    }

    /**
     * 管理员接口 - 存储配送管理 - 给订单安排运输的快递员和车辆
     */
    @Override
    public Integer arrangeSorderTransCar(Sorder sorder) {
        if (null == sorder.getOrderTransId() || null == sorder.getOrderCarId()) {
            log.error("AdminServiceImpl - arrangeSorderTransCar -> 订单安排运输的快递员和车辆为空");
            return null;
        }
        return sorderManager.updateById(sorder);
    }

    @Override
    public Integer updateSorderTransCar(Sorder sorder) {
        if (null == sorder.getOrderTransId() || null == sorder.getOrderCarId()) {
            log.error("AdminServiceImpl - updateSorderTransCar -> 订单安排运输的快递员和车辆为空");
            return null;
        }
        return sorderManager.updateById(sorder);
    }

    @Override
    public PageResult<Sorder> getAllSorderTransCar(PageRequestDTO pageRequestDTO) {

        Wrapper<Sorder> wrapper = conditionAdapter(pageRequestDTO);
        //待揽件 和 配送中 需要 安排快递员
        //wrapper.in("order_type", new Integer[]{1, 4});
        //分页条件查询
        Page<Sorder> sorderPage = sorderManager.selectPage(
                initPage(pageRequestDTO),
                wrapper);

        return new PageResult<>(pageRequestDTO.getPageSize(),
                pageRequestDTO.getPageCurrent(),
                (int) sorderPage.getTotal(),
                sorderPage.getRecords());
    }

    /**
     * 管理员接口 - 业务统一生成报表
     */
    @Override
    public PageResult<Sorder> getBusinessReport(PageRequestDTO pageRequestDTO) {
        return null;
    }
}
