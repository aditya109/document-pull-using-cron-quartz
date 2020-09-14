package com.github.cron.cmd.handler.confighandler;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

class Configuration {
    Map<String, Object> yamlConfig;

    public Configuration() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this
                .getClass()
                .getClassLoader()
                .getResourceAsStream("config/config.yaml");
        this.yamlConfig = yaml.load(inputStream);
    }
}

public class ConfigSingleton {
    private static ConfigSingleton single_instance = null;
    public Configuration configuration;

    private ConfigSingleton() {
        configuration = new Configuration();
    }

    public static ConfigSingleton getInstance() {
        if (single_instance == null)
            single_instance = new ConfigSingleton();
        return single_instance;
    }
}
/*
    ConfigSingleton configSingleton = ConfigSingleton.getInstance();
        System.out.println(configSingleton.configuration.yamlConfig);
*/
