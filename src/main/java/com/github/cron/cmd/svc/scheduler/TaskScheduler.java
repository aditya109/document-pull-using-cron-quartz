package com.github.cron.cmd.svc.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class TaskScheduler {

    public static void main(String[] args) {
        try {
            JobDetail job1 = JobBuilder.newJob(JobFactory.class)
                    .withIdentity("job1", "group1").build();

            Trigger trigger1 = TriggerBuilder.newTrigger()
                    .withIdentity("cronTrigger1", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                    .build();

            Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
            scheduler1.start();
            scheduler1.scheduleJob(job1, trigger1);

            Thread.sleep(100000);

            scheduler1.shutdown();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}