package com.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @auther: bobo_yu
 * @Date: 2018/12/17 13:08
 * @Description:
 */
@Component
public class TaskTest {

    @Scheduled(cron = "0/5 * * * * ? ") // 间隔5秒执行
    public void taskCycle() {
        System.out.println("使用SpringMVC框架配置定时任务 - 5s 一次");
    }

}
