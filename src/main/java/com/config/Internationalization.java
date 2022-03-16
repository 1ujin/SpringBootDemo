package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class Internationalization implements WebMvcConfigurer {
    @Autowired
    private MessageSource messageSource;

    /**
     * 数据验证国际化
     *
     * @return 验证器
     */
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setValidationMessageSource(messageSource);
        // 快速失败模式，只要错误一个就返回
        factoryBean.getValidationPropertyMap().put("hibernate.validator.fail_fast", "true");
        return factoryBean;
    }

    // 另一种设置快速失败模式方法
    // @Bean
    // public javax.validation.Validator validator() {
    //     return Validation.byProvider(HibernateValidator.class)
    //             .configure()
    //             // 快速失败模式
    //             .failFast(true)
    //             .buildValidatorFactory()
    //             .getValidator();
    // }

    /**
     * 默认解析器 其中{@code Locale}表示默认语言
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        // 修改默认地区
        // resolver.setDefaultLocale(Locale.US);

        // 不添加地区拦截器 LocaleChangeInterceptor 的方法
        // LocaleResolver resolver = new LocaleResolver() {
        //     @Override
        //     public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //         String lang = httpServletRequest.getParameter("lang");
        //         Locale locale = Locale.getDefault();
        //         if (lang != null) {
        //             String[] split = lang.split("_");
        //             locale = new Locale(split[0], split[1]);
        //         }
        //         return locale;
        //     }
        //
        //     @Override
        //     public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
        //     }
        // };
        return resolver;
    }

    /**
     * 默认拦截器 其中{@code lang}表示切换语言的参数名
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        // 根据请求参数lang切换语言
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 不实现地区解析器 LocaleResolver 接口的方法
        registry.addInterceptor(localeChangeInterceptor());
    }
}
