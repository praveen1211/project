package com.Automation.project.helper;

import java.util.LinkedList;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class BrowserHelper 
{
	public WebDriver driver;
	
	public BrowserHelper(WebDriver driver)
	{
		this.driver = driver;
	}
	public void WindowMaximize()
	{
		driver.manage().window().maximize();
	}
	public  void goBack()
	{
		driver.navigate().back();
	}
	public  void goForword()
	{
		driver.navigate().forward();
	}
	public  void goRefresh()
	{
		driver.navigate().refresh();
	}
	public Set<String> getWindowhandles()
	{
		return driver.getWindowHandles();
	}
	
	public void SwitchToWindow(int index) 
	{
	LinkedList<String>	window = new LinkedList<String>(getWindowhandles());
	if(index < 0 || index > window.size())
		throw new IllegalArgumentException("Invaid index="+index);
	driver.switchTo().window(window.get(index));
	}
	
	public void SwitchToParentWindow(int index) 
	{
	LinkedList<String>	window = new LinkedList<String>(getWindowhandles());
	driver.switchTo().window(window.get(0));
	}
	
	public void SwitchToParentWithChild() 
	{
	LinkedList<String>	window = new LinkedList<String>(getWindowhandles());
	for(int i=1; i<window.size();i++)
	{
		driver.switchTo().window(window.get(i));
		driver.close();
	}
	
	SwitchToParentWindow(0);
	}
	
	public void switchToFrame(String nameorId)
	{
		driver.switchTo().frame(nameorId);
	}
}
