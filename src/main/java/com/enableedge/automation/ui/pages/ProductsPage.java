package com.enableedge.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage extends BasePage {
    private static final String PRODUCTS_URL = "https://www.automationexercise.com/products";

    @FindBy(xpath = "//div[@class='features_items']")
    private WebElement productsContainer;

    @FindBy(xpath = "//h2[contains(text(), 'Products')]")
    private WebElement productsHeader;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToProductsPage() {
        driver.get(PRODUCTS_URL);
    }

    public boolean areProductsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(productsContainer)).isDisplayed();
    }

    public boolean isProductsHeaderDisplayed() {
        try {
            return productsHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getProductCount() {
        try {
            return driver.findElements(By.className("productinfo")).size();
        } catch (Exception e) {
            return 0;
        }
    }
}
