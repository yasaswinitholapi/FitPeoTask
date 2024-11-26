package com.ecom.Automation.FUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcel(String sheetName , int rowNum,int celNum) throws Throwable {
		//get the excel path location and java object of physicalfile
		FileInputStream fis=new FileInputStream("./testdata/TestScriptData1.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
		wb.close();
		return data;
	}
public int getRowCount(String sheetName) throws Throwable {
	FileInputStream fis=new FileInputStream("./testdata/TestScriptData1.xlsx");
	Workbook wb=WorkbookFactory.create(fis);
	int rowCount=wb.getSheet(sheetName).getLastRowNum();
	wb.close();
	return rowCount;
}
public void setDataIntoExcel(String sheetName , int rowNum,int celNum,String data) throws Throwable {
	FileInputStream fis=new FileInputStream("./testdata/TestScriptData1.xlsx");
	Workbook wb=WorkbookFactory.create(fis);
	wb.getSheet(sheetName).getRow(rowNum).createCell(celNum);
	FileOutputStream fos=new FileOutputStream("./testdata/TestScriptData1.xlsx");
	//to save
	wb.write(fos);
	wb.close();
	
}
}
