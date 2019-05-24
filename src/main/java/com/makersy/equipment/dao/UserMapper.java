package com.makersy.equipment.dao;

import com.makersy.equipment.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username); //判断是否存在

    User selectLogin(@Param("username")String username, @Param("password")String password);  //验证账号密码，若命中，返回命中用户，否则返回null

    void updateStateByPrimaryKey(@Param("userId") Integer userId, @Param("state") Integer state);  //改变用户登录状态

    Integer checkOnline(@Param("userId") int userId);

    List<User> selectAllUser();
}