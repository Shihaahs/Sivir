package com.sxx.sivir.web.controller;

import com.sxx.sivir.core.common.entity.APIResult;
import com.sxx.sivir.core.common.page.PageResult;
import com.sxx.sivir.core.common.request.PageRequestDTO;
import com.sxx.sivir.core.dal.domain.Sorder;
import com.sxx.sivir.core.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.sxx.sivir.core.common.constant.SivirURL.*;

/**
 * @Package com.sxx.sivir.web.controller
 * @Author: 尚星
 * @Date: 2019/4/9 16:23
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description: 客户业务入口
 */

@Slf4j
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @RequestMapping(value = CUSTOMER_GET_ALL_SORDER, method = RequestMethod.POST)
    public APIResult<PageResult<Sorder>> getAllSorderByCustomerId(@RequestBody PageRequestDTO pageRequestDTO) {
        return APIResult.ok(customerService.getAllSorderPageByUserId(pageRequestDTO));
    }

    @ResponseBody
    @RequestMapping(value = CUSTOMER_ADD_SORDER, method = RequestMethod.POST)
    public APIResult<Integer> addSorder(@RequestBody Sorder sorder) {
        return APIResult.ok(customerService.addSorder(sorder));
    }

    @ResponseBody
    @RequestMapping(value = CUSTOMER_CANCEL_SORDER, method = RequestMethod.POST)
    public APIResult<Integer> cancelSorder(@RequestBody Sorder sorder) {
        return APIResult.ok(customerService.cancelSorderBySorderId(sorder));
    }


}
