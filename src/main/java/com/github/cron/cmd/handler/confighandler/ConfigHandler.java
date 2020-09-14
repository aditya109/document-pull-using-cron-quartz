package com.github.cron.cmd.handler.confighandler;

import java.util.Map;

public class ConfigHandler {
    public Map<String, Object> paramProvider() {
        ConfigSingleton configSingleton = ConfigSingleton.getInstance();
        return configSingleton.configuration.yamlConfig;
    }
}
