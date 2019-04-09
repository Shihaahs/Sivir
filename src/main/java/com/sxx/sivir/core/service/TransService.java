package com.sxx.sivir.core.service;

import com.sxx.sivir.core.common.page.PageResult;
import com.sxx.sivir.core.common.request.PageRequestDTO;
import com.sxx.sivir.core.dal.domain.Sorder;

/**
 * @Package com.sxx.sivir.core.service
 * @Author: 尚星
 * @Date: 2019/4/9 16:30
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description: 快递员业务逻辑
 */

public interface TransService {

    /**
     * <p> 根据当前登录用户id获取相应的取件单 </p>
     * @param pageRequestDTO 分页参数
     * @return PageResult<Sorder>
     * @date 2019/4/9 16:35
     *
     */
    PageResult<Sorder> getReceivedSorderByUserId(PageRequestDTO pageRequestDTO);

    /**
     * <p> 更新取件单状态 </p>
     * @param sorder 取件单状态
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    Integer updateReceivedSorder(Sorder sorder);


    /**
     * <p> 根据当前登录用户id获取相应的派送单 </p>
     * @param pageRequestDTO 分页参数
     * @return PageResult<Sorder>
     * @date 2019/4/9 16:35
     *
     */
    PageResult<Sorder> getSendSorderByUserId(PageRequestDTO pageRequestDTO);

    /**
     * <p> 更新派送单状态 </p>
     * @param sorder 派送单状态
     * @return Integer
     * @date 2019/4/9 16:35
     *
     */
    Integer updateSendSorder(Sorder sorder);

}
