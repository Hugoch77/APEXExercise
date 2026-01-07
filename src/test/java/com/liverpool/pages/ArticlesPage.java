package com.liverpool.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//coments
import utilities.Utils;

public class ArticlesPage {

	WebDriver driver;

	// Scenario 2 elements
	// Element to validate we land on the expected page
	@FindBy(xpath = "//h1[@class='a-headline__typeahed searcherTitle-result']")
	WebElement articlePageValidationEle;

	// All articles from the page elements
	@FindBy(xpath = "//li[@class='m-product__card card-masonry a']")
	List<WebElement> articlesEle;

	// Size Filter Element
	@FindBy(xpath = "//label[@title='Tamaño']")
	WebElement sizeFiltEle;

	// Price Filter Element
	@FindBy(xpath = "//label[@title='Precios']")
	WebElement priceFiltEle;

	// See more under Tamaño filter Element
	@FindBy(id = "Tamao")
	WebElement sizeSeeMoreEle;

	// See more under Marcas filter Element
	@FindBy(id = "Marcas")
	WebElement brandSeeMoreEle;

	// Total fetched results Element
	@FindBy(xpath = "//p[@class='a-plp-results-title']/span")
	WebElement numberOfResults;

	// Scenario 3 elements
	// Validate perfumes hombre landing page element
	@FindBy(id = "abc")
	WebElement validatePerfHomPageEle;

	public ArticlesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	Utils utils = new Utils();

	// SC1-2 - Method to Validate landing in correct page
	public void validateArticlePage(String message) {
		utils.validateMessage(driver, articlePageValidationEle, message);
	}

	// SC1-2 - Method to validate if there a games for PS4 available
	public boolean validatePs4Games() {
		boolean ps4GameAvailable = false;
		for (WebElement wantEle : articlesEle) {
			if (wantEle.getText().contains("FC 24 para Play Station 4")) {
				ps4GameAvailable = true;
				System.out.println("There are games displayed for PS4 - pass\n-----");
				break;
			} else {
				continue;
			}
		}
		return ps4GameAvailable;
	}

	// SC1-2
	public boolean validatePs5Games() {
		boolean ps5GameAvailable = false;
		for (WebElement wantEle : articlesEle) {
			if (wantEle.getText().contains("FC 24 para Play Station 5")) {
				ps5GameAvailable = true;
				System.out.println("There are games displayed for PS5 - pass\n-----");
				break;
			} else {
				continue;
			}
		}
		return ps5GameAvailable;
	}

	// SC1-2 Method to select first PS5 article found and returns title and price
	public String[] selectPS5() {
		String[] articleTitle = null;
		String[] returnString = {"",""};
		for (WebElement wantEle : articlesEle) {
			if (wantEle.getText().contains("Consola PlayStation 5")) {
				articleTitle = wantEle.getText().split("\n");
				utils.clickBtn(driver, wantEle);
				break;
			} else {
				continue;
			}
		}
		if(articleTitle.length > 2) {
			returnString[0] = articleTitle[0];
			returnString[1] = articleTitle[2];
		return returnString;
		} else {
			return articleTitle;
		}
		
	}

	// SC2 - Method to validate that the Size/Price filters are available
	public boolean validateSizePriceFilters() {
		boolean catPresent = false;
		if (sizeFiltEle.getText().contains("Tamaño") && priceFiltEle.getText().contains("Precios")) {
			catPresent = true;
		}
		return catPresent;
	}

	// SC2 - Method to select a Size from the Size filter
	public void selectSizeFilter(String size) {
		// Opens the see more on the Size filter to avoid hidden elements
		utils.clickBtn(driver, sizeSeeMoreEle);
		// Clicks on the size we want
		String savedNumRes = numberOfResults.getText();
		utils.clickBtn(driver, driver.findElement(By.id("variants.normalizedSize-" + size + " pulgadas")));
		// This is basically a wait for the page to refresh after a filter is chosen
		while (savedNumRes.equals(numberOfResults.getText())) {
			continue;
		}
	}

	// SC2 - Method to select a Price from the price range filter
	public void selectPriceFilter(String price) {
		String savedNumRes = numberOfResults.getText();
		utils.clickBtn(driver, driver.findElement(By.id("variants.prices.sortPrice-" + price)));
		while (savedNumRes.equals(numberOfResults.getText())) {
			continue;
		}
	}

	// SC2 - Method to select a Brand from the brand filter
	public void selectBrandFilter(String brand) {
		// utils.clickBtn(driver, brandSeeMoreEle);
		String savedNumRes = numberOfResults.getText();
		brand = brand.toUpperCase();
		utils.clickBtn(driver, driver.findElement(By.id("brand-" + brand)));
		while (savedNumRes.equals(numberOfResults.getText())) {
			continue;
		}
	}

	// SC2/SC3 - Method to count the total result of articles fetched after filters applied
	public void resultCount() {
		System.out.println("Final Result count with the filters used: " + numberOfResults.getText() + " Products - pass\n-----");
	}

	// Scenario 3 Methods

	// Method to validate landing in expected page
	public void validatePerfHombrePage() {
		utils.validateMessage(driver, validatePerfHomPageEle, "Perfumes Hombre");
	}

	// Method to select the wanted brand from the brand filter
	public void selectBrandFilterSc3(String brand) {
		utils.clickBtn(driver, brandSeeMoreEle);
		brand = brand.toUpperCase();
		utils.clickBtn(driver, driver.findElement(By.id("brand-" + brand)));
	}

}
