package com.westlakefinancial.technology.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;


/**
 * @author jiapeng.wu
 */
public interface RbacAuthorityService {

    /**
     * 授权
     *
     * @param request
     * @param authentication
     * @return
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
