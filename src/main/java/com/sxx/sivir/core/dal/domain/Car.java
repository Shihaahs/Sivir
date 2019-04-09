package com.sxx.sivir.core.dal.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sxx.sivir.core.common.base.BaseModel;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Car extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 主键，自增长
     */
    @TableId(value = "car_id", type = IdType.AUTO)
    private Long carId;
    /**
     * 车牌号
     */
    @TableField("car_no")
    private String carNo;
    /**
     * 车所在的地区
     */
    @TableField("car_region_name")
    private String carRegionName;
    /**
     * 车所在的地区id
     */
    @TableField("car_region_id")
    private Long carRegionId;
    /**
     * 车状态；0-在库，1-在途，9-作废
     */
    @TableField("car_type")
    private Integer carType;
    /**
     * 逻辑删除，0-存在，1-已被删除
     */
    @TableLogic
    @TableField("is_delete")
    private Integer isDelete;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("gmt_create")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("gmt_modified")
    private Date gmtModified;

}
