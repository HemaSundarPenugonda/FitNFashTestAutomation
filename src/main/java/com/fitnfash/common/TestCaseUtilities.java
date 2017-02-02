package com.fitnfash.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Month;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class TestCaseUtilities {

	public static void pageDownMultipleTimes(Actions actions, int count) throws InterruptedException {
		for (int i = 0; i < count; i++) {
			actions.sendKeys(Keys.PAGE_DOWN).build().perform();
			System.out.println("page down successful");
			Thread.sleep(1000);
		}
	}

	public static void pageUpMultipleTimes(Actions actions, int count) throws InterruptedException {
		for (int i = 0; i < count; i++) {
			actions.sendKeys(Keys.PAGE_UP).build().perform();
			System.out.println("page up successful");
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

		objCurrentEnv.gmailUserName = objUtility.allPropMap.get("gmail.UserName");
		objCurrentEnv.gmailPassword = objUtility.allPropMap.get("gmail.Password");

		objCurrentEnv.dbHostName = objUtility.allPropMap.get("db.hostname");
		objCurrentEnv.dbPort = objUtility.allPropMap.get("db.port");
		objCurrentEnv.dbName = objUtility.allPropMap.get("db.name");
		objCurrentEnv.dbUserName = objUtility.allPropMap.get("db.username");
		objCurrentEnv.dbPassword = objUtility.allPropMap.get("db.password");
		objCurrentEnv.dbResourceFolder = objUtility.allPropMap.get("db.resourcefolder");
		objCurrentEnv.dbSchemaFile = objUtility.allPropMap.get("db.schemafile");
		objCurrentEnv.dbInsertFile = objUtility.allPropMap.get("db.insertfile");

		objCurrentEnv.adminURL = objUtility.allPropMap.get("admin.url");
		objCurrentEnv.adminUserName = objUtility.allPropMap.get("admin.username");
		objCurrentEnv.adminPassword = objUtility.allPropMap.get("admin.password");
	}

	public static void resetDB(CurrentEnv objCurrentEnv) {

		Connection con = null;
		// String url = "jdbc:mysql://138.201.175.186:3306/";
		// String db = "broadleaf";
		// String user = "root";
		// String pass = "fitnfash@2015";
		String url = "jdbc:mysql://" + objCurrentEnv.dbHostName + ":" + objCurrentEnv.dbPort + "/";
		String db = objCurrentEnv.dbName;
		String driver = "com.mysql.jdbc.Driver";
		String user = objCurrentEnv.dbUserName;
		String pass = objCurrentEnv.dbPassword;
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url + db, user, pass);

			ScriptRunner sr = new ScriptRunner(con);

			// Give the input file to Reader
			Reader readerSchema = new BufferedReader(new FileReader(
					System.getProperty("user.dir") + "/src/main/resources/DBFiles/" + objCurrentEnv.dbSchemaFile));
			Reader readerInsertData = new BufferedReader(new FileReader(
					System.getProperty("user.dir") + "/src/main/resources/DBFiles/" + objCurrentEnv.dbInsertFile));
			// Execute script
			sr.runScript(readerSchema);
			sr.runScript(readerInsertData);

		} catch (SQLException s) {
			System.out.println("SQL code does not execute." + s);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void reBuildIndex(CurrentEnv objCurrentEnv) throws InterruptedException {
		WebDriver driver;
		driver = SeleniumFunctions.openBrowser(20);
		driver.manage().window().maximize();

		driver.get(objCurrentEnv.adminURL);
		SeleniumFunctions.enterKeys("Admin.username", objCurrentEnv.adminUserName);
		SeleniumFunctions.enterKeys("Admin.password", objCurrentEnv.adminPassword);
		SeleniumFunctions.clickObject("Admin.signin");
		Thread.sleep(5000);
		SeleniumFunctions.clickObject("Admin.CatalogSettings");
		Thread.sleep(5000);
		SeleniumFunctions.clickObject("Admin.SlideAvailability");
		Thread.sleep(5000);
		SeleniumFunctions.clickObject("Admin.RebuildIndex");
		Thread.sleep(5000);
		driver.quit();

	}

	public static void beforeSuiteMethod(Utilities objUtility, CurrentEnv objCurrentEnv) throws InterruptedException {
		objUtility.loadProperties();
		objUtility.loadObjRepo();
		TestCaseUtilities.assignEnv(objCurrentEnv, objUtility);
		TestCaseUtilities.resetDB(objCurrentEnv);
		TestCaseUtilities.reBuildIndex(objCurrentEnv);
	}
}
