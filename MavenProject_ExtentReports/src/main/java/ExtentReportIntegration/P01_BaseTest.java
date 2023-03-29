package ExtentReportIntegration;

import java.awt.Desktop;
import java.io.File;


import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.http.AddSeleniumUserAgent;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class P01_BaseTest {
	
	public static WebDriver driver;
	Actions act ;
	public static String frmt2;
	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	public static String screenshotsSubFolderName;

	
	@Parameters ("browserName")
	@BeforeTest  
	public void setUp(ITestContext context , @Optional("chrome") String browserName )
	{
			switch(browserName.toLowerCase())
			{
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break ;
			
			default:
				System.out.println("Broswer is invalid");	
				break;


			}
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
	
			extentTest = extentReport.createTest(context.getName());
		
	}
	@AfterTest
	public void TearDown()
	{
		
		driver.quit();
	}
	
	@BeforeSuite
	public void InitialiseExtentReport() 
	{
		extentReport =  new ExtentReports();
		ExtentSparkReporter ExtentReportInegration_AllTest =  new ExtentSparkReporter("ExtentReportInegration_AllTest.html");
		extentReport.attachReporter(ExtentReportInegration_AllTest);
		
		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		extentReport.setSystemInfo("java version", System.getProperty("java.version"));;
		
		
		
		
	
	}
	@AfterSuite
	public void generateReports() throws IOException
	{
		extentReport.flush();
	
		Desktop.getDesktop().browse(new File ("ExtentReportInegration_AllTest.html").toURI());
		
	}
	
	@AfterTest
	public void checkstatus(Method m ,ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE)
		{
			
			String screenshotepath = captureScreenshot(result.getTestContext().getName() +"_"+result.getMethod().getMethodName()+".jpg");
		    extentTest.addScreenCaptureFromBase64String(screenshotepath);
		    extentTest.fail(result.getThrowable());
		
		}else if(result.getStatus() == ITestResult.SUCCESS)
		{
			extentTest.pass(m.getName() + "is passed");
		}
		
	}
	public String captureScreenshot(String fileName) {
		if(screenshotsSubFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		    screenshotsSubFolderName = myDateObj.format(myFormatObj);
		}
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/"+ screenshotsSubFolderName+"/"+fileName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");
		return destFile.getAbsolutePath();


	}	}
	
	



