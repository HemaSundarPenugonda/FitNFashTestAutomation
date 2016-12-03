package com.fitnfash.common;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class TestCaseUtilities {
	public static Utilities objUtility = new Utilities();
	public static CurrentEnv objCurrentEnv = new CurrentEnv();
	
	public static void loadObjRepo() {
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
	}

	public static void loadProperties() {
		Properties sysProperties = System.getProperties();
		HashMap<String, String> propFile2HashMap = objUtility
				.propFile2HashMap(System.getProperty("user.dir") + "/src/main/resources/uiautomation.properties");
		
		HashMap<String, String> propObj2HashMap = objUtility.propObj2HashMap(sysProperties);
		
		objUtility.allPropMap.putAll(propFile2HashMap);
		objUtility.allPropMap.putAll(propObj2HashMap);
	}
	public static void pageDownMultipleTimes(Actions actions, int count) throws InterruptedException {
		for (int i = 0; i < count; i++) {
			actions.sendKeys(Keys.PAGE_DOWN).build().perform();
			Thread.sleep(1000);
		}
	}
}
