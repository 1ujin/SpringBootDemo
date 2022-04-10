package com.controller;

import com.component.GetLogHome;
import com.component.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logger")
public class LoggerController {
    @Autowired
    private GetLogHome getLogHome;

    @Autowired
    private LoggerUtil loggerUtil;

    @RequestMapping("/reload")
    public String reloadLogger() {
        getLogHome.logPath = "E:/tmp/log21/";
        loggerUtil.reloadLogger();
        return "reload logger";
    }
}
