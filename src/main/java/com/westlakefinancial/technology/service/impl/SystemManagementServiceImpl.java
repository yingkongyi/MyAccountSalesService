package com.westlakefinancial.technology.service.impl;

import com.westlakefinancial.technology.dao.SystemManagementDao;
import com.westlakefinancial.technology.entity.UserInfo;
import com.westlakefinancial.technology.service.SystemManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Business Class Service
 *
 * @author jiapeng.wu
 */
@Service
public class SystemManagementServiceImpl implements SystemManagementService {

    @Autowired
    private SystemManagementDao systemManagementDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(UserInfo user) {
        return systemManagementDao.addUser(user) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUser(String userId) {
        return systemManagementDao.deleteUser(userId) > 0;
    }
}
