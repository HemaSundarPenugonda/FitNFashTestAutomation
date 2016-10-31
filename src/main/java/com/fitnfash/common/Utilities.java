package com.fitnfash.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class Utilities {
	public HashMap<String, String> allPropMap = new HashMap<>();
	public HashMap<String, String> objRepoHashMap = new HashMap<>();
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
}