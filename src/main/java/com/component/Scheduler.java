package com.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

// https://www.cnblogs.com/MaxElephant/p/8108462.html
// 需要在启动类中设置@EnableScheduling
@Component
public class Scheduler {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    // 注解中的参数 cron、fixedDelay、fixedRate 三者之间不能共存

    // https://blog.csdn.net/u011244202/article/details/54382466
    @Scheduled(cron = "*/5 * * * * ?")
    public void cronSch() {
        Date date = new Date();
        String strDate = format.format(date);
        System.out.println("Java cron job expression:: " + strDate);
    }

    @Scheduled(fixedRate = 1000)
    public void fixedRateSch() {
        Date date = new Date();
        String strDate = format.format(date);
        System.out.println("Fixed Rate scheduler:: " + strDate);
    }

    @Scheduled(initialDelay = 3000, fixedDelay = 1000)
    public void fixedDelaySch() {
        Date date = new Date();
        String strDate = format.format(date);
        System.out.println("Fixed Delay scheduler:: " + strDate);
    }
}
