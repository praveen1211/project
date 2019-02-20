package com.Automation.project.helper;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class waitHelper 
{
public WebDriver driver;

	
	public waitHelper(WebDriver driver)
	{
		this.driver = driver;
	}

	public void ImplictWait(long timeout, TimeUnit unit)
	{
	driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);	
	}
	
	public WebDriverWait getWait(int timeOutInSeconds, int poolingEveryInMiliSec ) 
	{
	WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
	wait.pollingEvery(poolingEveryInMiliSec, TimeUnit.SECONDS);
	wait.ignoring(NoSuchElementException.class);
	wait.ignoring(ElementNotVisibleException.class);
	wait.ignoring(NoSuchFrameException.class);
	return wait;
	}
	
	public void waitForElementVisible(int timeOutInSeconds, int poolingEveryInMiliSec, WebElement locator)
	{
		WebDriverWait wait = getWait(timeOutInSeconds , poolingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}
	
	public void waitForElement(WebDriver driver, WebElement element, long timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public WebElement waitForElement(WebDriver driver, long time,  WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
