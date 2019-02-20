package com.Automation.project.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Automation.project.ExecutionTest;

public class ExcelReader 
{
	//public static final Logger logger = Logger.getLogger(ExcelReader.class.getName());
	public FileInputStream file;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	
	public String[][] getExcelData(String excellocation , String sheetname)
	{
		//logger.info("Excel data is reading");
		try {
			String datasheets[][] = null;
			file = new FileInputStream(new File(excellocation));
			
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetname);
			//counting the rows
			int totalRow = sheet.getLastRowNum()+1;
			//counting number of active columns
			int totalcol= sheet.getRow(0).getLastCellNum();
			datasheets = new String[totalRow-1][4];
			//iteration by row by row
			Iterator<Row> rowIterator = sheet.iterator();
			int i=0;
			int j=0; 
			while(rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				if(i++ != 0)
				{
					int k=j;
					j++;
					//one cell constant reading the multiple columns cells
					Iterator<Cell> cellIterator = row.cellIterator();
					int m=0;
					while (cellIterator.hasNext()) 
					{
						Cell cell = cellIterator.next();
						switch(cell.getCellType())
						{
						case Cell.CELL_TYPE_NUMERIC:
							datasheets[k][m++] = cell.getStringCellValue();
//							System.out.println(k);
//							System.out.println(m);
//							System.out.println(cell.getStringCellValue());
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							datasheets[k][m++] = cell.getStringCellValue();
//							System.out.println(k);
//							System.out.println(m);
							break;
						case Cell.CELL_TYPE_FORMULA:
							datasheets[k][m++] = cell.getStringCellValue();
//							System.out.println(k);
//							System.out.println(m);
							break;
						case Cell.CELL_TYPE_STRING:
//							System.out.println(k);
//							System.out.println(m);
							datasheets[k][m++] = cell.getStringCellValue();
							//System.out.println(cell.getStringCellValue());
							//System.out.println(k);
							break;
					}
				}
					datasheets[k][m++]=Integer.toString(k);
					System.out.println("");
			}
			
}
			file.close();
			return datasheets;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		return null;
}

//	public static void main(String args[])
//	{
//		String excellocation ="C:\\Users\\miracle\\eclipse-workspace\\project\\src\\main\\java\\com\\Automation\\project\\data\\Data.xlsx";
//		String sheetName = "Login";
//		ExcelReader excel = new ExcelReader();
//		excel.getExcelData(excellocation, sheetName);
//	}
	
}