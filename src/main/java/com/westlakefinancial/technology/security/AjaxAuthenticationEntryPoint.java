package com.westlakefinancial.technology.security;

import com.google.gson.Gson;
import com.westlakefinancial.technology.entity.ResultInfo;
import com.westlakefinancial.technology.enums.ResultEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Not logged in to the processor
 *
 * @author jiapeng.wu
 */
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.getWriter().write(new Gson().toJson(ResultInfo.result(ResultEnum.USER_NEED_AUTHORITIES, false)));
    }
}
