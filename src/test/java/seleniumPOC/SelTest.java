package seleniumPOC;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.testng.annotations.Test;

public class SelTest {
	WebDriver driver;

	@Test
	public void openBrowser() throws IOException {

		// System.setProperty("webdriver.gecko.driver",
		// "/home/hemasundar/Apps/geckodriver");
		System.setProperty("webdriver.firefox.marionette", "false");
		
		FirefoxBinary binary = new FirefoxBinary(new File("/home/hema/firefox-sdk/bin/firefox"));

//		FirefoxBinary binary = new FirefoxBinary(new File("/home/hemasundar/Apps/firefox/firefox"));
		// GeckoDriverService createDefaultService =
		// GeckoDriverService.createDefaultService();

		// createDefaultService.start();
		// System.out.println(createDefaultService.getUrl());
		// System.out.println(createDefaultService.getClass());
		driver = new FirefoxDriver(binary, new FirefoxProfile());

		driver.get("http://google.com");
		File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		File file = new File("src/main/resources/test.png");
		if (!file.exists()) {
			file.createNewFile();
		}
		Files.copy(screenshotAs.toPath(), new FileOutputStream(file));
		driver.quit();
	}
}
