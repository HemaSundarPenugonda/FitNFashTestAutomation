package com.fitnfash.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class Utilities {
	public HashMap<String, String> allPropMap = new HashMap<>();
	public static List<String[]> objRepo = new ArrayList<String[]>();

	public HashMap<String, String> propFile2HashMap(String path) {

		Properties properties = loadPropertiesfromFile(path);

		return propObj2HashMap(properties);
	}

	public HashMap<String, String> propObj2HashMap(Properties properties) {
		HashMap<String, String> map = new HashMap<>();
		Set<Object> keySet = properties.keySet();
		for (Object object : keySet) {
			Object object2 = properties.get(object);
			map.put(object.toString(), object2.toString());
		}
		return map;
	}

	public Properties loadPropertiesfromFile(String path) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static String[] getLocatorValues(String name) {
		String[] split = name.split("\\.");
		for (String[] string : objRepo) {
			if (string[0].equalsIgnoreCase(split[0])) {
				if (string[1].equalsIgnoreCase(split[1])) {
					String[] str = new String[2];
					str[0] = string[3];
					str[1] = string[4];
					return str;
				}
			}
		}
		return null;
	}

	public void loadObjRepo() {
		HashMap<String, String> objRepoHashMap = propFile2HashMap(
				System.getProperty("user.dir") + "/src/main/resources/objRepo.properties");
		Set<String> keySet = objRepoHashMap.keySet();

		for (String indKey : keySet) {
			String[] split = new String[5];
			String[] split1 = indKey.split("\\.");
			split[0] = split1[0];
			split[1] = split1[1];
			split[2] = split1[2];
			split[3] = split1[3];
			split[4] = objRepoHashMap.get(indKey);
			objRepo.add(split);

		}
	}

	public void loadProperties() {
		Properties sysProperties = System.getProperties();
		HashMap<String, String> propFile2HashMap = propFile2HashMap(
				System.getProperty("user.dir") + "/src/main/resources/uiautomation.properties");

		HashMap<String, String> propObj2HashMap = propObj2HashMap(sysProperties);

		allPropMap.putAll(propFile2HashMap);
		allPropMap.putAll(propObj2HashMap);
	}

	public void pageDownMultipleTimes(Actions actions, int count) throws InterruptedException {
		for (int i = 0; i < count; i++) {
			actions.sendKeys(Keys.PAGE_DOWN).build().perform();
			Thread.sleep(1000);
		}
	}

	public String readFile(File file) {

		try {

			file.getParentFile().mkdirs();
			file.createNewFile();

			Scanner scanner = new Scanner(file);
			String str = scanner.useDelimiter("\\Z").next();
			scanner.close();
			return str;
		} catch (IOException e) {
			System.out.println("Exception occurred while creating file" + e);
			return "";
		}

	}
}