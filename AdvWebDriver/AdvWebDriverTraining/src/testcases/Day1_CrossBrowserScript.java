package testcases;

import org.openqa.selenium.WebDriver;

import commonlib.CommonUtils;

public class Day1_CrossBrowserScript 
{

	public static void main(String[] args)  throws Exception 
	{
		WebDriver oDriver = CommonUtils.getDriver("ie");
		oDriver.get("http://www.seleniumhq.org");
		
		System.out.println("Title =" + oDriver.getTitle());
		
		oDriver.quit();

	}

}
