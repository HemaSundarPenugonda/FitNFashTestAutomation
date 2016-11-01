package seleniumPOC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	}

	@org.testng.annotations.Test
	public void sampleTest() throws InterruptedException {
		System.out.println("test case 1");

		WebDriver driver = SeleniumFunctions.openBrowser();

		// 1) Go to home page
		driver.get("http://fit91485.fitnfash.com/");

		// 2) Click view all/borrow now
		SeleniumFunctions.clickObject("HomePage.BorrowNow");
//		SeleniumFunctions.clickObject("xpath", "//a[text()='Borrow Now']");

		// 3) 70 items should be present (test data ) (Assertion)
		boolean verifyElementText = SeleniumFunctions.verifyElementText("HomePage.ResultCount", "70 results found.");

		// 4) Select Grace Skater Dress
		SeleniumFunctions.clickObject("HomePage.GraceSkaterDress");

		// 5) Product details page should appear

		// 6) Click on select date
		SeleniumFunctions.clickObject("HomePage.SelectDate");

		SeleniumFunctions.clickObject("HomePage.Date30");
		Thread.sleep(5000);

		// 7) select 7 days ahead date (if today is 1 then select 8)
		// 8) select size M
		SeleniumFunctions.clickObject("HomePage.SizeM");

		// 9) Click on reserve now
		SeleniumFunctions.clickObject("HomePage.ReserveNow");

		// 10) Cart page should open where all the data (price insurance rent
		// etc ) should be of the selected dress only

		// 11) Click on add an alternative
		// 12) Click on Jena Gown
		// 13) Product detail should open , click on size L
		// 14) click on add alternative
		// 15) Click on login
		// 16) Login through google
		// 15) in Promocode box add promo code FIRSTBUY
		// 16) Click on apply
		// 17) Promo code should be applied with 10% discount
		// 18) Click on Proceed to checkout
		// 19) Fill Address Phone number and pin code
		// 20) click on continue
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
