package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import commonlib.CommonUtils;
import commonlib.WebDriverTestNGBaseClass;

public class Day3_InteractingWithHtmlAndWindowPopup extends WebDriverTestNGBaseClass
{


	@Test
	public void htmlWindowPopupValidation() throws Exception
	{
		//oDriver.findElement(By.id("qbi")).click();
		//oDriver.findElement(By.className("S3Wjs")).click(); //clicking the camera icon using class name
		oDriver.findElement(By.xpath("//*[@id='sbtc']/div/div[2]/div[1]/span")).click(); //clicking the camera icon using xpath name
		oDriver.findElement(By.linkText("Upload an image")).click();
		oDriver.findElement(By.id("qbfile")).click();

		handleWindowPopup();

		CommonUtils.wait_Till_ElementIs_Visible(oDriver, By.id("topstuff"), 60);
		System.out.println(oDriver.findElement(By.id("topstuff")).getText());

	}

	//************************************************************************************

	private void handleWindowPopup() throws Exception
	{
		String sTool,sImage,sCommand;
		Process oProcess;
		
		sTool = "C:\\Users\\vshanmugam24\\WorkDxc\\PopupHandler.exe";
		sImage = "C:\\Users\\vshanmugam24\\WorkDxc\\saucelabs-logo.png";
		sCommand = String.format("\"%s\" \"%s\"",sTool,sImage);
		
		
		oProcess = Runtime.getRuntime().exec(sCommand);
		oProcess.waitFor();
		oProcess.destroy();
		
	}
}
