package com.makersy.equipment.service;

import com.makersy.equipment.common.ServerResponse;
import com.makersy.equipment.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by makersy on 2019
 */

public interface IUserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse logout(int userId);

    ServerResponse regist(String username, String password);

    ServerResponse checkValid(String username);

    ServerResponse updateUserInfomation(User user);

    ServerResponse addUser(User user);

    ServerResponse deleteUser(String userId);

    ServerResponse<List<User>> selectAllUser();
}
