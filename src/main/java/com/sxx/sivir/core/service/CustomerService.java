package com.sxx.sivir.core.service;

import com.sxx.sivir.core.common.page.PageResult;
import com.sxx.sivir.core.common.request.PageRequestDTO;
import com.sxx.sivir.core.dal.domain.Sorder;
import com.sxx.sivir.core.dal.domain.User;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @Package com.sxx.sivir.core.service
 * @Author: 尚星
 * @Date: 2019/4/9 16:30
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description: 客户业务逻辑
 */
public interface CustomerService {

    /**
     * <p> 根据当前登录用户id获取相应的订单 </p>
     * @param pageRequestDTO 分页参数
     * @return PageResult<Sorder>
     * @date 2019/4/9 16:35
     *
     */
    PageResult<Sorder> getAllSorderPageByUserId(PageRequestDTO pageRequestDTO);

    /**
     * <p> 新增订单 </p>
     * @param sorder 订单详细信息
     * @return PageResult<Sorder>
     * @date 2019/4/9 16:35
     *
     */
    Integer addSorder(Sorder sorder);

    /**
     * <p> 根据订单id取消订单 </p>
     * @param sorder 订单编号
     * @return PageResult<Sorder>
     * @date 2019/4/9 16:35
     *
     */
    Integer cancelSorderBySorderId(Sorder sorder);
}
