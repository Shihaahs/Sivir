package com.sxx.sivir.core.dal.manager.impl;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sxx.sivir.core.common.base.BaseManagerImpl;
import com.sxx.sivir.core.dal.domain.Region;
import com.sxx.sivir.core.dal.dao.RegionDao;
import com.sxx.sivir.core.dal.manager.RegionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegionManagerImpl extends BaseManagerImpl<RegionDao, Region> implements RegionManager{

    @Autowired
    private RegionDao regionDao;

    @Override
    public List<Long> getRegionIdWithCarOrTrans(Pagination page) {
        return regionDao.getRegionIdWithCarOrTrans(page);

    }
}
