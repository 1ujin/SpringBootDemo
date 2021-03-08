package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

// Pom文件里面如果是springboot start这种类型的依赖，就不需要加
// 因为SpringBootAutoConfigure这个jar包里的SpringBootWebSecurityConfiguration这个类
// 已经帮助自动加上了,所以不要在加@EnableWebSecurity这个注解了
// 但是如果单独导入的是 spring-security-core类似这种的依赖,就需要加上@EnableWebSecurity,因为springboot没有帮助自动装配
@Configuration
// @EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // For example: Use only Http Basic and not form login.
        // 配置登入，logoutUrl默认为"/logout"可以不写
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated() // 任意请求都需要权限
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll() // 允许login
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll(); // 允许logout
    }

    // http://blog.didispace.com/xjf-spring-security-3/
    // 内存中的用户认证器
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 父类方法，调用则采用properties文件中的配置，覆盖以下配置
        // super.configure(auth);
        // https://blog.csdn.net/cauchy6317/article/details/85162225
        auth
                .inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user")
                .password(new BCryptPasswordEncoder().encode("password"))
                .authorities("USER")
                .and()
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .authorities("ADMIN");
    }
}
