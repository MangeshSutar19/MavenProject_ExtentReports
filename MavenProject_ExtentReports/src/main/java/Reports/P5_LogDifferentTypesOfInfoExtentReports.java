package Reports;

import java.awt.Desktop; 
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class P5_LogDifferentTypesOfInfoExtentReports {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ExtentReports extentreports =  new ExtentReports();
		ExtentSparkReporter extentSparkReport =  new ExtentSparkReporter("/home/mangeshs/eclipse-workspace/MavenProject_ExtentReports/Reports/P5_TextXMLJSONListMapSetHighLightTextExceptionBasedReport.html");
		extentreports.attachReporter(extentSparkReport);

		//Text -Bold , italic , both format at time and manymore format we can use 
		extentreports.createTest("Text Based test")
		.info("Information")
		.info("<b>Information</b>")
		.info("<i> Information </i>")
		.log(Status.INFO, "<b><i>Information</i></b>");


		//XML based test
		String XMLData ="<menu id=\"file\" value=\"File\">\n" + 
				"  <popup>\n" + 
				"    <menuitem value=\"New\" onclick=\"CreateNewDoc()\" />\n" + 
				"    <menuitem value=\"Open\" onclick=\"OpenDoc()\" />\n" + 
				"    <menuitem value=\"Close\" onclick=\"CloseDoc()\" />\n" + 
				"  </popup>\n" + 
				"</menu>";

		extentreports.createTest("XML based test")
		.info(MarkupHelper.createCodeBlock(XMLData,CodeLanguage.XML));


		//JSON based test

		String JSONData = "{\"menu\": {\n" + 
				"  \"id\": \"file\",\n" + 
				"  \"value\": \"File\",\n" + 
				"  \"popup\": {\n" + 
				"    \"menuitem\": [\n" + 
				"      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\n" + 
				"      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\n" + 
				"      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\n" + 
				"    ]\n" + 
				"  }\n" + 
				"}}";

		extentreports.createTest("JSON based test")
		.info(MarkupHelper.createCodeBlock(JSONData, CodeLanguage.JSON));


		List<String> opitons = new ArrayList<>();
		opitons.add("option1");
		opitons.add("option2");
		opitons.add("option3");


		extentreports.createTest("List based test")
		.info(MarkupHelper.createOrderedList(opitons))
		.info(MarkupHelper.createUnorderedList(opitons));


		Map<Integer, String > mapData =  new HashMap<>();

		mapData.put(101,"Mangesh1");
		mapData.put(102,"Mangesh2");
		mapData.put(103,"Mangesh3");


		extentreports.createTest("Map based test")
		.info(MarkupHelper.createOrderedList(mapData))
		.info(MarkupHelper.createUnorderedList(mapData));

		Set<Integer> setData = mapData.keySet();


		extentreports.createTest("Set based test")
		.info(MarkupHelper.createOrderedList(setData))
		.info(MarkupHelper.createUnorderedList(setData));

		extentreports.createTest("Highlight any message")
		.info("This is not hightlited message")
		.info(MarkupHelper.createLabel("This is highlighted text", ExtentColor.YELLOW));
		
		
		try
		{
			int i = 5/0;

	         System.out.println(i);
	
		}
		catch(Exception e)
		{
			//e.printStackTrace();

			extentreports.createTest("Excetion message 1")
			.fail(e);
		}
		

       Throwable t = new RuntimeException("This is a custome exception");
   	extentreports.createTest("Excetion message 2")
   	.fail(t);
	

		extentreports.flush();
		Desktop.getDesktop().browse(new File ("/home/mangeshs/eclipse-workspace/MavenProject_ExtentReports/Reports/P5_TextXMLJSONListMapSetHighLightTextExceptionBasedReport.html").toURI());
	}

}
