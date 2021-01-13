package com.zgq.plainarticle.config;

import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

public class LoggingFilter extends CommonsRequestLoggingFilter {

    private static long beforeTime;
    private static long afterTime;

    public LoggingFilter(){
        super.setIncludeClientInfo(false);
        super.setIncludeHeaders(false);
        super.setIncludePayload(true);
        super.setMaxPayloadLength(2000);
        super.setIncludeQueryString(true);

    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        // Checks if request matches /api/calendarCompletionDate
        boolean shouldLog = requestURI.matches("^/api\\/calendarCompletionDate$");

        if (shouldLog) {
            String method = request.getMethod();
//            super.setBeforeMessagePrefix("Before request [" + method + "," + "currentUserId:" + currentUserId + ","); //Default is just Before request
//            super.setAfterMessagePrefix("After request [" + method + "," + "currentUserId:" + currentUserId + ",");   //Default is just After request
        }
        return shouldLog;
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        beforeTime = System.currentTimeMillis();
        super.beforeRequest(request, message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        afterTime = System.currentTimeMillis();
        logger.info("afterRequest: Time taken: " + (afterTime-beforeTime) + " in milliseconds");
        super.afterRequest(request, message);
    }

}
