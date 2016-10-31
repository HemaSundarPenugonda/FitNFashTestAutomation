package com.fitnfash.common;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumFunctions {
	public static WebDriver driver;
	public static WebDriverWait wait = new WebDriverWait(driver, 20);

	public static WebDriver openBrowser() {
		// System.setProperty("webdriver.gecko.driver",
		// "/home/hemasundar/Apps/geckodriver");
		System.setProperty("webdriver.firefox.marionette", "false");

		// FirefoxBinary binary = new FirefoxBinary(new
		// File("/home/hema/firefox-sdk/bin/firefox"));
		// FirefoxBinary binary = new FirefoxBinary(new
		// File("/home/hemasundar/Apps/firefox/firefox"));
		System.out.println("fire fox binary path set successfully.");
		// GeckoDriverService createDefaultService =
		// GeckoDriverService.createDefaultService();

		// createDefaultService.start();
		// System.out.println(createDefaultService.getUrl());
		// System.out.println(createDefaultService.getClass());
		// driver = new FirefoxDriver(binary, new FirefoxProfile());
		driver = new FirefoxDriver();
		System.out.println("firefox browser launched successfully.");
		return driver;

	}

	public static void clickObject(String locatorType, String locatorValue) {
		By borrowNowBy = By.xpath(locatorValue);
		wait.until(ExpectedConditions.visibilityOfElementLocated(borrowNowBy));
		WebElement borrowNow = driver.findElement(borrowNowBy);
		borrowNow.click();
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
}
