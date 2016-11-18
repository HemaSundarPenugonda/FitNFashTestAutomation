package seleniumPOC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import com.fitnfash.common.CurrentEnv;
import com.fitnfash.common.SeleniumFunctions;
import com.fitnfash.common.Utilities;

public class Test {
	public Utilities objUtility = new Utilities();
	public CurrentEnv objCurrentEnv = new CurrentEnv();

	@BeforeSuite
	public void beforeSuite() {

		Properties sysProperties = System.getProperties();
		HashMap<String, String> propFile2HashMap = objUtility
				.propFile2HashMap(System.getProperty("user.dir") + "/src/main/resources/uiautomation.properties");
		System.out.println(propFile2HashMap.size());
		HashMap<String, String> propObj2HashMap = objUtility.propObj2HashMap(sysProperties);
		System.out.println(propObj2HashMap.size());
		objUtility.allPropMap.putAll(propFile2HashMap);
		objUtility.allPropMap.putAll(propObj2HashMap);
		System.out.println(objUtility.allPropMap.size());
		HashMap<String, String> objRepoHashMap = objUtility
				.propFile2HashMap(System.getProperty("user.dir") + "/src/main/resources/objRepo.properties");
		Set<String> keySet = objRepoHashMap.keySet();

		for (String indKey : keySet) {
			String[] split = new String[5];
			String[] split1 = indKey.split("\\.");
			split[0] = split1[0];
			split[1] = split1[1];
			split[2] = split1[2];
			split[3] = split1[3];
			split[4] = objRepoHashMap.get(indKey);
			objUtility.objRepo.add(split);

		}
		objCurrentEnv.browserName = objUtility.allPropMap.get("browser");
		objCurrentEnv.url = objUtility.allPropMap.get("url");
	}

