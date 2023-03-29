package Reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class P3_CreateBasicExtentReport {

	public static void main(String[] args) throws IOException {

		ExtentReports ExtentReports = new ExtentReports();  //Engine 
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./Reports/folderCreation.html") ; //reporter object- all the destination information we should provide 
	
		//ExtentSparkReporter sparkReporter = new ExtentSparkReporter("P3_BasicReportUsingRelativePath.html") ; 
		
	//Note : Every reporter we need to attached to the engine (as below)
        
        ExtentReports.attachReporter(sparkReporter);
        ExtentReports.flush(); //once we added all the test cases, logs ,screenshots after that we should use this statement to generate the statement
	    Desktop.getDesktop().browse(new File("/home/mangeshs/eclipse-workspace/MavenProject_ExtentReports/Reports/folderCreation.html").toURI());
	
		 

	
	}

}
