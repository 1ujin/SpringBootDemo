package com;

import com.controller.HelloController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
// Spring Boot 1.x 版本中为 SpringApplicationConfiguration
// 使用 MockServletContext 构建一个空的 WebApplicationContext
@SpringBootTest(classes = SpringBootDemoApplication.class)
// 指定配置文件
// @TestPropertySource("classpath:/application.properties")
@WebAppConfiguration
public class SpringBootDemoApplicationTests {
    private MockMvc mvc;

    @Autowired
    Environment env;

    @Autowired
    HelloController helloController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }

    @Test
    public void getHello() throws Exception {
        System.out.println(env.getProperty("com.didispace.blog.name"));
        System.out.println(env.getProperty("com.didispace.blog.title"));
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string(equalTo("Hello World<br>lujin<br>Spring Boot ??")));
    }

}
