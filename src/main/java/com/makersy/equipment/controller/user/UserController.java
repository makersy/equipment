package com.makersy.equipment.controller.user;

import com.makersy.equipment.common.Const;
import com.makersy.equipment.common.LoginCache;
import com.makersy.equipment.common.ResponseCode;
import com.makersy.equipment.common.ServerResponse;
import com.makersy.equipment.pojo.User;
import com.makersy.equipment.service.IUserService;
import com.makersy.equipment.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by makersy on 2019
 */

@Controller
@RequestMapping("/user/")
@Slf4j
public class UserController {

    @Autowired
    private IUserService iUserService;


    //用户登录
    @RequestMapping("login.do")
    public void login(@Param("username") String username, @Param("password") String password, HttpSession session, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ServerResponse<User> response = iUserService.login(username, password);

        int state = -1;
        if (response.isSuccess()) {
            //登录成功，重定向到成功后页面
            User user = response.getData();
            session.setAttribute(Const.CURRENT_USER, user);
            //向缓存中放入当前用户对应的session
            LoginCache.getInstance().setSessionIdByUsername(user.getUserAccount(), session.getId());
            LoginCache.getInstance().setSessionBySessionId(session.getId(), session);

            try {
                httpServletRequest.getRequestDispatcher(Const.BASEPATH + "/user/userPage.jsp").forward(httpServletRequest, httpServletResponse);
                return;
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            if (response.getStatus() == ResponseCode.ERROR.getCode()) {
                //密码错误
                state = 0;
            } else if (response.getStatus() == ResponseCode.NOT_EXIST.getCode()) {
                //用户名不存在
                state = 1;
            }
        }
        //登录失败，转发到主页面，附上状态码
        try {
            httpServletRequest.getRequestDispatcher("/index.jsp?state="+state).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
//        return response;
    }

    //用户登出
    @RequestMapping("logout.do")
    public void logout(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        iUserService.logout(user.getUserId());
        session.removeAttribute(Const.CURRENT_USER);
        session.invalidate();
    }

    /**
     * 转发接口，将主页面转发至注册页面
     * @param request
     * @param response
     */
    @RequestMapping("registforward.do")
    public void loginForward(HttpServletRequest request, HttpServletResponse response){
        try {
            request.getRequestDispatcher(Const.BASEPATH + "/user/userRegist.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户注册接口，只允许post方式
     * @param username
     * @param password
     * @param request
     * @param httpServletResponse
     * @throws IOException
     */
    @RequestMapping(value = "regist.do", method = RequestMethod.POST)
    public void regist(@Param("username") String username, @Param("password")String password, HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        ServerResponse response = iUserService.regist(username, password);
        if (response.isSuccess()) {
            //注册成功，当前页面弹框提示，并重定向至登录页面

            try {
    //                httpServletResponse.sendRedirect(request.getContextPath() + "/index.jsp");
                request.setAttribute("flag", 1);
                request.getRequestDispatcher(Const.BASEPATH + "/user/userRegist.jsp").forward(request, httpServletResponse);
                return;
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        }

        //注册失败，设置状态码，向前端提示信息
        request.setAttribute("flag", 0);
        try {
            request.getRequestDispatcher(Const.BASEPATH + "/user/userRegist.jsp").forward(request, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
