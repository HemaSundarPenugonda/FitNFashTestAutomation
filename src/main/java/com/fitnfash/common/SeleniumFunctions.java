package com.fitnfash.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SeleniumFunctions {
	public static WebDriver driver;
	public static WebDriverWait wait;
	static {
		// wait = new WebDriverWait(driver, 20);
	}

	public static WebDriver openBrowser(int timeOut) {
		// System.setProperty("webdriver.gecko.driver",
		// "/home/hemasundar/Apps/geckodriver");
		System.setProperty("webdriver.firefox.marionette", "false");

		FirefoxBinary binary = new FirefoxBinary(new File("/home/hema/firefox-sdk/bin/firefox"));
		// FirefoxBinary binary = new FirefoxBinary(new
		// File("/home/hemasundar/Apps/firefox/firefox"));
		System.out.println("fire fox binary path set successfully.");
		// GeckoDriverService createDefaultService =
		// GeckoDriverService.createDefaultService();

		// createDefaultService.start();
		// System.out.println(createDefaultService.getUrl());
		// System.out.println(createDefaultService.getClass());
		// driver = new FirefoxDriver(binary, new FirefoxProfile());
		driver = new FirefoxDriver(binary, new FirefoxProfile());
		System.out.println("firefox browser launched successfully.");
		wait = new WebDriverWait(driver, timeOut);
		return driver;

	}

	public static void saveScreenshot() {
		String baseDir = System.getProperty("user.dir");
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(baseDir + "/screenShot/test.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}

	}

	public static void clickObject(String locator) throws InterruptedException {
		try {
			String[] locatorValues = Utilities.getLocatorValues(locator);
			clickObject(locatorValues[0], locatorValues[1]);
			System.out.println("Click on Object successfull : " + locator);
		} catch (Exception e) {

			throw e;
		}
	}

	public static void clickObject(String locatorType, String locatorValue) throws InterruptedException {
		By borrowNowBy = byLocator(locatorType, locatorValue);
		clickObject(borrowNowBy);
	}

	public static void clickObject(By borrowNowBy) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(borrowNowBy));
		WebElement borrowNow = driver.findElement(borrowNowBy);
		Thread.sleep(1000);
		borrowNow.click();
	}

	public static boolean verifyElementText(String locator, String expected) {
		String[] locatorValues = Utilities.getLocatorValues(locator);
		By byLocator = byLocator(locatorValues[0], locatorValues[1]);
		wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		WebElement findElement = driver.findElement(byLocator);
		String actualText = findElement.getText();
		if (actualText.toLowerCase().contains(expected.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}

	public static void enterKeys(String locator, String value) {
		String[] locatorValues = Utilities.getLocatorValues(locator);
		By borrowNowBy = byLocator(locatorValues[0], locatorValues[1]);
		wait.until(ExpectedConditions.visibilityOfElementLocated(borrowNowBy));
		WebElement borrowNow = driver.findElement(borrowNowBy);
		borrowNow.sendKeys(value);
		System.out.println("Entering text in the filed is successfull : " + locator);

	}

	public static By byLocator(String locatorType, String locatorValue) {
		By byLocator = null;
		locatorType = locatorType.toUpperCase();
		switch (locatorType) {
		case "XPATH":
			byLocator = By.xpath(locatorValue);
			break;
		case "CSS":
			byLocator = By.cssSelector(locatorValue);
			break;
		case "ID":
			byLocator = By.id(locatorValue);
			break;
		default:
			break;
		}
		return byLocator;

	}

	public static By byLocator(String locatorType, String locatorValue, String data) {
		locatorValue = locatorValue.replace("${data}", data);
		return byLocator(locatorType, locatorValue);

	}

	public static String enterDataSelectDropDown(By byLocator, String selectText) throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		WebElement element = driver.findElement(byLocator);
		Select select = new Select(element);
		Thread.sleep(3000);
		select.selectByVisibleText(selectText);
		return "Selecting [" + selectText + "] from drop down is successfull: " + byLocator;

	}

	public static String enterDataSelectDropDown(String locator, String selectText) throws InterruptedException {

		String[] locatorValues = Utilities.getLocatorValues(locator);
		By byLocator = byLocator(locatorValues[0], locatorValues[1]);

		return enterDataSelectDropDown(byLocator, selectText);
	}

	public static String enterDataSelectDropDown(By byLocator, int index) throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		WebElement element = driver.findElement(byLocator);
		Select select = new Select(element);
		Thread.sleep(3000);
		select.selectByIndex(index);
		return "Selecting [" + index + "] from drop down is successfull: " + byLocator;

	}

	public static String enterDataSelectDropDown(String locator, int index) throws InterruptedException {

		String[] locatorValues = Utilities.getLocatorValues(locator);
		By byLocator = byLocator(locatorValues[0], locatorValues[1]);

		return enterDataSelectDropDown(byLocator, index);
	}

	public static boolean wait4ElementtobeDisplayed(String locator) {

		String[] locatorValues = Utilities.getLocatorValues(locator);
		return wait4ElementtobeDisplayed(locatorValues[0], locatorValues[1]);
	}

	private static boolean wait4ElementtobeDisplayed(String locatorType, String locatorValue) {
		By borrowNowBy = byLocator(locatorType, locatorValue);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(borrowNowBy));
		return true;
	}

	public static void clickObjectWIthOutWait(String locator) throws InterruptedException {
		String[] locatorValues = Utilities.getLocatorValues(locator);
		clickObjectWIthOutWait(locatorValues[0], locatorValues[1]);

	}

	public static void clickObjectWIthOutWait(String locatorType, String locatorValue) throws InterruptedException {
		By borrowNowBy = byLocator(locatorType, locatorValue);
		Thread.sleep(3000);
		WebElement borrowNow = driver.findElement(borrowNowBy);
		new Actions(driver).click(borrowNow);
	}
}