package com.makersy.equipment.util;




import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by makersy on 2019
 */

//Json工具类
public class JsonUtil {

    //注意要初始化
    private static ObjectMapper objectMapper = new ObjectMapper();

    //将对象集合转化为json字符串
    public static <T> String list2string(List<T> list) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    //将单个对象转化为json字符串
    public static <T> String obj2string(T obj) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}
