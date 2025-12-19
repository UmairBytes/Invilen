package com.invilens.bakery.config;

import com.invilens.bakery.interceptor.Logginginterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Logginginterceptor())
                .addPathPatterns("/api/**");
    }
}
