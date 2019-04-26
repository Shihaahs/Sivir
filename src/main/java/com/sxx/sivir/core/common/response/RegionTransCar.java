package com.sxx.sivir.core.common.response;

import com.sxx.sivir.core.dal.domain.Car;
import com.sxx.sivir.core.dal.domain.User;
import lombok.Data;

import java.util.List;

/**
 * @Package com.sxx.sivir.core.common.response
 * @Author: 尚星
 * @Date: 2019/4/10 09:41
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description: 存放区域车辆和快递员
 */

@Data
public class RegionTransCar {

    /**
     * 快递员id、
     */
    private Long regionTransId;
    /**
     * 车辆id
     */
    private Long regionCarId;


    private Long regionId;



}
