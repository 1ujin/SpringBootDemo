package com.controller;

// import brave.sampler.Sampler;
import com.SpringBootDemoApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;

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

}
