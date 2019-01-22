package commonlib;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtils 
{
	//***********************************************************************************************
	
	public static Proxy getProxy() throws Exception
	{
		Proxy oProxy = new Proxy();
		
		if(AutomationConstants.bUseProxySettings)
		{
			oProxy.setProxyType(ProxyType.MANUAL);
			oProxy.setHttpProxy(AutomationConstants.sProxyString);
			oProxy.setSslProxy(AutomationConstants.sProxyString);
		}
		else
		{
			oProxy.setProxyType(ProxyType.DIRECT);
		}
		
		return oProxy;
	}
	
	
	//************************************LAUNCHING IE BROWSER***********************************************************
	
	public static InternetExplorerOptions getIEOptions() throws Exception
	{
		InternetExplorerOptions  oBrowserOptions = new InternetExplorerOptions();
		oBrowserOptions.setProxy(getProxy());
		
		return oBrowserOptions;
	}
	
	//*************************************LAUNCHING CHROME BROWSER**********************************************************
	
	public static ChromeOptions getChromeOptions() throws Exception
	{
		ChromeOptions  oBrowserOptions = new ChromeOptions();
		oBrowserOptions.setProxy(getProxy());
		
		return oBrowserOptions;
	}
	
	
	//************************************LAUNCHING FIREFOX BROWSER***********************************************************
	
	public static FirefoxOptions getFirefoxOptions() throws Exception
	{
		FirefoxOptions  oBrowserOptions = new FirefoxOptions();
		oBrowserOptions.setProxy(getProxy());
		
		return oBrowserOptions;
	}
	
	
	//***********************************************************************************************
	
	public static int getBrowserId(String sBrowserName) throws Exception
	{
		if(sBrowserName.equalsIgnoreCase("chrome")) return 1;
		
		if(sBrowserName.equalsIgnoreCase("firefox")) return 2;
		
		if(sBrowserName.equalsIgnoreCase("ie")) return 3;
		
		return -1;
	}
	
	
	//***********************************************************************************************
	
	public static WebDriver getDriver(String sBrowserName) throws Exception
	{
		WebDriver oDriver;
		
		switch (getBrowserId(sBrowserName)) 
		{
		case 1:
			System.setProperty("webdriver.chrome.driver", AutomationConstants.sChromeDriverPath);
			
			oDriver = new ChromeDriver(getChromeOptions()); // this line  is responsible  for opening the browser in the desktop
		break;
		
		case 2:
			System.setProperty("webdriver.gecko.driver", AutomationConstants.sGeckoDriverPath);
			
			oDriver = new FirefoxDriver(getFirefoxOptions()); // this line  is responsible for opening the browser in the desktop
		break;

		case 3:
			System.setProperty("webdriver.ie.driver", AutomationConstants.sIEDriverPath);
			
			oDriver = new InternetExplorerDriver(getIEOptions()); // this line  is responsible for opening the browser in the desktop
		break;
		default:
			throw new Exception("Unknown BrowserName =" + sBrowserName);
			
		}
		
		oDriver.manage().window().maximize(); //open the browser in maximize
		oDriver.manage().deleteAllCookies();    //delete all cookies
		
		oDriver.manage().timeouts().pageLoadTimeout(AutomationConstants.lngPageLoadTimeout, TimeUnit.SECONDS);
		oDriver.manage().timeouts().implicitlyWait(AutomationConstants.lngPageLoadTimeout, TimeUnit.SECONDS);
	
		if(AutomationConstants.bLogEventsToConsole)
		{
			EventFiringWebDriver oEventDriver;
			
			oEventDriver = new EventFiringWebDriver(oDriver);
			oEventDriver.register(new MyWebDriverEventListener());
			
			return oEventDriver;
		}
		else
		{
		
		return oDriver;
		}
		}
	
	
	//***********************************************************************************************
	
	public static boolean  isElementExist(WebDriver oDriver, By oBy)
	{
		oDriver.manage().timeouts().implicitlyWait(AutomationConstants.lngPageLoadTimeout, TimeUnit.SECONDS);
		
		boolean bFound;
		
		try
		{
			oDriver.findElement(oBy);
			bFound=true;
		}
		catch(Exception e)
		{
			bFound =false;
		}
		
		oDriver.manage().timeouts().implicitlyWait(AutomationConstants.lngPageLoadTimeout, TimeUnit.SECONDS);
	
		return bFound;
	}
	
	//***********************************************************************************************
	 public static void wait_Till_ElementIs_Visible(WebDriver oDriver, By oBy,long timeoutseconds) throws Exception
	 {
		 WebDriverWait oWait = new WebDriverWait(oDriver,timeoutseconds);
		 oWait.until(ExpectedConditions.visibilityOfElementLocated(oBy));   //Synchronization condition to wait
	 }
	
	//***********************************************************************************************
	
	public static void scrollPageTo(WebDriver oDriver,int x,int y) throws Exception
	{
		JavascriptExecutor oJsEngine;
		String sCommand;
		
		sCommand = String.format("window.scrollTo(%d,%d)", x,y);
		
		oJsEngine = (JavascriptExecutor) oDriver;
		oJsEngine.executeScript(sCommand);
	}
	 
	//***********************************************************************************************
	
	public static WebDriver getRemoteDriver(String sHubUrl, String sBrowserName) throws Exception
	{
		WebDriver oDriver;
		DesiredCapabilities oCapabilities = new DesiredCapabilities();
		 oCapabilities.setPlatform(Platform.WINDOWS);
		 oCapabilities.setCapability("proxy", getProxy());
		
		switch (getBrowserId(sBrowserName)) 
		{
		case 1:
			oCapabilities.setBrowserName("chrome"); // this line  is responsible  for opening the browser in the desktop
		break;
		
		case 2:
			oCapabilities.setBrowserName("firefox"); // this line  is responsible for opening the browser in the desktop
		break;

		case 3:
			oCapabilities.setBrowserName("internet explorer"); // this line  is responsible for opening the browser in the desktop
		break;
		default:
			throw new Exception("Unknown BrowserName =" + sBrowserName);
			
		}
		
		oDriver = new RemoteWebDriver(new URL(sHubUrl), oCapabilities);
		
		oDriver.manage().window().maximize(); //open the browser in maximize
		oDriver.manage().deleteAllCookies();    //delete all cookies
		
		oDriver.manage().timeouts().pageLoadTimeout(AutomationConstants.lngPageLoadTimeout, TimeUnit.SECONDS);
		oDriver.manage().timeouts().implicitlyWait(AutomationConstants.lngPageLoadTimeout, TimeUnit.SECONDS);
	
		if(AutomationConstants.bLogEventsToConsole)
		{
			EventFiringWebDriver oEventDriver;
			
			oEventDriver = new EventFiringWebDriver(oDriver);
			oEventDriver.register(new MyWebDriverEventListener());
			
			return oEventDriver;
		}
		else
		{
		
		return oDriver;
		}
		}
	
	//***********************************************************************************************
	
	public static String getDateTimeStamp() throws Exception
	{
		//20180921_130000(24hour format)
		SimpleDateFormat oDateStamp = new SimpleDateFormat("YYYYMMdd_HHmmss");
		return oDateStamp.format(new Date());
	}
	
	
	//***********************************************************************************************
	
	public static By getByObject(String sLocatorType, String sLocatorValue)
	{
		if (sLocatorType.equalsIgnoreCase("id")) return By.id(sLocatorValue);
		
		if (sLocatorType.equalsIgnoreCase("name")) return By.name(sLocatorValue);
		
		if (sLocatorType.equalsIgnoreCase("link")) return By.linkText(sLocatorValue);
		
		if (sLocatorType.equalsIgnoreCase("xpath")) return By.xpath(sLocatorValue);
		
		if (sLocatorType.equalsIgnoreCase("css")) return By.cssSelector(sLocatorValue);
		
		if (sLocatorType.equalsIgnoreCase("classname")) return By.className(sLocatorValue);
		
		if (sLocatorType.equalsIgnoreCase("tagname")) return By.tagName(sLocatorValue);
		
		if (sLocatorType.equalsIgnoreCase("partiallink")) return By.partialLinkText(sLocatorValue);
		
		return null;
	}
	
	//***********************************************************************************************
	
	
	
	//***********************************************************************************************
	
	
	
	//***********************************************************************************************
	
	
	
	//***********************************************************************************************
	
	
	
	//***********************************************************************************************
	
	
	
	
	//***********************************************************************************************
	
	
	
	
	//***********************************************************************************************
	
	
	
	
	//***********************************************************************************************
	
	
	
	//***********************************************************************************************
	
	
	//***********************************************************************************************
	
	
	//***********************************************************************************************
	
	//***********************************************************************************************
	
	//***********************************************************************************************
	
	//***********************************************************************************************
	
	//***********************************************************************************************
	
	//***********************************************************************************************
	
	
	//***********************************************************************************************
	
	
	//***********************************************************************************************
	
	
	//***********************************************************************************************
}
