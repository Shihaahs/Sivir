
package com.sxx.sivir.core.dal.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sxx.sivir.core.common.base.BaseDao;
import com.sxx.sivir.core.common.page.PageResult;
import com.sxx.sivir.core.dal.domain.Region;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RegionDao extends BaseDao<Region> {

    /**
     * 获取已分配车辆或快递员的地区id
     * @param page 分页参数
     * @return 地区ids
     *
     *     @Select("SELECT car_region_id as region_id FROM car where car_region_id != 0 UNION SELECT trans_region_id as region_id FROM user where trans_region_id != 0;")
     */
    List<Long>  getRegionIdWithCarOrTrans(Pagination page);
}
