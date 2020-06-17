package com.oktfolio.tero.security.handlers;

import com.oktfolio.tero.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: tero
 * @description:
 * @author: wb-yk668146
 * @create: 2020-06-17 14:34
 */
@Component
public class TeroSessionInformationExpired implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent)
            throws IOException, ServletException {

        HttpServletResponse response = sessionInformationExpiredEvent.getResponse();
        Response.json(response, HttpStatus.UNAUTHORIZED);
    }
}
