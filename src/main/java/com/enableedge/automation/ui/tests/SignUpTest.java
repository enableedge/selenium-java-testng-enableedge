package com.enableedge.automation.ui.tests;

import com.enableedge.automation.ui.pages.HomePage;
import com.enableedge.automation.ui.pages.SignUpPage;
import com.enableedge.automation.utils.CustomLogger;
import com.enableedge.automation.utils.TestDataManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class SignUpTest extends TestBase {
    private HomePage homePage;
    private SignUpPage signUpPage;

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) {
        super.setUp(browser);
        CustomLogger.info("Starting SignUpTest setup for browser: " + browser);
        homePage = new HomePage(driver);
        signUpPage = new SignUpPage(driver);
        TestDataManager.initializeTestData();
    }

    @Test(priority = 1)
    public void testSignUpWithValidCredentials() {
        String name = TestDataManager.getTestData("user.name");
        String email = TestDataManager.getTestData("valid.email");
        
        CustomLogger.info("Starting signup test with valid credentials: " + email);
        
        homePage.navigateToHomePage();
        homePage.clickSignUpLink();
        signUpPage.enterName(name);
        signUpPage.enterEmail(email);
        signUpPage.clickSignUpButton();
        
        CustomLogger.info("Verifying successful signup");
        assertTrue(signUpPage.isSignUpSuccessDisplayed(), "Signup success message not displayed");
    }

    @Test(priority = 2)
    public void testSignUpWithInvalidEmail() {
        String name = TestDataManager.getTestData("valid.name");
        String email = "invalid-email";
        
        CustomLogger.info("Starting signup test with invalid email: " + email);
        
        homePage.navigateToHomePage();
        homePage.clickSignUpLink();
        signUpPage.enterName(name);
        signUpPage.enterEmail(email);
        signUpPage.clickSignUpButton();
        
        CustomLogger.info("Verifying error message display");
        assertTrue(signUpPage.isErrorMessageDisplayed(), "No error message displayed for invalid email");
    }

    @Test(priority = 3)
    public void testSignUpFormNavigation() {
        homePage.navigateToHomePage();
        homePage.clickSignUpLink();
        
        assertTrue(signUpPage.isSignUpFormDisplayed(), "Signup form should be displayed");
    }
}
