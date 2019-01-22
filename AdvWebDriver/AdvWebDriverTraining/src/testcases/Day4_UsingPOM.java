package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import commonlib.CommonUtils;
import commonlib.WebDriverTestNGBaseClass;
import commonpomlib.MVNRepositoryPOMWithPF;

public class Day4_UsingPOM extends WebDriverTestNGBaseClass
{

	
	@Test
	public void pomExample() throws Exception
	{
	MVNRepositoryPOMWithPF oMVNPage = new MVNRepositoryPOMWithPF(oDriver);
	
	oMVNPage.oSearchTextBox.sendKeys("selenium");
    oMVNPage.oSearchButton.click();
    
    CommonUtils.wait_Till_ElementIs_Visible(oDriver, By.id("maincontent"), 60);
    
   
    System.out.println("Result = " + oMVNPage.oSearchResultText.getText());
	
	}
	
	
}
