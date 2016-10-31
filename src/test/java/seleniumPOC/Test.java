package seleniumPOC;

import java.util.HashMap;
import java.util.Properties;

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
		HashMap<String, String> propFile2HashMap = objUtility.propFile2HashMap(
				"/home/hemasundar/Xebia/projects/seleniumPOC/src/main/resources/uiautomation.properties");
		System.out.println(propFile2HashMap.size());
		HashMap<String, String> propObj2HashMap = objUtility.propObj2HashMap(sysProperties);
		System.out.println(propObj2HashMap.size());
		objUtility.allPropMap.putAll(propFile2HashMap);
		objUtility.allPropMap.putAll(propObj2HashMap);
		System.out.println(objUtility.allPropMap.size());

		objUtility.objRepoHashMap = objUtility
				.propFile2HashMap("/home/hemasundar/Xebia/projects/seleniumPOC/src/main/resources/objRepo.properties");

		objCurrentEnv.browserName = objUtility.allPropMap.get("browser");
	}

	@org.testng.annotations.Test
	public void sampleTest() {
		System.out.println("test case 1");
		
		WebDriver driver = SeleniumFunctions.openBrowser();
		
		driver.get("http://fit91485.fitnfash.com/");
		
		SeleniumFunctions.clickObject("xpath", "//a[text()='Borrow Now']");
		
		

	}

	
}
