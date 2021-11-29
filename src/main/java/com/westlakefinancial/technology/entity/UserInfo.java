package com.westlakefinancial.technology.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * User information entity (user entity that implements security)
 *
 * @author jiapeng.wu
 */
@Data
public class UserInfo implements UserDetails, Serializable {
    private String userId;
    private String username;
    private String password;
    private Set<? extends GrantedAuthority> authorities;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lastUpdateDate;

    /**
     * Whether the account expires
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Is the account locked?
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Is the certificate expired
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Whether the user is disabled
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
