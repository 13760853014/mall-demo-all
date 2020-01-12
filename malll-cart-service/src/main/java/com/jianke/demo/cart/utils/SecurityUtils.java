package com.jianke.demo.cart.utils;

import com.jianke.demo.auth.utils.CookieUtil;
import com.jianke.demo.constant.Constant;
import com.jianke.demo.exception.AccessException;
import com.jianke.demo.vo.MallAccountVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Component
public class SecurityUtils {

    private static DataCacheService dataCache;

    @Autowired
    private DataCacheService cacheService;

    @PostConstruct
    public void init() {
        dataCache = cacheService;
    }

    public static String getAccountId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionId = CookieUtil.get(Constant.SESSION_IDENTITY_KEY, request);
        if (StringUtils.isEmpty(sessionId)) {
            throw new AccessException("401", "您还没有登陆，请登陆");
        }
        return "202cb962ac59075b964b07152d234b70";
//        MallAccountVo accountVo = dataCache.findBySessionId(sessionId);
//        if (accountVo == null) {
//            throw new AccessException("401", "登陆已失效，请重新登陆");
//        }
//        return accountVo.getAccountId();
    }

}
