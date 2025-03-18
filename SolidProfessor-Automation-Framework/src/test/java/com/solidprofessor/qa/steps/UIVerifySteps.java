package com.solidprofessor.qa.steps;

import static com.solidprofessor.qa.setup.ObjInitialize.getInstance;


import com.solidprofessor.qa.utils.TestResultTracker;

import io.cucumber.java.en.Then;

public class UIVerifySteps {

	@Then(".* elementDisplayed \"(.*)\"$")
	public void elementDisplayed(String objectKey) {

		boolean isDisplayed = getInstance().commonUIObj.isElementDisplayed(objectKey);
		getInstance().softAssertObj.assertTrue(isDisplayed);
		if (!isDisplayed) {
			getInstance().commonUIObj.takeScreenshot();
		} else {
			String expWebElement = objectKey + " DISPLAYED:" + true;
			String actWebElement = objectKey + " DISPLAYED:" + isDisplayed;
			TestResultTracker.addTestResult(expWebElement, actWebElement);
		}
	}

	@Then(".* messageVerify \"(.*)\" \"(.*)\"$")
	public void verifyMessage(String objectKey, String expMessage) {
		String actMessage = getInstance().commonUIObj.getText(objectKey);
		boolean isMessagedMatched = actMessage.equals(expMessage);

		getInstance().softAssertObj.assertEquals(actMessage, expMessage);

		if (!isMessagedMatched) {
			getInstance().commonUIObj.takeScreenshot();
		} else {
			TestResultTracker.addTestResult(expMessage, actMessage);
		}
	}

	@Then(".* verifyPageTitle \"(.*)\"$")
	public void verifyPageTitle(String expPageTitle) {
		String actPageTitle = getInstance().commonUIObj.getTitle();
		boolean isTitleMatched = actPageTitle.equals(expPageTitle);

		getInstance().softAssertObj.assertEquals(actPageTitle, expPageTitle);

		if (!isTitleMatched) {
			getInstance().commonUIObj.takeScreenshot();
		} else {
			TestResultTracker.addTestResult(expPageTitle, actPageTitle);
		}
	}
}
