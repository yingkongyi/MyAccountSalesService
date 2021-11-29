package com.westlakefinancial.technology.security;

import com.google.gson.Gson;
import com.westlakefinancial.technology.entity.ResultInfo;
import com.westlakefinancial.technology.enums.ResultEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Logon failure handler
 *
 * @author jiapeng.wu
 */
@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.getWriter().write(new Gson().toJson(ResultInfo.result(ResultEnum.USER_LOGIN_FAILED, false)));
    }

}
