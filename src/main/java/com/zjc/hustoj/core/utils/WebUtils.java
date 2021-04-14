/*
 * Copyright 2005-2013 xiucheren.net. All rights reserved.
 * Support: http://www.xiucheren.net
 * License: http://www.xiucheren.net/license
 */
package com.zjc.hustoj.core.utils;

import lombok.extern.slf4j.Slf4j;


import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;


/**
 * Utils - Web
 *
 * @author XIUCHEREN Team
 * @version 3.0
 */
@Slf4j
public final class WebUtils {


    public static String getRequestIp(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Real-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("PRoxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            log.info("get request real ip is error", e);
        }
        if (null != ip) {
            String[] ips = ip.split(",");
            if (ips.length > 1) {
                ip = ips[0];
            }
        }
        return ip;
    }

    public static String getRequestIp() {
        return getRequestIp(getCurrentRequest());
    }

    public static HttpServletRequest getCurrentRequest() {
        HttpServletRequest req = null;
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null) {
            req = ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return req;
    }
}