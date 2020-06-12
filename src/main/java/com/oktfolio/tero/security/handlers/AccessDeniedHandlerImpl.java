package com.oktfolio.tero.security.handlers;

import com.oktfolio.tero.common.ResultEntity;
import com.oktfolio.tero.common.enums.ResultCodeEnum;
import com.oktfolio.tero.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/11
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException {

        Response.json(httpServletResponse, HttpStatus.FORBIDDEN,
                ResultEntity.forbidden(ResultCodeEnum.FORBIDDEN));
    }
}
