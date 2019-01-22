package commonlib;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class WebDriverTestNGBaseClass 
{
	public WebDriver oDriver;
	
	//*******************************************************************************************************************
@Parameters({"browserToOpen","appUrl","runInGridEnv","gridHubUrl"})//make sure of the order left to right // these is the same name given in Day2TestSuite.xml  under TestSuiteFiles
@BeforeClass
public void automationStart(String sBrowserName,String sUrl,boolean bRunInGridEnv, String sHubUrl) throws Throwable
{
	if (bRunInGridEnv)
	{
		oDriver = CommonUtils.getRemoteDriver(sHubUrl , sBrowserName);
	}
	else
	{
	oDriver = CommonUtils.getDriver(sBrowserName);
	}
	
	oDriver.get(sUrl);
	Thread.sleep(1000);
}


	//*******************************************************************************************************************
@AfterClass
public void automationStop() throws Throwable
{
	oDriver.quit();
}
	
	
	//*******************************************************************************************************************
	
	
	
	//*******************************************************************************************************************
	
	
	
	
	//*******************************************************************************************************************
	
	
	
	//*******************************************************************************************************************










}
