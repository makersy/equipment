package com.makersy.equipment.common;

/**
 * Created by makersy on 2019
 */

public class Const {

    public static final String CURRENT_USER = "currentUser";
    public static final String USERNAME = "username";
    public static final String BASEPATH = "/WEB-INF/view";

    public enum State {
        OFFLINE(0),  //不在线 -- 0
        ONLINE(1),  //在线 -- 1
        NOT_EXIST(2);  //不存在此用户 -- 2

        private int code;

        State(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public interface Priority{
        int ADMIN = 0;
        int USER = 1;
    }

}
