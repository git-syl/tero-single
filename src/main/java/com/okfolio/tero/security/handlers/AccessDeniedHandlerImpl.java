package com.okfolio.tero.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okfolio.tero.common.ResultEntity;
import com.okfolio.tero.common.enums.ResultCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/11
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public AccessDeniedHandlerImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException {

        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(
                ResultEntity.forbidden(ResultCodeEnum.FORBIDDEN)));
    }
}
