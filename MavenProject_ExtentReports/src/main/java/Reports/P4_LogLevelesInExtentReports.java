package Reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class P4_LogLevelesInExtentReports {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		ExtentReports extentReports =  new ExtentReports();
		ExtentSparkReporter extentSparkReports  = new ExtentSparkReporter("/home/mangeshs/eclipse-workspace/MavenProject_ExtentReports/Reports/P4_LogLevelesInExtentReports.html");
		extentReports.attachReporter(extentSparkReports);
		extentReports.createTest("Test1")
		.log(Status.INFO, "This is information method")
		.log(Status.INFO, "This is information method")
		.log(Status.INFO, "This is information method")
		.log(Status.PASS, "This is passed method")
		.log(Status.PASS, "This is passed method")
		.log(Status.SKIP, "This is Skipped method")
		.log(Status.WARNING, "This is WARNING method");
		
		 
		
		extentReports.flush();
		Desktop.getDesktop().browse(new File ("/home/mangeshs/eclipse-workspace/MavenProject_ExtentReports/Reports/P4_LogLevelesInExtentReports.html").toURI());
	}

}
