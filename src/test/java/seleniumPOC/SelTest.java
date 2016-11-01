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
	public void openBrowser() throws IOException, InterruptedException {

		// System.setProperty("webdriver.gecko.driver",
		// "/home/hemasundar/Apps/geckodriver");
		System.setProperty("webdriver.firefox.marionette", "false");

		// FirefoxBinary binary = new FirefoxBinary(new
		// File("/home/hema/firefox-sdk/bin/firefox"));
		// FirefoxBinary binary = new FirefoxBinary(new
		// File("/home/hemasundar/Apps/firefox/firefox"));
		System.out.println("fire fox binary path set successfully.");
		// GeckoDriverService createDefaultService =
		// GeckoDriverService.createDefaultService();

		// createDefaultService.start();
		// System.out.println(createDefaultService.getUrl());
		// System.out.println(createDefaultService.getClass());
		driver = new FirefoxDriver();
		// driver = new FirefoxDriver(binary, new FirefoxProfile());
		System.out.println("firefox browser launched successfully.");
		driver.get("http://google.com");
		System.out.println("User navigated to app url.");
		File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		System.out.println("temp file of screen shot created.");
		File file = new File(System.getProperty("user.dir") + "src/main/resources/test.png");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();

		}
		Files.copy(screenshotAs.toPath(), new FileOutputStream(file));
		System.out.println(file.getTotalSpace());
		System.out.println("Screen shot file copied to the mentioned location successfully.");
//		Thread.sleep(300000);
		driver.quit();
	}
}