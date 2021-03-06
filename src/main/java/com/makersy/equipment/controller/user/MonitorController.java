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
    public String forwardToLBSApi(@Param("mcc") String mcc, @Param("mnc") String mnc, @Param("lac") String lac, @Param("ci") String ci) {

        String baseURL = "http://api.cellocation.com:81/cell/";
//        Map<String, String> params = new HashMap<>();

        StringBuilder sb = new StringBuilder(baseURL);

        sb.append("?mcc=").append(mcc)
                .append("&mnc=").append(mnc)
                .append("&lac=").append(lac)
                .append("&ci=").append(ci)
                .append("&output=json");

//        params.put("mcc", mcc);
//        params.put("mnc", mnc);
//
//        params.put("lac", lac);
//        params.put("ci", ci);
//        params.put("output", "json");

        String url = sb.toString();
        log.info(url);

//        HttpHeaders headers = new HttpHeaders();
        RestTemplate template = new RestTemplate();

//        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class, params);


        ResponseEntity<String> response = template.getForEntity(url, String.class);
        return response.getBody();
    }
}
