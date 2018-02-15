package com.rozetka.cartQuickTest;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	@DataProvider(name = "fridges")
	public static Object[][] getFridgesData() {

		return new Object[][] { { "bosch" }, { "indesit" }, { "samsung" }, { "liebherr" }, { "atlant" }, { "lg" } };

	}

}
