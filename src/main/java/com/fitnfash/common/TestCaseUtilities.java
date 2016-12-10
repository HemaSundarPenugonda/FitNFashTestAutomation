package com.fitnfash.common;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class TestCaseUtilities {
	
	public static void pageDownMultipleTimes(Actions actions, int count) throws InterruptedException {
		for (int i = 0; i < count; i++) {
			actions.sendKeys(Keys.PAGE_DOWN).build().perform();
			Thread.sleep(1000);
		}
	}
}
