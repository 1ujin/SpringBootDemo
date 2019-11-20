package com.controller;

import com.pojo.Greeting;
import org.apache.http.protocol.ResponseDate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @RequestMapping(value = "/greet")
    @SendTo(value = "/topic/greetings")
    public Greeting greeting(Greeting greeting) throws InterruptedException {
        Thread.sleep(1000);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(greeting.getContent()) + "!");
    }

}
