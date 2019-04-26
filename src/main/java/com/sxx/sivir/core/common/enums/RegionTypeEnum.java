package com.sxx.sivir.core.common.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Package com.sxx.sivir.core.common.enums
 * @Author: 尚星
 * @Date: 2019/4/18 10:48
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description: 地区类型枚举
 * 1-省， 2-市， 3-区
 */
public enum RegionTypeEnum {

    PROVINCE(1,"省"),
    CITY(2,"市"),
    COUNTRY(3,"区");


    @Getter
    private Integer code;
    @Getter
    private String desc;

    RegionTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(Integer code){
        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(code))
                .map(RegionTypeEnum::getDesc)
                .findFirst()
                .orElse("");
    }

}
