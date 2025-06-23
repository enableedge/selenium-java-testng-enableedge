package com.enableedge.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private static final String LOGIN_URL = "https://www.automationexercise.com/login";

    private WebDriverWait wait;

    private final By emailInput = By.cssSelector("input[data-qa='login-email']");
    private final By passwordInput = By.cssSelector("input[data-qa='login-password']");
    private final By loginButton = By.cssSelector("button[data-qa='login-button']");
    private final By loginForm = By.cssSelector(".login-form");
    private final By errorMessage = By.cssSelector(".login-form p[style*='red']");
    private final By logOut = By.cssSelector("a[href='/logout']");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    public LoginPage(WebDriverWait wait, WebDriver driver) {
        super(driver);
        this.wait = wait;
    }

    public void navigateToLoginPage() {
        driver.get(LOGIN_URL);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void enterLoginCredentials(String email, String password) {
        enterEmail(email);
        enterPassword(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isLoginFormDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm)).isDisplayed();
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

    public void clickLogOut() {
        driver.findElement(logOut).click();
    }
}
