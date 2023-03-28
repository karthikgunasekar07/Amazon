package com.Runner;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.Amazon.Helper.ConfigurationHelper;
import com.Amazon.Reader.ConfigurationReader;
import com.BaseClass.BaseClass;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features = "src//test//java//com//AmazonFeature//amazon.feature" ,
glue = "com.StepDefinition" ,  
plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" ,
		  "html:Report/htmlReport.html"
		
		})
public class Amazon_Runner {
	
	public static WebDriver driver = null;
	@BeforeClass
	public static void setUp() throws IOException {
		
		
		String browser = ConfigurationHelper.getInstance().getInstanceCR().getBrowser();
		driver = BaseClass.browser_Launch(browser);
		
		
		
	}
	@AfterClass
	public static void tearDown() {
		 driver.quit();
	}

}
