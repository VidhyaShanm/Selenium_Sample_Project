package testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import commonlib.WebDriverTestNGBaseClass;

public class Day3_ReadingHtmlTable extends WebDriverTestNGBaseClass
{

	
	@Test
	public void htmlTableValidation() throws Exception
	{
		WebElement oTable, oRow, oCell;
		List<WebElement> oAllRows, oAllCells;
		int iRow, iRowCount, iCell, iCellCount;
		
		oTable= oDriver.findElement(By.xpath("//*[@id='mainContent']/table[1]"));
		
		
		oAllRows =oTable.findElements(By.tagName("tr"));
		
		iRowCount = oAllRows.size();
		
		for(iRow=0; iRow<iRowCount; iRow++)
		{
		
		oRow= oAllRows.get(iRow);
		
			if(iRow == 0)
			{
				oAllCells =oRow.findElements(By.tagName("th"));
				
			}
			else
			{
				oAllCells =oRow.findElements(By.tagName("td"));
			}
			
			iCellCount =oAllCells.size();
			for(iCell=0; iCell<iCellCount; iCell++)
			{
				oCell= oAllCells.get(iCell);
				System.out.printf("%s\t", oCell.getText());
			}
		System.out.printf("\n");
	}
	
	
}
}
