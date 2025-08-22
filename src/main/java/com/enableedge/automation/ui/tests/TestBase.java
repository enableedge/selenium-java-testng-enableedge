package com.enableedge.automation.ui.tests;

import com.enableedge.automation.utils.CustomLogger;
import com.enableedge.automation.utils.TestDataManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;

public class TestBase {
    protected WebDriver driver;
    private static final String CHROME = "chrome";
    private static final String EDGE = "edge";

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) {
        CustomLogger.info("Starting TestBase setup for browser: " + browser);
        
        switch (browser.toLowerCase()) {
//            case CHROME:
//                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--start-maximized");
//
//                // These are the critical arguments to block the password breach warning
//                chromeOptions.addArguments(
//                        "--disable-features=PasswordLeakDetection",
//                        "--disable-blink-features=PasswordLeakDetection",
//                        "--password-store=basic",
//                        "--disable-blink-features=AutomationControlled"
//                );
//
//                chromeOptions.setExperimentalOption("prefs", Map.of(
//                        "password_leak_detection.enabled", false,
//                        "profile.password_manager_leak_detection", false,
//                        "credentials_enable_service", false,
//                        "profile.password_manager_enabled", false
//                ));
//                driver = new ChromeDriver(chromeOptions);
//                break;
        	case CHROME:
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");

            // Disable password breach warnings (you already have)
            chromeOptions.addArguments(
                    "--disable-features=PasswordLeakDetection",
                    "--disable-blink-features=PasswordLeakDetection",
                    "--password-store=basic",
                    "--disable-blink-features=AutomationControlled"
            );

            // Chrome preferences
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.notifications", 2); // Block notifications
            prefs.put("profile.default_content_setting_values.geolocation", 2);   // Block geolocation
            prefs.put("profile.default_content_setting_values.media_stream_camera", 2); // Block camera
            prefs.put("profile.default_content_setting_values.media_stream_mic", 2);    // Block mic
            prefs.put("profile.default_content_setting_values.popups", 2);       // Block popups
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("password_leak_detection.enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);
            prefs.put("profile.default_content_setting_values.autofill_address_enabled", 2); // Block save address
            prefs.put("profile.default_content_setting_values.autofill_credit_card_enabled", 2); // Block save card
            
            prefs.put("autofill.profile_enabled", false);
            prefs.put("autofill.credit_card_enabled", false);
            prefs.put("autofill.enabled", false);
            
            prefs.put("profile.default_content_setting_values.autofill", 2);
            //prefs.put("profile.password_manager_enabled", false);
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.autofill_profile_enabled", false);
            prefs.put("profile.autofill_credit_card_enabled", false);
            chromeOptions.setExperimentalOption("prefs", prefs);

            // Optional: run Chrome in automation-friendly mode
            chromeOptions.setExperimentalOption("excludeSwitches", 
                List.of("enable-automation", "enable-logging"));

            driver = new ChromeDriver(chromeOptions);
            break;

            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        TestDataManager.initializeTestData();
        CustomLogger.info("Test data initialized successfully");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            CustomLogger.info("Closing browser");
            driver.quit();
        }
    }
}
