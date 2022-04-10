package com.component;

import ch.qos.logback.core.PropertyDefinerBase;
import org.springframework.stereotype.Component;

/**
 * 监听日志路径
 */
@Component
public class GetLogHome extends PropertyDefinerBase {

    public static String logPath = "E:/tmp/log20/";

    @Override
    public String getPropertyValue() {
        return logPath;
    }
}
