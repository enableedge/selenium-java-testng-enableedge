package com.enableedge.automation.ui.tests;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.enableedge.automation.ui.pages.HomePage;
import com.enableedge.automation.ui.pages.SignUpPage;
import com.enableedge.automation.utils.CustomLogger;
import com.enableedge.automation.utils.TestDataManager;

public class SignUpTest extends TestBase {

    private HomePage homePage;
    private SignUpPage signUpPage;

    @Parameters("browser")
    @BeforeClass // A method that is carried out one-time before doing a task (like setting things up)
    @Override
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
        String email = "messi@football.com";

        CustomLogger.info("Starting signup test with valid credentials: " + email);

        homePage.navigateToHomePage();
        homePage.clickSignUpLink();
        signUpPage.enterName(name);
        signUpPage.enterSignUpEmail(email);
        signUpPage.clickSignUpButton();
        signUpPage.verifyCredentialsPage();

        CustomLogger.info("Verifying successful signup");
        assertTrue(signUpPage.isSignUpSuccessDisplayed(), "Signup success message not displayed");
    }

    @Test(priority = 2)
    public void testSignUpWithInvalidEmail() {
        String name = TestDataManager.getTestData("user.name");
        String email = "ulhas@enableedge.com";

        CustomLogger.info("Starting signup test with invalid email: " + email);

        homePage.navigateToHomePage();
        homePage.clickSignUpLink();
        signUpPage.enterName(name);
        signUpPage.enterSignUpEmail(email);
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
