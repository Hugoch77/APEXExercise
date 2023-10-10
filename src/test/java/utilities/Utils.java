package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	// Send keys to a type field
	public void sendText(WebDriver driver, WebElement element, String text) {
		element.sendKeys(text);
	}

	// Click on a button
	public void clickBtn(WebDriver driver, WebElement element) {
		element.click();
	}

	// Validation of a message in any webelement
	public void validateMessage(WebDriver driver, WebElement element, String expected) {
		if (element.getText().contains(expected)) {
			System.out.println("The expected message is correct: " + "'" + element.getText() + "' - pass\n-----");
		}
	}

	// Hover over any webelement
	public void hoverOver(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		Actions action = new Actions(driver);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		action.moveToElement(element).build().perform();
	}

	public String getScreenshotPath(WebDriver driver, String fileName) throws IOException {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotName = fileName + ".png";
		FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\Screenshots\\" + screenshotName));
		return System.getProperty("user.dir") + "\\Screenshots\\" + screenshotName;
	}

}
