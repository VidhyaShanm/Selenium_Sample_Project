package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonlib.AutomationConstants;
import commonlib.BOTLibrary;
import commonlib.CommonUtils;
import commonlib.ExcelDriver;

public class MainDriver 
{
	private WebDriver oDriver;
	private ExtentReports oHtmlReport;
	private ExcelDriver oExcel;
	private String sHtmlReportFileName;

	public static void main(String[] args) throws Exception 
	{
		MainDriver oDriver = new MainDriver();
		
		oDriver.init_Framework_Values();
		oDriver.execute_Framework();
	}
	

	//************************************************************************
	
	public void init_Framework_Values() throws Exception
	{
		AutomationConstants.loadAutomationProperties();
		
		sHtmlReportFileName = AutomationConstants.sReportFolder + "\\Report_AsOn_" + CommonUtils.getDateTimeStamp() + ".html";
		
		oHtmlReport = new ExtentReports(sHtmlReportFileName);
		
		oExcel = new ExcelDriver();
	}
	
	
	//************************************************************************
	
	public void execute_Framework() throws Exception
	{
		//TestCaseName	RunFlag	BrowserName	SheetName	StepsStartsFromRow	StepsEndsAtRow

		String sTestCaseName;
		String sRunFlag;
		String sBrowserName;
		String sSheetName;
		
		int iStepsStartsFromRow;
		int iStepsEndsAtRow;
		int iRow , iRowCount;
		
		ExtentTest oTestCaseLog;
		
		oExcel.openExcelWorkbook(AutomationConstants.sFrameworkExcelFile);
		
		iRowCount = oExcel.getRowCount("MainSuite");
		
		for(iRow=2; iRow<=iRowCount; iRow++)
		{
			
			sTestCaseName ="";
			sRunFlag	  ="";
			sBrowserName  ="";
			sSheetName	  ="";

			iStepsStartsFromRow=0;
			iStepsEndsAtRow=0;
			
			oTestCaseLog = null;
			oDriver		 = null;

			sTestCaseName  = oExcel.getCellData("MainSuite", iRow, 2);
			sRunFlag 	   = oExcel.getCellData("MainSuite", iRow, 3);
			sBrowserName   = oExcel.getCellData("MainSuite", iRow, 4);
			sSheetName	   = oExcel.getCellData("MainSuite", iRow, 5);
			
			if (oExcel.getCellData("MainSuite", iRow, 6).equals(""))
			{
				iStepsStartsFromRow = 0;
			}
			else
			{
			iStepsStartsFromRow = Integer.valueOf(oExcel.getCellData("MainSuite", iRow, 6).split("\\.")[0]);
			}
			
			
			if (oExcel.getCellData("MainSuite", iRow, 7).equals(""))
			{
				iStepsEndsAtRow = 0;
			}
			else
			{
				iStepsEndsAtRow = Integer.valueOf(oExcel.getCellData("MainSuite", iRow, 7).split("\\.")[0]);
			}
			
			oTestCaseLog = oHtmlReport.startTest(sTestCaseName);
			
			if(sRunFlag.equalsIgnoreCase("yes")) 
			{
				execute_TestCase(oTestCaseLog, sBrowserName, sSheetName, iStepsStartsFromRow, iStepsEndsAtRow);
			}
			else
			{
				oTestCaseLog.log(LogStatus.SKIP, "Because, RunFlag was set to =>" +sRunFlag);
			}
			
			oHtmlReport.endTest(oTestCaseLog);
			
		}
		
		
		oHtmlReport.flush();
		oExcel.closeExcelWorkbook();
			
	}
	
	
	//************************************************************************
	
