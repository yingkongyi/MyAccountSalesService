package com.westlakefinancial.technology.security;

import com.google.gson.Gson;
import com.westlakefinancial.technology.entity.ResultInfo;
import com.westlakefinancial.technology.entity.UserInfo;
import com.westlakefinancial.technology.enums.ResultEnum;
import com.westlakefinancial.technology.utils.JwtUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Log in successfully processor
 *
 * @author jiapeng.wu
 */
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        UserInfo userDetails = (UserInfo) authentication.getPrincipal();

        String jwtToken = JwtUtils.generateToken(userDetails.getUsername(), userDetails.getUserId());

        httpServletResponse.getWriter().write(new Gson().toJson(ResultInfo.result(ResultEnum.USER_LOGIN_SUCCESS, jwtToken, true)));
    }
}
