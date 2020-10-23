package com.lyn.juc.jmm.lock;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * 创建国家枚举类
 */
public enum CountryEnum {
    ONE(1,"齐国"),TWO(2,"楚国"),THREE(3,"燕国"),FOUR(4,"韩国"),FIVE(5,"赵国"),SIX(6,"魏国");
    private Integer code;
    private String message;
    CountryEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static CountryEnum getCountry(int number){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum countryEnum : values) {
            if(countryEnum.code == number){
                return countryEnum;
            }
        }
        return null;
    }
}
