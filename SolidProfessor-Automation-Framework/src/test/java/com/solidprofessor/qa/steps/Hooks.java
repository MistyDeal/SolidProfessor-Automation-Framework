package com.solidprofessor.qa.steps;

import static com.solidprofessor.qa.setup.ObjInitialize.getInstance;
import java.sql.SQLException;

import com.solidprofessor.qa.utils.Constants;
import com.solidprofessor.qa.utils.TestResultTracker;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    public static Scenario scenario;

	@Before
	public void setUp(Scenario scenario){
		Hooks.scenario = scenario;
		Constants.testExecutionDetails.put("testcase_name", scenario.getName());
	}

	@After
	public void tearDown() throws SQLException {

		String typeOfExecution =  Constants.testExecutionDetails.get("testcase_type").toUpperCase();
		
		switch (typeOfExecution) {
		case "UI":
			getInstance().commonUIObj.quitBrowser();
			break;
		}

		try {
			getInstance().softAssertObj.assertAll();
			String executionStatus = scenario.getStatus().toString();
			setAndUpdateResult(executionStatus);

		} catch (AssertionError e) {
			String errorMessage = "assertAll() FAILED: " + e.getMessage();
			getInstance().logObj.logError(errorMessage);
			TestResultTracker.addUnexpectedException(errorMessage);
			setAndUpdateResult("FAILED");
			throw new AssertionError(e.getMessage(), e);
		}
	}
	
	
	public void setAndUpdateResult(String executionStatus) throws SQLException {		
		Constants.testExecutionDetails.put("status", executionStatus);
		Constants.testExecutionDetails.put("message",
				TestResultTracker.getAllTestResults());
		getInstance().reset();
	}
}
