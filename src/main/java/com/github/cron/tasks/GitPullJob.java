package com.github.cron.tasks;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class GitPullJob implements org.quartz.Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("com.github.cron.workers.Job1 --->>> Hello geeks! Time is " + new Date());


    }
}
