package com.xxx.mvn.jobs;

import com.xxx.mvn.util.HttpURLConnectionUtil;
import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        FileOutputStream fileOutputStream = new FileOutputStream();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("定时调度..."+simpleDateFormat.format(new Date()));
        for(int i=0; i<10000000;i++) {
            String sc = HttpURLConnectionUtil.doGet("https://tools.lenovo.com.cn/searchTools/drive/downloadList/id/"+i);
            System.out.println(sc);
//            fileOutputStream.write();
        }
    }


//    /**
//     * 创建 quartz job.
//     *
//     * @param jobName job名字
//     * @param clazz   对应的 类 字节码
//     * @return jobDetail
//     */
//    public static JobDetail createJob(String jobName, Class<? extends StatefulJob> clazz) {
//        return JobBuilder.newJob(clazz).
//                withIdentity(jobName, DEFAULT_GROUP).build();
//    }
//
//    /**
//     * 创建 cron 触发器 (quartz)..
//     * 关于cron表达式  -- <a>http://cron.qqe2.com/</a>
//     *
//     * @param triggerName 触发器名称
//     * @param jobName     job名字 应该与 createJob 里 的第一个参数一致 {@link JobUtil#createJob}
//     * @param cron        cron 表达式,
//     * @return Trigger cron触发器
//     * @throws Exception 抛出异常
//     */
//    public static Trigger createCronTrigger(String triggerName, String jobName, String cron) throws Exception {
//        return TriggerBuilder.newTrigger()
//                .withIdentity(triggerName, DEFAULT_GROUP)
//                .forJob(jobName, DEFAULT_GROUP)
//                .withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
//    }


}
