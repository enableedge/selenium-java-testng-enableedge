package com.enableedge.automation.ui.tests;

import com.enableedge.automation.ui.pages.HomePage;
import com.enableedge.automation.ui.pages.SignUpPage;
import com.enableedge.automation.utils.CustomLogger;
import com.enableedge.automation.utils.TestDataManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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

    @BeforeMethod
    public void beforeEachTest() {
        driver.get("https://www.automationexercise.com");
    }

    // 1. Valid Sign-Up (No arguments - Random name/email)
    @Test(priority = 1)
    public void testSignUpWithValidCredentials() throws InterruptedException {
        CustomLogger.info("Starting signup test with new valid credentials (no args)");

        homePage.navigateToHomePage();
        homePage.clickSignUpLink();

        // No-args version → generates random new name/email
        signUpPage.enterName();
        signUpPage.enterEmail();
        signUpPage.clickSignUpButton();

        // Fill other required fields
        signUpPage.enterPassword();
        signUpPage.enterFullName();
        signUpPage.enterAddress();
        signUpPage.enterNumber();
        signUpPage.clickCreateAccount();

        CustomLogger.info("Completing signup, continue, and deleting account");
        
        signUpPage.verifySignUpSuccessAndProceed();
        signUpPage.findDeleteAccountBtn();
        CustomLogger.info("Completed deleted acc");

    }

    // 2. Invalid Sign-Up (Arguments passed - Duplicate credentials)
    @Test(priority = 2)
    public void testSignUpWithInvalidCredentials() throws InterruptedException {
        String name = TestDataManager.getTestData("user.name");   // duplicate/existing user
        String email = TestDataManager.getTestData("valid.email"); // duplicate/invalid email

        CustomLogger.info("Starting signup test with invalid/duplicate credentials: " + email);

        // Ensure logged-out state or previous account cleanup
        


        //homePage.navigateToHomePage();
        homePage.clickSignUpLink();

        // Arg version → existing user data
        signUpPage.enterName(name);
        signUpPage.enterEmail(email);
        signUpPage.clickSignUpButton();

        CustomLogger.info("Verifying unsuccessful signup");
        assertTrue(signUpPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid/duplicate credentials");
    }

    // 3. Navigation Test - Checks if Sign-Up Form is Displayed
    @Test(priority = 3)
    public void testSignUpFormNavigation() throws InterruptedException {
        homePage.navigateToHomePage();
        Thread.sleep(2000);
        homePage.clickSignUpLink();

        CustomLogger.info("Checking for signup form");
        assertTrue(signUpPage.isSignUpFormDisplayed(), "Signup form should be displayed");
        CustomLogger.info("Checked");
    }
}
