package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonlib.WebDriverTestNGBaseClass;

public class Day2_AttributeValueValidation extends WebDriverTestNGBaseClass
{

	
	@Test
	public void buttonCaptionValidation()
	{
		String sExpected, sActual;
	
		sExpected ="Go";
		sActual = oDriver.findElement(By.id("submit")).getAttribute("value");
		
		
		Reporter.log("Expected Caption ="+ sExpected);
		Reporter.log("Actual  Caption ="+ sActual);
		
		Assert.assertEquals(sActual, sExpected);
		
	}
	
	
}
