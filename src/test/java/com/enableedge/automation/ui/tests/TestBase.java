package com.enableedge.automation.ui.tests;

import com.enableedge.automation.utils.CustomLogger;
import com.enableedge.automation.utils.TestDataManager;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBase {

    protected WebDriver driver;
    private static final String CHROME = "chrome";
    private static final String EDGE = "edge";

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) {
        CustomLogger.info("Starting TestBase setup for browser: " + browser);

        switch (browser.toLowerCase()) {
            case CHROME:
                ChromeOptions Options = new ChromeOptions();
                Options.addArguments("--start-maximized");

                // These are the critical arguments to block the password breach warning
                Options.addArguments(
                        "--disable-features=PasswordLeakDetection",
                        "--disable-blink-features=PasswordLeakDetection",
                        "--password-store=basic"
                );

                Options.setExperimentalOption("prefs", Map.of(
                        "password_leak_detection.enabled", false,
                        "profile.password_manager_leak_detection", false,
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false
                ));

                driver = new ChromeDriver(Options);
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
