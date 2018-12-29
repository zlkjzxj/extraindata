package com.zlkj.train.confs;


import com.zlkj.train.utils.Constant;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Auther: zxj
 * @Date: 2018\12\17 0017 17:27
 * @Description: 连网的情况下就要添加这个了，不联网就不添加了
 */
@Configuration
@Order(value = 1)
public class MvcConfig extends WebMvcConfigurationSupport {

    @Bean
    public HandlerInterceptor handlerInterceptor() {
        return new HandlerInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        boolean b = Constant.USE_DB;
        System.out.println(b);
        registry.addInterceptor(handlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/", "/login", "/init", "/static/**", "/error");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**", "/favicon.ico")
                .addResourceLocations("classpath:/static/", "/favicon.ico");
    }
}
