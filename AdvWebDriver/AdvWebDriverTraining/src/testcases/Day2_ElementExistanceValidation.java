package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import commonlib.CommonUtils;
import commonlib.WebDriverTestNGBaseClass;

public class Day2_ElementExistanceValidation extends WebDriverTestNGBaseClass
{

	
	@Test
	public void ElementExistanceValidation()
	{
		
		
		Assert.assertTrue(CommonUtils.isElementExist(oDriver, By.linkText("Projects")),"Unable to find 'Projects' link on page");
		
	}
	
	
}
