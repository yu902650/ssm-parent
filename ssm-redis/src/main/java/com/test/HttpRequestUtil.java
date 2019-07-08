package com.test;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by bobo on 2019/4/22 15:58
 */
@Slf4j
public class HttpRequestUtil {

    private static final String NGINX_IP_HEADER = "X-Real-IP";
    private static final String NGINX_URL_HEADER = "X-Real-Url";
    private static final String NGINX_X_FORWARDED_FOR = "X-Forwarded-For";

    /**
     * 功能描述: 获取ip（兼容nginx转发）
     *
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String ips = request.getHeader(NGINX_X_FORWARDED_FOR);
        String[] ipArray = StringUtils.split(ips, ",");
        if (ArrayUtils.isNotEmpty(ipArray)) {
            return StringUtils.trim(ipArray[0]);
        } else {
            String ip = request.getHeader(NGINX_IP_HEADER);
            if (StringUtils.isEmpty(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        }
    }

    /**
     * 从request中抽取当前url(兼容nginx转发模式)
     *
     * @param request
     * @return
     * @see #NGINX_URL_HEADER
     */
    public static String getRemoteUrl(HttpServletRequest request) {
        /*if (checkParamNull(request)) {
            return null;
        }*/
        String url = request.getHeader(NGINX_URL_HEADER);
        if (StringUtils.isEmpty(url)) {
            return request.getRequestURL().toString();
        } else {
            if (log.isDebugEnabled()) {
                log.debug("NGINX_URL_HEADER:" + url);
            }
            return url;
        }
    }

    /**
     * 功能描述: <br>
     * 获取hostname
     *
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getHostName(HttpServletRequest request) {
        String host = request.getHeader("Host");
        return host;
    }

    /**
     * 获取用户代理
     */
    public static String getUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return userAgent;
    }

    private static boolean checkParamNull(Object... params) {
        for (Object param : params) {
            if (null == param) {
                log.error("Invalid Parameter.");
                return true;
            }
        }
        return false;
    }

    /**
     * 获取格式化的URI
     * @param request
     * @return
     */
    public static String getURIFormat(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if("".equals(request.getContextPath())) {
            uri = uri.substring(1);
        }
        if (StringUtils.isNotBlank(request.getQueryString())) {
            uri += "?" + request.getQueryString();
        }
        return uri;
    }

    /**
     * 获取请求method
     * @param request
     * @return
     */
    public static String getRequestMethod(HttpServletRequest request) {
        return request.getMethod();
    }

    /**
     * 将cookie value封装到Map里面
     * @param request
     * @return
     */
    public static Map<String, String> getCookieMap(HttpServletRequest request){
        Map<String, String> cookieMap = Maps.newHashMap();
        Cookie[] cookies = request.getCookies();
        if(null != cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieMap;
    }
}