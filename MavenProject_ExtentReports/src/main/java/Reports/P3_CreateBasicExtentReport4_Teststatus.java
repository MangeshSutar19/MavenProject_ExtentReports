package Reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class P3_CreateBasicExtentReport4_Teststatus {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ExtentReports extentReports =  new ExtentReports();
		ExtentSparkReporter extentSparkReporter =  new ExtentSparkReporter("/home/mangeshs/eclipse-workspace/MavenProject_ExtentReports/P3_TestMethodStatus.html");
		extentReports.attachReporter(extentSparkReporter);
		
		ExtentTest  TestMethod = extentReports.createTest("Test 1");
		TestMethod.pass("This status is passed");  // we can use status directly of test 
        
		
		ExtentTest  TestMethod2 = extentReports.createTest("Test 2");
		TestMethod2.log(Status.FAIL, "This is fail test case ");
		
		extentReports.createTest("Test 3").skip("this is skipped method");
		extentReports.createTest("Test 4 ").log(Status.FAIL, "This method is also failed");
		
		extentReports.flush();
		Desktop.getDesktop().browse(new File ("/home/mangeshs/eclipse-workspace/MavenProject_ExtentReports/P3_TestMethodStatus.html").toURI());
		
		

	}

}
