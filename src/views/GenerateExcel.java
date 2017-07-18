/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.DB;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.PayrollDetails;
import models.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author L R E
 */

public class GenerateExcel {
    DB db = new DB();
    private static User sessionUser = null;
    private final JPanel panel = new JPanel();
    public GenerateExcel(User user){
        this.sessionUser = user;
    }
    public void generateExcel(String menu) throws SQLException, ClassNotFoundException{
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //Create a blank sheet    
        if(menu.equals("Employees")){
            XSSFSheet sheet = workbook.createSheet(menu);
          
            //This data needs to be written (Object[])
            List<User> employees = new ArrayList<User>();
            employees = db.getUsers();
            Map<String, Object[]> data = new TreeMap<String, Object[]>();
            data.put("1", new Object[] {"Employee ID", "Name", "Rate", "SSS Number", "SSS Deduction",
            "Pagibig Number", "Pagibig Deduction", "Tin Number", "Tax Deduction", "PhilHealth Number", "PhilHealth Deduction"});
            int counter = 1;
            for(int i = 0; i < employees.size(); i++){
                counter = i + 2;
                data.put(Integer.toString(counter), new Object[] {
                    employees.get(i).getFirstName() + " " + employees.get(i).getLastName(), 
                    employees.get(i).getRate(),
                    employees.get(i).getSSSNumber(),
                    employees.get(i).getSSSDeduction(),
                    employees.get(i).getPagibigNumber(),
                    employees.get(i).getPagibigDeduction(),
                    employees.get(i).getTinNumber(),
                    employees.get(i).getTaxDeduction(),
                    employees.get(i).getPhilHealthNumber(),
                    employees.get(i).getPhilHealthDeduction()
                });
            }
             //Iterate over data and write to sheet
            Set<String> keyset = data.keySet();
            int rownum = 0;
            for (String key : keyset)
            {
                Row row = sheet.createRow(rownum++);
                Object [] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr)
                {
                   Cell cell = row.createCell(cellnum++);
                   if(obj instanceof String)
                        cell.setCellValue((String)obj);
                    else if(obj instanceof Integer)
                        cell.setCellValue((Integer)obj);
                }
            }
        }
        else if(menu.equals("Payroll")){
            //This data needs to be written (Object[])
            List<User> employees = new ArrayList<User>();
            employees = db.getUsers();
            Map<String, Object[]> data = new TreeMap<String, Object[]>();
            
            for(int i = 0 ; i < employees.size(); i++){
                XSSFSheet sheet = workbook.createSheet(employees.get(i).getFirstName() + " " + employees.get(i).getLastName());
                data.put("1", new Object[] {"Employee ID", "Name", "Rate", "SSS Number", "SSS Deduction",
                "Pagibig Number", "Pagibig Deduction", "Tin Number", "Tax Deduction", "PhilHealth Number", "PhilHealth Deduction",
                "Bonus", "Cash Advance", "Loan", "Holiday Bonus", "Work Days", "Total Salary", "Claim Date"});
                
                List<PayrollDetails> details = new ArrayList<PayrollDetails>();
                details = db.getPayrollHistory(employees.get(i).getEmployeeID());
                int counter = 0;
                for(int j = 0; j < details.size(); j++){
                    counter = j + 2;
                    data.put(Integer.toString(counter), new Object[] {
                        employees.get(i).getEmployeeID(),
                        employees.get(i).getFirstName() + " " + employees.get(i).getLastName(), 
                        details.get(j).getRate(),
                        employees.get(i).getSSSNumber(),
                        details.get(j).getSssDeduction(),
                        employees.get(i).getPagibigNumber(),
                        details.get(j).getPagibigDeduction(),
                        employees.get(i).getTinNumber(),
                        details.get(j).getTaxDeduction(),
                        employees.get(i).getPhilHealthNumber(),
                        details.get(j).getPhilHealthDeduction(),
                        details.get(j).getBonus(),
                        details.get(j).getCashAdvance(),
                        details.get(j).getLoan(),
                        details.get(j).getHolidayBonus(),
                        details.get(j).getDays(),
                        details.get(j).getTotalSalary(),
                        details.get(j).getClaimDate()
                    });
                }
                Set<String> keyset = data.keySet();
                int rownum = 0;
                for (String key : keyset)
                {
                    Row row = sheet.createRow(rownum++);
                    Object [] objArr = data.get(key);
                    int cellnum = 0;
                    for (Object obj : objArr)
                    {
                       Cell cell = row.createCell(cellnum++);
                       if(obj instanceof String)
                            cell.setCellValue((String)obj);
                        else if(obj instanceof Integer)
                            cell.setCellValue((Integer)obj);
                    }
                }
            }
 
        }
          
       
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("C:\\DSL-Downloads\\" + menu + " - List.xlsx"));
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(panel, menu + "List export successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(panel, menu + "List export failed", "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }
}
