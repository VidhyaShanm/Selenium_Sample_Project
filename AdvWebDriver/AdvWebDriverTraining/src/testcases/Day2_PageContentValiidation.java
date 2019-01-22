package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import commonlib.WebDriverTestNGBaseClass;

public class Day2_PageContentValiidation extends WebDriverTestNGBaseClass
{

	
	@Test
	public void contentValidation()
	{
		String sExpectedContent;
		
		sExpectedContent = "What is Selenium?";
		
		Assert.assertTrue(oDriver.getPageSource().contains(sExpectedContent), "Unable to find expected content on page.Expected=" + sExpectedContent);
		
		
	}
	
	
}
