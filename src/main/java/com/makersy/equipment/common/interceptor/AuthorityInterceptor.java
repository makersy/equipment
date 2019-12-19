package com.makersy.equipment.common.interceptor;


import com.makersy.equipment.common.Const;
import com.makersy.equipment.common.LoginCache;
import com.makersy.equipment.dao.UserMapper;
import com.makersy.equipment.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by makersy on 2019
 */

/**
 * mvc拦截器
 */
@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor{

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
        //请求中Controller中的方法名
        HandlerMethod handlerMethod = (HandlerMethod)handler;

        //解析HandlerMethod

        String methodName = handlerMethod.getMethod().getName();
        String className = handlerMethod.getBean().getClass().getSimpleName();



        //放行登录请求
        if(request.getRequestURI().contains("login") || request.getRequestURI().contains("regist") || request.getRequestURI().contains("logout")){
            log.info("权限拦截器拦截到请求,className:{},methodName:{}",className,methodName);
            //如果是拦截到登录请求，不打印参数，因为参数里面有密码，全部会打印到日志中，防止日志泄露
            return true;
        }



        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(Const.CURRENT_USER);

        if (user == null || (userMapper.checkOnline(user.getUserId()) != Const.State.ONLINE.getCode())) {
            //如果session中user为空，或者user不在线
            //返回false.即不会调用controller里的方法
            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                //如果是ajax请求需要特殊处理
                response.setHeader("SESSIONSTATUS", "TIMEOUT");
                response.setHeader("CONTEXTPATH", request.getContextPath() + "/index.jsp?state=2");
                // FORBIDDEN，forbidden。也就是禁止、403
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } else {
//            response.sendRedirect("/index.jsp?state=2");
                //转发到登录页面
                request.getRequestDispatcher("/index.jsp?state=2").forward(request, response);
                return false;

            }
        } else {
            //user已登录的逻辑,查看当前缓存中有无用户对应的sessionId
            String existSessionId = LoginCache.getInstance().getSessionId(user.getUserAccount());
            if ( StringUtils.isNotBlank(existSessionId)) {
                //如果当前缓存中存在此sessionId，说明用户已经登录。
                //此时判断当前session和旧sessionId是否是同一个，如果不是同一个说明不是单点登录
                if (!StringUtils.equals(existSessionId, session.getId())) {
                    //如果不是同一个
                    //此时应该让旧session失效，同时将新的session放入缓存
//                    HttpSession oldSession = LoginCache.getInstance().getSession(existSessionId);
//                    oldSession.invalidate();
                    //缓存中放入新的session
//                    LoginCache.getInstance().setSessionIdByUsername(user.getUserAccount(), session.getId());
//                    LoginCache.getInstance().setSessionBySessionId(session.getId(), session);
                    session.invalidate();
//                    request.getRequestDispatcher("/index.jsp?state=2").forward(request, response);
                } else {
                    if (request.getRequestURI().contains("deluser") || request.getRequestURI().contains("adduser")) {
                        if (user.getUserPriority() != Const.Priority.ADMIN) {
                            //不是管理员
                            request.getRequestDispatcher(Const.BASEPATH + "/admin/adminLogin.jsp?state=2").forward(request, response);
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}
