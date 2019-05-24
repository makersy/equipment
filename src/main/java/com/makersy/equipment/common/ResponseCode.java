package com.makersy.equipment.common;

/*
* 响应状态码
* */
public enum ResponseCode {
    NEED_LOGIN(0, "NEED_LOGIN"),  //未登录
    SUCCESS(1, "SUCCESS"), //登录成功
    NOT_EXIST(2, "NOT_EXIST"),  //用户不存在
    ERROR(3, "ERROR");  //密码错误
    //    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT"),  //非法

    private final int code;     //状态码
    private final String desc;  //描述

    ResponseCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }
}
