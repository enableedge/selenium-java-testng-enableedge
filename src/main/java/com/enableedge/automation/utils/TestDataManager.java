package com.enableedge.automation.utils;

import java.util.HashMap;
import java.util.Map;

public class TestDataManager {
    private static Map<String, String> testData = new HashMap<>();

    public static void setTestData(String key, String value) {
        testData.put(key, value);
    }

    public static String getTestData(String key) {
        return testData.get(key);
    }

    public static void clearTestData() {
        testData.clear();
    }

    public static boolean hasTestData(String key) {
        return testData.containsKey(key);
    }

    public static void initializeTestData() {
        testData.put("valid.email", TestDataUtils.getTestData("valid.email"));
        testData.put("valid.password", TestDataUtils.getTestData("valid.password"));
        testData.put("user.name", TestDataUtils.getTestData("user.name"));
        testData.put("user.address", TestDataUtils.getTestData("user.address"));
        testData.put("user.city", TestDataUtils.getTestData("user.city"));
        testData.put("user.state", TestDataUtils.getTestData("user.state"));
        testData.put("user.zipcode", TestDataUtils.getTestData("user.zipcode"));
        testData.put("user.number", TestDataUtils.getTestData("user.number"));
        testData.put("product.name", TestDataUtils.getTestData("product.name"));
        testData.put("product.price", TestDataUtils.getTestData("product.price"));
        testData.put("order.quantity", TestDataUtils.getTestData("order.quantity"));
        testData.put("order.shipping", TestDataUtils.getTestData("order.shipping"));
        testData.put("order.payment", TestDataUtils.getTestData("order.payment"));
    }
}
