package com.jxyj.delivery.app.config;

import com.jxyj.delivery.app.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Classname WebMvcConfig
 * @Description
 * @Date 2022/04/05 17:25
 * @Created mr_xie
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/applet/**")
            .excludePathPatterns("/applet/customer/login");

    }

}
