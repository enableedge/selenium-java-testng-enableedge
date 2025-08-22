package com.enableedge.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage extends BasePage {

    // Locators
    private By nameOnCardField = By.cssSelector("input[data-qa='name-on-card']");
    private By cardNumberField = By.cssSelector("input[data-qa='card-number']");
    private By cvcField = By.cssSelector("input[data-qa='cvc']");
    private By expiryMonthField = By.cssSelector("input[data-qa='expiry-month']");
    private By expiryYearField = By.cssSelector("input[data-qa='expiry-year']");
    private By payButton = By.cssSelector("button[data-qa='pay-button']");

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    // Method to fill payment details
    public void fillPaymentDetails(String name) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameOnCardField)).sendKeys(name);
        driver.findElement(cardNumberField).sendKeys("4111111111111111"); // Hardcoded Card Number
        driver.findElement(cvcField).sendKeys("123"); // Hardcoded CVC
        driver.findElement(expiryMonthField).sendKeys("10"); // Hardcoded Month
        driver.findElement(expiryYearField).sendKeys("2026"); // Hardcoded Year
    }

    // Method to click Pay and Confirm Order
    public void clickPayAndConfirm() throws InterruptedException {
        Thread.sleep(1000); // slight wait before clicking
        wait.until(ExpectedConditions.elementToBeClickable(payButton)).click();
    }
    
 // Verify order confirmation and click Continue
    public void verifyOrderAndContinue() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2[data-qa='order-placed']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-qa='continue-button']"))).click();
    }
}
