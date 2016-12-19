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

	public static void pageUpMultipleTimes(Actions actions, int count) throws InterruptedException {
		for (int i = 0; i < count; i++) {
			actions.sendKeys(Keys.PAGE_UP).build().perform();
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

		String[] locatorValues1 = Utilities.getLocatorValues(calNext);
		By calNextBy = SeleniumFunctions.byLocator(locatorValues1[0], locatorValues1[1]);

		if (!(actualMonth.equalsIgnoreCase(expectedMonth) && actualYear.equalsIgnoreCase(expectedYear))) {
			driver.findElement(calNextBy).click();
			Thread.sleep(1000);
		}
		return expectedDate;

	}
	public static void assignEnv(CurrentEnv objCurrentEnv, Utilities objUtility) {
		objCurrentEnv.browserName = objUtility.allPropMap.get("browser");
		objCurrentEnv.url = objUtility.allPropMap.get("url");
		objCurrentEnv.maxTimeout = objUtility.allPropMap.get("MaximumTimeout");
		objCurrentEnv.totalDresses = objUtility.allPropMap.get("TotalDresses");
		
		objCurrentEnv.dress1Name = objUtility.allPropMap.get("Dress1.name");
		objCurrentEnv.dress1Price = objUtility.allPropMap.get("Dress1.price");
		objCurrentEnv.dress1Size = objUtility.allPropMap.get("Dress1.size");
		
		objCurrentEnv.dress2Name = objUtility.allPropMap.get("Dress2.name");
		objCurrentEnv.dress2Price = objUtility.allPropMap.get("Dress2.price");
		objCurrentEnv.dress2Size = objUtility.allPropMap.get("Dress2.size");
		
		objCurrentEnv.demoDress1Name = objUtility.allPropMap.get("DemoDress1.name");
		objCurrentEnv.demoDress1Price = objUtility.allPropMap.get("DemoDress1.price");
		objCurrentEnv.demoDress1Size = objUtility.allPropMap.get("DemoDress1.size");
		
		objCurrentEnv.demoDress2Name = objUtility.allPropMap.get("DemoDress2.name");
		objCurrentEnv.demoDress2Price = objUtility.allPropMap.get("DemoDress2.price");
		objCurrentEnv.demoDress2Size = objUtility.allPropMap.get("DemoDress2.size");
		
	}
}
