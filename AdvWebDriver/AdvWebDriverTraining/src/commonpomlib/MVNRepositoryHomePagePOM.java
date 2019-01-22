package commonpomlib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MVNRepositoryHomePagePOM
{
	
	public WebElement oSearchTextBox;
	public WebElement oSearchButton;
	
	public MVNRepositoryHomePagePOM(WebDriver oDriver)
	{
		oSearchTextBox = oDriver.findElement(By.id("query"));
		oSearchButton = oDriver.findElement(By.xpath("//*[@value='Search']"));
		}
	

}
