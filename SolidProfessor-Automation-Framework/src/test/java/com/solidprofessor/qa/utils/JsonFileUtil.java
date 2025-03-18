package com.solidprofessor.qa.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonFileUtil {

	private Map<String, Map<String, Object>> fileDataMaps;
	private Map<String, Object> filteredObjMap;

	/**
	 * Loads JSON file data to a Map object and filters the map if filterBy is not
	 * null or "NONE".
	 *
	 * @param filePath The file path of the JSON file to be loaded.
	 * @param filterBy The key to be used to filter the JSON data.
	 * @return A Map object containing the filtered JSON data.
	 */
	public Map<String, Object> loadJsonFileDataToMap(String filePath, String filterBy) {
		try {

			JsonObject jsonObject = JsonParser.parseReader(new FileReader(filePath)).getAsJsonObject();
			Gson gson = new Gson();
			fileDataMaps = gson.fromJson(jsonObject, Map.class);

			if (filterBy != null && !filterBy.equals("NONE")) {
				filteredObjMap = fileDataMaps.get(filterBy);
			} else {
				filteredObjMap = new LinkedHashMap<>();

				for (Map.Entry<String, Map<String, Object>> entry : fileDataMaps.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					filteredObjMap.put(key, value);
				}
			}

			System.out.println(filteredObjMap);
			return filteredObjMap;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			String erroMessage = "Error while loading JSON file: " + e.getMessage();
			TestResultTracker.addUnexpectedException(erroMessage);

			return null;
		}
	}

 

 
	  
}
