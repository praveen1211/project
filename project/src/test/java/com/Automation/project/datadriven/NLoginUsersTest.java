package com.Automation.project.datadriven;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Automation.project.ExecutionTest;
import com.Automation.project.excel.ExcelWriter;
import com.Automation.project.helper.BrowserHelper;
import com.Automation.project.helper.waitHelper;
import com.Automation.project.otherFeatures.ElementHighlighting;
import com.thoughtworks.selenium.webdriven.commands.Submit;

public class NLoginUsersTest extends ExecutionTest
{
	String excellocation =System.getProperty("user.dir")+"/src/main/java/com/Automation/project/data/Data.xlsx";
	
	@DataProvider(name = "Data")
public Object[][] datasource() throws Exception
{
	return getData("Data.xlsx","Login");
}
	@Test(dataProvider="Data")
	public void testmultipleLogin(String username, String password, String run, String Result) throws Exception
	{
		if(run.equalsIgnoreCase("N"))
		{
			ExcelWriter.writeExcel1(excellocation, "Login", Integer.parseInt(Result)+1, "Skip", 3);
			throw new SkipException("Skipped the test case");
		}
		//Condition
		else if(run.equalsIgnoreCase("Y")) 
		{	
		loadPropertiesFile();
		driver.get(config.getProperty("url"));
		BrowserHelper hel = new BrowserHelper(driver);
		hel.WindowMaximize();
			
						Thread.sleep(1000);
						ElementHighlighting.ElementHighlighting(driver, getWebElement("username"));
						getWebElement("username").sendKeys(username);
						
						ElementHighlighting.ElementHighlighting(driver, getWebElement("password"));
						getWebElement("password").sendKeys(password);
						
						ElementHighlighting.ElementHighlighting(driver, getWebElement("submit"));
						getWebElement("submit").click();				
												
		if(driver.getCurrentUrl().equals("http://opensource.demo.orangehrmlive.com/index.php/dashboard")) 
		{
						ElementHighlighting.ElementHighlighting(driver, getWebElement("Welcome"));
						getWebElement("Welcome").click();
								
						ElementHighlighting.ElementHighlighting(driver, getWebElement("logout"));
						waitHelper new1 = new waitHelper(driver);
						WebElement ele = getWebElement("logout");
						new1.waitForElementVisible(5, 2, ele);
						ele.click();
					
						ExcelWriter.writeExcel1(excellocation, "Login", Integer.parseInt(Result)+1, "Pass", 3);
		}
		else
		{
			ExcelWriter.writeExcel1(excellocation, "Login", Integer.parseInt(Result)+1, "Fail", 3);
			Assert.assertEquals("http://opensource.demo.orangehrmlive.com/index.php/dashboard", driver.getCurrentUrl());
		
		
		}
				
		} 
		
		}
		
	}
			
		

		
		
	

