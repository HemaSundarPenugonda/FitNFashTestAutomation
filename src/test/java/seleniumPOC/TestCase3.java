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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.fitnfash.common.CurrentEnv;
import com.fitnfash.common.SeleniumFunctions;
import com.fitnfash.common.TestCaseUtilities;
import com.fitnfash.common.Utilities;

public class TestCase3 {
	public Utilities objUtility = new Utilities();
	public CurrentEnv objCurrentEnv = new CurrentEnv();
	WebDriver driver;

	@BeforeSuite
	public void beforeSuite() throws InterruptedException {

		TestCaseUtilities.beforeSuiteMethod(objUtility, objCurrentEnv);
	}

	@org.testng.annotations.Test
	public void sampleTest() throws InterruptedException {
		System.out.println("test case 3");
		// SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");

		driver = SeleniumFunctions.openBrowser(Integer.parseInt(objCurrentEnv.maxTimeout));
		driver.manage().window().maximize();
		Actions actions = new Actions(driver);
		// 1) Go to home page
		driver.get(objCurrentEnv.url);
		SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");

		// 2) Click view all/borrow now
		SeleniumFunctions.clickObject("HomePage.BorrowNow");
		SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");

		// 3) 70 items should be present (test data ) (Assertion)
		boolean verifyElementText = SeleniumFunctions.verifyElementText("HomePage.ResultCount",
				objCurrentEnv.totalDresses);

		TestCaseUtilities.pageDownMultipleTimes(actions, 15);
		SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");
		// Thread.sleep(3000);

		// 4) Select Grace Skater Dress
		// SeleniumFunctions.clickObject("HomePage.GraceSkaterDress");
		String[] locatorValues = Utilities.getLocatorValues("HomePage.DressLink");
		By byLocatorDress1 = SeleniumFunctions.byLocator(locatorValues[0], locatorValues[1], objCurrentEnv.dress1Name);
		SeleniumFunctions.clickObject(byLocatorDress1);
		SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");

		// 5) Product details page should appear

		// 6) Click on select date
		SeleniumFunctions.clickObject("HomePage.SelectDate");

		String date = TestCaseUtilities.selectDate(driver, "HomePage.CalMonth", "HomePage.CalNext");

		String[] dateLocator = Utilities.getLocatorValues("HomePage.Date");
		By dateBy = SeleniumFunctions.byLocator(dateLocator[0], dateLocator[1], date);
		SeleniumFunctions.clickObject(dateBy);

		// SeleniumFunctions.clickObject("HomePage.Date30");
		SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");
		Thread.sleep(5000);

		SeleniumFunctions.clickObject("HomePage.closeSideBar");
		Thread.sleep(3000);
		// 7) select 7 days ahead date (if today is 1 then select 8)
		// 8) select size M
		if (objCurrentEnv.dress1Size.equalsIgnoreCase("M")) {
			SeleniumFunctions.clickObject("HomePage.SizeM");
			SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");
			Thread.sleep(3000);
		} else if (objCurrentEnv.dress1Size.equalsIgnoreCase("L")) {
			SeleniumFunctions.clickObject("HomePage.SizeL");
			SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");
			Thread.sleep(3000);
		}

		// 9) Click on reserve now
		SeleniumFunctions.clickObject("HomePage.ReserveNow");
		SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");

		// 10) Cart page should open where all the data (price insurance rent
		// etc ) should be of the selected dress only
		boolean verifyElementText1 = SeleniumFunctions.verifyElementText("HomePage.SubTotal",
				objCurrentEnv.dress1Price);

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
		System.out.println(driver.getTitle());

		SeleniumFunctions.enterKeys("HomePage.EmailAddress", objCurrentEnv.gmailUserName);
		SeleniumFunctions.clickObject("HomePage.Next");

		SeleniumFunctions.enterKeys("HomePage.password", objCurrentEnv.gmailPassword);
		SeleniumFunctions.clickObject("HomePage.signIn");

		Set<String> windowsHandles1 = driver.getWindowHandles();
		Iterator<String> iterator2 = windowsHandles1.iterator();

		driver.switchTo().window(iterator2.next());
		System.out.println(driver.getTitle());
		Thread.sleep(3000);
		SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");
		// 15) in Promocode box add promo code FIRSTBUY
		SeleniumFunctions.enterKeys("HomePage.promoCode", "FIRSTBUY");
		SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");
		// Thread.sleep(3000);

		// 16) Click on apply
		SeleniumFunctions.clickObject("HomePage.applyPromoCode");
		SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");
		// Thread.sleep(3000);

		// 17) Promo code should be applied with 10% discount
		/*
		 * boolean verifyElementText2 =
		 * SeleniumFunctions.verifyElementText("HomePage.promoCodeSuccess",
		 * "Promo code FIRSTBUY applied");
		 * 
		 * boolean verifyElementText3 = SeleniumFunctions.verifyElementText(
		 * "HomePage.promoCodeDiscountValue", "49.90");
		 */
		Thread.sleep(3000);

		// 18) Click on Proceed to checkout
		SeleniumFunctions.clickObject("HomePage.proceedToCheckOut");
		SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");

		// 19) Fill Address Phone number and pin code
		SeleniumFunctions.enterKeys("HomePage.fullName", "Test");
		SeleniumFunctions.enterKeys("HomePage.phoneNumber", "8867773565");
		SeleniumFunctions.enterKeys("HomePage.addressBuilding", "Test");
		SeleniumFunctions.enterKeys("HomePage.addressLocality", "Test");
		SeleniumFunctions.enterDataSelectDropDown("HomePage.city", "Gurgaon");
		SeleniumFunctions.enterKeys("HomePage.postalCode", "122002");

		// 20) click on continue
		SeleniumFunctions.clickObject("HomePage.continueAddress");
		SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");
		// Thread.sleep(3000);

		// 21) Click on continue to pay
		SeleniumFunctions.clickObject("HomePage.Continue2Pay");
		// SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");

		// 22) ccavuene payment page should open. Select Avuenue Test
		SeleniumFunctions.enterDataSelectDropDown("HomePage.SelectBank", "AvenuesTest");

		// 23) Click on make payment
		SeleniumFunctions.clickObject("HomePage.MakePayment");
		Thread.sleep(3000);

		// 24) Click on Return to the merchant site (Transaction Status should
		// be selected as N)
		SeleniumFunctions.enterDataSelectDropDown("HomePage.TransactionStatus", "Y");

		// 25) Success page should open with the placed order details
		SeleniumFunctions.clickObject("HomePage.Return2Murchant");
		Thread.sleep(3000);

		// 26) Click on continue
		SeleniumFunctions.clickObject("HomePage.Continue");
		Thread.sleep(3000);
		// 27) Select the same date for which previous order was placed
		SeleniumFunctions.clickObject("HomePage.DateMenu");

		SeleniumFunctions.clickObject(dateBy);
		// SeleniumFunctions.clickObject("HomePage.Date30");
		SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");
		// Thread.sleep(5000);
		Thread.sleep(3000);
		SeleniumFunctions.clickObject("HomePage.closeSideBar");

		// 28) Click on Borrow now
		SeleniumFunctions.clickObject("HomePage.BorrowNow");
		SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");

		TestCaseUtilities.pageDownMultipleTimes(actions, 15);
		Thread.sleep(3000);
		// 4) Select Grace Skater Dress
		SeleniumFunctions.clickObject("HomePage.GraceSkaterDress");
		SeleniumFunctions.wait4ElementtobeDisplayed("HomePage.LoadingGif");

		boolean verifyElementText4 = SeleniumFunctions.verifyElementText("HomePage.FullyBooked",
				"fully booked on selected dates");
		// 29) Select Size L
		// 30) It should be not available and reserve now should be disabled
		if (verifyElementText4) {
			System.out.println("Dress is ordered successfully");
		}

	}

	@AfterSuite
	public void afterSuite() {
		driver.quit();

	}
}
