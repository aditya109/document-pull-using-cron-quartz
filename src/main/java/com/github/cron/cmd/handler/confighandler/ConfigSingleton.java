package com.github.cron.cmd.handler.confighandler;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

class Configuration {
    //  declaring Map object to store yaml config
    Map<String, Object> yamlConfig;

    public Configuration() {
        //  Configuration constructor
        //  creating Yaml object
        Yaml yaml = new Yaml();
        //  retrieving `config/config.yaml` from `resources` directory in this project
        InputStream inputStream = this
                .getClass()
                .getClassLoader()
                .getResourceAsStream("config/config.yaml");
        //  loading yaml from `InputStream`
        this.yamlConfig = yaml.load(inputStream);
    }
}

public class ConfigSingleton {
    //  declaring and initializing Singleton object
    private static ConfigSingleton single_instance = null;
    //  declaring Configuration object
    public Configuration configuration;

    private ConfigSingleton() {
        //  ConfigSingleton constructor
        //  initializing `Configuration` object
        configuration = new Configuration();
    }

    public static ConfigSingleton getInstance() {
        //  function returns Singleton `Configuration` object
        if (single_instance == null)
            single_instance = new ConfigSingleton();
        return single_instance;
    }
}
