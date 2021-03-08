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

    // fixedRate 调用固定周期（以毫秒为单位）执行方法。就是上一次开始执行时间点之后延迟执行
    @Scheduled(fixedRate = 1000)
    public void fixedRateSch() {
        Date date = new Date();
        String strDate = format.format(date);
        System.out.println("Fixed Rate scheduler:: " + strDate);
    }

    // initialDelay 在第一次执行fixedRate（）或fixedDelay（）任务之前延迟（以毫秒为单位）
    // fixedDelay 上次调用结束和下一次调用结束之间的固定周期（以毫秒为单位）执行方法。就是上一次执行完毕时间点之后延迟执行
    @Scheduled(initialDelay = 3000, fixedDelay = 1000)
    public void fixedDelaySch() {
        Date date = new Date();
        String strDate = format.format(date);
        System.out.println("Fixed Delay scheduler:: " + strDate);
    }
}
