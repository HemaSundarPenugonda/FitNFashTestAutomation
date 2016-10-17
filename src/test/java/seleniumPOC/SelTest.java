package seleniumPOC;

import java.io.File;
import java.io.IOException;

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

//		System.setProperty("webdriver.gecko.driver", "/home/hemasundar/Apps/geckodriver");
		System.setProperty("webdriver.firefox.marionette", "false");
		FirefoxBinary binary = new FirefoxBinary(new File("/home/hemasundar/Apps/firefox/firefox"));
		// GeckoDriverService createDefaultService =
		// GeckoDriverService.createDefaultService();

		// createDefaultService.start();
		// System.out.println(createDefaultService.getUrl());
		// System.out.println(createDefaultService.getClass());
		driver = new FirefoxDriver(binary, new FirefoxProfile());

		driver.get("http://google.com");
	}
}