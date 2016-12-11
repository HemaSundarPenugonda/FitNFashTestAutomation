package com.fitnfash.common;

import java.time.Month;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class TestCaseUtilities {

	public static void pageDownMultipleTimes(Actions actions, int count) throws InterruptedException {
		for (int i = 0; i < count; i++) {
			actions.sendKeys(Keys.PAGE_DOWN).build().perform();
			Thread.sleep(1000);
		}
	}

	public static String selectDate(WebDriver driver, String calMonth, String calNext) throws InterruptedException {

		Calendar timeInstance = Calendar.getInstance();
		timeInstance.add(Calendar.DATE, 15);

		String expectedMonth = Month.of(timeInstance.get(Calendar.MONTH) + 1).toString();
		String expectedYear = String.valueOf(timeInstance.get(Calendar.YEAR));
		String expectedDate = String.valueOf(timeInstance.get(Calendar.DATE));

		String[] locatorValues = Utilities.getLocatorValues(calMonth);
		By calMonthBy = SeleniumFunctions.byLocator(locatorValues[0], locatorValues[1]);

		String actualMonth = driver.findElements(calMonthBy).get(0).getText();
		String actualYear = driver.findElements(calMonthBy).get(1).getText();

		String[] locatorValues1 = Utilities.getLocatorValues(calMonth);
		By calNextBy = SeleniumFunctions.byLocator(locatorValues1[0], locatorValues1[1]);

		if (!(actualMonth.equalsIgnoreCase(expectedMonth) && actualYear.equalsIgnoreCase(expectedYear))) {
			driver.findElement(calNextBy).click();
			Thread.sleep(1000);
		}
		return expectedDate;

	}
}
