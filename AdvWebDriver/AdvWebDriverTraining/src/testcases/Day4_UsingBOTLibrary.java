package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import commonlib.BOTLibrary;
import commonlib.CommonUtils;
import commonlib.WebDriverTestNGBaseClass;

public class Day4_UsingBOTLibrary extends WebDriverTestNGBaseClass
{

	
	@Test
	public void botLibraryExample() throws Exception
	{
		
	BOTLibrary oBotLib = new BOTLibrary(oDriver);
	
	oBotLib.selectVisibleText(By.id("searchDropdownBox"), "Software");
	oBotLib.setText(By.id("twotabsearchtextbox"), "MS Office");
	oBotLib.ClickElement(By.xpath("//*[@value='Go']"));
	
	CommonUtils.wait_Till_ElementIs_Visible(oDriver, By.id("resultsCol"), 120);
	
	String sResult = oBotLib.getText(By.id("s-result-count"));
	System.out.println("Result = " + sResult);
	
	}
	
	
}
