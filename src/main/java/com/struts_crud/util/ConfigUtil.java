package com.struts_crud.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtil {
	
    private static final Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                logger.error("Sorry, unable to find application.properties");

            }
            properties.load(input);
        }catch (IOException e) {
            logger.error("Error loading application.properties", e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
