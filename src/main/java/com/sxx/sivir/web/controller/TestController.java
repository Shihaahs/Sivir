package com.sxx.sivir.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sxx.sivir.core.common.entity.APIResult;
import com.sxx.sivir.core.common.page.PageResult;
import com.sxx.sivir.core.common.request.PageRequestDTO;
import com.sxx.sivir.core.common.response.RegionInfo;
import com.sxx.sivir.core.dal.domain.User;
import com.sxx.sivir.core.dal.manager.UserManager;
import com.sxx.sivir.core.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private UserManager userManager;
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/user.json", method = RequestMethod.GET)
    public APIResult<List<User>> testInitDal(){
        log.info("初始化项目测试");
        return APIResult.ok(userManager.selectList(new EntityWrapper<>()));
    }

    @RequestMapping(value = "/region.json", method = RequestMethod.POST)
    public APIResult<PageResult<RegionInfo>> testRegionSQL(){
        log.info("测试sql");
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setPageSize(1);
        pageRequestDTO.setPageCurrent(1);
        return APIResult.ok(adminService.getAllRegionInfo(pageRequestDTO));
    }

}
