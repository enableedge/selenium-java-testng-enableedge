package com.enableedge.automation.ui.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.enableedge.automation.utils.CustomLogger;

public class ProductsPage extends BasePage {
	
    private static final String PRODUCTS_URL = "https://www.automationexercise.com/products";

    private By viewCartBtn = By.cssSelector("a[href='/view_cart']");

    @FindBy(xpath = "//div[@class='features_items']")
    private WebElement productsContainer;

    @FindBy(xpath = "//h2[contains(text(), 'Products')]")
    private WebElement productsHeader;

 
    public ProductsPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
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

    public void clickAddToCart(String partialProductName) {
        CustomLogger.info("Adding product containing: " + partialProductName + " to cart");

        // XPath to locate 'p' containing partialProductName and then its sibling 'a'
        String xpath = String.format(
            "//div[@class='productinfo text-center']//p[contains(text(),'%s')]/following-sibling::a[contains(@class,'add-to-cart')]",
            partialProductName
        );

        By addToCartButton = By.xpath(xpath);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }
    
    public void clickAddToCartByName(String partialProductName) {
        CustomLogger.info("Adding product containing: " + partialProductName);

        // Find the product card by partial name (case-insensitive)
        String cardXPath = String.format(
            "//div[contains(@class,'col-sm-4')]//div[contains(@class,'product-image-wrapper')]" +
            "[.//div[contains(@class,'productinfo')]" +
            "[.//p[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), '%s')]]]",
            partialProductName.toLowerCase()
        );

        WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cardXPath)));

        // Scroll into view and hover (overlay add-to-cart becomes visible on hover)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", card);
        new Actions(driver).moveToElement(card).pause(Duration.ofMillis(300)).perform();

        // Prefer overlay add-to-cart; fallback to productinfo add-to-cart
        WebElement addBtn;
        List<WebElement> overlayBtns = card.findElements(By.xpath(".//div[contains(@class,'overlay-content')]//a[contains(@class,'add-to-cart')]"));
        if (!overlayBtns.isEmpty()) {
            addBtn = overlayBtns.get(0);
        } else {
            addBtn = card.findElement(By.xpath(".//div[contains(@class,'productinfo')]//a[contains(@class,'add-to-cart')]"));
        }

        // Click (with JS fallback to avoid intercept issues)
        wait.until(ExpectedConditions.elementToBeClickable(addBtn));
        try {
            addBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
        }
        CustomLogger.info("Clicked Add to Cart for: " + partialProductName);
    }
 // Clicks the "View Cart" button inside the add-to-cart modal
    public void clickViewCartInModal() {
        // Wait for modal header to appear (like viewCart())
        By modalHeader = By.cssSelector("div.modal-header");
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalHeader));

        // Locator for 'View Cart' button inside the modal
        //By viewCartInModal = By.xpath("//div[contains(@class,'modal') and contains(@style,'display: block')]//a[@href='/view_cart']");

        CustomLogger.info("Opening cart from modal");

        // Use JavascriptExecutor for reliable click (like viewCart())
        WebElement viewCartElement = wait.until(ExpectedConditions.elementToBeClickable(viewCartBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCartElement);
    }





    public void viewCart() throws InterruptedException {
    	
    	By modalHeader = By.cssSelector("div.modal-header");
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalHeader));
        CustomLogger.info("Opening cart");

        //wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartBtn)).click();
        WebElement viewCartElement = wait.until(ExpectedConditions.elementToBeClickable(viewCartBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCartElement);

    }
}
