package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBaseClass {

	public static WebDriver driver;

	@BeforeClass
	public void setUp() {
		// set property for driver; could be set in System properties directly
		System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://rozetka.com.ua");
		driver.manage().window().maximize();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		driver = null;
	}
}
