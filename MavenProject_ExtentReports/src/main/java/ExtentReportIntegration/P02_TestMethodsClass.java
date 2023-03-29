package ExtentReportIntegration;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class P02_TestMethodsClass extends P01_BaseTest{
	@Test( testName = "TestGoogle" ,groups = {"Smoke","sanity"}) 
	public void TestGoogle()
	{
        driver.get("https://www.google.com/");
        extentTest.log(Status.INFO, "Navigate to google site");
        driver.findElement(By.name("q")).sendKeys("Mangesh Sutar");
        
        act.sendKeys(Keys.ENTER).build().perform();
        String ActualTitle = driver.getTitle();
        String ExpectedTitle = "Mangesh";
        AssertJUnit.assertEquals(ActualTitle, ExpectedTitle,"Title does not matched");
        extentTest.pass("This test passed");
                
	}

	@Test( testName = "TestFacebook" ,groups = {"Smoke","regression"})
	public void TestFacebook() throws Exception
	{		
       
		 driver.get("https://www.facebook.com/");

	        extentTest.log(Status.INFO, "Navigate to facebook site");
		 driver.findElement(By.name("email")).sendKeys("Mangesh Sutar" , Keys.ENTER);
			Thread.sleep(7000);
			
			
			 
			//Title
			String ExpectedTitle = "Log in to Facebook";
			String ActualTitle ;;
			ActualTitle = driver.getTitle();
			AssertJUnit.assertEquals(ActualTitle, ExpectedTitle , "Title does not matached");
			
			//URL
			Thread.sleep(5000);
			
			driver.navigate().back();
			String ExpectedURL = "https://www.facebook.com/";
			String ActualURL ;
			ActualURL = driver.getCurrentUrl();
			AssertJUnit.assertEquals(ActualURL, ExpectedURL , "URL does not matached");
			
			//TextEmpty
			
			String ExpectedText = "";
			String ActualText = driver.findElement(By.name("email")).getAttribute("value");
			AssertJUnit.assertEquals(ActualText,ExpectedText,"Text does not matached");
			
			//Border
			String ExpectedBorder = "1px solid rgb(27, 116, 228)";
			driver.findElement(By.name("email")).sendKeys(Keys.ENTER);
			String ActualBorder = driver.findElement(By.name("email")).getCssValue("border"); 
			AssertJUnit.assertEquals(ActualBorder,ExpectedBorder,"Border does not matached");
			
		
	
	}
}
