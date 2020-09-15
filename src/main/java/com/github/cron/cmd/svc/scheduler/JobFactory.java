package com.github.cron.cmd.svc.scheduler;

import com.github.cron.cmd.handler.confighandler.ConfigHandler;
import com.github.cron.cmd.handler.destinationdeltahandler.DestinationDeltaHandler;
import com.github.cron.cmd.handler.githandler.GitHandler;
import org.json.simple.parser.ParseException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class JobFactory implements Job {
    public void execute(JobExecutionContext jobExecutionContext) {
        //  creating `ConfigHandler` object
        ConfigHandler configHandler = new ConfigHandler();
        //  retrieving yaml config as Map<String, Object>
        Map<String, Object> yamlConfig = configHandler.paramProvider();

        //  getting `gitLink` from yamlConfig
        String gitLink = yamlConfig.get("gitLink").toString();
        //  creating `GitHandler` object
        GitHandler gitHandler = new GitHandler(gitLink);
        //  getting `responseMap` from controller function of `GitHandler` object
        Hashtable<String, Object> responseMap = null;
        try {
            responseMap = gitHandler.controller();
            if (responseMap == null) return;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        //  getting `destinationPath` from yaml config
        String destinationPath = yamlConfig.get("destinationPath").toString();
        //  creating `DestinationDeltaHandler` object
        DestinationDeltaHandler destinationDeltaHandler = new DestinationDeltaHandler(responseMap, destinationPath);
        //  triggering controller of `DestinationDeltaHandler` object
        try {
            destinationDeltaHandler.controller();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
