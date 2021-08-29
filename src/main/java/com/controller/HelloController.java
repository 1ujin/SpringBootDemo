package com.controller;

// import brave.sampler.Sampler;

import com.SpringBootDemoApplication;
// import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
// import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;

// @RestController = @Controller + @ResponseBody
@RestController
public class HelloController {
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(SpringBootDemoApplication.class.getName());
    // 冒号后为默认值
    @Value("${com.didispace.blog.name:default_name}")
    private String name;
    @Value("${com.didispace.blog.title}")
    private String title;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World<br>" + name + "<br>" + title;
    }

    @RequestMapping("/log")
    public String log() {
        LOG.log(Level.INFO, "Index API is calling");
        return "Welcome Sleuth!";
    }

    // @Bean
    // public Sampler defaultSampler() {
    //     return Sampler.ALWAYS_SAMPLE;
    // }

    @RequestMapping("/hystrix")
    // @HystrixCommand(fallbackMethod = "fallback_sleep", commandProperties = {
    //         @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    // })
    public String sleepSeconds(@RequestParam(value = "sleep", required = false, defaultValue = "0") String sleep) throws InterruptedException {
        Thread.sleep(Integer.parseInt(sleep));
        return "Sleep " + Integer.parseInt(sleep) + " milliseconds.";
    }

    // 备用方法和原方法参数类型需要相同
    private String fallback_sleep(String sleep) {
        return "Request fails. It takes time to response more than 5000 milliseconds.";
    }

}
