package com.makersy.equipment.service.impl;

import com.makersy.equipment.common.Const;
import com.makersy.equipment.common.ResponseCode;
import com.makersy.equipment.common.ServerResponse;
import com.makersy.equipment.dao.UserMapper;
import com.makersy.equipment.pojo.Dev;
import com.makersy.equipment.pojo.User;
import com.makersy.equipment.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by makersy on 2019
 */

@Service("iUserService")
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录，
     *
     * @param username
     * @param password
     * @return
     */

    public ServerResponse<User> login(String username, String password) {
        int result = userMapper.checkUsername(username);
        if (result == 0) {
            //不存在此用户
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NOT_EXIST.getCode(), ResponseCode.NOT_EXIST.getDesc());
        }
        User user = userMapper.selectLogin(username, password);
        if (user == null) {
            //密码错误
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        } else {
            //密码正确，登陆成功
            user.setUserState(Const.State.ONLINE.getCode());
            user.setUserPassword(StringUtils.EMPTY); //不返回用户密码
            //把数据库中设置为在线
            userMapper.updateStateByPrimaryKey(user.getUserId(), Const.State.ONLINE.getCode());
            return ServerResponse.createBySuccess(ResponseCode.SUCCESS.getDesc(), user);
        }
    }

    /**
     * 判断用户是否在线
     *
     * @param user
     * @return
     */
    public ServerResponse checkOnline(User user) {
        int userState = userMapper.checkOnline(user.getUserId());
        if (userState == Const.State.ONLINE.getCode()) {
            //如果在线
            return ServerResponse.createBySuccess("当前在线");
        } else {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "当前未登录");
        }
    }

    /**
     * 登出
     *
     * @param userId
     * @return
     */
    public ServerResponse logout(int userId) {
        userMapper.updateStateByPrimaryKey(userId, Const.State.OFFLINE.getCode());  //更改用户登录状态值
        return ServerResponse.createBySuccess();
    }

    /**
     * 注册普通用户
     *
     * @param password
     * @return
     */
    public ServerResponse regist(String username, String password) {
        ServerResponse validResponse = checkValid(username);
        if (!validResponse.isSuccess()) {
            //如果用户已存在，或者用户名为空
            return validResponse;
        }
        User user = new User();
        user.setUserAccount(username);
        user.setUserPassword(password);
        user.setUserPriority(Const.Priority.USER);
        user.setUserState(Const.State.OFFLINE.getCode());
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }


    /**
     * 判断该用户是否存在
     *
     * @param username
     * @return
     */
    public ServerResponse checkValid(String username) {
        if (username != null) {
            //用户名不为空，判断是否存在
            if (userMapper.checkUsername(username) != 0) {
                //查询结果不为0，说明已存在
                return ServerResponse.createByErrorMessage("用户已存在");
            } else {
                //不存在
                return ServerResponse.createBySuccess("用户不存在");
            }

        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "用户名为空");
    }

    /**
     * 管理员更新用户信息，可以只更新部分字段
     *
     * @param user
     * @return
     */
    public ServerResponse updateUserInfomation(User user) {
        //不能更新密码，更新密码有特殊逻辑
        User updateUser = new User();
        updateUser.setUserPriority(user.getUserPriority());  //更新用户类型
        updateUser.setUserAccount(user.getUserAccount());  //更新权限
        int result = userMapper.updateByPrimaryKeySelective(user);
        if (result == 0) {
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccess();
    }

    /**
     * 管理员添加用户
     *
     * @param user
     * @return
     */
    public ServerResponse addUser(User user) {
        ServerResponse validResponse = checkValid(user.getUserAccount());
        if (!validResponse.isSuccess()) {
            //如果用户已存在，或者用户名为空
            return ServerResponse.createByErrorMessage("用户已存在");
        }

        user.setUserPriority(Const.Priority.USER);
        user.setUserState(Const.State.OFFLINE.getCode());
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("添加失败");
        }
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    /**
     * 管理员删除用户
     *
     * @param
     * @return
     */
    public ServerResponse deleteUser(String userId) {
        ServerResponse validResponse = checkValid(userId);

        //先判断用户是否存在
        if (!validResponse.isSuccess()) {
            //如果用户已存在，或者用户名为空
            return validResponse;
        }
        //用户存在，可以删除
        userMapper.deleteByPrimaryKey(Integer.valueOf(userId));
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @Override
    public ServerResponse<List<User>> selectAllUser() {
        List<User> userList = userMapper.selectAllUser();
        return ServerResponse.createBySuccess("获取所有用户信息成功", userList);
    }
}
