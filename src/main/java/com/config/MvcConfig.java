package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// https://www.jianshu.com/p/52f39b799fbb
// https://cloud.tencent.com/developer/article/1398533
// WebMvcConfigurer为Spring Boot中的一个接口，用来配置web相关的属性或工具插件，比如消息转换器，拦截器，视图处理器，跨域设置等等
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 配置页面跳转，等同于Controller类
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");
    }
}
