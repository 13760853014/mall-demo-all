package com.jianke.demo.account.config;

import com.jianke.demo.account.aop.LoginRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * web访问配置
 * @author CGQ
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	/**
	 * 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginRequestInterceptor())
				.addPathPatterns("/mgmt/**")
				.excludePathPatterns("/api/login")
				.excludePathPatterns("/api/logout");
		super.addInterceptors(registry);
	}
}
