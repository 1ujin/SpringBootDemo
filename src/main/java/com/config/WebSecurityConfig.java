package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // For example: Use only Http Basic and not form login.
        // 配置登入，logoutUrl默认为"/logout"可以不写
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll();
    }

    // http://blog.didispace.com/xjf-spring-security-3/
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
