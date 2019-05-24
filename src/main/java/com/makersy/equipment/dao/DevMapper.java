package com.makersy.equipment.dao;

import com.makersy.equipment.pojo.Dev;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DevMapper {
    int deleteByPrimaryKey(String devMac);

    int insert(Dev record);

    int insertSelective(Dev record);

    Dev selectByPrimaryKey(String devMac);

    int updateByPrimaryKeySelective(Dev record);

    int updateByPrimaryKey(Dev record);

    int checkMac(@Param("mac") String mac);

    List<Dev> selectAllDev();
}