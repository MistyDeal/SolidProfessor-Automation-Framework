package com.solidprofessor.qa.steps;

import static com.solidprofessor.qa.setup.ObjInitialize.getInstance;

import com.solidprofessor.qa.utils.Constants;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class UISteps {

	@Given(".* loginToApp \"(.*)\" \"(.*)\"$")
	public void loginToApp(String browser, String region) throws Exception {

		Constants.testExecutionDetails.put("region", region);

		String envPath = Constants.filePathMap.get("Environment").toString();
		Constants.environmentMap = getInstance().jsonFileDataObj.loadJsonFileDataToMap(envPath,
				Constants.testExecutionDetails.get("region"));

		getInstance().commonUIObj.openBrowser(browser);

		getInstance().loginPageObj.login();

	}

	@When(".* enterValue \"(.*)\" \"(.*)\"$")
	public void enterValue(String objectKey, String objectValue) throws InterruptedException {

		getInstance().commonUIObj.enter(objectKey, objectValue);

	}

	@When(".* clickElement \"(.*)\"$")
	public void clickElement(String objectKey) throws InterruptedException {
		getInstance().commonUIObj.click(objectKey);

	}

}
