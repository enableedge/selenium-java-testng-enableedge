package com.enableedge.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage extends BasePage {
    private static final String SIGNUP_URL = "https://www.automationexercise.com/signup";

    private final By nameField = By.cssSelector("input[name='name']");
    private final By emailField = By.cssSelector("input[name='email']");
    private final By errorMessage = By.cssSelector(".signup-form p[style*='red']");
    private final By signUpForm = By.cssSelector(".signup-form");
    private final By emailSignUpField = By.cssSelector("input[data-qa='signup-email']");
    private final By signUpBtn = By.cssSelector("button[data-qa='signup-button']");
    private final By verifyDetailsPage = By.cssSelector("div[class='login-form']");

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

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterSignUpEmail(String email) {
        driver.findElement(emailSignUpField).sendKeys(email);
    }

    public void clickSignUpButton() {
        driver.findElement(signUpBtn).click();
    }

    public boolean isSignUpSuccessDisplayed() {
        try {
            // return wait.until(ExpectedConditions.visibilityOfElementLocated(signUpSuccess)).isDisplayed();
            return wait.until(ExpectedConditions.visibilityOfElementLocated(verifyDetailsPage)).isDisplayed();
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

    public void verifyCredentialsPage() {
        driver.findElement(verifyDetailsPage);
    }
}

