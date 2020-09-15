package com.github.cron.master;

import com.github.cron.cmd.svc.scheduler.TaskScheduler;

public class Controller {
    public static void main(String[] args) {
        //  initialize and start TaskScheduler
        TaskScheduler.schedule();
    }
}
