package com.solidprofessor.qa.utils;

public class TestResultTracker {

	private static StringBuilder results = new StringBuilder();

	public static void addTestResult(Object expected, Object actual) {
		results.append("EXP[").append(expected + "]").append(" : ACT[").append(actual).append("]\n");
	}

	public static void addTestResult(int expected, int actual) {
		results.append("EXP[").append(expected + "]").append(" : ACT[").append(actual).append("]\n");
	}

	public static void addTestResult(double expected, double actual) {
		results.append("EXP[").append(expected + "]").append(" : ACT[").append(actual).append("]\n");
	}

	public static void addTestResult(boolean expected, boolean actual) {
		results.append("EXP[").append(expected + "]").append(" : ACT[").append(actual).append("]\n");
	}

	public static void addTestResult(char expected, char actual) {
		results.append("EXP[").append(expected + "]").append(" : ACT[").append(actual).append("]\n");
	}

	public static void addTestResult(Object expected, Object actual, Object errorMessage) {
		results.append("EXP[").append(expected + "]").append(" : ACT[").append(actual + "]").append(" : Exception[")
				.append(errorMessage).append("]\n");
	}

	public static void addUnexpectedException(String errorMessage) {
		results.append(" : Exception Occured[").append(errorMessage).append("]\n");
	}

	public static String getAllTestResults() {
		return results.toString();
	}

	public static void resetTestResults() {
		results.setLength(0);
	}

}
