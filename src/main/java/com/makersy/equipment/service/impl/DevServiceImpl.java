package com.makersy.equipment.service.impl;


import com.makersy.equipment.common.ServerResponse;
import com.makersy.equipment.dao.DevMapper;
import com.makersy.equipment.pojo.Dev;
import com.makersy.equipment.service.IDevService;
import com.makersy.equipment.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by makersy on 2019
 */

@Service("iDevService")
public class DevServiceImpl implements IDevService {

    @Autowired
    private DevMapper devMapper;

    /**
     * 管理员：添加设备
     * @param dev
     * @return
     */
    @Override
    public ServerResponse addDev(Dev dev) {
        //需要先检查该设备是否已存在,注意mac值是主键
        ServerResponse validResponse = checkDevExists(dev.getDevMac());
        if (validResponse.isSuccess()) {
            //若mac不存在,进行添加操作
            int result = devMapper.insert(dev);
            if (result > 0) {
                return ServerResponse.createBySuccess("添加成功");
            }
        }
        return validResponse;
    }


    /**
     * 检查mac值是否存在，存在返回false，不存在返回success
     * @param devMac
     * @return
     */
    private ServerResponse checkDevExists(String devMac) {
        if (!StringUtils.isBlank(devMac)) {
            //mac值非空
            int count = devMapper.checkMac(devMac);
            if (count > 0) {
                //mac值已存在
                return ServerResponse.createByErrorMessage("mac值已存在");
            } else {
                return ServerResponse.createBySuccess();
            }
        }
        return ServerResponse.createByErrorMessage("输入mac值为空");
    }

    /*todo 删除设备：感觉这里可以不用直接删除掉，可以采取设置一个表示是否存在的值，“删除”时只需要改变那个值即可。但是会带来一系列问题，比如增加时
           可能出现mac值重复但是旧mac值已经弃用的情况，需要详细判断
     */

    /**
     * 管理员：删除设备
     * @param devMac
     * @return
     */
    @Override
    public ServerResponse deleteDev(String devMac) {
        //需要先检查该设备是否已存在
        ServerResponse validResponse = checkDevExists(devMac);
        if (!validResponse.isSuccess()) {
            //若mac存在,进行删除操作
            int result = devMapper.deleteByPrimaryKey(devMac);
            if (result > 0) {
                return ServerResponse.createBySuccess("删除成功");
            }
        }
        return validResponse;
    }


    /**
     * 更新设备信息
     * @param dev
     * @return
     */
    @Override
    public ServerResponse updateDev(Dev dev) {
        int result = devMapper.updateByPrimaryKeySelective(dev);
        if (result > 0) {
            return ServerResponse.createBySuccess("更新成功");
        } else {
            return ServerResponse.createByErrorMessage("更新失败");
        }
    }

    /**
     * 获取所有设备
     * @return
     */
    @Override
    public ServerResponse<List<Dev>> selectAllDev() {
        List<Dev> devList = devMapper.selectAllDev();
        return ServerResponse.createBySuccess("获取所有设备信息成功", devList);
    }

    /**
     * 获取某个mac值的设备信息
     * @param devMac
     * @return
     */
    @Override
    public ServerResponse<Dev> getDevInfo(String devMac) {
        Dev dev = devMapper.selectByPrimaryKey(devMac);
        if (dev != null) {
            return ServerResponse.createBySuccess("获取该设备信息成功", dev);
        }
        return ServerResponse.createByError();
    }
}
