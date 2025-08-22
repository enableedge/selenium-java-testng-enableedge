package com.enableedge.automation.ui.tests;

import com.enableedge.automation.ui.pages.HomePage;
import com.enableedge.automation.ui.pages.SignUpPage;
import com.enableedge.automation.ui.pages.ProductsPage;
import com.enableedge.automation.ui.pages.ViewCartPage;
import com.enableedge.automation.ui.pages.CheckoutPage;
import com.enableedge.automation.ui.pages.PaymentPage;
import com.enableedge.automation.utils.CustomLogger;
import com.enableedge.automation.utils.TestDataManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import org.testng.Assert;

public class OrderTest extends TestBase {
    private HomePage homePage;
    private SignUpPage signUpPage;
    private ProductsPage productsPage;
    private ViewCartPage viewCartPage;
    private CheckoutPage checkoutPage;
    private PaymentPage paymentPage;
    

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) {
        super.setUp(browser);
        CustomLogger.info("Starting SignUpTest setup for browser: " + browser);
        
        homePage = new HomePage(driver);
        signUpPage = new SignUpPage(driver);
        productsPage = new ProductsPage(driver);
        viewCartPage = new ViewCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        paymentPage = new PaymentPage(driver);
        TestDataManager.initializeTestData();
    }

    @BeforeMethod
    public void beforeEachTest() {
        driver.get("https://www.automationexercise.com");
    }
    
 // 1. Valid Sign-Up (No arguments - Random name/email)
    @Test(priority = 1)
    public void testSignUpAndCheckout() throws InterruptedException {
        CustomLogger.info("Starting signup with new valid credentials (no args)");

        homePage.navigateToHomePage();
        homePage.clickSignUpLink();

       
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
        
//        homePage.clickProducts();
//        productsPage.clickAddToCart("Men Tshirt");
//        productsPage.viewCart();
        homePage.clickProducts();               // however you navigate to Products
        productsPage.clickAddToCartByName("Men");
        productsPage.clickViewCartInModal();
        
        assertTrue(viewCartPage.isProductDisplayed(2), "The product inside cart should match what we added");
        CustomLogger.info("Product id successfully matched");
        assertTrue(viewCartPage.isDisabledButtonValueOne(), "Disabled button does not have value 1");
        CustomLogger.info("Quantity checked for 1");

        viewCartPage.clickProceedToCheckout();
        
        CustomLogger.info("Now verifying checkout details");
        boolean isVerified = checkoutPage.verifyCheckoutDetails(
        	    2, ". Bill Nadella", "B1", "Mum Maha 400", "India", "9819376202", "1"
        	);
        CustomLogger.info("verifyCheckoutDetails has returned a value "+ isVerified);
        Assert.assertTrue(isVerified, "Checkout details verification failed!");
        checkoutPage.clickPlaceOrder();
        
        paymentPage.fillPaymentDetails("Bill Nadella");
        paymentPage.clickPayAndConfirm();
        paymentPage.verifyOrderAndContinue();
        
        signUpPage.findDeleteAccountBtn();	
    }
    
}