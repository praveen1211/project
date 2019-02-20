package com.Automation.project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.Automation.project.excel.ExcelReader;
import com.Automation.project.excel.ExcelWriter;
import com.Automation.project.helper.BrowserHelper;
import com.Automation.project.helper.waitHelper;
import com.Automation.project.otherFeatures.Mail;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExecutionTest 
{
	//public static final Logger logger = Logger.getLogger(ExecutionTest.class.getName());
	public  WebDriver driver;
	public static Properties config; 
	public File f1;
	public FileInputStream file;
	public File image;
	public String imagelocation;
	public static Calendar calendar;
	public static SimpleDateFormat format;
	public String actualImagename;
	public File destFile;
	public static ExtentReports extent;
	public static ExtentTest test;
	public WebDriverWait wait;
	public String locatortype;
	public String locatorvalue;
	public String[] split;
	public static Properties BasicDetails;
	public static String open;
	static
	{
		//False is used for the retaining the old reports
		calendar = Calendar.getInstance();
		format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		open = System.getProperty("user.dir") +"/src/main/java/com/Automation/project/report/testreport_" + format.format(calendar.getTime())+".html";
		extent = new ExtentReports(open, false);	
	}
	
	@BeforeTest
	public void launchBrowser() throws IOException
	{
		
		try
		{
			loadPropertiesFile1();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		BasicDetails_reader reader = new BasicDetails_reader(BasicDetails);
		getbrowser(reader.getBrowser());
		waitHelper wait = new waitHelper(driver);
		wait.ImplictWait(reader.getImplicitWait(), TimeUnit.SECONDS);
	}
	
	//Browser
	public void getbrowser(String browser)
	{
		if(System.getProperty("os.name").contains("Window"))
			
		{
			System.out.print(System.getProperty("os.name")+"\n");
			System.out.println(System.getProperty("user.dir"));
			if(browser.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				System.out.print("Firefox opened");
				
			}
			else if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
				driver = new ChromeDriver();
				System.out.print("Chrome opened");
			}
			else if (browser.equalsIgnoreCase("ie"))
			{
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				System.out.print("IE opened");
			}
		}
			
		else if(System.getProperty("os.name").contains("Mac"))
{
			System.out.print(System.getProperty("os.name")+"\n");
			System.out.println(System.getProperty("user.dir"));
				if(browser.equalsIgnoreCase("firefox"))
				{
					System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver");
					driver = new FirefoxDriver();
					System.out.print("Firefox opened");
					
				}
				else if(browser.equalsIgnoreCase("chrome"))
				{
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver");
					driver = new ChromeDriver();
					System.out.print("Chrome opened");
				}
				else if(browser.equalsIgnoreCase("ie"))
				{
					System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer");
					driver = new InternetExplorerDriver();
					System.out.print("IE opened");
				}
}
		
	}
	public WebElement getLocator(String locator) throws Exception 
	{
        String[] split = locator.split("~");
		String locatorType = split[0];
		String locatorValue = split[1];
		
		if (locatorType.toLowerCase().equals("id"))
		{
			return driver.findElement(By.id(locatorValue));
		}
		else if (locatorType.toLowerCase().equals("name"))
		{
			return driver.findElement(By.name(locatorValue));
		}
		else if ((locatorType.toLowerCase().equals("classname")))
		{
			return driver.findElement(By.className(locatorValue));
		}
		else if ((locatorType.toLowerCase().equals("tagname")))
		{
			return driver.findElement(By.className(locatorValue));
		}
		else if ((locatorType.toLowerCase().equals("linktext")))
		{
			return driver.findElement(By.linkText(locatorValue));
		}
		else if (locatorType.toLowerCase().equals("partiallinktext"))
		{
			return driver.findElement(By.partialLinkText(locatorValue));
		}
		else if ((locatorType.toLowerCase().equals("cssselector")))
		{
			return driver.findElement(By.cssSelector(locatorValue));
		}
		else if (locatorType.toLowerCase().equals("xpath"))
		{
			return driver.findElement(By.xpath(locatorValue));
		}
		else
		{
			throw new Exception("Unknown locator type '" + locatorType + "'");
		}
	}
	
	public WebElement getWebElement(String locator) throws Exception
	{
		return getLocator(config.getProperty(locator));
	}
	
	
	//Properties File loader
	public void loadPropertiesFile( ) throws IOException
	{
		//String log4jPath ="C:\\Users\\miracle\\eclipse-workspace\\project\\src\\main\\java\\com\\Automation\\project\\config\\log.properties";
		//PropertyConfigurator.configure(log4jPath);
		config = new Properties();
		f1 = new File(System.getProperty("user.dir")+"/src/main/java/com/Automation/project/config/config.properties");
		file = new FileInputStream(f1);
		config.load(file);
	//	logger.info("Loading the config.properties");
	}
	public void loadPropertiesFile1( ) throws IOException
	{
		BasicDetails = new Properties();
		f1 = new File(System.getProperty("user.dir")+"/src/main/java/com/Automation/project/config/BasicDetails.properties");
		file = new FileInputStream(f1);
		BasicDetails.load(file);
	}
	
	//Screenshot
	public String getScreenShot(String imagename) throws IOException
	{
		if(imagename.equals(""))
		{
			imagename = "blank";
		}
		image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		imagelocation = System.getProperty("user.dir")+"/src/main/java/com/Automation/project/screenshot/";
		calendar = Calendar.getInstance();
		format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		actualImagename = imagelocation+imagename+"_"+format.format(calendar.getTime())+".png";
		destFile = new File(actualImagename);
		FileUtils.copyFile(image, destFile);
		return actualImagename;	
	}
	
	//Get Result method
	public void getResult(ITestResult result) throws Exception
	{
		if (result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(LogStatus.PASS, result.getName()+"test is Passed" );
			String screen = getScreenShot("");
			test.log(LogStatus.PASS, test.addScreenCapture(screen));
			String excellocation =System.getProperty("user.dir")+"/src/main/java/com/Automation/project/data/Data.xlsx";
			ExcelWriter.writeExcel(excellocation, "TestScenerios", "Login", "Pass", 2);
		}
		
		else if (result.getStatus() == ITestResult.FAILURE) 
		{
			test.log(LogStatus.FAIL, result.getName()+"test is Failed");
			String screen = getScreenShot("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
			String excellocation =System.getProperty("user.dir")+"/src/main/java/com/Automation/project/data/Data.xlsx";
			ExcelWriter.writeExcel(excellocation, "TestScenerios", "Login", "Fail", 2);
			
		}
		
		else if (result.getStatus() == ITestResult.SKIP) 
		{
			test.log(LogStatus.SKIP, result.getName()+"test is Skipped");
		}
		
		else if (result.getStatus() == ITestResult.STARTED) 
		{
			test.log(LogStatus.INFO, result.getName()+"test Started" );
			//screen = getScreenShot("");
		}
	}
	
	//Excel Reading
	public String[][] getData(String excelName, String sheetName) throws Exception
	{
			
			String excellocation =System.getProperty("user.dir")+"/src/main/java/com/Automation/project/data/"+excelName;
			ExcelReader excel = new ExcelReader();
			return excel.getExcelData(excellocation, sheetName);		
	}
	

	//Html status printed 
	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception
	{
		getResult(result);
	}
	
	@BeforeSuite
	public void before()
	{
		extent.addSystemInfo("Project Name","Sample Website Testing");
		extent.addSystemInfo("Framework","Hybdrid Driven Framework");
		extent.addSystemInfo("os.version", System.getProperty("os.version"));
		extent.addSystemInfo("Created By", "Praveen Kumar Reddy Reddem");
		String loc="C:\\Users\\miracle\\eclipse-workspace\\project\\ExtentReport-cust.xml";
		extent.loadConfig(new File(loc));
	}
	
	@BeforeMethod
	public void beforeMethod(Method result)
	{
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName()+"test Started");
		
		
	}
	
	@AfterClass(alwaysRun = true)
	public void endTest() throws Exception
	{
	driver.quit();
	extent.endTest(test);
	extent.flush();
//	String excellocation =System.getProperty("user.dir")+"/src/main/java/com/Automation/project/data/Data.xlsx";
//	ExcelWriter.writeExcel(excellocation, "TestScenerios", "Login", "Fail", 2);
	//System.out.println("Written succesfully");
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
	driver = new ChromeDriver();
	BrowserHelper hel = new BrowserHelper(driver);
	hel.WindowMaximize();
	driver.get(open);
	String file1 = open ;
	System.out.print("Chrome opened");
	Mail.Mail1(file1);
	
	}
}
	
