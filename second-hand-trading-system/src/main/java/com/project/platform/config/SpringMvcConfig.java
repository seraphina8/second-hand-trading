package com.project.platform.config;

import com.project.platform.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                //允许直接访问的接口
                .excludePathPatterns(
                        "/sysUser/login",
                        "/sysUser/smsLogin",
                        "/sysUser/register",
                        "/sysUser/resetPasswordBySmsCode",
                        "/file/**",
                        "/test/**",
                        "/sysSmsCode/add",
                        //swagger放行
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/images/**",
                        "/webjars/**",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/configuration/security",
                        "/doc.html",
			"/sysSmsCode"
                );
    }
}
