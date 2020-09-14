package com.github.cron.master;

import com.github.cron.cmd.handler.confighandler.ConfigHandler;
import com.github.cron.cmd.handler.destinationdeltahandler.DestinationDeltaHandler;
import com.github.cron.cmd.handler.githandler.GitHandler;
import org.quartz.SchedulerException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.Map;

public class Controller {

    public static void main(String[] args) throws ParseException, InterruptedException, SchedulerException, IOException, org.json.simple.parser.ParseException {
        ConfigHandler configHandler = new ConfigHandler();
        Map<String, Object> yamlConfig = configHandler.paramProvider();

        GitHandler gitHandler = new GitHandler();
        String gitLink = yamlConfig.get("gitLink").toString();
        Hashtable<String, Object> responseMap = gitHandler.controller(gitLink);

        DestinationDeltaHandler destinationDeltaHandler = new DestinationDeltaHandler();
        destinationDeltaHandler.controller(responseMap);
    }
}
