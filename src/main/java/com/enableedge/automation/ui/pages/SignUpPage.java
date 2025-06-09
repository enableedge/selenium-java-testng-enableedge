package com.enableedge.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage extends BasePage {
    private static final String SIGNUP_URL = "https://www.automationexercise.com/signup";

    private By nameField = By.cssSelector("input[name='name']");
    private By emailField = By.cssSelector("input[name='email']");
    private By signUpButton = By.cssSelector("button[type='submit']");
    private By signUpSuccess = By.cssSelector(".signup-form h2");
    private By errorMessage = By.cssSelector(".signup-form .alert-danger");
    private By signUpForm = By.cssSelector(".signup-form");

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

    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    public boolean isSignUpSuccessDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(signUpSuccess)).isDisplayed();
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
}
