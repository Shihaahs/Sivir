package com.sxx.sivir.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sxx.sivir.core.common.entity.APIResult;
import com.sxx.sivir.core.common.enums.RegionTypeEnum;
import com.sxx.sivir.core.dal.domain.Region;
import com.sxx.sivir.core.dal.manager.RegionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.sxx.sivir.core.common.constant.SivirURL.*;

/**
 * @Package com.sxx.sivir.web.controller
 * @Author: 尚星
 * @Date: 2019/4/10 10:13
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description: 公共接口
 */

@RestController
public class PublicController {

    @Autowired
    private RegionManager regionManager;

    @RequestMapping(PROVIDE_PROVINCE_DATA)
    public List<Region> provideProvinceData(){

        //提供全部省份
        List<Region> regionList = regionManager.selectList(new EntityWrapper<Region>()
                .eq("region_type", RegionTypeEnum.PROVINCE.getCode()));

        return regionList;
    }

    @RequestMapping(PROVIDE_CITY_DATA)
    public List<Region> provideCityData(@RequestBody Region region){

        //根据省id获取所含市
        List<Region> regionList = regionManager.selectList(new EntityWrapper<Region>()
                .eq("region_type", RegionTypeEnum.CITY.getCode())
                .eq("region_parent_id", region.getRegionId()));

        return regionList;
    }
    @RequestMapping(PROVIDE_COUNTRY_DATA)
    public List<Region> provideCountryData(@RequestBody Region region){

        //根据市id获取所含区或县
        List<Region> regionList = regionManager.selectList(new EntityWrapper<Region>()
                .eq("region_type", RegionTypeEnum.COUNTRY.getCode())
                .eq("region_parent_id", region.getRegionId()));

        return regionList;
    }

}
