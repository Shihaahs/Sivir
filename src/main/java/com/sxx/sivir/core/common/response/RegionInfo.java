package com.sxx.sivir.core.common.response;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sxx.sivir.core.dal.domain.Car;
import com.sxx.sivir.core.dal.domain.User;
import lombok.Data;

import java.util.Date;
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
     * 0-快递员， 1-车
     */
    private Integer regionContentType;
    /**
     * 快递员/车辆 id
     */
    private Long regionContentId;
    /**
     * 快递员/车辆
     */
    private String regionContent;
    /**
     * 快递员/车辆状态
     */
    private String regionContentInfo;


    private Long regionId;

    private String regionName;


    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

}
