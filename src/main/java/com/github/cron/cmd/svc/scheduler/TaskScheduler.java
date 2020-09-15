package com.github.cron.cmd.svc.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class TaskScheduler {
    public static void schedule() {
        //  function to start the schedule
        try {
            //  creating `JobDetail` as a job
            JobDetail job1 = JobBuilder
                    .newJob(JobFactory.class)
                    .withIdentity("job1", "group1")
                    .build();
            //  creating `Trigger` as a trigger
            Trigger trigger1 = TriggerBuilder.newTrigger()
                    .withIdentity("cronTrigger1", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                    .build();
            //  creating `Schedule` as a schedule
            Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
            //  start `Schedule`
            scheduler1.start();
            //  schedule `Job`
            scheduler1.scheduleJob(job1, trigger1);

            Thread.sleep(100000);
            //  shutdown schedule
            scheduler1.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}