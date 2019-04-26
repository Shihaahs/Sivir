package com.sxx.sivir.core.common.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Package com.sxx.sivir.core.common.enums
 * @Author: 尚星
 * @Date: 2019/4/18 10:48
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description: 车类型枚举
 * 0-在库， 1-在途， 2-维修中， 3-待分配， 9-作废
 */
public enum CarTypeEnum {

    IN_WAREHOUSE(0,"在库"),
    ON_WAY(1,"在途"),
    REPAIRING(2,"维修中"),
    WAIT_ALLOCATION(3,"待分配"),
    OBSOLETED(9,"作废");


    @Getter
    private Integer code;
    @Getter
    private String desc;

    CarTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(Integer code){
        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(code))
                .map(CarTypeEnum::getDesc)
                .findFirst()
                .orElse("");
    }

}
