package com.rozetka.cartQuickTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import junit.framework.Assert;
import pages.CartPageWidget;
import pages.HomePage;
import pages.ProductDetails;
import pages.ResultsPage;
import util.TestBaseClass;

public class SearchTest extends TestBaseClass {

	@Test(priority = 1)
	public void cartQuickCheckTest() throws InterruptedException {

		HomePage hp = new HomePage(driver);
		hp.searchFieldOnHomePage().sendKeys("телевизор");
		hp.findButtonClick().click();
		// getting search results to the list
		ResultsPage rp = new ResultsPage(driver);
		List<WebElement> listOfResults = rp.getResultsBlock();
		// click on 6th product from result list
		if (listOfResults.get(5) != null) {
			listOfResults.get(5).click();
		}
		// click on buy button
		ProductDetails pd = new ProductDetails(driver);
		pd.buyButtonOnDetails().click();
		// close cart
		CartPageWidget cw = new CartPageWidget(driver);
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(cw.continuePurchasing()));
		Thread.sleep(3000);
		cw.continuePurchasing().click();

	}

	@Test(dataProvider = "fridges", dataProviderClass = DataProviderClass.class, priority = 2)
	public void addFridgesToCart(String fridgeName) throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		HomePage hp = new HomePage(driver);

		hp.searchFieldOnHomePage().sendKeys("холодильник");
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(hp.findButtonClick()));

		hp.findButtonClick().click();
		// using dataProvider to visit required fridges lists
		driver.get("https://bt.rozetka.com.ua/bigbt/refrigerators/filter/producer=" + fridgeName + "/");
		// traversing results list and adding second product to the cart
		ResultsPage rp = new ResultsPage(driver);
		List<WebElement> listOfResults = rp.findFrigesList();
		if (listOfResults.get(1) != null) {
			listOfResults.get(1).click();

			ProductDetails pd = new ProductDetails(driver);
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(pd.buyButtonOnDetails()));
			pd.buyButtonOnDetails().click();
			Thread.sleep(3000);
			CartPageWidget cw = new CartPageWidget(driver);
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(cw.continuePurchasing()));
			cw.continuePurchasing().click();
		}
	}

	@Test(priority = 3)
	public void countSum() {
		CartPageWidget cart = new CartPageWidget(driver);
		cart.findCartButton().click();
		// list of all sums from cart items list
		List<WebElement> sumList = cart.findSum();
		int resultActual = 0;
		for (WebElement s : sumList) {
			String res = s.getText().replaceAll("\\s", "");
			resultActual += Integer.parseInt(res); // counting actual result
		}
		String totalCost = cart.getTotalCost().getText().replaceAll("\\s", "");
		int totalCostInt = Integer.parseInt(totalCost);
		Assert.assertEquals(totalCostInt, resultActual);
		cart.continuePurchasing().click(); // added click on close cart button
	}

	@Test(priority = 4)
	public void deleteThirdProduct() throws InterruptedException {
		CartPageWidget cart = new CartPageWidget(driver);
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(cart.findCartButton()));
		cart.findCartButton().click();
		boolean itemActuallyDeleted = false;
		String deletedItemName = "";
		int sizeOfItemsList = 0;
		// getting third item from the cart
		List<WebElement> listOfItems = cart.findListOfItems();
		sizeOfItemsList = listOfItems.size();
		if (listOfItems.get(2) != null) {
			deletedItemName = listOfItems.get(1).getAttribute("href"); // getting
																		// href
																		// attribute
		}
		// deleting third item specifying zero in quantity field
		List<WebElement> deleteIcons = cart.findDeleteItemIcon();
		if (deleteIcons.get(2) != null) {
			deleteIcons.get(2).clear();
			deleteIcons.get(2).sendKeys("0");
			deleteIcons.get(2).sendKeys(Keys.ENTER);
			sizeOfItemsList -= 1; // decreasing size of the list of items
		}
		for (WebElement item : listOfItems) {
			String href = item.getAttribute("href");
			if (href != deletedItemName) // comparing deleted href with remained
											// in a list
				itemActuallyDeleted = true;
		}
		Assert.assertTrue(itemActuallyDeleted);
		Assert.assertEquals(listOfItems.size() - 1, sizeOfItemsList);

		List<WebElement> sumList = cart.findSum();
		int resultActual = 0;
		for (WebElement s : sumList) {
			String res = s.getText().replaceAll("\\s", "");
			resultActual += Integer.parseInt(res); // counting actual result
		}
		String totalCost = cart.getTotalCost().getText().replaceAll("\\s", "");
		int totalCostInt = Integer.parseInt(totalCost);
		Assert.assertEquals(totalCostInt, resultActual);
		// deleting all items from the cart
		List<WebElement> deleteAllIcons = cart.findDeleteItemIcon();
		int productsCount = deleteAllIcons.size();
		for (WebElement del : deleteAllIcons) {
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(del));
			del.clear();
			del.sendKeys("0");
			del.sendKeys(Keys.ENTER);
			productsCount -= 1;
		}
		Assert.assertEquals(0, productsCount);
	}

}
