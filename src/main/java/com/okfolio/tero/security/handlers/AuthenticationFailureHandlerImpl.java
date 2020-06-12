package com.okfolio.tero.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okfolio.tero.common.ResultEntity;
import com.okfolio.tero.common.enums.UserResultCodeEnum;
import com.okfolio.tero.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/11
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        if (exception instanceof AuthenticationServiceException) {
            logger.info("AuthenticationServiceException");
            Response.json(response, HttpStatus.UNAUTHORIZED,
                    ResultEntity.unauthorized(exception.getMessage()));

        } else if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            logger.info("UsernameNotFoundException || BadCredentialsException");
            Response.json(response, HttpStatus.UNAUTHORIZED,
                    ResultEntity.unauthorized(UserResultCodeEnum.BAD_USERNAME_PASSWORD));

        } else if (exception instanceof AccountExpiredException) {
            logger.info("AccountExpiredException");
            Response.json(response, HttpStatus.UNAUTHORIZED,
                    ResultEntity.unauthorized(UserResultCodeEnum.USER_EXPIRED));

        } else if (exception instanceof LockedException) {
            logger.info("LockedException");
            Response.json(response, HttpStatus.UNAUTHORIZED,
                    ResultEntity.unauthorized(UserResultCodeEnum.USER_LOCKED));

        } else if (exception instanceof CredentialsExpiredException) {
            logger.info("CredentialsExpiredException");
            Response.json(response, HttpStatus.UNAUTHORIZED,
                    ResultEntity.unauthorized(UserResultCodeEnum.CREDENTIALS_EXPIRED));

        } else if (exception instanceof DisabledException) {
            logger.info("DisabledException");
            Response.json(response, HttpStatus.UNAUTHORIZED,
                    ResultEntity.unauthorized(UserResultCodeEnum.USER_DISABLED));

        } else {
            logger.info("onAuthenticationFailure");
            Response.json(response, HttpStatus.UNAUTHORIZED,
                    ResultEntity.unauthorized(UserResultCodeEnum.FAILED_LOGIN));
        }
    }
}
