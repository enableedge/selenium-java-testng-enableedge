package com.enableedge.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewCartPage extends BasePage {

    public ViewCartPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }
    
    private By proceedToCheckoutButton = By.cssSelector("a.btn.btn-default.check_out");
    
    public boolean isProductDisplayed(int productId) {
        String selector = String.format("a[href='/product_details/%d']", productId);
        try {
            return wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.cssSelector(selector)))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isDisabledButtonValueOne() {
        try {
            By disabledButton = By.cssSelector("button.disabled");
            String value = wait.until(ExpectedConditions.visibilityOfElementLocated(disabledButton)).getText();
            return value.trim().equals("1");
        } catch (Exception e) {
            return false;
        }
    }
    public void clickProceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton)).click();
    }


}