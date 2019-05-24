package com.sxx.sivir.core.dal.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sxx.sivir.core.common.base.BaseModel;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Sorder extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 主键，自增长
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;
    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 订单发送者手机
     */
    @TableField("order_sender_phone")
    private String orderSenderPhone;
    /**
     * 订单发送者
     */
    @TableField("order_sender_name")
    private String orderSenderName;
    /**
     * 订单发送地
     */
    @TableField("order_sender_position")
    private String orderSenderPosition;
    /**
     * 订单接收者手机
     */
    @TableField("order_receiver_phone")
    private Long orderReceiverPhone;
    /**
     * 订单接收者
     */
    @TableField("order_receiver_name")
    private String orderReceiverName;
    /**
     * 订单接收地
     */
    @TableField("order_receiver_position")
    private String orderReceiverPosition;
    /**
     * 订单接收地id
     */
    @TableField("order_receiver_position_id")
    private Long orderReceiverPositionId;
    /**
     * 订单快递员id
     */
    @TableField("order_trans_id")
    private Long orderTransId;
    /**
     * 订单快递员
     */
    @TableField("order_trans_name")
    private String orderTransName;
    /**
     * 订单车辆id
     */
    @TableField("order_car_id")
    private Long orderCarId;
    /**
     * 订单车牌号
     */
    @TableField("order_car_no")
    private String orderCarNo;
    /**
     * 订单位置id
     */
    @TableField("order_region_id")
    private Long orderRegionId;
    /**
     * 订单位置
     */
    @TableField("order_region_name")
    private String orderRegionName;
    /**
     * 0-待揽件，1-已通知快递员，2-快递员已揽件，3-在途，4-配送中，5-已完成，9-作废
     */
    @TableField("order_type")
    private Integer orderType;
    /**
     * 逻辑删除，0-存在，1-已被删除
     */
    @TableLogic
    @TableField("is_delete")
    private Integer isDelete;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("gmt_create")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("gmt_modified")
    private Date gmtModified;

    /**
     * 订单下单人id
     */
    @TableField("order_owner_id")
    private Long orderOwnerId;

}
