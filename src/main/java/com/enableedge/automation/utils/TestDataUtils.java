package com.enableedge.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataUtils {
    private static Properties properties;
    private static final String TEST_DATA_FILE = "src/main/resources/testdata.properties";

    static {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(TEST_DATA_FILE);
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTestData(String key) {
        return properties.getProperty(key);
    }

    public static void setTestData(String key, String value) {
        properties.setProperty(key, value);
    }

    public static String getRandomEmail() {
        return "test" + System.currentTimeMillis() + "@example.com";
    }

    public static String getRandomPassword() {
        return "password" + System.currentTimeMillis();
    }

    public static String getRandomName() {
        return "Test User " + System.currentTimeMillis();
    }
}
