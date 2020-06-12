package com.okfolio.tero.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okfolio.tero.common.ResultEntity;
import com.okfolio.tero.common.enums.UserResultCodeEnum;
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

    private final ObjectMapper objectMapper;

    public AuthenticationFailureHandlerImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        PrintWriter writer = response.getWriter();

        if (exception instanceof AuthenticationServiceException) {
            logger.info("AuthenticationServiceException");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            writer.write(
                    objectMapper.writeValueAsString(
                            ResultEntity.unauthorized(exception.getMessage())));
            writer.flush();
            writer.close();
        } else if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            logger.info("UsernameNotFoundException || BadCredentialsException");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            writer.write(
                    objectMapper.writeValueAsString(
                            ResultEntity.unauthorized(UserResultCodeEnum.BAD_USERNAME_PASSWORD)));
            writer.flush();
            writer.close();
        } else if (exception instanceof AccountExpiredException) {
            logger.info("AccountExpiredException");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            writer.write(
                    objectMapper.writeValueAsString(
                            ResultEntity.unauthorized(UserResultCodeEnum.USER_EXPIRED)));
            writer.flush();
            writer.close();
        } else if (exception instanceof LockedException) {
            logger.info("LockedException");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            writer.write(
                    objectMapper.writeValueAsString(
                            ResultEntity.unauthorized(UserResultCodeEnum.USER_LOCKED)));
            writer.flush();
            writer.close();
        } else if (exception instanceof CredentialsExpiredException) {
            logger.info("CredentialsExpiredException");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            writer.write(
                    objectMapper.writeValueAsString(
                            ResultEntity.unauthorized(UserResultCodeEnum.CREDENTIALS_EXPIRED)));
            writer.flush();
            writer.close();
        } else if (exception instanceof DisabledException) {
            logger.info("DisabledException");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            writer.write(
                    objectMapper.writeValueAsString(
                            ResultEntity.unauthorized(UserResultCodeEnum.USER_DISABLED)));
            writer.flush();
            writer.close();
        } else {
            logger.info("onAuthenticationFailure");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            writer.write(
                    objectMapper.writeValueAsString(
                            ResultEntity.unauthorized(UserResultCodeEnum.FAILED_LOGIN)));
            writer.flush();
            writer.close();
        }
    }
}
