package com.github.cron.master;

import com.github.cron.cmd.handler.ConfigHandler;
import com.github.cron.cmd.svc.TaskScheduler;
import com.github.cron.tasks.GitPullJob;
import org.quartz.SchedulerException;

import java.text.ParseException;
import java.util.Hashtable;

public class Controller {

    public static void main(String[] args) throws ParseException, InterruptedException, SchedulerException {
        System.out.println("🐕 starting watchdog...");
        System.out.println("🚀 initiating config handler...");
        ConfigHandler configHandler = new ConfigHandler();
        Hashtable<String, String> config = null;

        System.out.print("💫 accessing config properties... ");
        String filename = "config/config.properties";
        config = configHandler.readConfigProperties("config/config.txt");

//            print the config dict
        /**
         for (Map.Entry<String, String> entry : config.entrySet()) {
         String key = entry.getKey();
         String value = entry.getValue();
         System.out.println(key + " : " + value);
         }
         */
//
//        TaskScheduler taskScheduler = new TaskScheduler();
//        GitPullJob gitPullJob = new GitPullJob();
//        taskScheduler.runScheduler(gitPullJob);

    }
}
