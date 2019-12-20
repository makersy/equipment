package com.makersy.equipment.controller.manage;

import com.makersy.equipment.common.Const;
import com.makersy.equipment.common.ResponseCode;
import com.makersy.equipment.common.ServerResponse;
import com.makersy.equipment.pojo.Dev;
import com.makersy.equipment.pojo.User;
import com.makersy.equipment.service.IUserService;
import com.makersy.equipment.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by makersy on 2019
 */

@Controller
@RequestMapping("/manage/")
@Slf4j
public class AdminController{

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "adminlogin.do")
    public void adminLogin(HttpServletRequest request, HttpServletResponse response){
        try {
            request.getRequestDispatcher(Const.BASEPATH + "/admin/adminLogin.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员登录
     * @param username
     * @param password
     * @param session
     * @param
     * @param
     * @return
     */
    @RequestMapping("login.do")
    public void login(@Param("username") String username, @Param("password") String password, HttpSession session, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        ServerResponse<User> response = iUserService.login(username, password);
        int state = -1;
        if (response.isSuccess()) {
            //账号存在且密码正确
            User user = response.getData();
            if (user.getUserPriority() == Const.Priority.ADMIN) {
                //确认是管理员，登录成功
                session.setAttribute(Const.CURRENT_USER, user);
                request.removeAttribute("state");
                try {
                    request.getRequestDispatcher(Const.BASEPATH + "/admin/manageDev.jsp").forward(request, httpServletResponse);
                    return;
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                //不是管理员
                state = 2;
            }
        } else {
            if (response.getStatus() == ResponseCode.ERROR.getCode()) {
                //密码错误
                state = 0;
            } else if (response.getStatus() == ResponseCode.NOT_EXIST.getCode()) {
                //不存在此用户
                state = 1;
            }
        }
        //登录失败就返回管理员登录页面
        request.setAttribute("state", state);
        try {
            request.getRequestDispatcher(Const.BASEPATH + "/admin/adminLogin.jsp").forward(request, httpServletResponse);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "add_user.do")
    @ResponseBody
    public ServerResponse addUser(@RequestBody User user){
        User user1 = new User();
        user1.setUserAccount(user.getUserAccount());
        user1.setUserPassword(user.getUserPassword());
        return iUserService.addUser(user);
    }

    @RequestMapping(value = "adduser.do")
    public void adduser(HttpServletRequest request, HttpServletResponse response){
        try {
            request.getRequestDispatcher(Const.BASEPATH + "/admin/addUser.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "show_all_user.do", method = RequestMethod.POST)
    public void selectAllUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        ServerResponse<List<User>> response1 = iUserService.selectAllUser();

        //将对象转换为json字符串
        List<User> userList = response1.getData();
        String json = JsonUtil.list2string(userList);
        try {
            System.out.println(json);
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "delete_user.do")
    @ResponseBody
    public ServerResponse deleteUser(@RequestParam("userId") String userId){
        return iUserService.deleteUser(userId);
    }

    @RequestMapping(value = "deluser.do")
    public void deluser(HttpServletRequest request, HttpServletResponse response){
        try {
            request.getRequestDispatcher(Const.BASEPATH + "/admin/deleteUser.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}