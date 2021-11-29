package com.westlakefinancial.technology.service.impl;

import com.westlakefinancial.technology.dao.RbacAuthorityDao;
import com.westlakefinancial.technology.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Security authentication implementation class
 *
 * @author jiapeng.wu
 */
@Component
public class SelfUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RbacAuthorityDao rbacAuthorityDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user = rbacAuthorityDao.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        List<String> roles = rbacAuthorityDao.getRoles(user);

        Set<GrantedAuthority> authoritiesSet = new HashSet<>();
        roles.forEach(role -> authoritiesSet.add(new SimpleGrantedAuthority(role)));

        user.setAuthorities(authoritiesSet);

        return user;
    }
}
