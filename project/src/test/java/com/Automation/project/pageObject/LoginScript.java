package com.Automation.project.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Automation.project.BasicDetails_reader;
import com.Automation.project.ExecutionTest;
import com.Automation.project.helper.GenericHelper;
import com.Automation.project.helper.waitHelper;
import com.Automation.project.otherFeatures.ElementHighlighting;

public class LoginScript 
{
	WebDriver driver;
	waitHelper wait;
	
	@FindBy(xpath=".//*[@id='txtUsername']")
	WebElement Username;
	
	@FindBy(xpath=".//*[@id='txtPassword']")
	WebElement Password;
	
	@FindBy(xpath=".//*[@id='btnLogin']")
	WebElement Submit;
	
	public LoginScript(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new waitHelper(driver);
		wait.waitForElement(driver, Username, new BasicDetails_reader(ExecutionTest.BasicDetails).getExplicitWait());
	}
	
	public void clickOnSignInLink()
	{
		this.Submit.click();
	}
	
	public void enterUsername(String Username1)
	{
		this.Username.sendKeys(Username1);
	}
	
	public void enterPassword(String Password1)
	{
		this.Password.sendKeys(Password1);
	}
	
	
	public void loginfunctionality(String Username1, String Password1)
	{
		ElementHighlighting.ElementHighlighting(driver, Username);
		enterUsername(Username1);
		
		ElementHighlighting.ElementHighlighting(driver, Password);
		enterPassword(Password1);
		
		ElementHighlighting.ElementHighlighting(driver, Submit);
		clickOnSignInLink();
		
		
	}
}
