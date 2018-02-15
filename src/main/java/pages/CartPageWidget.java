package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPageWidget {
	private WebDriver driver;
	
	By sum = By.cssSelector("[name='sum']");
	By costTotal = By.cssSelector("[name='cost']");
	By cartButton = By.cssSelector("[href='https://my.rozetka.com.ua/cart/']");
	By continuePurchasingButton = By.cssSelector("div#cart-popup > a");
	By deleteItemIcon = By.cssSelector("[name='quantity']");
	By itemLink = By.cssSelector("[name='goods-link']");
	
	public List<WebElement> findListOfItems () {
		return driver.findElements(itemLink);
	}
	
	public List<WebElement> findDeleteItemIcon () {
		return driver.findElements(deleteItemIcon);
	}
	
	public WebElement getTotalCost () {
		return driver.findElement(costTotal);
	}
	
	public WebElement continuePurchasing () {
		return driver.findElement(continuePurchasingButton);
	}
	
	public List<WebElement> findSum  () {
		return driver.findElements(sum);
	}
	
	public WebElement findCartButton () {
		return driver.findElement(cartButton);
	}
	
	public CartPageWidget (WebDriver driver) {
		this.driver = driver;
	}
}
