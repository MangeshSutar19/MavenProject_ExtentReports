package Reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class P8_ExtentReportconfigurationJavaJSONAndXML {

	public static void main(String[] args) throws Exception {



		ExtentReports ExtentReports = new ExtentReports(); 
		File file = new File("P8_ExtentReportconfigurationJavaJSONXML.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file) ;
		ExtentReports.attachReporter(sparkReporter);
		
		//By using java
		
		/*ExtentSparkReporterConfig config = sparkReporter.config();		
		config.setTheme(Theme.DARK);
		config.setReportName("Report name-Use of Attribute");
		config.setDocumentTitle("Document name -Use of Attribute");
		config.setTimeStampFormat("dd-MMM-yyyy hh:mm:ss");
		config.setCss(".badge-primary {font-size: larger }");
		config.setCss(".badge {background-color: #42390c }");		
		config.setJs("document.getElementsByClassName('logo')[0].style.display='none'");*/
		
		
		
		sparkReporter.loadXMLConfig(new File ("/home/mangeshs/eclipse-workspace/MavenProject_ExtentReports/src/test/resources/extent-reports-config.xml"));
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



		ExtentReports.flush(); 
		Desktop.getDesktop().browse(new File("P8_ExtentReportconfigurationJavaJSONXML.html").toURI());

	}

}
