package com.oktfolio.tero.common.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oktfolio.tero.common.annotation.Log;
import com.oktfolio.tero.common.enums.LogTypeEnum;
import com.oktfolio.tero.model.LogDO;
import com.oktfolio.tero.security.userdetails.ITeroUserDetails;
import com.oktfolio.tero.utils.SecurityUtils;
import com.oktfolio.tero.utils.ThreadPoolUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
@Aspect
@Component
public class LogAspect {

    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private final HttpServletRequest request;

    @Autowired
    private ObjectMapper objectMapper;

    public LogAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Pointcut("@annotation(com.oktfolio.tero.common.annotation.Log)")
    private void aspect() {
    }

    @Around("aspect() && @annotation(log)")
    public Object around(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        long timeCost = stopWatch.getTotalTimeMillis();

        ITeroUserDetails currentUser = SecurityUtils.getCurrentUser(request);
        String username = currentUser != null ? currentUser.getUsername() : "";
        String logName = log.name();
        LogTypeEnum logType = log.type();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String requestMethod = request.getMethod();
        String requestURI = request.getRequestURI();
        Map<String, String[]> requestParams = request.getParameterMap();
        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        String params = objectMapper.writeValueAsString(requestParams);
        String variables = objectMapper.writeValueAsString(pathVariables);

        LogDO logDO = assembleLogDO(targetName, logName, logType.name(),
                methodName, requestURI, requestMethod, params, variables,
                timeCost, username);

        ExecutorService executorService = ThreadPoolUtils.getExecutorService();
        executorService.execute(() -> record(logDO));
        return result;
    }

    private LogDO assembleLogDO(String targetName,
                                String methodName,
                                String logName,
                                String logType,
                                String requestUri,
                                String requestMethod,
                                String requestParams,
                                String pathVariables,
                                Long timeCost,
                                String username) {

        LogDO logDO = new LogDO();
        logDO.setClassName(targetName);
        logDO.setMethodName(methodName);
        logDO.setLogName(logName);
        logDO.setLogType(logType);
        logDO.setRequestUri(requestUri);
        logDO.setRequestMethod(requestMethod);
        logDO.setRequestParams(requestParams);
        logDO.setPathVariables(pathVariables);
        logDO.setTimeCost(timeCost);
        logDO.setUsername(username);
        logDO.setCreateTime(LocalDateTime.now());
        logDO.setUpdateTime(LocalDateTime.now());
        return logDO;
    }

    private void record(LogDO logDO) {
        try {
            logger.info(objectMapper.writeValueAsString(logDO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
