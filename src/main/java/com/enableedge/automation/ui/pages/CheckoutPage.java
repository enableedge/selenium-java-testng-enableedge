package com.enableedge.automation.ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.enableedge.automation.utils.CustomLogger;

public class CheckoutPage extends BasePage {
	
	private WebDriverWait wait;
    
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }
    
	
	
	private By nameLocator = By.cssSelector("#address_delivery li.address_firstname.address_lastname");
    private By addressLocator = By.cssSelector("#address_delivery li.address_address1.address_address2");
    private By cityStatePostLocator = By.cssSelector("#address_delivery li.address_city.address_state_name.address_postcode");
    private By countryLocator = By.cssSelector("#address_delivery li.address_country_name");
    private By phoneLocator = By.cssSelector("#address_delivery li.address_phone");
    
    private By placeOrderBtn = By.cssSelector("a[href='/payment']");
    
 // Disabled button (quantity)
    private By quantityButton = By.cssSelector("td.cart_quantity button.disabled");

    
 // Dynamic Product locator
    private By getProductRow(int productId) {
        return By.cssSelector("tr#product-" + productId);
    }
    
    public void clickPlaceOrder() throws InterruptedException {
    	CustomLogger.info("Placing Order");
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderBtn)).click();
    	
    }
    
    public boolean verifyCheckoutDetails(int productId, String expectedName, String expectedAddress,
            String expectedCityStatePost, String expectedCountry, 
            String expectedPhone, String expectedQuantity) {
	    try {
			// Name
			String nameText = wait.until(ExpectedConditions.visibilityOfElementLocated(nameLocator)).getText();
			if (!nameText.contains(expectedName)) return false;
			CustomLogger.info("Name correct");
			
			// Address
			List<WebElement> addressElements = driver.findElements(addressLocator);
			boolean addressMatch = false;
			for (WebElement element : addressElements) {
			    String text = element.getText().trim();
			    if (!text.isEmpty() && text.contains(expectedAddress)) {
			        addressMatch = true;
			        break;
			    }
			}
			if (!addressMatch) return false;
			CustomLogger.info("Address correct");

			
			// City-State-Postcode
			String cityStatePostText = wait.until(ExpectedConditions.visibilityOfElementLocated(cityStatePostLocator)).getText();
			if (!cityStatePostText.contains(expectedCityStatePost)) return false;
			CustomLogger.info("City State Postcode correct");
			
			// Country
			String countryText = wait.until(ExpectedConditions.visibilityOfElementLocated(countryLocator)).getText();
			if (!countryText.contains(expectedCountry)) return false;
			CustomLogger.info("Country correct");
			
			// Phone
			String phoneText = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneLocator)).getText();
			if (!phoneText.contains(expectedPhone)) return false;
			CustomLogger.info("Phone correct");
			
			// Product row check
			if (!wait.until(ExpectedConditions.visibilityOfElementLocated(getProductRow(productId))).isDisplayed())
			return false;
			CustomLogger.info("Product id check");
			
			// Quantity button check
			WebElement quantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityButton));
			String quantityValue = quantityElement.getText().trim();
			if (!quantityValue.equals(expectedQuantity)) return false;

			CustomLogger.info("Quantity checked");
			
			return true; // all verifications passed
		} 
	    catch (Exception e) {
			return false;
		}
	}
}