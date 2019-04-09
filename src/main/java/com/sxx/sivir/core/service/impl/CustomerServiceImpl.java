package com.sxx.sivir.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sxx.sivir.core.common.page.PageResult;
import com.sxx.sivir.core.common.request.PageRequestDTO;
import com.sxx.sivir.core.dal.domain.Sorder;
import com.sxx.sivir.core.dal.manager.SorderManager;
import com.sxx.sivir.core.service.CustomerService;
import com.sxx.sivir.web.session.SessionUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.sxx.sivir.core.common.page.PageQuery.conditionAdapter;
import static com.sxx.sivir.core.common.page.PageQuery.initPage;
import static java.util.stream.Collectors.toList;

/**
 * @Package com.sxx.sivir.core.service.impl
 * @Author: 尚星
 * @Date: 2019/4/9 16:31
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description:
 */

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    SessionUser sessionUser;
    @Autowired
    private SorderManager sorderManager;

    @Override
    public PageResult<Sorder> getAllSorderPageByUserId(PageRequestDTO pageRequestDTO) {
        Assert.notNull(pageRequestDTO,"CustomerServiceImpl - getAllSorderPageByUserId -> 分页参数为空");

        //用户只能看到自己的订单
        List<Long> orderIds = sorderManager.selectList(
                new EntityWrapper<Sorder>()
                        .eq("order_sender_id", pageRequestDTO.getUserId()))
                .stream().map(Sorder::getOrderId).collect(toList());
        Wrapper<Sorder> wrapper = conditionAdapter(pageRequestDTO);
        wrapper.in("order_id", orderIds);
        //分页查询
        Page<Sorder> productPage = sorderManager.selectPage(
                initPage(pageRequestDTO),
                conditionAdapter(pageRequestDTO));

        return new PageResult<>(pageRequestDTO.getPageSize(),
                pageRequestDTO.getPageCurrent(),
                (int) productPage.getTotal(),
                productPage.getRecords());
    }

    @Override
    public Integer addSorder(Sorder sorder) {
        Assert.notNull(sorder,"CustomerServiceImpl - addSorder -> 订单参数为空");
        //参数校验

        return sorderManager.insert(sorder);
    }

    @Override
    public Integer cancelSorderBySorderId(Sorder sorder) {
        Assert.notNull(sorder,"CustomerServiceImpl - cancelSorderBySorderId -> 订单参数为空");
        if (null == sorder.getOrderId() || 0L == sorder.getOrderId()) {
            log.error("CustomerServiceImpl - cancelSorderBySorderId -> 订单id为空");
            return null;
        }
        return sorderManager.deleteById(sorder.getOrderId());
    }
}
