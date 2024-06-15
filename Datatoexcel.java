package practice;


import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Datatoexcel {

    public void writeExcel(String filePath,String fileName,String sheetName,String[] dataToWrite) throws IOException{

        //Create an object of File class to open xlsx file

        File file =    new File(filePath+"\\"+fileName);

        //Create an object of FileInputStream class to read excel file

        FileInputStream inputStream = new FileInputStream(file);

        Workbook guru99Workbook = null;

        //Find the file extension by splitting  file name in substring and getting only extension name

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        //Check condition if the file is xlsx file

        if(fileExtensionName.equals(".xlsx")){

        //If it is xlsx file then create object of XSSFWorkbook class

        guru99Workbook = new XSSFWorkbook(inputStream);

        }

        //Check condition if the file is xls file

        else if(fileExtensionName.equals(".xls")){

            //If it is xls file then create object of XSSFWorkbook class

            guru99Workbook = new HSSFWorkbook(inputStream);

        }    

    //Read excel sheet by sheet name    

    Sheet sheet = guru99Workbook.getSheet(sheetName);
    
    //Get the current count of rows in excel file

    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

    //Get the first row from the sheet

    Row row = sheet.getRow(0);

    //Create a new row and append it at last of sheet

    Row newRow = sheet.createRow(rowCount+1);

    //Create a loop over the cell of newly created Row

    for(int j = 0; j < row.getLastCellNum(); j++){

        //Fill data in row

        Cell cell = newRow.createCell(j);

        cell.setCellValue(dataToWrite[j]);

    }

    //Close input stream

    inputStream.close();

    //Create an object of FileOutputStream class to create write data in excel file

    FileOutputStream outputStream = new FileOutputStream(file);

    //write data in the excel file

    guru99Workbook.write(outputStream);

    //close output stream

    outputStream.close();
	
    }

    public static void main(String...strings) throws IOException{
    	
    	WebDriver driver = new ChromeDriver();
    	driver.get("https://www.flipkart.com/search?q=hindi+books&sid=bks&as=on&as-show=on&otracker=AS_QueryStore_OrganicAutoSuggest_1_10_na_na_na&otracker1=AS_QueryStore_OrganicAutoSuggest_1_10_na_na_na&as-pos=1&as-type=RECENT&suggestionId=hindi+books%7CBooks&requestId=f6472a80-6b25-453e-88ac-ca51a0df7d8d&as-searchtext=hindi%20books");
    	List<WebElement> books = driver.findElements(By.className("slAVV4"));
    	int totcount = books.size();
    	for (int i = 0; i < totcount; i++) {
    	String name = books.get(i).findElement(By.className("wjcEIp")).getText();
    	String price = "";
    	try {
    	price = books.get(i).findElement(By.className("Nx9bqj")).getText();
    	} catch(NoSuchElementException e) {
    	   price = "Not Available";
    	}
    	String rating ="";
    	try {
    	rating = books.get(i).findElement(By.className("XQDdHH")).getText();
    	} catch(NoSuchElementException e) {
     	   rating = "Not Available";
     	}
        	
        //Create an array with the data in the same order in which you expect to be filled in excel file

    	 String[] valueToWrite = {name,price,rating};

        //Create an object of current class

        Datatoexcel objExcelFile = new Datatoexcel();

        //Write the file using file name, sheet name and the data to be filled

        objExcelFile.writeExcel(System.getProperty("user.dir")+"\\src\\excelExportAndFileIO","ExportExcel.xlsx","ExcelGuru99Demo",valueToWrite);
    	}
    }

}

