package commonpomlib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MVNRepositoryPOMWithPF
{
	@FindBy(id="query")
	public WebElement oSearchTextBox;
	
	@FindBy(xpath="//*[@value='search']")
	public WebElement oSearchButton;
	
	
	@FindBy(css="#maincontent>h2")
	public WebElement oSearchResultText;
	
	public MVNRepositoryPOMWithPF(WebDriver oDriver)
	{
	PageFactory.initElements(oDriver, this);
		}
	

}
