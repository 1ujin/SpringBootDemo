package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@SpringBootApplication
// @EnableEurekaServer
@EnableZuulProxy
@EnableSwagger2
@EnableConfigServer
// 可选择定时任务
// @EnableScheduling
// 默认情况下spring boot只会扫描启动类当前包和以下的包, 添加其他的包
@ComponentScan({"com.component", "com.controller", "com.exception", "com.interceptor", "com.filter", "com.service", "com.pojo", "com"})
public class SpringBootDemoApplication implements ApplicationRunner, CommandLineRunner {

    /**
     * https://blog.csdn.net/MRL6140x/article/details/78077942
     * spring boot默认加载文件的路径：
     * /META-INF/resources/
     * /resources/
     * /static/
     * /public/
     */
    // 日志配置需要放到加载的路径中
    private static final Logger logger = LoggerFactory.getLogger(SpringBootDemoApplication.class);

    @Value("${LOG_FILE}")
    private static String log_file;

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        logger.info("this is a info message");
        logger.warn("this is a warn message");
        logger.error("this is a error message");
        SpringApplication.run(SpringBootDemoApplication.class, args);
        // 这里获取不到属性文件中的LOG_FILE,而xml配置文件中可以获取到
        System.out.println(log_file);
        System.out.println("main方法结束");
    }

    // 产生一个Bean对象，然后这个Bean对象交给Spring管理。产生这个Bean对象的方法Spring只会调用一次，随后这个Spring将会将这个Bean对象放在自己的IOC容器中
    public @Bean
    RestTemplate getRestTemplate() {
        System.out.println("返回RestTemple实例");
        return new RestTemplate();
    }

    @Override
    public void run(ApplicationArguments arg) {
        System.out.println("在Tomcat启动后执行Application Runner");
        System.out.println(arg);
    }

    @Override
    public void run(String... strs) {
        System.out.println("在Tomcat启动后执行Command Line Runner");
        System.out.println(Arrays.toString(strs));
        /*
         * [Z = boolean
         * [B = byte
         * [S = short
         * [I = int
         * [J = long
         * [F = float
         * [D = double
         * [C = char
         * [L = any non-primitives(Object)
         */
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    // 配置Swagger2 http://blog.didispace.com/springbootswagger2/
    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfoBuilder().title("Swagger2 演示").build()).select().apis(RequestHandlerSelectors.basePackage("com")).build();
    }
}
