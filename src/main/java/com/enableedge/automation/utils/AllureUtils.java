package com.enableedge.automation.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureUtils {
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            return null;
        }
    }

    @Attachment(value = "Page Source", type = "text/html")
    public static String attachPageSource(WebDriver driver) {
        try {
            return driver.getPageSource();
        } catch (Exception e) {
            System.err.println("Failed to get page source: " + e.getMessage());
            return "Failed to get page source";
        }
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String attachText(String message) {
        return message;
    }

    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }
}
