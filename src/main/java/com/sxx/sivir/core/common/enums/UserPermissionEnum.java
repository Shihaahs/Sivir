package com.sxx.sivir.core.common.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Package com.sxx.sivir.core.common.enums
 * @Author: 尚星
 * @Date: 2019/4/18 10:48
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description: 车类型枚举
 * 0-管理员， 1-快递员， 2-客户
 */
public enum UserPermissionEnum {

    ADMIN(0,"管理员"),
    TRANS(1,"快递员"),
    CUSTOMER(2,"客户");


    @Getter
    private Integer code;
    @Getter
    private String desc;

    UserPermissionEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(Long code){
        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(code))
                .map(UserPermissionEnum::getDesc)
                .findFirst()
                .orElse("");
    }

}
