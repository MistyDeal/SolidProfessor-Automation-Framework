package com.solidprofessor.qa.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/resources/features/ui/", 
					glue = "com.solidprofessor.qa.steps", 
					tags = "@smoke",				 
					plugin = {
							"summary", "pretty", 
							"html:target/cucumber-reports.html", "json:target/cucumber-reports",
							"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })
public class UIRunner {

}
 