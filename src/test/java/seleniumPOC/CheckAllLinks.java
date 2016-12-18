package seleniumPOC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.fitnfash.common.SeleniumFunctions;

public class CheckAllLinks {

	private final static String USER_AGENT = "Mozilla/5.0";
	static List<String> brokenLinkList = new ArrayList<String>();
	static ArrayList<String> allLinksSet = new ArrayList<>();

	public static void main(String[] args) throws InterruptedException, IOException {
		int invalidLinksCount = 0;

		WebDriver driver = SeleniumFunctions.openBrowser(20);
		driver.get("http://fit91485.fitnfash.com/");
		allLinksSet.add("http://fit91485.fitnfash.com/");
		// List<WebElement> anchorTagsList =
		// driver.findElements(By.tagName("a"));
		// addUniqueUrl2List(allLinksSet, anchorTagsList);
		List<WebElement> anchorTagsList;
		for (int i = 0; i < allLinksSet.size(); i++) {
			driver.get(allLinksSet.get(i));
			Thread.sleep(2000);

			anchorTagsList = driver.findElements(By.tagName("a"));
			addUniqueUrl2List(allLinksSet, anchorTagsList);
		}
		System.out.println("Total number of links: " + allLinksSet.size());

		for (int i = 0; i < allLinksSet.size(); i++) {
			String currentURL = allLinksSet.get(i);

			if (currentURL.toLowerCase().contains("fitnfash.com")) {
				CloseableHttpClient client = HttpClientBuilder.create().build();
				HttpGet request = new HttpGet(currentURL);

				HttpResponse response = null;
				try {
					response = client.execute(request);
				} catch (IOException e) {
					e.printStackTrace();
				}

				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != 200) {
					System.out.println("Broken URL: " + currentURL);
					invalidLinksCount++;
					brokenLinkList.add(currentURL);
				}
				client.close();
			}
			
		}
		System.out.println("Total no. of invalid links are " + invalidLinksCount + " and they are:" + brokenLinkList);
		if (invalidLinksCount == 0) {
			System.out.println("All the links on the page are valid.");

		} else {

		}

	}

	private static void addUniqueUrl2List(ArrayList<String> allLinksSet, List<WebElement> anchorTagsList) {
		String url = null;
		for (WebElement anchorTagElement : anchorTagsList) {
			if (anchorTagElement != null) {
				try {
					url = anchorTagElement.getAttribute("href");
				} catch (Exception e) {
					System.out.println("Exception : " + e);

				}
				if (url != null && !url.contains("javascript") && !url.contains("@fitnfash.com") && url.startsWith("http://fit91485.fitnfash.com")) {
					boolean add = check4DuplicateAndAddElement(allLinksSet, url);
				} else {
					System.out.println("Invalid URL" + url);
				}

			}
		}

	}

	public static int sendGET() throws IOException {
		String url = "http://www.google.com/search?q=mkyong";
		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());
		return responseCode;

	}

	public static boolean check4DuplicateAndAddElement(ArrayList<String> list, String str) {
		if (!list.contains(str)) {
			list.add(str);
			return true;
		}
		return false;

	}
}
