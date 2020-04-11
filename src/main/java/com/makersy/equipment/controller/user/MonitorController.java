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
@Slf4j
public class MonitorController {

    @RequestMapping("/lbs.do")
    @ResponseBody
    public String forwardToLBSApi(@Param("lac") String lac, @Param("ci") String ci) {

        String url = "http://api.cellocation.com:81/cell/";
        Map<String, String> params = new HashMap<>();

        params.put("mcc", "460");
        params.put("mnc", "1");

        params.put("lac", lac);
        params.put("ci", ci);

        HttpHeaders headers = new HttpHeaders();
        RestTemplate template = new RestTemplate();

        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class, params);
        return response.getBody();
    }
}