	@org.testng.annotations.Test
	public void sampleTest() throws InterruptedException {
		System.out.println("test case 1");

		WebDriver driver = SeleniumFunctions.openBrowser();
		driver.manage().window().maximize();
		Actions actions = new Actions(driver);
		// 1) Go to home page
		driver.get("http://fit91485.fitnfash.com/");
		Logs logs = driver.manage().logs();
		Set<String> availableLogTypes = logs.getAvailableLogTypes();
		System.out.println(availableLogTypes);
		List<LogEntry> logEntries = logs.get("browser").getAll();
		System.out.println(logEntries);

		LogEntries logEntries2 = logs.get("driver");
		LogEntries logEntries3 = logs.get("client");
		// 2) Click view all/borrow now
		SeleniumFunctions.clickObject("HomePage.BorrowNow");
		// SeleniumFunctions.clickObject("xpath", "//a[text()='Borrow Now']");

		// 3) 70 items should be present (test data ) (Assertion)
		boolean verifyElementText = SeleniumFunctions.verifyElementText("HomePage.ResultCount", "70 results found.");
		for (int i = 0; i < 10; i++) {
			actions.sendKeys(Keys.PAGE_DOWN).build().perform();
			Thread.sleep(1000);
		}
		Thread.sleep(3000);
		// 4) Select Grace Skater Dress
		SeleniumFunctions.clickObject("HomePage.GraceSkaterDress");

		// 5) Product details page should appear

		// 6) Click on select date
		SeleniumFunctions.clickObject("HomePage.SelectDate");

		SeleniumFunctions.clickObject("HomePage.Date30");
		Thread.sleep(5000);
		SeleniumFunctions.clickObject("HomePage.closeSideBar");
		Thread.sleep(3000);
		// 7) select 7 days ahead date (if today is 1 then select 8)
		// 8) select size M
		SeleniumFunctions.clickObject("HomePage.SizeM");
		Thread.sleep(3000);
		// 9) Click on reserve now
		SeleniumFunctions.clickObject("HomePage.ReserveNow");

		// 10) Cart page should open where all the data (price insurance rent
		// etc ) should be of the selected dress only
		boolean verifyElementText1 = SeleniumFunctions.verifyElementText("HomePage.SubTotal", "499.00");
		// 11) Click on add an alternative
		SeleniumFunctions.clickObject("HomePage.AddAlternativeLink");
		// String[] locatorValues =
		// Utilities.getLocatorValues("HomePage.AddAlternativeLink");
		// By byLocator = SeleniumFunctions.byLocator(locatorValues[0],
		// locatorValues[1]);
		// SeleniumFunctions.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byLocator));
		// driver.findElement(byLocator).click();
		for (int i = 0; i < 10; i++) {
			actions.sendKeys(Keys.PAGE_DOWN).build().perform();
			Thread.sleep(1000);
		}
		Thread.sleep(3000);
		// 12) Click on Jena Gown
		SeleniumFunctions.clickObject("HomePage.JennaGown");

		// 13) Product detail should open , click on size L
		SeleniumFunctions.clickObject("HomePage.SizeL");
		Thread.sleep(3000);
		// 14) click on add alternative
		SeleniumFunctions.clickObject("HomePage.AddAlternativeBtn");
		Thread.sleep(3000);
		// 15) Click on login

		SeleniumFunctions.clickObject("HomePage.Login");
		Thread.sleep(3000);
		// 16) Login through google
		SeleniumFunctions.clickObject("HomePage.GoogleLogin");

		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> iterator = windowHandles.iterator();
		iterator.next();
		String next = iterator.next();
		WebDriver window = driver.switchTo().window(next);
		// WebDriver window = driver.switchTo().window("Sign in - Google
		// Accounts");
		System.out.println(driver.getTitle());

		SeleniumFunctions.enterKeys("HomePage.EmailAddress", "p.hemasundar@gmail.com");
		SeleniumFunctions.clickObject("HomePage.Next");

		SeleniumFunctions.enterKeys("HomePage.password", "*14Myself*");
		SeleniumFunctions.clickObject("HomePage.signIn");
		// driver.switchTo().window(iterator.next());
		Set<String> windowsHandles1 = driver.getWindowHandles();
		Iterator<String> iterator2 = windowsHandles1.iterator();
		
//		String windowHandle = driver.getWindowHandle();
		driver.switchTo().window(iterator2.next());
		System.out.println(driver.getTitle());
		Thread.sleep(3000);
		// 15) in Promocode box add promo code FIRSTBUY
		SeleniumFunctions.enterKeys("HomePage.promoCode", "FIRSTBUY");

		// 16) Click on apply
		SeleniumFunctions.clickObject("HomePage.applyPromoCode");

		// 17) Promo code should be applied with 10% discount
		boolean verifyElementText2 = SeleniumFunctions.verifyElementText("HomePage.promoCodeSuccess",
				"Promo code FIRSTBUY applied");
		boolean verifyElementText3 = SeleniumFunctions.verifyElementText("HomePage.promoCodeDiscountValue", "49.90");

		// 18) Click on Proceed to checkout
		SeleniumFunctions.clickObject("HomePage.proceedToCheckOut");

		// 19) Fill Address Phone number and pin code
		// input[@id='address.fullName']
		SeleniumFunctions.enterKeys("HomePage.fullName", "Test");
		// div[@class='addAddress']/ul/li[2]/input
		SeleniumFunctions.enterKeys("HomePage.phoneNumber", "8867773565");
		// div[@class='addAddress']/ul/li[/input[@id='address.postalCode']]/input
		SeleniumFunctions.enterKeys("HomePage.addressBuilding", "Test");
		SeleniumFunctions.enterKeys("HomePage.addressLocality", "Test");
		SeleniumFunctions.enterDataSelectDropDown("HomePage.city", "Gurgaon");
		SeleniumFunctions.enterKeys("HomePage.postalCode", "122002");
		// 20) click on continue
		// div[@class='addAddress']/ul//input[@id='addressBtn']
		SeleniumFunctions.clickObject("HomePage.continueAddress");
		// 21) Click on continue to pay
		// 22) ccavuene payment page should open. Select Avuenue Test
		// 23) Click on make payment
		// 24) Click on Return to the merchant site (Transaction Status should
		// be selected as Y)
		// 25) Success page should open with the placed order details
		// 26) Click on continue
		// 27) Select the same date for which previous order was placed
		// 28) Click on Borrow now
		// 29) Select Size L
		// 30) It should be not available and reserve now should be disabled

	}

}
