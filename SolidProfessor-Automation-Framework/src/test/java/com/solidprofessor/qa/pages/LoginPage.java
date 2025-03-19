package com.solidprofessor.qa.pages;

import static com.solidprofessor.qa.setup.ObjInitialize.getInstance;

import com.solidprofessor.qa.utils.Constants;

public class LoginPage {

	public void login() {

		String url = Constants.environmentMap.get("url").toString();
		getInstance().commonUIObj.navigateTo(url);
		// enter username
		getInstance().commonUIObj.enter("login.username", Constants.environmentMap.get("username").toString());
		// enter password
		getInstance().commonUIObj.enter("login.password", Constants.environmentMap.get("password").toString());
		// click login
		getInstance().commonUIObj.click("login.loginBtn");
	}

}
//using this as an example to show polling in jenkins
