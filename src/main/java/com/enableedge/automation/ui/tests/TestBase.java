package com.enableedge.automation.ui.tests;

import com.enableedge.automation.utils.CustomLogger;
import com.enableedge.automation.utils.TestDataManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
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
