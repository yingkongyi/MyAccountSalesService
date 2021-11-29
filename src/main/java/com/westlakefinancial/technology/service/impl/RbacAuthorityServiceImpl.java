package com.westlakefinancial.technology.service.impl;

import com.westlakefinancial.technology.dao.RbacAuthorityDao;
import com.westlakefinancial.technology.entity.UserInfo;
import com.westlakefinancial.technology.service.RbacAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Security authorization implementation class
 *
 * @author jiapeng.wu
 */
@Component("rbacauthorityservice")
public class RbacAuthorityServiceImpl implements RbacAuthorityService {

    @Autowired
    private RbacAuthorityDao rbacAuthorityDao;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object userInfo = authentication.getPrincipal();
        boolean hasPermission = false;
        if (userInfo instanceof UserInfo) {
            String username = ((UserDetails) userInfo).getUsername();
            List<String> urls = rbacAuthorityDao.getUrlsByUsername(username);
            hasPermission = urls.stream().anyMatch(url -> request.getRequestURI().contains(url));
        }
        return hasPermission;
    }
}
