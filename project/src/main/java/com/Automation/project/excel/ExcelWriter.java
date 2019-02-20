package com.Automation.project.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter 
{
	public static FileInputStream File;
	public static FileOutputStream File1;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row1;
	
	
	public static void writeExcel(String excellocation, String sheetname, String testCaseName, String testStatus, int columnIndex) throws Exception
	{
		File = new FileInputStream(new File(excellocation));	
		
		workbook = new XSSFWorkbook(File);
		
		sheet = workbook.getSheet(sheetname);
	
		//int rowcount = sheet.getLastRowNum();
		int totalRow = sheet.getLastRowNum()+1;
		System.out.println(totalRow);
		
		for(int i=1;i<totalRow;i++)
		{
			row1 = sheet.getRow(i);
			String value= row1.getCell(1).getStringCellValue();
		if(value.contains(testCaseName))
		{
			row1.createCell(columnIndex).setCellValue(testStatus);
			
		}
		File.close();
		System.out.print("done");
		File1 =  new FileOutputStream(new File(excellocation));
		
		workbook.write(File1);
		File1.close();
		
	}
		
	}
	
	public static void writeExcel1(String excellocation, String sheetname, int row, String result, int cell3) throws Exception
	{
		File = new FileInputStream(new File(excellocation));
		workbook = new XSSFWorkbook(File);
		sheet=workbook.getSheet(sheetname);
		Row r=sheet.getRow(row);
		Cell cell1= r.createCell(cell3);
		cell1.setCellValue(result);
		File1 =  new FileOutputStream(new File(excellocation));
		workbook.write(File1);
		File1.close();
	}
	
	public static int count(String index)
	{
		sheet = workbook.getSheet(index);
		int totalrows=sheet.getLastRowNum();
		return totalrows;
	}
	public static void removeCells(String excellocation, String sheetname, int cellNumber) throws Exception{
		
		sheet=workbook.getSheet(sheetname);
		CellStyle style=workbook.createCellStyle();
		for (int i=2;i<=count(sheetname);i++)
		{
			Row getRow=sheet.getRow(i);
			Cell cell1=getRow.getCell(cellNumber);
			if(cell1!=null)
			{
			getRow.removeCell(cell1);
			cell1= sheet.getRow(i).createCell(cellNumber);
			cell1.setCellStyle(style);
			FileOutputStream dest= new FileOutputStream(new File(excellocation));
			workbook.write(dest);
			}
		}
}
}
