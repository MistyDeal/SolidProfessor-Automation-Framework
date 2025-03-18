package com.solidprofessor.qa.steps;

import static com.solidprofessor.qa.setup.ObjInitialize.getInstance;

import java.util.List;
import java.util.Map;

import com.solidprofessor.qa.utils.Constants;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

public class ExecutionSetUpSteps {

	@Given(".* executionSetUp$")
	public void executionSetUp(DataTable dataTable) {
		List<Map<String, String>> listMap = dataTable.asMaps();

		for (Map<String, String> map : listMap) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				Constants.testExecutionDetails.put(key, value);
			}
		}

		getInstance();
	}

}
