package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetails {
	private WebDriver driver;

	By buyButton = By.cssSelector("[name=topurchases]");

	public WebElement buyButtonOnDetails() {
		return driver.findElement(buyButton);
	}

	public ProductDetails(WebDriver driver) {
		this.driver = driver;
	}
}
