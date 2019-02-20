package com.Automation.project.helper;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class DropdownHelper
{
public WebDriver driver;

	
	public DropdownHelper(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void selectByVisibleText(String visiblevalue , WebElement element)
	{
	Select select = new Select(element);	
	select.selectByVisibleText(visiblevalue);
	}
	
	public void getselectValue( WebElement element)
	{
	String select = new Select(element).getFirstSelectedOption().getText();	
	}
	
	public void selectValueIndex( WebElement element, int index)
	{
	Select select = new Select(element);
	select.selectByIndex(index);;	
	}
	
	public List<String> getAllDropDownValues(WebElement locator)
	{
		Select select = new Select(locator);
		List<WebElement> element1 = select.getOptions();
		List<String> value = new LinkedList<String>();
		for(WebElement element :element1 )
		{
		value.add(element.getText());
		}
		return value;
	}
}
