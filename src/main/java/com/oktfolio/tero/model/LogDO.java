package com.oktfolio.tero.model;

/**
 * @program: tero
 * @description:
 * @author: wb-yk668146
 * @create: 2020-06-18 19:23
 */
public class LogDO extends BaseModel{

    private static final long serialVersionUID = -7732383421149304386L;
    private String className;
    private String methodName;
    private String logName;
    private String logType;
    private String requestUri;
    private String requestMethod;
    private String requestParams;
    private String pathVariables;
    private Long timeCost;
    private String username;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getPathVariables() {
        return pathVariables;
    }

    public void setPathVariables(String pathVariables) {
        this.pathVariables = pathVariables;
    }

    public Long getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(Long timeCost) {
        this.timeCost = timeCost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LogDO{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", logName='" + logName + '\'' +
                ", logType='" + logType + '\'' +
                ", requestUri='" + requestUri + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestParams='" + requestParams + '\'' +
                ", pathVariables='" + pathVariables + '\'' +
                ", timeCost=" + timeCost +
                ", username='" + username + '\'' +
                '}';
    }
}
