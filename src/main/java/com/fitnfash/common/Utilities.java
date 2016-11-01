package com.fitnfash.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

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
}