package commonlib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriver 
{
	private InputStream oFileReader;
	private OutputStream oFileWriter;
	private Workbook oExcelWb;

	private String sExcelFileName;

	//--------------------------------------------------------------------------------

	private void setNullValues()
	{
		oFileReader = null;
		oFileWriter = null;
		oExcelWb = null;
		sExcelFileName = "";
	}

	//--------------------------------------------------------------------------------

	public ExcelDriver()
	{
		setNullValues();
	}

	//--------------------------------------------------------------------------------

	public void createNewExcelWorkbook(String sFileName)
	{
		try 
		{
			File oFile;
			oFile = new File(sFileName);

			if ( !oFile.exists() )
			{
				oExcelWb = new XSSFWorkbook();
				oFileWriter = new FileOutputStream(sFileName);
				oExcelWb.write(oFileWriter);
				oFileWriter.close();
				setNullValues();
			}
		} 

		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	//--------------------------------------------------------------------------------

	public void openExcelWorkbook(String sFileName)
	{
		try 
		{
			oFileReader = new FileInputStream(sFileName);
			oExcelWb = WorkbookFactory.create(oFileReader);
			sExcelFileName = sFileName;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	//--------------------------------------------------------------------------------

	public void closeExcelWorkbook()
	{
		try 
		{
			oExcelWb.close();
			oFileReader.close();
			setNullValues();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	//--------------------------------------------------------------------------------

	public void save()
	{
		try 
		{
			oFileWriter = new FileOutputStream(sExcelFileName);
			oExcelWb.write(oFileWriter);
			oFileWriter.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	//--------------------------------------------------------------------------------

	public void saveAs(String sNewExcelFileName)
	{
		try 
		{
			oFileWriter = new FileOutputStream(sNewExcelFileName);
			oExcelWb.write(oFileWriter);
			oFileWriter.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	//--------------------------------------------------------------------------------

	public void addSheet(String sSheetName)
	{
		try 
		{
			Sheet oSheet;
			oSheet = oExcelWb.getSheet(sSheetName);
			if (oSheet == null)
			{
				oExcelWb.createSheet(sSheetName);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	//--------------------------------------------------------------------------------

	public int getRowCount(String sSheetName)
	{
		try 
		{
			Sheet oSheet;
			oSheet = oExcelWb.getSheet(sSheetName);
			if (oSheet == null)
			{
				return 0;
			}
			else
			{
				return oSheet.getLastRowNum()+1;

			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return -1;
		}
	}
	//--------------------------------------------------------------------------------
	public int getColumnCount(String sSheetName, int iRow)
	{
		try 
		{
			Sheet oSheet;
			oSheet = oExcelWb.getSheet(sSheetName);
			if (oSheet != null)
			{
				Row oRow;

				oRow = oSheet.getRow(iRow-1);
				if (oRow == null)
				{
					return 0;
				}
				else
				{
					return oRow.getLastCellNum();
				}
			}
			else
			{
				return 0;
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return -1;
		}
	}

	//--------------------------------------------------------------------------------

	public String getCellData(String sSheetName, int iRow, int iColumn)
	{
		try 
		{
			Sheet oSheet;
			oSheet = oExcelWb.getSheet(sSheetName);
			if (oSheet != null)
			{
				Row oRow;

				oRow = oSheet.getRow(iRow-1);
				if (oRow == null)
				{
					return "";
				}
				else
				{
					Cell oCell;

					oCell = oRow.getCell(iColumn-1);
					if (oCell == null)
					{
						return "";
					}
					else
					{
						if (oCell.getCellType() == CellType.NUMERIC)
						{
							return String.valueOf(oCell.getNumericCellValue());
						}
						
						if (oCell.getCellType() == CellType.BOOLEAN)
						{
							return String.valueOf(oCell.getBooleanCellValue());
						}
            
            
						return oCell.getStringCellValue();
					}
				}
			}
			else
			{
				return "";
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "";
		}
	}
	//--------------------------------------------------------------------------------

	public void setCellData(String sSheetName, int iRow, int iColumn, String sValue)
	{
		try 
		{
			Sheet oSheet;
			Row oRow;
			Cell oCell;

			oSheet = oExcelWb.getSheet(sSheetName);

			if (oSheet == null)
			{
				addSheet(sSheetName);
				oSheet = oExcelWb.getSheet(sSheetName);
			}

			oRow = oSheet.getRow(iRow-1);
			if (oRow ==null)
			{
				oSheet.createRow(iRow-1);
				oRow = oSheet.getRow(iRow-1);
			}

			oCell = oRow.getCell(iColumn-1);
			if (oCell == null)
			{
				oRow.createCell(iColumn-1);
				oCell = oRow.getCell(iColumn-1);
			}

			oCell.setCellValue(sValue);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	//--------------------------------------------------------------------------------


	//--------------------------------------------------------------------------------


	//--------------------------------------------------------------------------------


	//--------------------------------------------------------------------------------


	//--------------------------------------------------------------------------------


	//--------------------------------------------------------------------------------


	//--------------------------------------------------------------------------------


	//--------------------------------------------------------------------------------


	//--------------------------------------------------------------------------------


	//--------------------------------------------------------------------------------


	//--------------------------------------------------------------------------------


	//--------------------------------------------------------------------------------




}

