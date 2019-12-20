package com.makersy.equipment.service;

import com.makersy.equipment.common.ServerResponse;
import com.makersy.equipment.pojo.Dev;
import com.makersy.equipment.pojo.User;

import java.util.List;

/**
 * Created by makersy on 2019
 */

public interface IDevService {

    ServerResponse addDev(Dev dev);

    ServerResponse deleteDev(String devMac);

    ServerResponse updateDev(Dev dev);

    ServerResponse<List<Dev>> selectAllDev();

    ServerResponse<Dev> getDevInfo(String devMac);


}
