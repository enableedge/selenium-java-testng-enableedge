package com.enableedge.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    private static final String HOME_URL = "https://www.automationExercise.com";

    private By loginLink = By.cssSelector("a[href='/login']");
    private By signUpLink = By.cssSelector("a[href='/signup']");
    private By signUpForm = By.cssSelector(".signup-form");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        driver.get(HOME_URL);
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    public void clickSignUpLink() {
        driver.findElement(signUpLink).click();
    }

    public boolean isSignUpFormDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(signUpForm)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoggedIn() {
        By logoutLink = By.cssSelector("a[href='/logout']");
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
