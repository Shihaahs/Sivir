package com.sxx.sivir.core.dal.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.sxx.sivir.core.common.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Region extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableField("parent_id")
    private Long parentId;

    @TableField("region_id")
    private Long regionId;

    @TableField("region_parent_id")
    private Long regionParentId;

    @TableField("region_name")
    private String regionName;
    /**
     * 1-省， 2-市， 3-区
     */
    @TableField("region_type")
    private Integer regionType;

}
