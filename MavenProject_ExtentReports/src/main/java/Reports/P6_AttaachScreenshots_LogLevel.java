package Reports;

import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class P6_AttaachScreenshots_LogLevel {


	public static WebDriver driver;
	
	public static void main(String[] args) throws IOException {

		
        WebDriverManager.chromedriver().setup();
        
		driver = new ChromeDriver();
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		driver.get("https://www.google.com/");
		
		
		ExtentReports ExtentReports = new ExtentReports(); 
		File file = new File("P6_attachedScreenshotAtTestLevel.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file) ;
		ExtentReports.attachReporter(sparkReporter);
		
		String base64Code = captureScreenshotByBase64();
		String path = captureScreenshotByFile("Google1.jpg");
		
		
		ExtentReports.createTest("Base64Code-1" , "This is description of test method")
		.info("This is the information of test method")
		.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build())
		.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code ,"This is base64 Screenshot").build());	
		

		ExtentReports.createTest("File-1" , "This is description of test method")
		.info("This is the information of test method")
		.fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build())
		.fail(MediaEntityBuilder.createScreenCaptureFromPath(path ,"This is path Screenshot").build());	
				

		
		
		
		
		
		ExtentReports.createTest("Base64Code-2" , "This is description of test method")
		.info("This is the information of test method")
		.fail("The details-base64" , MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build())
		.fail("The details-base64" , MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code ,"This is base64 Screenshot").build());	
		

		ExtentReports.createTest("File-2" , "This is description of test method")
		.info("This is the information of test method")
		.fail("The details-path" , MediaEntityBuilder.createScreenCaptureFromPath(path).build())
		.fail("The details-path" , MediaEntityBuilder.createScreenCaptureFromPath(path ,"This is path Screenshot").build());	
				
		


		Throwable t = new Throwable("This is an exception");
		
		ExtentReports.createTest("Base64Code-3" , "This is description of test method")
		.info("This is the information of test method")
		.fail(t , MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build())
		.fail(t , MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code ,"This is base64 Screenshot").build());	
		

		ExtentReports.createTest("File-3" , "This is description of test method")
		.info("This is the information of test method")
		.fail(t , MediaEntityBuilder.createScreenCaptureFromPath(path).build())
		.fail(t , MediaEntityBuilder.createScreenCaptureFromPath(path ,"This is path Screenshot").build());
		

		
		
		
		ExtentReports.flush(); 
		
		
		Desktop.getDesktop().browse(new File("P6_attachedScreenshotAtTestLevel.html").toURI());
		
		driver.close();
	}
	public static String captureScreenshotByFile(String filename) 
	{
		TakesScreenshot takescreenshot = (TakesScreenshot)driver;
		File Source = takescreenshot.getScreenshotAs(OutputType.FILE);
		File destination =  new File ("/home/mangeshs/eclipse-workspace/MavenProject_ExtentReports/Screenshhots/"+ filename);
	    try {
			Files.copy(Source, destination);
		} catch (IOException e) {

			e.printStackTrace();			
		}	
	    System.out.println("Screenshot saved successfully");
	    return destination.getAbsolutePath();     
	}
	public static String captureScreenshotByBase64()
	{
		TakesScreenshot takescreenshot = (TakesScreenshot)driver;
		String base64Code = takescreenshot.getScreenshotAs(OutputType.BASE64);
	    System.out.println("Screenshot saved successfully");
		return base64Code;
	}
   

	}
