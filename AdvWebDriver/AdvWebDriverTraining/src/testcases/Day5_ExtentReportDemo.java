package testcases;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonlib.CommonUtils;

public class Day5_ExtentReportDemo
{
	private static ExtentReports oReport;

	public static void main(String[] args) throws Exception
	{
	String sHtmlFile = "Report_AsOn_" + CommonUtils.getDateTimeStamp() + ".html";
	System.out.println("Html File ==> " + sHtmlFile);
	
	 oReport = new ExtentReports(sHtmlFile);
	 
	 reportTestStatus("Test1",true);
	 reportTestStatus("Test2",false);
	 reportTestStatus("Test3",true);
	 reportTestStatus("Test4",false);
	 reportTestStatus("Test5",true);
	 
	 oReport.flush();
	
	}
	
	private static void reportTestStatus(String sTestName, boolean bTestStatusPassed)
	{
		ExtentTest oTest = oReport.startTest(sTestName);
		
		if (bTestStatusPassed)
		{
			oTest.log(LogStatus.PASS, sTestName +" -test is passed");
		}
		else
		{
			oTest.log(LogStatus.FAIL, sTestName +" - test is failed");
		}
		oReport.endTest(oTest);
	}

}
