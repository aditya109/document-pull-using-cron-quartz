package com.github.cron.cmd.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

public class ConfigHandler {

    public Hashtable<String, String> readConfigProperties() throws IOException {
        String fileRelativePath = "config/config.properties";

//        creating a properties object for storing config properties
        Properties properties = new Properties();

        Hashtable<String, String> config = null;
        try {
            // creating a classloader object
            ClassLoader classLoader = getClass().getClassLoader();
            // reading the resource off the relative path given
            File file = new File(classLoader.getResource(fileRelativePath).getFile());
            // File is found
            System.out.println("file found : " + file.exists());
            // using FileReader the file content
            FileReader fileReader = new FileReader(file);

            properties.load(fileReader);

            // assign the property names in a enumeration
            Enumeration<?> enumeration = properties.propertyNames();

            // creating a My HashTable Dictionary
            config = new Hashtable<String, String>();

            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement().toString();
                String value = properties.getProperty(key);
                config.put(key, value);
            }

        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        } finally {
            return config;
        }

    }
}
