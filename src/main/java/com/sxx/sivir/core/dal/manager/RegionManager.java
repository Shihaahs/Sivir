package com.sxx.sivir.core.dal.manager;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sxx.sivir.core.common.base.BaseManager;
import com.sxx.sivir.core.dal.domain.Region;

import java.util.List;

public interface RegionManager extends BaseManager<Region> {


    /**
     * 获取已分配车辆或快递员的地区id
     * @param page 分页参数
     * @return 地区ids
     */
    List<Long> getRegionIdWithCarOrTrans(Pagination page);

    String getAllRegionNameByRegionId(Long regionId);
}
