package com.liverpool.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.Utils;

public class SelectedArticlePage {

	WebDriver driver;

	// Article title element
	@FindBy(xpath = "//h1[@class='a-product__information--title']")
	WebElement articleNameEle;

	// Article price element
	@FindBy(xpath = "//p[@class='a-product__paragraphDiscountPrice m-0 d-inline ']")
	WebElement articlePriceEle;

	public SelectedArticlePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	Utils utils = new Utils();
	
	// Method to validate title of the article against the title fetched on previous page for this article
	public void validateTitle(String expectedTitle) {
		Assert.assertEquals(articleNameEle.getText(), expectedTitle);
		System.out.println("Expected title found: " + articleNameEle.getText() + " - pass\n-----");
	}
	
	// SC1-2 - Method to validate the price of the article
	public void validatePrice(String expectedPrice) {
		String[] a = articlePriceEle.getText().split("\n");
		String actualPrice = a[0].concat(a[1]);
		String showResult = a[0].concat(".").concat(a[1]);
		Assert.assertEquals(actualPrice, expectedPrice);
		System.out.println("Expected Price found: " + showResult + " - pass\n-----");
	}
	
	// SC1-1 - Second method to validate Price of the article selected
	public String validatePrice() {
		String[] a = articlePriceEle.getText().split("\n");
		String showResult = a[0].concat(".").concat(a[1]);
		System.out.println("Price of Article: " + showResult + " - pass\n-----");
		return showResult;
	}
	
}
