package commonlib;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class AutomationConstants 
{
	
	public static String sIEDriverPath= ".\\DriverExes\\IEDriverServer.exe";
	public static String sChromeDriverPath= ".\\DriverExes\\chromedriver.exe";
	public static String sGeckoDriverPath= ".\\DriverExes\\geckodriver.exe";
	
	public static long lngPageLoadTimeout = 120L;
	public static long lngImplicitWaitTimeout = 30L;
	
	public static boolean bUseProxySettings = true;
	public static String sProxyString = "web-proxy.atl.hp.com:8080";
	
	public static boolean bLogEventsToConsole =false;// if true event listener must be activated
	
	public static String sReportFolder = ".\\Reports";
	
	public static String sFrameworkExcelFile = "";
	
	//*************************************************************************
	
	public static void loadAutomationProperties() throws Exception
	{
		InputStream oFileReader = new FileInputStream("AutomationConfig.properties");
		Properties oProperties = new Properties();
		
		oProperties.load(oFileReader);
		

		sIEDriverPath = oProperties.getProperty("ie.driver.path").trim();
		sChromeDriverPath = oProperties.getProperty("chrome.driver.path").trim();
		sGeckoDriverPath = oProperties.getProperty("firefox.driver.path").trim();
		
		sProxyString  = oProperties.getProperty("proxy.string").trim();
		sReportFolder = oProperties.getProperty("report.folder.name").trim();
		
		sFrameworkExcelFile = oProperties.getProperty("framework.excel.workbook.path").trim();
		
		String sValue;
		
		sValue = oProperties.getProperty("page.load.timeout").trim();
		lngPageLoadTimeout = Long.valueOf(sValue);
		
		sValue = oProperties.getProperty("implicit.wait.timeout").trim();
		lngImplicitWaitTimeout = Long.valueOf(sValue);
		
		
		sValue = oProperties.getProperty("use.proxy.settings").trim();
		bUseProxySettings = Boolean.valueOf(sValue);
		
		sValue = oProperties.getProperty("log.events.to.console").trim();
		bLogEventsToConsole = Boolean.valueOf(sValue);
		
		
		
	}


	
	//*************************************************************************
	
	
	//**************************************************************************
	
}
