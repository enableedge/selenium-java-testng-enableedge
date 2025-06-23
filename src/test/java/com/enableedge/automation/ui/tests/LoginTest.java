package com.enableedge.automation.ui.tests;

import static org.testng.Assert.assertTrue;

// import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.enableedge.automation.ui.pages.HomePage;
import com.enableedge.automation.ui.pages.LoginPage;
import com.enableedge.automation.utils.CustomLogger;
import com.enableedge.automation.utils.TestDataManager;



public class LoginTest extends TestBase {

    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        super.setUp(browser);
        CustomLogger.info("Starting LoginTest setup for browser: " + browser);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        TestDataManager.initializeTestData();
    }

    @Test(priority = 1)
    public void testLoginWithValidCredentials() {
        String email = TestDataManager.getTestData("valid.email");
        String password = TestDataManager.getTestData("valid.password");

        CustomLogger.info("Starting login test with valid credentials: " + email);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();
        loginPage.enterLoginCredentials(email, password);
        loginPage.clickLoginButton();

        CustomLogger.info("Verifying successful login");
        assertTrue(homePage.isLoggedIn(), "User not logged in after valid credentials");
        loginPage.clickLogOut();
    }

    @Test(priority = 2)
    public void testLoginWithInvalidEmail() {
        String email = "invalid@test.com";
        String password = TestDataManager.getTestData("valid.password");

        CustomLogger.info("Starting login test with invalid email: " + email);

        homePage.navigateToHomePage();
        homePage.clickLoginLink();
        loginPage.enterLoginCredentials(email, password);
        loginPage.clickLoginButton();

        CustomLogger.info("Verifying error message display");
        assertTrue(loginPage.isErrorMessageDisplayed(), "No error message displayed for invalid email");
    }

    @Test(priority = 3)
    public void testLoginFormNavigation() {
        homePage.navigateToHomePage();
        homePage.clickLoginLink();

        assertTrue(loginPage.isLoginFormDisplayed(), "Login form should be displayed");
    }
}
