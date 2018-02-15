package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	private WebDriver driver;

	By searchField = By.cssSelector(".rz-header-search-input-text.passive");
	By findButton = By.cssSelector("div#rz-search > form > span > span > button");

	public WebElement findButtonClick() {
		return driver.findElement(findButton);
	}

	public WebElement searchFieldOnHomePage() {
		return driver.findElement(searchField);
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
}
