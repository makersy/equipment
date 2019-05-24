package com.makersy.equipment.controller.manage;

import com.makersy.equipment.common.Const;
import com.makersy.equipment.common.ServerResponse;
import com.makersy.equipment.dao.DevMapper;
import com.makersy.equipment.pojo.Dev;
import com.makersy.equipment.pojo.User;
import com.makersy.equipment.service.IDevService;
import com.makersy.equipment.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by makersy on 2019
 */

@Controller
@RequestMapping("/manage/")
@Slf4j
public class DevController {

    @Autowired
    private IDevService iDevService;

    @RequestMapping(value = "show_all_dev.do", method = RequestMethod.POST)
    public void selectAllDev(HttpServletRequest request, HttpServletResponse response, HttpSession session) {


        ServerResponse<List<Dev>> response1 = iDevService.selectAllDev();

        //将对象转换为json字符串
        List<Dev> devList = response1.getData();
        String json = JsonUtil.list2string(devList);
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "delete_dev.do")
    @ResponseBody
    public ServerResponse deleteDev(@RequestParam("devMac") String devMac) {
        ServerResponse response = iDevService.deleteDev(devMac);
        return response;
    }

    /**
     * 获取mac值为某个值的设备信息
     * @param httpServletResponse
     * @param devMac
     */
    @RequestMapping(value = "get_dev_infor.do")
    public void showDevByMac(HttpServletResponse httpServletResponse, @RequestParam("devMac") String devMac) {
        ServerResponse<Dev> response = iDevService.getDevInfor(devMac);
        String json = null;
        if (response.isSuccess()) {
            Dev dev = response.getData();
            json = JsonUtil.obj2string(dev);
        }
        try {
            httpServletResponse.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "update_dev.do")
    @ResponseBody
    public ServerResponse updateDev(@RequestBody Dev dev) {
        Dev vo = new Dev();
        vo.setDevMac(dev.getDevMac());
        //确保输入值有意义或者是null，防止空字符串如""的出现
        if (StringUtils.isNotBlank(dev.getDevIp())) {
            vo.setDevIp(dev.getDevIp());
        }
        if (dev.getDevId()!=null) {
            vo.setDevId(dev.getDevId());
        }
        ServerResponse response = iDevService.updateDev(vo);
        return response;
    }

    @RequestMapping(value = "add_dev.do")
    @ResponseBody
    public ServerResponse addDev(@RequestBody Dev dev) {
        dev.setDevId(null);
        return iDevService.addDev(dev);
    }

    @RequestMapping("controldev.do")
    public void controldev(HttpServletRequest request, HttpServletResponse response){
        try {
            request.getRequestDispatcher(Const.BASEPATH + "/user/controlDev.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到更新页面
     */
    @RequestMapping("updatedev.do")
    public void updatedev(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "devMac") String devMac){
        try {
            request.getRequestDispatcher(Const.BASEPATH + "/admin/updateDev.jsp?devMac=" + devMac).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("managedev.do")
    public void managedev(HttpServletRequest request, HttpServletResponse response){
        try {
            request.getRequestDispatcher(Const.BASEPATH + "/admin/manageDev.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("adddev.do")
    public void adddev(HttpServletRequest request, HttpServletResponse response){
        try {
            request.getRequestDispatcher(Const.BASEPATH + "/user/addDev.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
