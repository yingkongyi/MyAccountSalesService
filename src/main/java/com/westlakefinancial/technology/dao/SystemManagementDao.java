package com.westlakefinancial.technology.dao;

import com.westlakefinancial.technology.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Business Class Dao
 *
 * @author jiapeng.wu
 */
@Mapper
@Repository
public interface SystemManagementDao {
    int addUser(@Param("userInfo") UserInfo user);

    int deleteUser(@Param("userId") String userId);


}
