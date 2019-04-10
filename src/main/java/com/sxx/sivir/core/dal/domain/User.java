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

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "用户名称不能为空")
    @TableField("user_name")
    private String userName;
    /**
     * 登录密码
     */
    @NotBlank(message = "登录密码不能为空")
    private String password;
    /**
     * 登录手机
     */
    @NotBlank(message = "登录手机不能为空")
    private String phone;
    /**
     * 角色权限，0-管理员，1-快递员，2-客户
     */
    @Min(value = 0, message = "权限码错误")
    @Max(value = 2, message = "权限码错误")
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


    /**
     * 快递员所在地区id
     */
    @TableField(value = "trans_region_id")
    private Long transRegionId;

}
