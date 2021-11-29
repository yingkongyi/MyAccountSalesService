package com.westlakefinancial.technology.dao;

import com.westlakefinancial.technology.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Authorize Dao
 *
 * @author jiapeng.wu
 */
@Mapper
@Repository
public interface RbacAuthorityDao {

    /**
     * Get all permissions based on user name
     *
     * @param username
     * @return
     */
    List<String> getUrlsByUsername(@Param("username") String username);

    /**
     * Get user details based on user name
     *
     * @param username
     * @return
     */
    UserInfo getUser(@Param("username") String username);

    /**
     * Get roles based on user information
     *
     * @param user
     * @return
     */
    List<String> getRoles(@Param("userInfo") UserInfo user);
}
