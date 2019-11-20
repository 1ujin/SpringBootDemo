package com.controller;

import com.pojo.Greeting;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @MessageMapping(value = "/greet")
    @SendTo(value = "/topic/greetings")
    public String greeting(Greeting greeting) throws InterruptedException {
        Thread.sleep(3000);
        // return new Greeting("Hello, " + HtmlUtils.htmlEscape(greeting.getName()) + "!");
        return "Hello, " + HtmlUtils.htmlEscape(greeting.getName()) + "!";
    }

}
