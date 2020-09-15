package com.github.cron.cmd.handler.confighandler;

import java.util.Map;

public class ConfigHandler {
    public Map<String, Object> paramProvider() {
        // creating `ConfigSingleton` instance
        ConfigSingleton configSingleton = ConfigSingleton.getInstance();
        //  returning yaml from `ConfigSingleton` instance
        return configSingleton.configuration.yamlConfig;
    }
}
