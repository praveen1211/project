package com.Automation.project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Automation.project.helper.waitHelper;
public class HomePage
{
	WebDriver driver;
	waitHelper wait;
	
	@FindBy(xpath=".//*[@id='menu_admin_viewAdminModule']/b")
	WebElement Admin;
	
	public HomePage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	 
}
