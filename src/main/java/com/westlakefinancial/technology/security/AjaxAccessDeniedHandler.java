package com.westlakefinancial.technology.security;

import com.google.gson.Gson;
import com.westlakefinancial.technology.entity.ResultInfo;
import com.westlakefinancial.technology.enums.ResultEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * No access to the processor
 *
 * @author jiapeng.wu
 */
@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.getWriter().write(new Gson().toJson(ResultInfo.result(ResultEnum.USER_NO_ACCESS, false)));
    }
}
