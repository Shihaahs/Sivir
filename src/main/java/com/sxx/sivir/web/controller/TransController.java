package com.sxx.sivir.web.controller;

import com.sxx.sivir.core.common.entity.APIResult;
import com.sxx.sivir.core.common.page.PageResult;
import com.sxx.sivir.core.common.request.PageRequestDTO;
import com.sxx.sivir.core.dal.domain.Sorder;
import com.sxx.sivir.core.service.TransService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.sxx.sivir.core.common.constant.SivirURL.*;

/**
 * @Package com.sxx.sivir.web.controller
 * @Author: 尚星
 * @Date: 2019/4/9 16:23
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description: 快递员业务入口
 */

@Slf4j
@Controller
public class TransController {

    @Autowired
    private TransService transService;

    @ResponseBody
    @RequestMapping(value = TRANS_GET_RECEIVED_SORDER, method = RequestMethod.POST)
    public APIResult<PageResult<Sorder>> getReceivedSorder(PageRequestDTO pageRequestDTO){
        return APIResult.ok(transService.getReceivedSorderByUserId(pageRequestDTO));
    }

    @ResponseBody
    @RequestMapping(value = TRANS_UPDATE_RECEIVED_SORDER_INFO, method = RequestMethod.POST)
    public APIResult<Integer> updateReceivedSorder(Sorder sorder){
        return APIResult.ok(transService.updateReceivedSorder(sorder));
    }

    @ResponseBody
    @RequestMapping(value = TRANS_GET_SEND_SORDER, method = RequestMethod.POST)
    public APIResult<PageResult<Sorder>> getSendSorder(PageRequestDTO pageRequestDTO){
        return APIResult.ok(transService.getSendSorderByUserId(pageRequestDTO));
    }

    @ResponseBody
    @RequestMapping(value = TRANS_UPDATE_SEND_SORDER_INFO, method = RequestMethod.POST)
    public APIResult<Integer> updateSendSorder(Sorder sorder){
        return APIResult.ok(transService.updateSendSorder(sorder));
    }

    
}
