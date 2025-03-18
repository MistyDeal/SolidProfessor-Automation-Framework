package com.solidprofessor.qa.utils;

import static com.solidprofessor.qa.setup.ObjInitialize.getInstance;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.solidprofessor.qa.steps.Hooks;

 

public class CommonUI {
	/**
	 * Initializes a new instance of a WebDriver with the specified browser and sets
	 * it as the driver for the current Selenium session.
	 *
	 * @param browser a string representing the browser to use (e.g. "chrome",
	 *                "firefox", "ie").
	 * @throws Exception if an invalid browser type is provided or if there is an
	 *                   error initializing the driver.
	 */
	public void openBrowser(String browser) throws Exception {
		try {
			getInstance().driverObj = createDriver(browser);
		} catch (Exception e) {
			e.printStackTrace();
			String erroMessage = "Error initializing browser: " + e.getMessage();
			getInstance().logObj.logError(erroMessage);
			System.err.println(erroMessage);
			TestResultTracker.addUnexpectedException(erroMessage);
			throw e;
		}
	}

	/**
	 * Creates a new instance of the specified browser driver.
	 * 
	 * @param browser the name of the browser to create the driver for, e.g.
	 *                "chrome", "firefox", "ie"
	 * @return the new WebDriver instance
	 * @throws Exception if an invalid browser name is provided or if an error
	 *                   occurs while creating the driver
	 */
	private WebDriver createDriver(String browser) throws Exception {
		switch (browser.toLowerCase()) {
		case "chrome":
			
			return new ChromeDriver();
		case "firefox":
			return new FirefoxDriver();
		case "edge":
			return new EdgeDriver();			
		case "remote":
			ChromeOptions chromeOptions = new ChromeOptions();
			return new RemoteWebDriver(new URL(Constants.environmentMap.get("gridHubURL").toString()), chromeOptions);
		default:
			String erroMessage = "Invalid browser type: " + browser;
			getInstance().logObj.logError(erroMessage);
			throw new Exception(erroMessage);
		}
	}

	/**
	 * Waits for the specified element to be visible on the web page.
	 *
	 * @param objectName the name of the web element to wait for
	 * @return the WebElement object that was found
	 * @throws NoSuchElementException if the element is not found after a timeout or
	 *                                if the locator is invalid
	 */
	public WebElement waitForElement(String objectName) throws NoSuchElementException {
		try {

			By locator = getInstance().objectMapObj.getLocator(objectName);
		 
			WebDriverWait wait = new WebDriverWait(getInstance().driverObj, Duration.ofSeconds(Constants.DEFAULT_WAIT_TIME));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			takeScreenshot();
			String erroMessage = "Element " + objectName + " not found within " + Constants.DEFAULT_WAIT_TIME
					+ " seconds";

			getInstance().logObj.logError(erroMessage);
			TestResultTracker.addUnexpectedException(erroMessage);

			throw new NoSuchElementException(erroMessage);
		}
	}

	/**
	 * Navigates to the specified URL.
	 *
	 * @param url the URL to navigate to
	 */
	public void navigateTo(String url) {
		WebDriver driver = getInstance().driverObj;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.DEFAULT_WAIT_TIME));
		driver.get(url);
	}

	/**
	 * Clicks the element with the specified object name.
	 *
	 * @param objectName the name of the object to click
	 */
	public void click(String objectName) {

		WebElement element = waitForElement(objectName);
		element.click();
	}

	/**
	 * Enters the specified text into the element with the specified object name.
	 *
	 * @param objectName the name of the object to enter text into
	 * @param text       the text to enter into the element
	 * @throws InterruptedException
	 */
	public void enter(String objectName, String text) {
		WebElement element = waitForElement(objectName);
		element.click();
		element.sendKeys(text);
	}

	/**
	 * Returns the text of the element with the specified object name.
	 *
	 * @param objectName the name of the object to retrieve the text from
	 * @return the text of the element with the specified object name
	 */
	public String getText(String objectName) {
		WebElement element = waitForElement(objectName);
		return element.getText();
	}

	/**
	 * Returns the value of the specified attribute of the element with the
	 * specified object name.
	 *
	 * @param objectName the name of the object to retrieve the attribute value from
	 * @param attribute  the name of the attribute to retrieve
	 * @return the value of the specified attribute of the element with the
	 *         specified object name
	 */
	public String getAttribute(String objectName, String attribute) {
		WebElement element = waitForElement(objectName);
		return element.getDomAttribute(attribute);
	}

	/**
	 * Returns the title of the current web page.
	 *
	 * @return the title of the current web page
	 */
	public String getTitle() {
		return getInstance().driverObj.getTitle();
	}

	/**
	 * Maximizes the current web page window.
	 */
	public void maximize() {
		getInstance().driverObj.manage().window().maximize();
	}

 

	 
 

 

	/**
	 * Checks if an element is displayed on the page.
	 * 
	 * @param objectName the name of the element to check
	 * @return true if the element is displayed, false otherwise
	 */
	public boolean isElementDisplayed(String objectName) {

		WebElement element = waitForElement(objectName);
		return element.isDisplayed();

	}

 
	/**
	 * Quits the current WebDriver instance and closes all associated windows.
	 */
	public void quitBrowser() {
		try {
			Thread.sleep(3000);
			if (getInstance().driverObj != null) {
				getInstance().driverObj.quit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			String erroMessage = "Error while quitting browser: " + e.getMessage();
			getInstance().logObj.logError(erroMessage);
			TestResultTracker.addUnexpectedException(erroMessage);
		}
	}

 




	public void takeScreenshot() {
		try {
			TakesScreenshot screenshot = (TakesScreenshot) getInstance().driverObj;
			File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
			String screenshotPath = "screenshots/" + System.currentTimeMillis() + ".png";

			File screenshotFile = new File(screenshotPath);

			 
			FileUtils.copyFile(srcFile, screenshotFile);
		
			getInstance().logObj.logInfo("Screenshot saved to: " + screenshotPath);

			byte[] screenshotBytes = FileUtils.readFileToByteArray(screenshotFile);
			Hooks.scenario.attach(screenshotBytes, "image/png", "Screenshot");

			getInstance().logObj.logInfo("Took Screenshot");

			// delete the screenshot file
			if (screenshotFile.exists()) {
				FileUtils.forceDelete(screenshotFile);
				String message = "Screenshot file deleted.";
				getInstance().logObj.logInfo(message);

			}

		} catch (IOException e) {
			e.printStackTrace();

			String erroMessage = "Error While Taking screenshot: " + e.getMessage();
			getInstance().logObj.logError(erroMessage);
			TestResultTracker.addUnexpectedException(erroMessage);
		}
	}

}
