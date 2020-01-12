package com.jianke.demo.account.aop;

import com.jianke.demo.account.cache.DataCacheService;
import com.jianke.demo.constant.Constant;
import com.jianke.demo.account.utils.CookieUtil;
import com.jianke.demo.exception.AccessException;
import com.jianke.demo.vo.MallAccountVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 * @author CGQ
 */
public class LoginRequestInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private DataCacheService dataCache;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//启动支持@Autowired注解
		WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
		//登录信息验证结果
		String sessionId = CookieUtil.get(Constant.SESSION_IDENTITY_KEY, request);
		if (StringUtils.isEmpty(sessionId)) {
			throw new AccessException("401", "您还没有登陆，请登陆");
		}
		MallAccountVo accountVo = dataCache.findBySessionId(sessionId);
		if (accountVo == null) {
			throw new AccessException("401", "登陆已失效，请重新登陆");
		}
		return super.preHandle(request, response, handler);
	}

}
