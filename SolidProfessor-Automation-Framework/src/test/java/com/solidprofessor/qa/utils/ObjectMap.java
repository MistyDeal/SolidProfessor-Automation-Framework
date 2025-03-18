package com.solidprofessor.qa.utils;

import static com.solidprofessor.qa.setup.ObjInitialize.getInstance;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

/**
 * 
 * The ObjectMap class reads and loads the application properties file
 * 
 * and provides the By object for locating a web element on the page.
 */
public class ObjectMap {
	private Properties properties;// it has PRIVATE access modifier that means only class can access

	/**
	 * Constructs an ObjectMap object and loads the application properties file. If
	 * the file cannot be found or loaded, an IOException is thrown.
	 */
	public ObjectMap(String filePath) {
		properties = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(filePath);
			properties.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			String erroMessage = "Cannot Load Property File" + e.getMessage();
			getInstance().logObj.logError(erroMessage);
			TestResultTracker.addUnexpectedException(erroMessage);
		}
	}

	/**
	 * Returns the By object for locating a web element on the page, based on the
	 * object name defined in the application properties file.
	 * 
	 * @param propertyName the name of the object in the application properties
	 *                     file.
	 * @return the By object for locating the web element on the page.
	 * @throws Exception if the object name is not found or the locator type is
	 *                   invalid.
	 */

	public By getLocator(String propertyName) throws Exception {

		String locator = properties.getProperty(propertyName);
		getInstance().logObj.logInfo("Locator: " + locator);

		String locatorType = locator.split("~")[0];
		System.out.println("locatorType: " + locatorType);

		String locatorValue = locator.split("~")[1];
		System.out.println("locatorValue: " + locatorValue);

		switch (locatorType) {
		case "id":
			return By.id(locatorValue);
		case "name":
			return By.name(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		case "css":
			return By.cssSelector(locatorValue);
		case "linkText":
			return By.linkText(locatorValue);
		case "partialLinkText":
			return By.partialLinkText(locatorValue);
		case "tagName":
			return By.tagName(locatorValue);
		case "className":
			return By.className(locatorValue);
		default:
			String erroMessage = "Invalid locator type: " + locatorType;
			getInstance().logObj.logError(erroMessage);
			throw new Exception(erroMessage);
		}
	}
}
