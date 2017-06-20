/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
import controllers.DB;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import models.User;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelParser {
    private static User sessionUser = null;
    private static ArrayList<String> employeePages = new ArrayList<>();
    
    public ExcelParser(User user, ArrayList<String> employeePages){
        this.sessionUser = user;
        this.employeePages = employeePages;
    }
    public void parseExcel(File selectedFile) throws FileNotFoundException, IOException, ParseException, ClassNotFoundException, SQLException{
        // String excelFilePath = "C:\\Users\\L R E\\Desktop\\attn1.xlsx";
        FileInputStream inputStream = new FileInputStream(selectedFile);
        
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        
        Iterator<Row> iterator = firstSheet.iterator();
        Row rowHeader = firstSheet.getRow(0);
        int lastCell=firstSheet.getRow(0).getLastCellNum();
        
        //String[] array = {"Emp No.", "AC-No.", "Name", "Date", "Clock In", "Clock Out", "ATT_Time"};
        String[] array = {"Name", "Department", "Date", "Time In", "Time Out"};
        List<Integer> columnIDs = new ArrayList<Integer>();
        Cell c;
        DB db = new DB();
        for(int k = 0; k < array.length; k++){
            for (int i = 0; i < lastCell; i++) {
                c = rowHeader.getCell(i);
                String cellData = c.toString();
                if(cellData.equals(array[k])){
                    columnIDs.add(i);
                }
                
            }
         }

        for (Row row : firstSheet) { // For each Row.
            if(row.getRowNum() == 0){
                continue;
            }
            else{
                Cell cell1 = row.getCell(columnIDs.get(0)); // Get the Cell at the Index / Colum you want.
                Cell cell2 = row.getCell(columnIDs.get(1));
                Cell cell3 = row.getCell(columnIDs.get(2));
                Cell cell4 = row.getCell(columnIDs.get(3));
                Cell cell5 = row.getCell(columnIDs.get(4));
                String name = null;
                String department = null;
                String date = null;
                String timeIn = null;
                String timeOut = null;
                if(cell1 != null){
                    name = cell1.toString();
                }
                if(cell2 != null){
                    department = cell2.toString();
                }
                if(cell3 != null){
                    date = cell3.toString();
                }
                if(cell4 != null){
                    timeIn = cell4.toString();
                }
                if(cell5 != null){
                    timeOut = cell5.toString();
                }
                
                
                
                db.insertUserLogFromExcel(name, department, date, timeIn, timeOut);
            }
            
        }
        DB.computeForPayout();
        workbook.close();
        inputStream.close();  
    }
}
