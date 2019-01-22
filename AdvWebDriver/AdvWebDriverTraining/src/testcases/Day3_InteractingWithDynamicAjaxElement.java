package testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import commonlib.CommonUtils;
import commonlib.WebDriverTestNGBaseClass;

public class Day3_InteractingWithDynamicAjaxElement extends WebDriverTestNGBaseClass
{

	
	@Test
	public void dynamicElementValidation() throws Exception
	{
		
		
	  oDriver.findElement(By.id("sb_form_q")).sendKeys("iPad 4");
	  Thread.sleep(2000);
	  
	  CommonUtils.wait_Till_ElementIs_Visible(oDriver, By.id("sa_ul"), 60);
	  List<WebElement> oAllItems;
	  int i,iCount;
	  
	  oAllItems = oDriver.findElements(By.xpath("//ul[@id='sa_ul']/li"));
	  iCount =oAllItems.size();
	  
	  for(i=0;i<iCount;i++) 
	  {
		  System.out.printf("%d of %d=%s\n", i+1,iCount,oAllItems.get(i).getText());
	  }
	  
	  oAllItems.get(0).click();
	  Thread.sleep(2000);
	}
	
	
}
