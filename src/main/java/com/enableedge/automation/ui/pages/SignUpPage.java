package com.enableedge.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import static org.testng.Assert.*;  // For assertTrue, assertEquals, etc.
import java.time.Duration;          // For defining explicit wait durations
import org.testng.Assert;

import com.enableedge.automation.utils.CustomLogger;

//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.JavascriptExecutor;


public class SignUpPage extends BasePage {
    private static final String SIGNUP_URL = "https://www.automationexercise.com/login";

    private By nameField = By.cssSelector("input[data-qa='signup-name'	]");
    private By emailField = By.cssSelector("input[data-qa='signup-email']");
    private By signUpButton = By.cssSelector("button[data-qa='signup-button']");
    private By signUpSuccess = By.cssSelector("h2[data-qa='account-created']");
    //private By deleteSuccess = By.cssSelector("h2[data-qa='account-deleted']");
    //private By errorMessage = By.cssSelector(".signup-form .alert-danger");
    private By errorMessage = By.cssSelector("form[action='/signup'] > p");

    private By signUpForm = By.cssSelector("[action='/signup']");
    private By passWord = By.cssSelector("input[data-qa='password']");
    private By fName = By.cssSelector("input[data-qa='first_name']");
    private By lName = By.cssSelector("input[data-qa='last_name']");
    private By addressField = By.cssSelector("input[data-qa='address']");
    private By stateField = By.cssSelector("input[data-qa='state']");
    private By cityField = By.cssSelector("input[data-qa='city']");
    private By zipcodeField = By.cssSelector("input[data-qa='zipcode']");
    private By mobileNum = By.cssSelector("input[data-qa='mobile_number']");
    private By createAccountBtn = By.cssSelector("button[data-qa='create-account']");
    private By deleteAcc = By.cssSelector("a[href='/delete_account']");
    private By continueBtn = By.cssSelector("a[data-qa='continue-button']");
    //private By continueDeleteAccBtn = By.cssSelector("\"a[href='/logout']\"");
  

    public SignUpPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    public void navigateToSignUpPage() {
        driver.get(SIGNUP_URL);
    }

    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    public void enterName() {
        driver.findElement(nameField).sendKeys("Bill Nadella");
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    public void enterEmail() {
        driver.findElement(emailField).sendKeys("wrongmail@example.com");
    }
    public void enterPassword() {
        driver.findElement(passWord).sendKeys("abcd");
    }
    public void enterFullName() {
        driver.findElement(fName).sendKeys("Bill");
        driver.findElement(lName).sendKeys("Nadella");
    }
    public void enterAddress() {
        driver.findElement(addressField).sendKeys("B1");
        driver.findElement(stateField).sendKeys("Maha");
        driver.findElement(cityField).sendKeys("Mum");
        driver.findElement(zipcodeField).sendKeys("400");
    }
    public void enterNumber() {
        driver.findElement(mobileNum).sendKeys("9819376202");
    }
    
    public void clickCreateAccount() {
        driver.findElement(createAccountBtn).click();
    }
    
    public void continueButton() {
        driver.findElement(continueBtn).click();
	
    }
    
    public void deleteAccount() {
    	driver.findElement(deleteAcc).click();
    }
    

    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    public boolean isAccountCreated() {
        try {
        	return wait.until(ExpectedConditions.presenceOfElementLocated(signUpSuccess)).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }
    

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSignUpFormDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(signUpForm)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void verifySignUpSuccess() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By signUpSuccess = By.xpath("//h2[contains(text(),'Account Created')]");
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(signUpSuccess));
        Assert.assertTrue(driver.findElement(signUpSuccess).isDisplayed(),
                "Signup success message is not displayed");
        System.out.println("Account successfully created!");
    }

    public void findDeleteAccountBtn() throws InterruptedException {

    	driver.findElement(deleteAcc).click();	
   
        CustomLogger.info("Deleted account");
        Thread.sleep(2000);
//        driver.findElement(continueBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueBtn)).click();
        
    }

    public void verifySignUpSuccessAndProceed() throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    	Thread.sleep(2000);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(signUpSuccess));
        CustomLogger.info("Account successfully created!");
        //driver.findElement(continueBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueBtn)).click();
        CustomLogger.info("Clicked continue btn");
        // Wait for home page URL
        //wait.until(ExpectedConditions.urlToBe("https://www.automationexercise.com"));
        CustomLogger.info("Account Succcessfully Created");
        
    }

}
