package com.Automation.project.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericHelper 
{
public WebDriver driver;

	
	public GenericHelper(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String readValueFormElement(WebElement element)
	{
		if(null == element)
		{
			return null;
		}
		boolean displayed = false;
		try {
			//Checking the element is located in the UI
			displayed = isDisplayed(element);
		} catch (Exception e) {
			return null;
		}
		if(!displayed)
		{
			return null;
		}
			String text = element.getText();
			return text;
		}

public String readValueFromInput(WebElement element)
{
	if(null == element)
	{
		return null;
	}
	if(!isDisplayed(element))
	{
		return null;
	}
	String value = element.getAttribute("value");
	return value;
}

public boolean isDisplayed(WebElement element)
{
	try {
		element.isDisplayed();
		return true;
	} catch (Exception e) 
	{
		return false;
	}
}
public boolean isNotDisplayed(WebElement element)
{
	try {
		element.isDisplayed();
		return false;
	} catch (Exception e) 
	{
		return true;
	}
}

public String getDisplayText(WebElement element) 
{
	if(null == element)
	{
		return null;
	}
	if(!isDisplayed(element))
	{
		return null;
	}
	return element.getText();
}
}
