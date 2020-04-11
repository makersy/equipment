package com.makersy.equipment.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yhl
 * @date 2020/4/11
 */

@Controller
public class MonitorController {

    @RequestMapping("/lbs.do")
    @ResponseBody
    public String forwardToLBSApi(@Param("ie") String ie, @Param("wd") String wd) {

        String url = "www.baidu.com/s";
        Map<String, String> params = new HashMap<>();

        params.put("ie", ie);
        params.put("wd", wd);

        HttpHeaders headers = new HttpHeaders();
        RestTemplate template = new RestTemplate();

        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class, params);
        return response.getBody();
    }
}
