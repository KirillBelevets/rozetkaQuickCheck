package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultsPage {
	private WebDriver driver;

	By manufacturerList = By.cssSelector("div#catalog_goods_block > div > div");

	public List<WebElement> findFrigesList() {
		return driver.findElements(manufacturerList);
	}

	By resultsBlock = By.cssSelector("div#catalog_goods_block > div > div");

	public List<WebElement> getResultsBlock() {
		return driver.findElements(resultsBlock);
	}

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
	}
}