	private void execute_TestCase(ExtentTest oTestCaseLog, String sBrowserName, String sSheetName, int startRow, int endRow)
	{
		//ActionName	RunFlag	Argument	LocatorType	LocatorValue	AttributeName	ExpectedValue

		String sActionName;
		String sRunFlag;
		String sArgument;
		String sLocatorType;
		String sLocatorValue;
		String sAttributeName;
		String sExpectedValue;
		
		int iRow;
		
		BOTLibrary oBOTLib;
		
		try
		{
			
		oDriver = CommonUtils.getDriver(sBrowserName);
		}
		
		catch(Throwable t)
		{
			oTestCaseLog.log(LogStatus.ERROR, "Invoking Browser", t);
			t.printStackTrace();
			return;
		}
		
		oBOTLib = new BOTLibrary(oDriver);
		
		
		for(iRow=startRow; iRow<=endRow; iRow++)
		{
			 sActionName 	 ="";
			 sRunFlag   	 ="";
			 sArgument		 ="";
			 sLocatorType 	 ="";
			 sLocatorValue	 ="";
			 sAttributeName  ="";
			 sExpectedValue  ="";
			 
			 
			 sActionName 	 =oExcel.getCellData(sSheetName,  iRow, 2);
			 sRunFlag   	 =oExcel.getCellData(sSheetName,  iRow, 3);
			 sArgument		 =oExcel.getCellData(sSheetName,  iRow, 4);
			 sLocatorType 	 =oExcel.getCellData(sSheetName,  iRow, 5);
			 sLocatorValue	 =oExcel.getCellData(sSheetName,  iRow, 6);
			 sAttributeName  =oExcel.getCellData(sSheetName,  iRow, 7);
			 sExpectedValue  =oExcel.getCellData(sSheetName,  iRow, 8);
			 
			 
			 if(sRunFlag.equalsIgnoreCase("yes"))
			 {
				 try
					{
						boolean bActionKeywordFound = true;
						
						if (sActionName.equalsIgnoreCase("navigate"))
						{
							oBOTLib.navigate(sArgument);
						} 
						else if (sActionName.equalsIgnoreCase("clickElement"))
						{
							By oBy = CommonUtils.getByObject(sLocatorType, sLocatorValue);
							oBOTLib.ClickElement(oBy);
						}
						else if (sActionName.equalsIgnoreCase("setText"))
						{
							By oBy = CommonUtils.getByObject(sLocatorType, sLocatorValue);
							oBOTLib.setText(oBy, sArgument);
						}
						else if (sActionName.equalsIgnoreCase("getText"))
						{
							By oBy = CommonUtils.getByObject(sLocatorType, sLocatorValue);
							String sText = oBOTLib.getText(oBy);
							oTestCaseLog.log(LogStatus.INFO, sActionName, "Text = " + sText);
						}
						else if (sActionName.equalsIgnoreCase("getAttributeValue"))
						{
							By oBy = CommonUtils.getByObject(sLocatorType, sLocatorValue);
							String sText = oBOTLib.getAttributeValue(oBy, sAttributeName);
							oTestCaseLog.log(LogStatus.INFO, sActionName, sAttributeName + " = " + sText);
						}
						else if (sActionName.equalsIgnoreCase("selectVisibleText"))
						{
							By oBy = CommonUtils.getByObject(sLocatorType, sLocatorValue);
							oBOTLib.selectVisibleText(oBy, sArgument);
						}
						else if (sActionName.equalsIgnoreCase("setCheckBox"))
						{
							By oBy = CommonUtils.getByObject(sLocatorType, sLocatorValue);
							oBOTLib.setCheckBox(oBy, Boolean.valueOf(sArgument));
						}
						else if (sActionName.equalsIgnoreCase("selectRadioButton"))
						{
							By oBy = CommonUtils.getByObject(sLocatorType, sLocatorValue);
							oBOTLib.selectRadioButton(oBy);
						}
						else if (sActionName.equalsIgnoreCase("waitTillElementIsVisible"))
						{
							By oBy = CommonUtils.getByObject(sLocatorType, sLocatorValue);
							CommonUtils.wait_Till_ElementIs_Visible(oDriver, oBy, 120);
						}
						else if (sActionName.equalsIgnoreCase("assertTitle"))
						{
							String sActualTitle = oDriver.getTitle();
							
							if (sActualTitle.equals(sExpectedValue))
							{
								oTestCaseLog.log(LogStatus.PASS, sActionName, 
										"Expected Title = " + sExpectedValue + 
										", Actual Title = " + sActualTitle);
							}
							else
							{
								oTestCaseLog.log(LogStatus.FAIL, sActionName, 
										"Expected Title = " + sExpectedValue + 
										", Actual Title = " + sActualTitle);
							}
						}
						else if (sActionName.equalsIgnoreCase("assertAttribute"))
						{
							By oBy = CommonUtils.getByObject(sLocatorType, sLocatorValue);
							
							String sActualValue = oBOTLib.getAttributeValue(oBy, sAttributeName);
							
							if (sActualValue.equals(sExpectedValue))
							{
								oTestCaseLog.log(LogStatus.PASS, sActionName,
										"Attribute Name = " + sAttributeName +
										", Expected Value = " + sExpectedValue + 
										", Actual Value = " + sActualValue);
							}
							else
							{
								oTestCaseLog.log(LogStatus.FAIL, sActionName,
										"Attribute Name = " + sAttributeName +
										", Expected Value = " + sExpectedValue + 
										", Actual Value = " + sActualValue);
							}
						}
						else
						{
							bActionKeywordFound = false;
							oTestCaseLog.log(LogStatus.WARNING, 
								"Unable to find framework keyword for action name = " + sActionName);
						}
						
						if (bActionKeywordFound)
						{
							oTestCaseLog.log(LogStatus.PASS, sActionName, 
									sActionName + " - action completed successfully");
						}
						
					}
					catch(Throwable t)
					{
						oTestCaseLog.log(LogStatus.ERROR, sActionName, t);
						t.printStackTrace();
					}
			 }
			 
			 else
			 {
				 oTestCaseLog.log(LogStatus.SKIP, sActionName, "Skipped because RunFlag was set to ==> " + sRunFlag);
			 }
			
		}
		
		
		oDriver.quit();
		
	}
	

	//************************************************************************

}
