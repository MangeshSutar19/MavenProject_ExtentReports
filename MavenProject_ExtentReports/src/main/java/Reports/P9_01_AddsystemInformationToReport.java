package Reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class P9_01_AddsystemInformationToReport {

	public static void main(String[] args) throws Exception {


		ExtentReports ExtentReports = new ExtentReports(); 
		  
		ExtentSparkReporter Alltest = new ExtentSparkReporter("Alltest.html") ;
		
		ExtentSparkReporter Allpassed = new ExtentSparkReporter("Allpassed.html") ;
		Allpassed.filter().statusFilter().as(new Status[] {Status.PASS}).apply();
		
		
		ExtentSparkReporter AllSkippedAndWarning = new ExtentSparkReporter("AllSkippedAndWarning.html") ;
		AllSkippedAndWarning.filter().statusFilter().as(new Status[] {Status.SKIP ,Status.WARNING }).apply();
		
		ExtentSparkReporter AllFailed = new ExtentSparkReporter("AllFailed.html") ;
		AllFailed.filter().statusFilter().as(new Status[] {Status.FAIL }).apply();

		
		ExtentReports.attachReporter(Alltest, Allpassed,AllSkippedAndWarning,AllFailed);
		
		

				
		WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        
        Capabilities capabilities = ((RemoteWebDriver)driver).getCapabilities();
        
        
        //Add system information
        ExtentReports.setSystemInfo("Browser Name", capabilities.getBrowserName());
        ExtentReports.setSystemInfo("Browser Version", capabilities.getBrowserVersion());
        //To get all the properties
       // System.getProperties().list(System.out);
        ExtentReports.setSystemInfo("OS Name " , System.getProperty("os.name"));
        ExtentReports.setSystemInfo("Java vserion ", System.getProperty("java.version"));
        
        ExtentReports.setSystemInfo("User name ", "Mangesh Sutar");
        ExtentReports.setSystemInfo("Password", "User@1234");
        
        ExtentReports.setSystemInfo("APP URL", "https://www.google.com/");
        
    	
		//sparkReporter.loadXMLConfig(new File ("/home/mangeshs/eclipse-workspace/MavenProject_ExtentReports/src/test/resources/extent-reports-config.xml"));
		
		
		ExtentReports.createTest("Test 1", "Author-1 , category -1 and device -1")
		.assignAuthor("Mangesh")
		.assignCategory("Smoke")
		.assignDevice("Chrome 90")
		.pass("This is passed test");
 

		ExtentReports.createTest("Test 2" , "This is second test method which is created by another author, different category and different device ")
		.assignAuthor("Shreya")
		.assignCategory("Regression")
		.assignDevice("Chrome 89")
		.fail("This is failed test");		

		// order does not matter
		ExtentReports.createTest("Test 3" , "order does not matter")
		.assignDevice("Chrome 89")
		.assignCategory("Smoke")
		.assignAuthor("Shreya")
		.fail("This is failed test");	

		//CASE 1 :We can provide multiple details as well 
		ExtentReports.createTest("Test 4" , "order does not matter")
		.assignDevice("Chrome 89")
		.assignCategory("Smoke")
		.assignAuthor("Tester 1 ")
		.assignDevice("Chrome 89")
		.assignCategory("Smoke")
		.assignAuthor("Tester 2")
		.fail("This is failed test");	

		//CASE 1(a) :We can provide multiple details as well 
		ExtentReports.createTest("Test 5" , "We can provide multiple details as well")
		.assignDevice("Chrome 89", "Edge98", "Edge 50")
		.assignCategory("Smoke","Regression", "Functional")
		.assignAuthor("Tester 1 ", "Tester 3", "Tester 4")
		.fail("This is failed test");	



		//CASE 1(a) :We can provide multiple details  in arrays  
		ExtentReports.createTest("Test 6" , "We can provide multiple details in array")
		.assignDevice(new String [] {"Chrome 75", "Edge76", "Edge 77"})
		.assignCategory(new String []{"Smoke1","Regression1", "Functional1"})
		.assignAuthor(new String[] {"Tester 5 ", "Tester 6", "Tester 7"})
		.pass("This is failed test");	

		ExtentReports.createTest("Test 7" , "skipr")
		.assignDevice("Chrome 89")
		.assignCategory("Smoke")
		.assignAuthor("Shreya")
		.skip("This is Skipped test");	


		ExtentReports.createTest("Test 8" , "skipr")
		.assignDevice("Chrome 89")
		.assignCategory("Smoke")
		.assignAuthor("Shreya")
		.skip("This is Skipped test");	

		
		ExtentReports.createTest("Test 9" , "warning")
		.assignDevice("Chrome 89")
		.assignCategory("Smoke")
		.assignAuthor("Shreya")
		.warning("This is warning test");	


		
		ExtentReports.createTest("Test 10" , "warning")
		.assignDevice("Chrome 89")
		.assignCategory("Smoke")
		.assignAuthor("Shreya")
		.warning("This is warning test");	


		ExtentReports.flush();
		driver.quit();

	
		Desktop.getDesktop().browse(new File("Alltest.html").toURI());
		Desktop.getDesktop().browse(new File("Allpassed.html").toURI());
		Desktop.getDesktop().browse(new File("AllSkippedAndWarning.html").toURI());
		Desktop.getDesktop().browse(new File("AllFailed.html").toURI());

	}

}
