package com.github.cron.cmd.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Properties;

public class ConfigHandler {

    public Hashtable<String, String> readConfigProperties(String fileRelativePath) {

//        creating a properties object for storing config properties
        Properties properties = new Properties();

        Hashtable<String, String> config = null;

        // creating a classloader object
        ClassLoader classLoader = getClass().getClassLoader();
        // reading the resource off the relative path given
        File file = null;
        FileReader fileReader = null;
        try {
            file = new File(Objects.requireNonNull(classLoader.getResource(fileRelativePath)).getFile());
        } catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
            System.out.println("📛 config.properties file not found");
            return null;
        }
        // using FileReader the file content
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        try {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // assign the property names in a enumeration
        Enumeration<?> enumeration = properties.propertyNames();

        // creating a My HashTable Dictionary
        config = new Hashtable<>();

        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString();
            String value = properties.getProperty(key);
            config.put(key, value);
        }
        return config;
    }
}
