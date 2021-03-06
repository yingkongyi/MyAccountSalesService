package com.westlakefinancial.technology.security;

import com.google.gson.Gson;
import com.westlakefinancial.technology.entity.ResultInfo;
import com.westlakefinancial.technology.enums.ResultEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Logout processor
 *
 * @author jiapeng.wu
 */
@Component
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        httpServletResponse.getWriter().write(new Gson().toJson(ResultInfo.result(ResultEnum.USER_LOGOUT_SUCCESS, true)));
    }

}
