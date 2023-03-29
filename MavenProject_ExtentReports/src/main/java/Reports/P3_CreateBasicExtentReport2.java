package Reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class P3_CreateBasicExtentReport2 {

	public static void main(String[] args) throws IOException {

		ExtentReports ExtentReports = new ExtentReports(); 
		File file = new File("P3_ByUsingFileParameterInExtentSparkReporter.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file) ;
		ExtentReports.attachReporter(sparkReporter);
		ExtentReports.flush(); 
		Desktop.getDesktop().browse(new File("P3_ByUsingFileParameterInExtentSparkReporter.html").toURI());
	}

}
