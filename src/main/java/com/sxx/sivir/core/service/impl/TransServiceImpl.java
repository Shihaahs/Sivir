package com.sxx.sivir.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sxx.sivir.core.common.enums.OrderTypeEnum;
import com.sxx.sivir.core.common.page.PageResult;
import com.sxx.sivir.core.common.request.PageRequestDTO;
import com.sxx.sivir.core.dal.domain.Sorder;
import com.sxx.sivir.core.dal.manager.SorderManager;
import com.sxx.sivir.core.service.TransService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
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
public class TransServiceImpl implements TransService {

    @Autowired
    private SorderManager sorderManager;

    @Override
    public PageResult<Sorder> getReceivedSorderByUserId(PageRequestDTO pageRequestDTO) {
        //派送单
        //快递员只能看见系统分配给他的单
        List<Long> orderIds = sorderManager.selectList(
                new EntityWrapper<Sorder>()
                        .eq("order_trans_id", pageRequestDTO.getUserId()))
                .stream().map(Sorder::getOrderId).collect(toList());

        if (orderIds.size() == 0) {
            log.info("快递员【" + pageRequestDTO.getUserId() + "】 - 没有派送单");
            return new PageResult<>(pageRequestDTO.getPageSize(),
                    pageRequestDTO.getPageCurrent(),
                    0,
                    Collections.emptyList());
        }
        Wrapper<Sorder> wrapper = conditionAdapter(pageRequestDTO);
        wrapper.in("order_id", orderIds);

        //快递已通知快递员
        wrapper.eq("order_type", 4);

        //分页查询
        Page<Sorder> productPage = sorderManager.selectPage(
                initPage(pageRequestDTO),
                wrapper);

        return new PageResult<>(pageRequestDTO.getPageSize(),
                pageRequestDTO.getPageCurrent(),
                (int) productPage.getTotal(),
                productPage.getRecords());
    }

    @Override
    public Integer updateReceivedSorder(Sorder sorder) {
        if (null == sorder.getOrderId() || 0L == sorder.getOrderId()
                || null == sorder.getOrderType() ) {
            log.error("TransServiceImpl - updateReceivedSorder -> 订单id为空");
            return null;
        }
        if (sorder.getOrderType().equals(OrderTypeEnum.NOTICE_TRANS.getCode())) {
            sorder.setOrderType(OrderTypeEnum.HAS_GOTTEN.getCode());
        }
        if (sorder.getOrderType().equals(OrderTypeEnum.SENDING.getCode())) {
            sorder.setOrderType(OrderTypeEnum.DONE.getCode());
        }

        int row = sorderManager.updateById(sorder);
        if (row == 1 && sorder.getOrderType().equals(OrderTypeEnum.HAS_GOTTEN.getCode())) {
            log.info("订单 id = " + sorder.getOrderId() + " 已揽件");
        }else if (row == 1 && sorder.getOrderType().equals(OrderTypeEnum.DONE.getCode())) {
            log.info("订单 id = " + sorder.getOrderId() + " 已完成");
        } else {
            log.error("订单 id = " + sorder.getOrderId() + " 状态更新失败");
        }
        return row;
    }

    @Override
    public PageResult<Sorder> getSendSorderByUserId(PageRequestDTO pageRequestDTO) {
        //取件单
        //快递员只能看见系统分配给他的单
        List<Long> orderIds = sorderManager.selectList(
                new EntityWrapper<Sorder>()
                        .eq("order_trans_id", pageRequestDTO.getUserId()))
                .stream().map(Sorder::getOrderId).collect(toList());
        if (orderIds.size() == 0) {
            log.info("快递员【" + pageRequestDTO.getUserId() + "】 - 没有取件单");
            return new PageResult<>(pageRequestDTO.getPageSize(),
                    pageRequestDTO.getPageCurrent(),
                    0,
                    Collections.emptyList());
        }
        Wrapper<Sorder> wrapper = conditionAdapter(pageRequestDTO);
        wrapper.in("order_id", orderIds);

        //快递已通知快递员
        wrapper.eq("order_type", 1);

        //分页查询
        Page<Sorder> productPage = sorderManager.selectPage(
                initPage(pageRequestDTO),
                wrapper);

        return new PageResult<>(pageRequestDTO.getPageSize(),
                pageRequestDTO.getPageCurrent(),
                (int) productPage.getTotal(),
                productPage.getRecords());
    }

    @Override
    public Integer updateSendSorder(Sorder sorder) {
        if (null == sorder.getOrderId() || 0L == sorder.getOrderId()
                || null == sorder.getOrderType() ) {
            log.error("TransServiceImpl - updateSendSorder -> 订单id为空");
            return null;
        }
        return sorderManager.updateById(sorder);
    }
}
