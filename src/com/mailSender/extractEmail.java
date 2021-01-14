package com.mailSender;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *  Returns a value from a certain cell in an excel sheet.
 *  In this version, the excel file must be at the same directory of the program. However, you may
 *  modify the path to your needs.
 *
 *  All thanks to https://www.javatpoint.com/how-to-read-excel-file-in-java
 *  for providing the necessary dependencies and of course, the code samples!
* */


public class extractEmail {
    /**
     * Reads and returns String data from a cell
     * @param vRow Row of Excel sheet
     * @param vColumn Column of Excel sheet
     * @param file Filename
     * @return String value of specified cell
     */
    public String ReadCellData(int vRow, int vColumn,String file)
    {
        String value;          //variable for storing the cell value
        Workbook wb=null;           //initialize Workbook null
        try
        {
            String path = System.getProperty("user.dir");       //gets directory wherever you stored this folder in
            FileInputStream f=new FileInputStream(path + "\\" + file);
            /*this file is already provided in the folder but you can also choose a file of your own
            * the one I provided is an actual template from my work and I based my code there :D
            */
        //constructs an XSSFWorkbook object, by buffering the whole stream into the memory
            wb = new XSSFWorkbook(f);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
        catch(IOException e)
        {
            System.out.println("Something went wrong, check the row.");
        }
        assert wb != null;
        Sheet sheet=wb.getSheetAt(0);   //getting the XSSFSheet object at given index
        Row row=sheet.getRow(vRow); //returns the logical row
        Cell cell=row.getCell(vColumn); //getting the cell representing the given column
        value=cell.getStringCellValue();    //getting cell value
        return value;               //returns the cell value
    }
}