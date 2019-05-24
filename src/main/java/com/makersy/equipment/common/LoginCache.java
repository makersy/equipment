package com.makersy.equipment.common;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by makersy on 2019
 */

public class LoginCache {

    private HashMap<String, String> loginUsers = Maps.newHashMap();  //存储 用户名,sessionId 键值对
    private HashMap<String, HttpSession> loginSessions = Maps.newHashMap();   //存储 sessionId，session 键值对

    private static class InstanceFactory {
        private static LoginCache instance = new LoginCache();
    }

    public static LoginCache getInstance() {
        return InstanceFactory.instance;
    }

    public HttpSession getSession(String sessionId) {
        return loginSessions.get(sessionId);
    }

    public String getSessionId(String username) {
        return loginUsers.get(username);
    }

    public void setSessionBySessionId(String sessionId, HttpSession session) {
        loginSessions.put(sessionId, session);
    }

    public void setSessionIdByUsername(String username, String sessionId) {
        loginUsers.put(username, sessionId);
    }

}
