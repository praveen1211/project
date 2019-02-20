package com.Automation.project.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class assertionHelper 
{
	public WebDriver driver;
	
	public static synchronized boolean verifyElementPresent(WebElement element)
	{
		boolean isDisplayed = false;
		
		try {
			isDisplayed = element.isDisplayed();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return isDisplayed;
	}
	public static synchronized boolean verifyElementNotPresent(WebElement element)
	{
		boolean isDisplayed = false;
		
		try {
			element.isDisplayed();
		} 
		catch (Exception e) 
		{
			isDisplayed = true;
		}
		return isDisplayed;
	}
	
	public static synchronized boolean verifyTextEquals(WebElement element, String expected)
	{
		boolean flag = false;
		
		try {
			String actualText = element.getText();
			if(actualText.equals(expected))
			{
				return flag = true;
			}
			else
			{
				return flag;
			}
		} 
		catch (Exception e) 
		{
			return flag;
		}
		
		
	}
	
}
