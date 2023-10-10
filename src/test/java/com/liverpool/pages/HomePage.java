package com.liverpool.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Utils;

public class HomePage {

	WebDriver driver;

	// Search bar element
	@FindBy(id = "mainSearchbar")
	WebElement searchBarEle;

	// Search button element
	@FindBy(xpath = "//div[@class='input-group']/div")
	WebElement searchBarBtnEle;

	// Search displaying results on home page elements
	@FindBy(xpath = "//a[@class='a-header__typeaheadLink typeahead-link']")
	List<WebElement> searchResultsEle;

	// Search displaying results on most wanted home page elements
	@FindBy(xpath = "//p[@class='a-search-suggestion-title']")
	List<WebElement> mostWantedEle;

	// 5ft search displaying result on most wanted home page element
	@FindBy(xpath = "(//p[@class='a-search-suggestion-title'])[5]")
	WebElement fifthArticleMWEle;

	// Categories menu home page element
	@FindBy(xpath = "//span[@class='a-header__strongLink nav-desktop-menu-action pr-3 pb-3']")
	WebElement categoriesEle;

	// Belleza category home page element
	@FindBy(xpath = "//div[@class='col-lg-10']/span[contains(.,'Belleza')]")
	WebElement bellezaEle;

	// Perfumes de hombre sub category home page element
	@FindBy(xpath = "//a[contains(.,'Perfumes Hombre')]")
	WebElement perfumesHombreEle;

	// Initializing of page factory elements and assigning driver to use in this page
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Creating utils object
	Utils utils = new Utils();

	// Method to send keys to the search bar in home page
	public void typeOnSearchBar(String text) {
		utils.sendText(driver, searchBarEle, text);
		System.out.println("Typed on search bar the word: " + text + " - pass\n-----");
		//test.log(Status.PASS, "Typed on search bar the word: \" + text + \" - pass\\n-----");
	}

	// Method to click on the search button on the home page and returns a new ArticlesPage object
	public ArticlesPage clickSearchBtn() {
		utils.clickBtn(driver, searchBarBtnEle);
		System.out.println("Click on search button - pass\n-----");
		return new ArticlesPage(driver);
	}

	// Method to validate if there are PS4 games displaying on the results from search in home page
	public boolean validatePs4Games() {
		boolean ps4GameAvailable = false;

		while (ps4GameAvailable == false) {
			// First we search on the results displaying for the PS4 game
			for (WebElement wantEle : mostWantedEle) {
				if (wantEle.getText().contains("ea sports fc 24 para play station 4")) {
					ps4GameAvailable = true;
					break;
				} else {
					continue;
				}
			}

			// If PS4 game was found in the before statement this for will break and exit the while loop
			// If PS4 was not found we will navigate through the prediction options from the left and search
			// again for the PS4 game, once found this for breaks and we exit the while loop
			for (WebElement ele : searchResultsEle) {
				if (ps4GameAvailable == true) {
					break;
				} else {
					String savedName = fifthArticleMWEle.getText();
					utils.hoverOver(driver, ele);
					while (savedName.equals(fifthArticleMWEle.getText())) {
						continue;
					}
				}
				for (WebElement wantEle : mostWantedEle) {
					if (wantEle.getText().contains("ea sports fc 24 para play station 4")) {
						ps4GameAvailable = true;
						break;
					} else {
						continue;
					}
				}
			}
		}
		System.out.println("Validated there are games for PS4 displaying - pass\n-----");
		return ps4GameAvailable;
	}

	public boolean validatePs5Games() {
		boolean ps5GameAvailable = false;

		while (ps5GameAvailable == false) {
			for (WebElement wantEle : mostWantedEle) {
				if (wantEle.getText().contains("ea sports fc 24 para play station 5")) {
					ps5GameAvailable = true;
					break;
				} else {
					continue;
				}
			}

			for (WebElement ele : searchResultsEle) {
				if (ps5GameAvailable == true) {
					break;
				} else {
					String savedName = fifthArticleMWEle.getText();
					utils.hoverOver(driver, ele);
					while (savedName.equals(fifthArticleMWEle.getText())) {
						continue;
					}
				}
				for (WebElement wantEle : mostWantedEle) {
					if (wantEle.getText().contains("ea sports fc 24 para play station 5")) {
						ps5GameAvailable = true;
						break;
					} else {
						continue;
					}
				}
			}
		}
		System.out.println("Validated there are games for PS5 displaying - pass\n-----");
		return ps5GameAvailable;
	}

	// Method to click on the first PS5 article we see on the most searched options on the home page and returns the title of the article
	public String selectPS5() {
		String articleTitle = null;
		boolean ps5Found = false;

		// We first search for the PS5 on the results we have and if we have it we click on it
		while (ps5Found == false) {
			for (WebElement wantEle : mostWantedEle) {
				if (wantEle.getText().contains("Consola PlayStation 5")) {
					articleTitle = wantEle.getText();
					ps5Found = true;
					utils.clickBtn(driver, wantEle);
					break;
				} else {
					continue;
				}
			}
			
			// If PS5 was not found in the previous loop we then search again through the different
			// prediction option from the left until we find it ad click on it
			for (WebElement ele : searchResultsEle) {
				if (ps5Found == true) {
					break;
				} else {
					String savedName = fifthArticleMWEle.getText();
					utils.hoverOver(driver, ele);
					while (savedName.equals(fifthArticleMWEle.getText())) {
						continue;
					}
				}
				for (WebElement wantEle : mostWantedEle) {
					if (wantEle.getText().contains("Consola PlayStation 5")) {
						articleTitle = wantEle.getText();
						ps5Found = true;
						utils.clickBtn(driver, wantEle);
						break;
					} else {
						continue;
					}
				}
			}
		}
		System.out.println("click on PS5 article - pass\n-----");
		return articleTitle;
	}

	// Method to hover over the categories functionality
	public void hoverOverCategories() {
		utils.hoverOver(driver, categoriesEle);
		System.out.println("Hover over Categories pass\n-----");
	}

	// Method to hover over the Belleza subCategory
	public void hoverOverBelleza() {
		utils.hoverOver(driver, bellezaEle);
		System.out.println("Hover over Belleza pass\n-----");
	}

	// Method to click on Perfumes de Hombre under Belleza subCategory
	public ArticlesPage selectPerfHombre() {
		utils.clickBtn(driver, perfumesHombreEle);
		System.out.println("Click on perfumes de hombre pass\n-----");
		return new ArticlesPage(driver);
	}

}
