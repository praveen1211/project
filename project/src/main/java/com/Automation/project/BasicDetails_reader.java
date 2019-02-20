package com.Automation.project;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.Automation.project.ExecutionTest;

public class BasicDetails_reader extends ExecutionTest
{
	private Properties BasicDetails;
	
	public BasicDetails_reader(Properties BasicDetails)
	{
		this.BasicDetails = BasicDetails;
	}
	public String getUserName() 
	{
		return BasicDetails.getProperty("Username");
	}
	
	public String getPassword() 
	{
		return BasicDetails.getProperty("Password");
	}
	
	public String getBrowser() 
	{
		return BasicDetails.getProperty("Browser");
	}
	
	public String getUrl() 
	{
		return BasicDetails.getProperty("Url");
	}
	
	public int getPageLoadTimeOut()
	{
		return Integer.parseInt(BasicDetails.getProperty("PageLoadTimeout"));
	}
	
	public int getImplicitWait()
	{
	return 	Integer.parseInt(BasicDetails.getProperty("ImplicitWait"));
	}
	
	public int getExplicitWait()
	{
	return 	Integer.parseInt(BasicDetails.getProperty("ExplicitWait"));
	}
}
