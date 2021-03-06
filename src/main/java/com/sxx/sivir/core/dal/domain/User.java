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
public class User extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    /**
     * 用户名称
     */
    @TableField("user_name")
    private String userName;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 登录手机
     */
    private String phone;
    /**
     * 角色权限，0-管理员，1-快递员，2-客户
     */
    private Integer permission;
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

}
