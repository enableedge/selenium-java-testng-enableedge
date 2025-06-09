package com.enableedge.automation.utils;

public class EnvironmentUtils {
    private static final String ENVIRONMENT = System.getProperty("env", "local");
    private static final String BASE_URL = System.getProperty("base.url", "https://www.automationexercise.com/");

    public static String getEnvironment() {
        return ENVIRONMENT;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static boolean isLocalEnvironment() {
        return ENVIRONMENT.equalsIgnoreCase("local");
    }

    public static boolean isStagingEnvironment() {
        return ENVIRONMENT.equalsIgnoreCase("staging");
    }

    public static boolean isProductionEnvironment() {
        return ENVIRONMENT.equalsIgnoreCase("production");
    }
}
