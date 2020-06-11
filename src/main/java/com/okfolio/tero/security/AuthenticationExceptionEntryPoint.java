package com.okfolio.tero.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okfolio.tero.common.ResultEntity;
import com.okfolio.tero.common.enums.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2019/09/05
 */
@Component
public class AuthenticationExceptionEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public AuthenticationExceptionEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(
                ResultEntity.unauthorized(ResultCodeEnum.UNAUTHORIZED)));
    }
}
