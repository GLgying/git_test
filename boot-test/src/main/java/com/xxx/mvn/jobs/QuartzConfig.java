package com.xxx.mvn.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;

//@Configuration
public class QuartzConfig  {

    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(MyJob.class)
                .storeDurably().build();
    }

    @Bean
    public Trigger trigger1() {
        SimpleScheduleBuilder scheduleBuilder =SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10) //时间为 参数*单位 10*秒  = 10秒执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .withSchedule(scheduleBuilder)
                .forJob(jobDetail())
                .build();
    }

//    @Bean
//    public Trigger trigger2() {
//
//        return TriggerBuilder.newTrigger()
//                .withIdentity("trigger2","group1")
//                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? *"))
//                .forJob(jobDetail())
//                .build();
//    }


//    @Bean
//    public Trigger trigger3() {
//        return TriggerBuilder.newTrigger()
//                .forJob(jobDetail())
//                .withSchedule(scheduleBuilder) //日程
//                .build();
//    }
}
