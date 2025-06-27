package com.reports;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class DataLibrary {
    
    public static String[][] readdata() throws IOException {
        FileInputStream fis = new FileInputStream("./Utilities/data.xlsx");
        XSSFWorkbook wBook = new XSSFWorkbook(fis);
        XSSFSheet sheet = wBook.getSheet("Sheet1");
        int rowcount = sheet.getLastRowNum();
        int colcount = sheet.getRow(0).getLastCellNum();
        
        String[][] data = new String[rowcount][colcount];
        DataFormatter formatter = new DataFormatter(); // Handles different cell types
        
        for (int i = 1; i <= rowcount; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null) continue; // Avoid null row exception
            
            for (int j = 0; j < colcount; j++) {
                XSSFCell cell = row.getCell(j);
                data[i - 1][j] = (cell == null) ? "" : formatter.formatCellValue(cell);
            }
        }
        
        wBook.close();
        fis.close();
        return data;
    }
}
