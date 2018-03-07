package com.yinhai.weixin.configuration;

import com.yinhai.weixin.interceptor.AccessInterceptor;
import com.yinhai.weixin.interceptor.AddInfoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 把拦截器串起来
 * Created by 张汉江 on 2018/3/7
 */
@Component
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    private AccessInterceptor accessInterceptor;

    @Autowired
    private AddInfoInterceptor addInfoInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(accessInterceptor).addPathPatterns("/page/*");
        registry.addInterceptor(addInfoInterceptor).addPathPatterns("/view/*");
    }
}
