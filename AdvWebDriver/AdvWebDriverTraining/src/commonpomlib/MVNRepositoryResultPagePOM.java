package commonpomlib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MVNRepositoryResultPagePOM
{
	
	public WebElement oSearchResultText;
	
	public MVNRepositoryResultPagePOM(WebDriver oDriver)
	{
		oSearchResultText = oDriver.findElement(By.cssSelector("#mainconte"));
		}
	

}
