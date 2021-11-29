package com.westlakefinancial.technology.service;

import com.westlakefinancial.technology.entity.UserInfo;

public interface SystemManagementService {
    boolean addUser(UserInfo user);

    boolean deleteUser(String userId);
}
