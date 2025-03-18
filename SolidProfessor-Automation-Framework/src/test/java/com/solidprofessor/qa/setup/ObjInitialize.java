package com.solidprofessor.qa.setup;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.solidprofessor.qa.pages.LoginPage;
import com.solidprofessor.qa.utils.CommonUI;
import com.solidprofessor.qa.utils.Constants;
import com.solidprofessor.qa.utils.JsonFileUtil;
import com.solidprofessor.qa.utils.LoggerUtil;
import com.solidprofessor.qa.utils.ObjectMap;
import com.solidprofessor.qa.utils.TestResultTracker;

public class ObjInitialize extends Constants {

	private static ObjInitialize instance;

	public ObjectMap objectMapObj;
	public CommonUI commonUIObj;
	public WebDriver driverObj;
	public LoginPage loginPageObj;
	public SoftAssert softAssertObj;
	public JsonFileUtil jsonFileDataObj;
	public LoggerUtil logObj;

	/**
	 * Constructs an ObjInitialize object and initializes the ObjectMap, CommonUI,
	 * and Environment objects.
	 */
	private ObjInitialize() {

		// Create instance of JsonFileData Class
		jsonFileDataObj = new JsonFileUtil();

		// Load all the file path locations stored in jsonFile file and store in Map
		filePathMap = jsonFileDataObj.loadJsonFileDataToMap(FILE_PATH, "FilePath");

		softAssertObj = new SoftAssert();
		logObj = new LoggerUtil();

		// Retrieve testcase_type {either API or UI } This info passed from background
		// section in feature file
		String typeOfExecution = testExecutionDetails.get("testcase_type").toUpperCase();

		switch (typeOfExecution) {
		case "API":
			break;

		case "UI":
			String solidProfFilePath = filePathMap.get("SolidProfessorRepository").toString();
			objectMapObj = new ObjectMap(solidProfFilePath);
			commonUIObj = new CommonUI();
			loginPageObj = new LoginPage();
			break;
		}
	}

	/**
	 * Returns the singleton instance of the ObjInitialize class. If the instance
	 * has not been created, it will be created.
	 * 
	 * @return the singleton instance of the ObjInitialize class.
	 */
	public static ObjInitialize getInstance() {
		if (instance == null) {
			instance = new ObjInitialize();

		}
		return instance;
	}

	public void reset() {
		TestResultTracker.resetTestResults();
		Constants.testExecutionDetails.clear();
		softAssertObj = new SoftAssert();
	}

}
