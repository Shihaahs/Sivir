package com.sxx.sivir.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sxx.sivir.core.common.entity.APIResult;
import com.sxx.sivir.core.dal.domain.User;
import com.sxx.sivir.core.dal.manager.UserManager;
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

    @RequestMapping(value = "/user.json", method = RequestMethod.GET)
    public APIResult<List<User>> testInitDal(){
        log.info("初始化项目测试");
        return APIResult.ok(userManager.selectList(new EntityWrapper<>()));
    }

}
