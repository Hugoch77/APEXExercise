package com.liverpool.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.liverpool.pages.ArticlesPage;
import com.liverpool.pages.HomePage;
import com.liverpool.pages.SelectedArticlePage;
import com.liverpool.testNGbase.TestNGBase;

public class APEXTestsScenarios extends TestNGBase {

	// Scenario 1 (solution 1)
	// Search for a “playstation” using the search bar, verify that the results
	// displayed includes games for playstation 5 and playstation consoles. Then
	// select a playstation 5 in the results listed and validate the title and price
	// of the item in the page displayed.

	@Test(groups = {"demo"})
	public void APEXScenario1Sol1() {

		HomePage home = new HomePage(driver);
		SelectedArticlePage selectedArticlePage = new SelectedArticlePage(driver);

		String textToTypeInSB = "playstation";
		home.typeOnSearchBar(textToTypeInSB);
		Assert.assertTrue(home.validatePs5Games());
		Assert.assertTrue(home.validatePs4Games());

		String expectedTitle = home.selectPS5();
		selectedArticlePage.validateTitle(expectedTitle);
		selectedArticlePage.validatePrice();
	}

	// Scenario 1 (solution 2)
	// Search for a “playstation” using the search bar, verify that the results
	// displayed includes games for playstation 5 and playstation consoles. Then
	// select a playstation 5 in the results listed and validate the title and price
	// of the item in the page displayed.

	@Test(groups = {"demo"})
	public void APEXScenario1Sol2() {
		
		HomePage home = new HomePage(driver);
		SelectedArticlePage selectedArticlePage = new SelectedArticlePage(driver);

		String textToTypeInSB = "playstation";
		home.typeOnSearchBar(textToTypeInSB);
		ArticlesPage articlesPage = home.clickSearchBtn();
		articlesPage.validateArticlePage(textToTypeInSB);
		Assert.assertTrue(articlesPage.validatePs4Games());
		Assert.assertTrue(articlesPage.validatePs5Games());
		String[] titlePrice = articlesPage.selectPS5();
		selectedArticlePage.validateTitle(titlePrice[0]);
		selectedArticlePage.validatePrice(titlePrice[1]);
	}

	// Scenario 2:
	// Search for “smart tv” and navigate to the page. Validate that the
	// Size and Price filters are displayed. Filter the results by size: 55 inches,
	// price: > 10,000, brand: sony. Validate the results count.

	@Test
	public void APEXScenario2() {

		HomePage home = new HomePage(driver);

		String textToTypeInSB = "smart tv";
		home.typeOnSearchBar(textToTypeInSB);
		ArticlesPage articlesPage = home.clickSearchBtn();
		articlesPage.validateArticlePage(textToTypeInSB);
		Assert.assertTrue(articlesPage.validateSizePriceFilters(),
				"Either Size/Price Categorie or both are not displaying");

		String sizeInInches = "55";
		String price = "10000-700000";
		String brand = "sony";

		articlesPage.selectSizeFilter(sizeInInches);
		articlesPage.selectPriceFilter(price);
		articlesPage.selectBrandFilter(brand);
		articlesPage.resultCount();
	}

	// Scenario 3
	// Expand the “Categorias” menu, place the cursor over the “Belleza” option,
	// select the “Perfumes de Hombre” option in the menu displayed and filter the
	// results displayed by “Dior” brand.

	@Test
	public void APEXScenario3() {

		HomePage home = new HomePage(driver);

		home.hoverOverCategories();
		home.hoverOverBelleza();
		ArticlesPage articlesPage = home.selectPerfHombre();
		articlesPage.validatePerfHombrePage();
		String brand = "Dior";
		articlesPage.selectBrandFilterSc3(brand);
		articlesPage.resultCount();
	}

}
