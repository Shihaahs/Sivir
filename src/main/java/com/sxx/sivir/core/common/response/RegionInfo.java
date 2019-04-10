package com.sxx.sivir.core.common.response;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.sxx.sivir.core.dal.domain.Car;
import com.sxx.sivir.core.dal.domain.User;
import lombok.Data;

import java.util.List;

/**
 * @Package com.sxx.sivir.core.common.response
 * @Author: 尚星
 * @Date: 2019/4/10 09:41
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description: 存放区域车辆和快递员信息
 */

@Data
public class RegionInfo {

    /**
     * 快递员信息
     */
    private List<User> regionTransList;
    /**
     * 车辆信息
     */
    private List<Car> regionCarList;
    /**
     * 车辆信息
     */
    private Long regionId;
    /**
     * 车辆信息
     */
    private String regionName;
    /**
     * 1-省， 2-市， 3-区
     */
    private Integer regionType;



}
