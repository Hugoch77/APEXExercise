package com.liverpool.testNGbase;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestNGBase {

	public WebDriver driver;

	
	@Parameters({ "Browser", "url" })
	@BeforeMethod(alwaysRun = true)
	public void beforeTest(String browser, String url) throws Exception {
		if (browser.equalsIgnoreCase("Chrome")) {
			// Driver exe config
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/Webdrivers/chromedriver17.exe");
			// Instantiation the WebDriver Instance
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "/src/test/resources/Webdrivers/msedgedriver_17.exe");
			driver = new EdgeDriver();
		}

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		driver.close();
		driver.quit();
	}

}
