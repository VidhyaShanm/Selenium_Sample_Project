package testcases;

import commonlib.ExcelDriver;

public class Day5_ExcelDemo {

	public static void main(String[] args) 
	{
		ExcelDriver oExcel = new ExcelDriver();
		
		String sExcelFile = "Data.xlsx";
		
		
		oExcel.createNewExcelWorkbook(sExcelFile);
		oExcel.openExcelWorkbook(sExcelFile);
		oExcel.addSheet("Res1");
		oExcel.setCellData("Res1", 1, 1, "Testname");
		oExcel.setCellData("Res1", 1, 2, "Status");
		oExcel.setCellData("Res1", 2, 1, "Test1");
		oExcel.setCellData("Res1", 2, 2, "Pass");
		oExcel.setCellData("Res1", 3, 1, "Test2");
		oExcel.setCellData("Res1", 3, 2, "Failed");
		
		oExcel.save();
		oExcel.closeExcelWorkbook();
		
		
		
		oExcel.openExcelWorkbook(sExcelFile);
		int iRow, iRowCount, iCol, iColCount;
		iRowCount = oExcel.getRowCount("Res1");
		
		System.out.println("# of Rows = " + iRowCount);
		System.out.println("# Columns in Row1 = " + oExcel.getColumnCount("Res1", 1));
		
		System.out.println();
		
		for(iRow=1; iRow<=iRowCount; iRow++)
		{
			iColCount = oExcel.getColumnCount("Res1", iRow);
			for(iCol=1; iCol<=iColCount;iCol++)
			{
				System.out.printf("%s\t", oExcel.getCellData("Res1", iRow, iCol));
			}
			System.out.printf("\n");
			
		}
		
		oExcel.closeExcelWorkbook();
	}

}

