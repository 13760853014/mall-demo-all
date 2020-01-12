package com.jianke.demo.cart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * PromoServiceApplication
 *
 * @author huangxinghuai
 * @since 2018/11/28
 */
@EnableTransactionManagement
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.jianke.demo.cart.mapper")
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class MallCartServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallCartServiceApplication.class, args);
    }

    @Autowired
    private Executor executor;

    // 解决跨域问题
    @Bean
    public FilterRegistrationBean corsFilterBean() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(8640000L);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(SecurityProperties.DEFAULT_FILTER_ORDER - 1);
        return bean;
    }


    @Primary
    @Bean
    public Executor promoServerThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setThreadNamePrefix("MallCartThreadPool-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}

