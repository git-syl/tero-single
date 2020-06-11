package com.okfolio.tero.security.filter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.okfolio.tero.security.model.UsernamePassword;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/11
 */
@Component
public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JsonUsernamePasswordAuthenticationFilter.class);

    private boolean postOnly = true;

    public boolean isPostOnly() {
        return postOnly;
    }

    @Override
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        logger.info("JsonUsernamePasswordAuthenticationFilter attemptAuthentication");
        if (postOnly && !HttpMethod.POST.name().equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            if (request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
                logger.info("a json login request, continue");
                UsernamePasswordAuthenticationToken authenticationToken;
                try (InputStream inputStream = request.getInputStream()) {
                    JsonParser parser = objectMapper.createParser(inputStream);
                    UsernamePassword usernamePassword = parser.readValueAs(UsernamePassword.class);

                    //TODO
                    if (usernamePassword == null) {
                        throw new BadCredentialsException("");
                    }
                    if (StringUtils.isBlank(usernamePassword.getUsername())) {
                        throw new BadCredentialsException("");
                    }

                    if (StringUtils.isBlank(usernamePassword.getPassword())) {
                        throw new BadCredentialsException("");
                    }

                    logger.info("json login, user: {}", usernamePassword.getUsername());
                    authenticationToken = new UsernamePasswordAuthenticationToken(
                            usernamePassword.getUsername(), usernamePassword.getPassword());

                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("failed to obtain json from request, {}", e.getMessage());
                    authenticationToken = new UsernamePasswordAuthenticationToken(
                            "", "");
                }
                setDetails(request, authenticationToken);
                return this.getAuthenticationManager().authenticate(authenticationToken);
            } else {
                // if not JSON request，continue with attemptAuthentication
                logger.info("not a json request, use default UsernamePasswordAuthenticationFilter");
                return super.attemptAuthentication(request, response);
            }
        }
    }
}
