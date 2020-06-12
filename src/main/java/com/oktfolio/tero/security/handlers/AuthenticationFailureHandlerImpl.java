package com.oktfolio.tero.security.handlers;

import com.oktfolio.tero.common.ResultEntity;
import com.oktfolio.tero.common.enums.UserResultCodeEnum;
import com.oktfolio.tero.security.exception.ContentTypeNullException;
import com.oktfolio.tero.security.exception.InvalidCodeException;
import com.oktfolio.tero.security.exception.MethodNotSupportedException;
import com.oktfolio.tero.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            logger.info("UsernameNotFoundException || BadCredentialsException");
            Response.json(response, HttpStatus.UNAUTHORIZED,
                    ResultEntity.unauthorized(UserResultCodeEnum.BAD_USERNAME_PASSWORD));

        } else if (exception instanceof InvalidCodeException) {
            logger.info("InvalidCodeException");
            Response.json(response, HttpStatus.UNAUTHORIZED,
                    ResultEntity.unauthorized(UserResultCodeEnum.INVALID_VERIFICATION_CODE));

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

        } else if (exception instanceof AuthenticationServiceException) {
            logger.info("AuthenticationServiceException");
            Response.json(response, HttpStatus.UNAUTHORIZED,
                    ResultEntity.unauthorized(exception.getMessage()));

        } else if (exception instanceof ContentTypeNullException) {
            logger.info("ContentTypeNullException");
            Response.json(response, HttpStatus.BAD_REQUEST,
                    ResultEntity.of(HttpStatus.BAD_REQUEST, exception.getMessage()));

        } else if (exception instanceof MethodNotSupportedException) {
            logger.info("MethodNotSupportedException");
            Response.json(response, HttpStatus.BAD_REQUEST,
                    ResultEntity.of(HttpStatus.BAD_REQUEST, exception.getMessage()));

        } else {
            logger.info("onAuthenticationFailure");
            Response.json(response, HttpStatus.UNAUTHORIZED,
                    ResultEntity.unauthorized(UserResultCodeEnum.FAILED_LOGIN));
        }
    }
}
