package com.Automation.project.testscripts;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Automation.project.BasicDetails_reader;
import com.Automation.project.ExecutionTest;
import com.Automation.project.helper.BrowserHelper;
import com.Automation.project.helper.waitHelper;
import com.Automation.project.pageObject.LoginScript;

public class testLoginScript extends ExecutionTest
{
	
@Test
public void testLogin()
{	
	BasicDetails_reader reading = new BasicDetails_reader(BasicDetails);
	driver.get(reading.getUrl());
	System.out.println("\n"+"Url accessed");
	BrowserHelper hel = new BrowserHelper(driver);
	hel.WindowMaximize();
	LoginScript login = new LoginScript(driver);
	login.loginfunctionality(reading.getUserName(), reading.getPassword());	
}
}
