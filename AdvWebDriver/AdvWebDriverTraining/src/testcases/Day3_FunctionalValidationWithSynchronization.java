package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import commonlib.CommonUtils;
import commonlib.WebDriverTestNGBaseClass;

public class Day3_FunctionalValidationWithSynchronization extends WebDriverTestNGBaseClass
{

	
	@Test
	public void endToEndValidationWithExplicitSync() throws Exception
	{
		
		oDriver.findElement(By.id("query")).sendKeys("selenium");  //id=query for the search  box in mvn repository page
		oDriver.findElement(By.xpath("//*[@value='Search']")).click(); 
		
		CommonUtils.wait_Till_ElementIs_Visible(oDriver, By.id("right"), 120);
		
		oDriver.findElement(By.linkText("Selenium Java")).click();
		CommonUtils.wait_Till_ElementIs_Visible(oDriver, By.id("right"), 120);//2nd page 
		
		oDriver.findElement(By.linkText("3.14.0")).click();
		CommonUtils.wait_Till_ElementIs_Visible(oDriver, By.id("right"), 120);//3 page
		
		String sExpectedContent, sActualContent;
		
		sExpectedContent= "<artifactId>selenium-java</artifactId>";
		sActualContent = oDriver.findElement(By.id("maven-a")).getText();
		
		Assert.assertTrue(sActualContent.contains(sExpectedContent), "Unable to find expected content. Expected=" + sExpectedContent);
	}
	
	
}