/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import UareUFunctions.*;
import com.digitalpersona.uareu.Engine;
import com.digitalpersona.uareu.Fmd;
import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Constants;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import models.Absence;
import models.Attendance;
import models.Files;
import models.Notes;
import models.PayrollClass;
import models.PayrollDetails;
import models.Reports;
import models.SalaryCondition;
import models.SalaryItems;
import models.Uploads;
import models.User;
import models.UserLogs;

/**
 *
 * @author L R E
 */
public class DB {
//    private static String user = "dsl";
//    private static String pass = "DslDatabase";
//    private static String host = "localhost";
//    private static String port = "3306";
//    private static String url = "jdbc:mysql://" + host + ":" + port + "/dsl";
//    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    
    private static String user = "root";
    private static String pass = "password";
    private static String host = "localhost";
    private static String port = "3306";
    private static String url = "jdbc:mysql://" + host + ":" + port + "/dsl";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    
    public static Connection connect() throws ClassNotFoundException, SQLException {

       Connection conn = null;

       Class.forName("com.mysql.jdbc.Driver");

       conn = (Connection) DriverManager.getConnection(url, user, pass);
       
       return conn;
    }
    
    public static void setReader(Reader reader) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        
        PreparedStatement ps = c.prepareStatement("INSERT INTO reader(reader, active) VALUES(?,?)");
        ps.setBlob(1, (Blob) reader);
        ps.setInt(2, 1);
        int rows = ps.executeUpdate();
    }
    
    
    public static Reader getReader() throws ClassNotFoundException, SQLException{
        Reader m_reader = null;
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("SELECT reader from reader where active = 1");
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            m_reader = (Reader) rs.getBlob(1);
        }
        return m_reader;
    }
    public static User loginFingerPrint(byte[] fingerPrintImage) throws ClassNotFoundException, SQLException, UareUException, ParseException{
        String role = "";
        User user = new User();
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select employeeID as 'EmployeeID', firstName as 'FirstName', lastName as 'LastName'," + 
                " address as 'Address', telephoneNumber as 'TelephoneNumber', mobileNumber as 'MobileNumber', rate, timeIn, timeOut, role,"+
                " fingerPrint, password, pages, noLates, noMemos, noAbsences from users");
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            
            byte[] fingerPrint  = rs.getBytes(11);
            Engine engine = UareUGlobal.GetEngine();
            
            
            if(fingerPrint.length != 0){
                Fmd fmd1 = UareUGlobal.GetImporter().ImportFmd(fingerPrint, Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);
                Fmd fmd2 = UareUGlobal.GetImporter().ImportFmd(fingerPrintImage, Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);

                int falsematch_rate = engine.Compare(fmd1, 0, fmd2, 0);


                int target_falsematch_rate = Engine.PROBABILITY_ONE / 100000; //target rate is 0.00001

                if(falsematch_rate < target_falsematch_rate){
                    user.setEmployeeID(rs.getInt(1));
                    user.setFirstName(rs.getString(2));
                    user.setLastName(rs.getString(3));
                    user.setAddress(rs.getString(4));
                    user.setTelephoneNumber(rs.getString(5));
                    user.setMobileNumber(rs.getString(6));
                    user.setRate(rs.getFloat(7));
                    user.setTimeIn(rs.getString(8));
                    user.setTimeOut(rs.getString(9));
                    role = rs.getString(10);
                    user.setRole(role);
                    user.setFingerPrintImage(rs.getBytes(11));
                    user.setPassword(rs.getString(12));
                    user.setPages(rs.getString(13));
                    user.setNoLates(rs.getInt(14));
                    user.setNoMemos(rs.getInt(15));
                    user.setNoAbsences(rs.getInt(16));
                    
                    break;
                }
            }
            
            
        }
        c.close();
        return user;
    }
    public static List<User> getUsers() throws SQLException, ClassNotFoundException{
        List<User> employees = new ArrayList<User>();
        Connection c = connect();
        
        PreparedStatement ps = c.prepareStatement("Select employeeID, firstName, lastName, address, telephoneNumber, mobileNumber, rate, timeIn," + 
                " timeOut, role, fingerPrint, SSSNumber, philHealthNumber, pagibigNumber, SSSDeduction, philHealthDeduction, pagibigDeduction,"+
                " tinNumber, taxDeduction, pages, noLates, noMemos, noAbsences from users");
        
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
                User user = new User();
                user.setEmployeeID(rs.getInt("employeeID"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setAddress(rs.getString("address"));
                user.setTelephoneNumber(rs.getString("telephoneNumber"));
                user.setMobileNumber(rs.getString("mobileNumber"));
                user.setRate(rs.getFloat("rate"));
                user.setTimeIn(rs.getString("timeIn"));
                user.setTimeOut(rs.getString("timeOut"));
                user.setRole(rs.getString("role"));
                user.setSSSNumber(rs.getString("SSSNumber"));
                user.setPhilHealthNumber(rs.getString("philHealthNumber"));
                user.setPagibigNumber(rs.getString("pagibigNumber"));
                user.setSSSDeduction(rs.getFloat("SSSDeduction"));
                user.setPhilHealthDeduction(rs.getFloat("philHealthDeduction"));
                user.setPagibigDeduction(rs.getFloat("pagibigDeduction"));
                user.setTinNumber(rs.getString("tinNumber"));
                user.setTaxDeduction(rs.getFloat("taxDeduction"));
                user.setPages(rs.getString("pages"));
                user.setNoLates(rs.getInt("noLates"));
                user.setNoMemos(rs.getInt("noMemos"));
                user.setNoAbsences(rs.getInt("noAbsences"));
                employees.add(user);
        }
        c.close();
        return employees;
    }
    
    public static List<Notes> getAllNotes() throws ClassNotFoundException, SQLException{
        List<Notes> notesList = new ArrayList<Notes>();
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select a.noteID, a.employeeID, b.firstName, b.lastName, a.note, a.noteDate from " +
                "notes a, users b where month(noteDate) = ? and year(noteDate) = ? and a.employeeID = b.employeeID;");
        ps.setInt(1, month+1);
        ps.setInt(2, year);
        ResultSet rs = ps.executeQuery();
         while(rs.next()){
            Notes notes = new Notes();
            notes.setNoteID(rs.getInt(1));
            notes.setEmployeeID(rs.getInt(2));
            notes.setFirstName(rs.getString(3));
            notes.setLastName(rs.getString(4));
            notes.setNote(rs.getString(5));
            notes.setNoteDate(rs.getDate(6));
            
            notesList.add(notes);
        }
        c.close();
        return notesList;
    }
     public static List<UserLogs> getSystemLogs(int employeeID) throws ClassNotFoundException, SQLException, ParseException{
        Connection c = connect();
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        
        List<UserLogs> logs = new ArrayList<UserLogs>();
        PreparedStatement ps;
        if(employeeID != 0){
            ps = c.prepareStatement("SELECT a.employeeID, concat(b.firstName, ' ', b.lastName) as 'Name', a.type, a.logDetails, a.logDate"+
                        " FROM user_logs a, users b where a.employeeID = b.employeeID and month(a.logDate) = ? and year(a.logDate) = ? and a.employeeID = ? ORDER BY a.logDate DESC");
            ps.setInt(3, employeeID);
        
        }
        else{
            ps = c.prepareStatement("SELECT a.employeeID, concat(b.firstName, ' ', b.lastName) as 'Name', a.type, a.logDetails, a.logDate"+
                        " FROM user_logs a, users b where a.employeeID = b.employeeID and month(a.logDate) = ? and year(a.logDate) = ? ORDER BY a.logDate DESC");
        }
        ps.setInt(1, month+1);
        // ps.setInt(1, 3);
        ps.setInt(2, year);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            UserLogs ul = new UserLogs();
            ul.setEmployeeID(rs.getInt(1));
            ul.setEmployeeName(rs.getString(2));
            ul.setType(rs.getString(3));
            ul.setLogDetails(rs.getString(4));
            ul.setLogDate(rs.getTimestamp(5).toString());
            logs.add(ul);
        }
        c.close();
        return logs;
    }
    public static void saveReport(int employeeID, String fileName, String filePath) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("INSERT INTO reports(employeeID, fileName, filePath) VALUES (?,?,?)");
        ps.setInt(1, employeeID);
        ps.setString(2, fileName);
        ps.setString(3, filePath);
        
        int rows = ps.executeUpdate();
    }
    public static List<Reports> getAllReports() throws ClassNotFoundException, SQLException, ParseException{
        Connection c = connect();
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        List<Reports> reportList = new ArrayList<Reports>();
        PreparedStatement ps = c.prepareStatement("SELECT a.employeeID, a.fileName, a.filePath, a.generatedDate"+
                        " FROM reports a where year(a.generatedDate) = ?  ORDER BY a.generatedDate DESC");
        ps.setInt(1, year);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Reports reports = new Reports();
            String employeeName = null;
            if(rs.getInt(1) == 0){
                employeeName = "Generate All";
                
            }
            else{
                PreparedStatement ps2 = c.prepareStatement("Select Concat(firstName, ' ', lastName) from users where employeeID = ?");
                ps2.setInt(1, rs.getInt(1));
                ResultSet rsName = ps2.executeQuery();
                rsName.first();
                employeeName = rsName.getString(1);
            }
            reports.setEmployeeID(rs.getInt(1));
            reports.setEmployeeName(employeeName);
            reports.setFileName(rs.getString(2));
            reports.setFilePath(rs.getString(3));
            reports.setGeneratedDate(rs.getTimestamp(4));
            reportList.add(reports);
        }
        c.close();
        return reportList;
    }
    public static List<Uploads> getAllUploadedDocuments() throws ClassNotFoundException, SQLException{
        Connection c = connect();
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        PreparedStatement ps = c.prepareStatement("SELECT a.employeeID, b.firstName, b.lastName, a.uploadType, a.filePath, a.uploadDate"+
                        " FROM uploads a, users b where a.employeeID = b.employeeID and month(a.uploadDate) = ? and year(a.uploadDate) = ?  ORDER BY a.uploadDate DESC");
        
        ps.setInt(1, month+1);
        ps.setInt(2, year);
        
        ResultSet rs = ps.executeQuery();
        List<Uploads> uploadList = new ArrayList<Uploads>();
        while(rs.next()){
            Uploads uploads = new Uploads();
            uploads.setEmployeeID(rs.getInt(1));
            uploads.setFirstName(rs.getString(2));
            uploads.setLastName(rs.getString(3));
            uploads.setUploadType(rs.getString(4));
            uploads.setFilePath(rs.getString(5));
            uploads.setUploadDate(rs.getTimestamp(6));
            uploadList.add(uploads);
        }
        
        return uploadList;
    }
    public static List<PayrollDetails> getAllPayroll() throws ClassNotFoundException, SQLException{
        Connection c = connect();
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        PreparedStatement ps = c.prepareStatement("SELECT a.payrollID, a.employeeID, a.name, a.rate, a.sssDeduction, a.pagibigDeduction, a.philHealthDeduction,"+
                                                    " a.bonus, a.cashAdvance, a.loan, a.days, a.overTime, a.taxDeduction, a.totalSalary, a.claimed, a.claimDate "+
                                                    " FROM payroll a where a.claimed = 0 ORDER BY employeeID DESC");
        //and month(a.claimDate) = ? and year(a.claimDate) = ?  
//        ps.setInt(1, month+1);
//        ps.setInt(2, year);
//          
        List<PayrollDetails> payrollList = new ArrayList<PayrollDetails>();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            PayrollDetails payroll = new PayrollDetails();
            payroll.setPayrollID(rs.getInt(1));
            payroll.setEmployeeID(rs.getInt(2));
            payroll.setEmployeeName(rs.getString(3));
            payroll.setRate(rs.getFloat(4));
            payroll.setSssDeduction(rs.getFloat(5));
            payroll.setPagibigDeduction(rs.getFloat(6));
            payroll.setPhilHealthDeduction(rs.getFloat(7));
            payroll.setBonus(rs.getFloat(8));
            payroll.setCashAdvance(rs.getFloat(9));
            payroll.setLoan(rs.getFloat(10));
            payroll.setDays(rs.getInt(11));
            payroll.setOverTime(rs.getFloat(12));
            payroll.setTaxDeduction(rs.getFloat(13));
            payroll.setTotalSalary(rs.getInt(14));
            payroll.setIsClaimed(rs.getString(15));
            payroll.setClaimDate(rs.getTimestamp(16));
            
            payrollList.add(payroll);
        }
        return payrollList;
    }
    public static String signUp(String firstName, String lastName, int employeeID, String address, String telephoneNumber, String mobileNumber, float rate, 
            String timeIn, String timeOut, byte[] fingerPrint, String SSSNumber, String philHealthNumber, String tinNumber, String pagibigNumber, float SSSDeduction, 
            float pagibigDeduction, float philHealthDeduction, float taxDeduction, String role, ArrayList<String> pageNames, String password, int noLates, int noMemos, 
            int noAbsences) throws ClassNotFoundException, SQLException, FileNotFoundException, UareUException{
        Connection c = connect();
        
        PreparedStatement ps = c.prepareStatement("INSERT INTO users (firstName, lastName, employeeID, address, telephoneNumber," + 
                " mobileNumber, rate, timeIn, timeOut, fingerPrint, SSSNumber, philHealthNumber, tinNumber, pagibigNumber," + 
                "SSSDeduction, pagibigDeduction, philHealthDeduction, taxDeduction, role, pages, password, noLates, noMemos, noAbsences) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        //String status = determineDuplicateUser(fingerPrint);
        boolean employeeIDExist = determineDuplicateEmployeeID(employeeID);
            if(!employeeIDExist){
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setInt(3, employeeID);
                ps.setString(4, address);
                ps.setString(5, telephoneNumber);
                ps.setString(6, mobileNumber);
                ps.setFloat(7, rate);
                ps.setString(8, timeIn);
                ps.setString(9, timeOut);
                ps.setBytes(10, fingerPrint);
                //ps.setTimestamp(11, (Timestamp) getCurrentTimeStamp());
                ps.setString(11, SSSNumber);
                ps.setString(12, philHealthNumber);
                ps.setString(13, tinNumber);
                ps.setString(14, pagibigNumber);
                ps.setFloat(15, SSSDeduction);
                ps.setFloat(16, pagibigDeduction);
                ps.setFloat(17, philHealthDeduction);
                ps.setFloat(18, taxDeduction);
                ps.setString(19, role);
                ps.setString(20, pageNames.toString());
                ps.setString(21, password);
                ps.setInt(22, noLates);
                ps.setInt(23, noMemos);
                ps.setInt(24, noAbsences);
            }
            else{
                return "Duplicate ID";
            }
        int rows = ps.executeUpdate();
        c.close();

        if(rows > 0){
            return "Successful";
        }
        else{
            return "Failed";
        }
    }
    public static List<Notes> getNotes(int employeeID) throws ClassNotFoundException, SQLException{

        List<Notes> notesList = new ArrayList<Notes>();
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select a.noteID, a.employeeID, b.firstName, b.lastName, a.note, a.noteDate from " +
                "notes a, users b where a.employeeID = ? and a.employeeID = b.employeeID;");
        ps.setInt(1, employeeID);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Notes notes = new Notes();
            notes.setNoteID(rs.getInt(1));
            notes.setEmployeeID(rs.getInt(2));
            notes.setFirstName(rs.getString(3));
            notes.setLastName(rs.getString(4));
            notes.setNote(rs.getString(5));
            notes.setNoteDate(rs.getDate(6));
            
            notesList.add(notes);
        }
        c.close();
        return notesList;
    }
    public static String addNote(int employeeID, String note) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("INSERT INTO notes (employeeID, note, noteDate) VALUES (?,?,?)");
        ps.setInt(1, employeeID);
        ps.setString(2, note);
        ps.setTimestamp(3, getCurrentTimeStamp());
        int rows = ps.executeUpdate();
        c.close();

        if(rows > 0){
            return "Successful";
        }
        else{
            return "Failed";
        }
    }
    
    public static List<User> getUserDetails(int employeeID) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select employeeID, firstName, lastName, address, telephoneNumber, mobileNumber, rate, timeIn, timeOut, role, fingerPrint," +  
                "SSSNumber, philHealthNumber, tinNumber, pagibigNumber, SSSDeduction" + 
                ", philHealthDeduction, pagibigDeduction, taxDeduction, pages, password, noLates, noMemos, noAbsences from users where employeeID = ?");
        ps.setInt(1, employeeID);
        ResultSet rs = ps.executeQuery();
        List<User> userList = new ArrayList<User>();
        
        while(rs.next()){
            User user = new User();
            user.setEmployeeID(rs.getInt(1));
            user.setFirstName(rs.getString(2));
            user.setLastName(rs.getString(3));
            user.setAddress(rs.getString(4));
            user.setMobileNumber(rs.getString(5));
            user.setTelephoneNumber(rs.getString(6));
            user.setRate(rs.getFloat(7));
            user.setTimeIn(rs.getString(8));
            user.setTimeOut(rs.getString(9));
            user.setRole(rs.getString(10));
            user.setFingerPrintImage(rs.getBytes(11));
            user.setSSSNumber(rs.getString(12));
            user.setPhilHealthNumber(rs.getString(13));
            user.setTinNumber(rs.getString(14));
            user.setPagibigNumber(rs.getString(15));
            user.setSSSDeduction(rs.getFloat(16));
            user.setPhilHealthDeduction(rs.getFloat(17));
            user.setPagibigDeduction(rs.getFloat(18));
            user.setTaxDeduction(rs.getFloat(19));
            user.setPages(rs.getString(20));
            user.setPassword(rs.getString(21));
            user.setNoLates(rs.getInt(22));
            user.setNoMemos(rs.getInt(23));
            user.setNoAbsences(rs.getInt(24));
            userList.add(user);
        }
        c.close();
        return userList;
    }
    public static User getUserDetail(int employeeID) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select employeeID, firstName, lastName, address, telephoneNumber, mobileNumber, rate, timeIn, timeOut, role, fingerPrint," +  
                "SSSNumber, philHealthNumber, tinNumber, pagibigNumber, SSSDeduction" + 
                ", philHealthDeduction, pagibigDeduction, taxDeduction, pages, password, noLates, noMemos, noAbsences from users where employeeID = ?");
        ps.setInt(1, employeeID);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        
        while(rs.next()){
           
            user.setEmployeeID(rs.getInt(1));
            user.setFirstName(rs.getString(2));
            user.setLastName(rs.getString(3));
            user.setAddress(rs.getString(4));
            user.setMobileNumber(rs.getString(5));
            user.setTelephoneNumber(rs.getString(6));
            user.setRate(rs.getFloat(7));
            user.setTimeIn(rs.getString(8));
            user.setTimeOut(rs.getString(9));
            user.setRole(rs.getString(10));
            user.setFingerPrintImage(rs.getBytes(11));
            user.setSSSNumber(rs.getString(12));
            user.setPhilHealthNumber(rs.getString(13));
            user.setTinNumber(rs.getString(14));
            user.setPagibigNumber(rs.getString(15));
            user.setSSSDeduction(rs.getFloat(16));
            user.setPhilHealthDeduction(rs.getFloat(17));
            user.setPagibigDeduction(rs.getFloat(18));
            user.setTaxDeduction(rs.getFloat(19));
            user.setPages(rs.getString(20));
            user.setPassword(rs.getString(21));
            user.setNoLates(rs.getInt(22));
            user.setNoMemos(rs.getInt(23));
            user.setNoAbsences(rs.getInt(24));
        }
        c.close();
        return user;
    }
    public static String deleteUser(int employeeID) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Delete from users where employeeID = ?");
        ps.setInt(1, employeeID);
        int affectedRows = ps.executeUpdate();
        c.close();
        if(affectedRows != 0){
            return "Successful";
        }
        else{
            return "System Error";
        }
    }
    
    public static String updateUser(String firstName, String lastName, int employeeID, String address, String telephoneNumber, String mobileNumber,
            float rate, String timeIn, String timeOut, String SSSNumber, String philHealthNumber, String tinNumber, String pagibigNumber,
            float SSSDeduction, float pagibigDeduction, float philHealthDeduction, float taxDeduction,
            String password, int noLates, int noMemos, int noAbsences, ArrayList<String> pageNames) throws SQLException, ClassNotFoundException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, address =?, telephoneNumber =?, mobileNumber = ?, rate = ?, timeIn = ?, timeOut = ?, " +""
                + " SSSNumber = ?, philHealthNumber =?, tinNumber = ?, pagibigNumber = ?, SSSDeduction = ?, pagibigDeduction = ?, philHealthDeduction = ?, taxDeduction =?, password = ?,"+
                " noLates = ?, noMemos = ?, noAbsences = ?, pages = ? WHERE employeeID = ?");
        
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, address);
        ps.setString(4, telephoneNumber);
        ps.setString(5, mobileNumber);
        ps.setFloat(6, rate);
        ps.setString(7, timeIn);
        ps.setString(8, timeOut);
        ps.setString(9, SSSNumber);
        ps.setString(10, philHealthNumber);
        ps.setString(11, tinNumber);
        ps.setString(12, pagibigNumber);
        ps.setFloat(13, SSSDeduction);
        ps.setFloat(14, pagibigDeduction);
        ps.setFloat(15, philHealthDeduction);
        ps.setFloat(16, taxDeduction);
        ps.setString(17, password);
        ps.setInt(18, noLates);
        ps.setInt(19, noMemos);
        ps.setInt(20, noAbsences);
        ps.setString(21, pageNames.toString());
        ps.setInt(22, employeeID);
        int affectedRow = ps.executeUpdate();
        c.close();
        if(affectedRow > 0){
            return "Successful";
        }
        else{
            return "Error";
        }
        
    }
//    public static boolean checkIfDuplicateExcelImport(int employeeID, Date date) throws ClassNotFoundException, SQLException{
//        Connection c = connect();
//        PreparedStatement ps = c.prepareStatement("SELECT date, employeeID from time_logs where date = ? and employeeID = ? ");
//        ps.setDate(1, new java.sql.Date(convertDate(date)));
//        ps.setInt(2, employeeID);
//         
//        ResultSet rs = ps.executeQuery();
//        
//        return rs.first();
//         
//    }
    
    public static int insertUser(String name) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        String[] fullName = name.split(" ");
        String firstName = null;
        String lastName = null;
        if(fullName.length == 2){
            firstName = fullName[0];
            lastName = fullName[1];
        }
        else if(fullName.length== 3){
            firstName = fullName[0];
            lastName = fullName[1] + " " + fullName[2];
            
        }
        
        PreparedStatement ps = c.prepareStatement("INSERT INTO users(firstName, lastName) VALUES(?,?)");
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        int row = ps.executeUpdate();
        int id = 0;
        if(row != 0){
            
            PreparedStatement ps1 = c.prepareStatement("SELECT userID from users where firstName = ? and lastName = ?");
            ps1.setString(1, firstName);
            ps1.setString(2, lastName);
            ResultSet rs = ps1.executeQuery();
            while(rs.next()){
                 id = rs.getInt(1);
            }
           
            while(determineDuplicateEmployeeID(id) == true){
                id = id + 1;
            }
            PreparedStatement ps2 = c.prepareStatement("UPDATE users SET employeeID = ? where firstName = ? and lastName = ?");
            ps2.setInt(1, id);
            ps2.setString(2, firstName);
            ps2.setString(3, lastName);
            ps2.executeUpdate();
   
        }
        return id;
    }
    public static boolean determineDuplicateEmployeeID(int employeeID) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select * from users where employeeID = ? ");
        ps.setInt(1, employeeID);
        ResultSet rs = ps.executeQuery();
        
        
        return rs.first();
    }
    public static void insertUserLogFromExcel(String name, String department, String logDate, String timeIn, String timeOut) throws ClassNotFoundException, SQLException, ParseException{
        Connection c = connect();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = formatter.parse(logDate);
        String[] fullName = name.split(" ");
        if(fullName.length == 2){
            name = fullName[1] + " " + fullName[0];
        }
        else if(fullName.length == 3){
            char[] secondName = fullName[2].toCharArray();
            if(secondName.length > 4){
                name = fullName[2] + " " + fullName[0] + " " + fullName[1];
            }
            else{
                name = fullName[1] + " " + fullName[2] + " " + fullName[0];
            }
            
        }
        else;
        
        
        int employeeID = DB.getEmployeeIDFromName(name);
        
        //boolean status = checkIfDuplicateExcelImport(employeeID, date);
        if(employeeID == 0){
            employeeID = DB.insertUser(name);
        }
       
        PreparedStatement ps = c.prepareStatement("INSERT INTO time_logs (employeeID, name, department, logDate, timeIn, timeOut) VALUES (?,?,?,?,?,?)");
        ps.setInt(1, employeeID);
        ps.setString(2, name);
        ps.setString(3, department);
        ps.setDate(4, new java.sql.Date(convertDate(date)));
        ps.setString(5, timeIn);
        ps.setString(6, timeOut);
        ps.executeUpdate();
        
        c.close();
    }
    public static int getEmployeeIDFromName(String name) throws ClassNotFoundException, SQLException{
        int employeeID = 0;
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("SELECT employeeID, CONCAT(firstName,' ', lastName) as 'Name' from users");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(name.equals(rs.getString(2))){
                
                employeeID = rs.getInt(1);
            }
        }
        c.close();
        return employeeID;
    }

    public static void computeForPayout() throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select employeeID, firstName, lastName, rate," +  
                "SSSDeduction, philHealthDeduction, pagibigDeduction, taxDeduction from users");
        ResultSet rs = ps.executeQuery();
        List<String> employees = new ArrayList<String>();
        List<Integer> employeeIDs = new ArrayList<Integer>();
        List<User> userList = new ArrayList<User>();
        
        while(rs.next()){
            User user = new User();
            user.setEmployeeID(rs.getInt(1));
            user.setFirstName(rs.getString(2));
            user.setLastName(rs.getString(3));
            user.setRate(rs.getFloat(4));
            user.setSSSDeduction(rs.getFloat(5));
            user.setPhilHealthDeduction(rs.getFloat(6));
            user.setPagibigDeduction(rs.getFloat(7));
            user.setTaxDeduction(rs.getFloat(8));
            userList.add(user);
        }
        
        PreparedStatement psDelete = c.prepareStatement("Delete from payroll");
        psDelete.executeUpdate();
        // get date min max
        PreparedStatement psDate = c.prepareStatement("Select MIN(logDate) as minDate, MAX(logDate) as maxDate from time_logs");
        ResultSet rsDate = psDate.executeQuery();
        String startDate = null;
        String endDate = null;
        while(rsDate.next()){
            startDate = rsDate.getDate(1).toString();
            endDate = rsDate.getDate(2).toString();
        }
        
        // end of get date min max
        //get holiday
            PreparedStatement psHoliday = c.prepareStatement("Select count(*) from holiday where holidayDate BETWEEN "+ startDate +" AND " + endDate+ ";");
            ResultSet rsHoliday = psHoliday.executeQuery();
            int holidayCount = 0;
            rsHoliday.first();
            holidayCount = rsHoliday.getInt(1);    

        //end of holiday
        for(int k = 0; k < userList.size(); k++){
            // get attendance count 
            int attendanceCount = 0;
            PreparedStatement ps1 = c.prepareStatement("Select count(*) from dsl.time_logs where name = ?");
            ps1.setString(1, userList.get(k).getFirstName() + " " + userList.get(k).getLastName());
            ResultSet set = ps1.executeQuery();
            set.first();
            attendanceCount = set.getInt(1);
            // end of get attendance count
            
            //get bonus
            PreparedStatement psBonus = c.prepareStatement("Select amount from salary_extra where employeeID = ? and appliedDate between " + startDate +" AND " + endDate+ ";");
            psBonus.setInt(1, userList.get(k).getEmployeeID());
            ResultSet rsBonus = psBonus.executeQuery();
            float bonus = 0;
            while(rsBonus.next()){
                bonus = bonus + rsBonus.getFloat(1);
            }
            //end get bonus
            //get cash advance
            PreparedStatement psAdvance = c.prepareStatement("Select amount from salary_condition where employeeID = ? and type = 'Cash Advance' AND appliedDate between " + startDate +" AND " + endDate+ ";");
            psAdvance.setInt(1, userList.get(k).getEmployeeID());
            ResultSet rsAdvance = psAdvance.executeQuery();
            float cashAdvance = 0;
            while(rsAdvance.next()){
                cashAdvance = cashAdvance + rsAdvance.getFloat(1);
            }
            // end get cash advance
            PreparedStatement psLoan = c.prepareStatement("Select amount from salary_condition where employeeID = ? and type = 'Loan' AND appliedDate between " + startDate +" AND " + endDate+ ";");
            psLoan.setInt(1, userList.get(k).getEmployeeID());
            ResultSet rsLoan = psLoan.executeQuery();
            float loan = 0;
            while(rsLoan.next()){
                loan = loan + rsLoan.getFloat(1);
            }
            //compute total salary
            float totalSalary = (attendanceCount * userList.get(k).getRate()) - userList.get(k).getSSSDeduction() - userList.get(k).getPhilHealthDeduction() - 
                    userList.get(k).getPagibigDeduction()- userList.get(k).getTaxDeduction() + bonus - loan - cashAdvance;
            // 
            PreparedStatement ps2 = c.prepareStatement("INSERT INTO payroll (employeeID, name, rate, sssDeduction,"+
                    " philHealthDeduction, pagibigDeduction, taxDeduction, bonus, cashAdvance, loan, days, overTime, totalSalary) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            // 8 = rate
            // 17 = sssDeduction
            // 18 = philHealthDeduction
            // 19 = pagibigDeduction
            // 21 = taxDeduction
            ps2.setInt(1, userList.get(k).getEmployeeID());
            ps2.setString(2, userList.get(k).getFirstName() + " " + userList.get(k).getLastName());
            ps2.setFloat(3, userList.get(k).getRate());
            ps2.setFloat(4, userList.get(k).getSSSDeduction());
            ps2.setFloat(5, userList.get(k).getPhilHealthDeduction());
            ps2.setFloat(6, userList.get(k).getPagibigDeduction());
            ps2.setFloat(7, userList.get(k).getTaxDeduction());
            ps2.setFloat(8, bonus);
            ps2.setFloat(9, cashAdvance);
            ps2.setFloat(10, loan);
            ps2.setFloat(11, attendanceCount);
            ps2.setFloat(12, 0);
            ps2.setFloat(13, totalSalary);
            ps2.executeUpdate();
            
            PreparedStatement ps3 = c.prepareStatement("INSERT INTO payroll_history (employeeID, name, rate, sssDeduction,"+
                    " philHealthDeduction, pagibigDeduction, taxDeduction, bonus, cashAdvance, loan, days, overTime, totalSalary) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps3.setInt(1, userList.get(k).getEmployeeID());
            ps3.setString(2, userList.get(k).getFirstName() + " " + userList.get(k).getLastName());
            ps3.setFloat(3, userList.get(k).getRate());
            ps3.setFloat(4, userList.get(k).getSSSDeduction());
            ps3.setFloat(5, userList.get(k).getPhilHealthDeduction());
            ps3.setFloat(6, userList.get(k).getPagibigDeduction());
            ps3.setFloat(7, userList.get(k).getTaxDeduction());
            ps3.setFloat(8, bonus);
            ps3.setFloat(9, cashAdvance);
            ps3.setFloat(10, loan);
            ps3.setFloat(11, attendanceCount - holidayCount);
            ps3.setFloat(12, 0);
            ps3.setFloat(13, totalSalary);
            ps3.executeUpdate();  
        }
    }
    
    public static PayrollDetails claimSalary(int employeeID) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        //INSERT INTO payroll (employeeID, name, rate, sssDeduction,"+" philHealthDeduction, pagibigDeduction, taxDeduction, bonus, cashAdvance, loan, days, overTime, totalSalary
        PreparedStatement ps = c.prepareStatement("Select employeeID, name, rate, sssDeduction," +  
                "philHealthDeduction, pagibigDeduction, taxDeduction, bonus, cashAdvance, loan,"+
                " days, overTime, totalSalary from payroll where employeeID = ?");
        ps.setInt(1, employeeID);
        
        ResultSet rs = ps.executeQuery();
        PayrollDetails payroll = new PayrollDetails();
        while(rs.next()){
            payroll.setEmployeeID(rs.getInt(1));
            payroll.setEmployeeName(rs.getString(2));
            payroll.setRate(rs.getFloat(3));
            payroll.setSssDeduction(rs.getFloat(4));
            payroll.setPhilHealthDeduction(rs.getFloat(5));
            payroll.setPagibigDeduction(rs.getFloat(6));
            payroll.setTaxDeduction(rs.getFloat(7));
            payroll.setBonus(rs.getFloat(8));
            payroll.setCashAdvance(rs.getFloat(9));
            payroll.setLoan(rs.getFloat(10));
            payroll.setDays(rs.getInt(11));
            payroll.setOverTime(rs.getInt(12));
            payroll.setTotalSalary(rs.getFloat(13));
            
        }
        
        return payroll;
    }
    public static String setSalaryClaim(int employeeID, float rate, float sssDeduction, float pagibigDeduction, float philHealthDeduction, float bonus, float cashAdvance, float loan, int days, float overTime, float totalSalary, float taxDeduction) throws ClassNotFoundException, SQLException {
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("UPDATE payroll set rate = ?, sssDeduction = ?, pagibigDeduction = ?, philHealthDeduction = ?," +
            " bonus = ?, cashAdvance = ?, loan = ?, days = ?, overTime = ?, totalSalary = ?, taxDeduction = ?, claimed = ? where employeeID = ?");
        
        
        ps.setFloat(1, rate);
        ps.setFloat(2, sssDeduction);
        ps.setFloat(3, pagibigDeduction);
        ps.setFloat(4, philHealthDeduction);
        ps.setFloat(5, bonus);
        ps.setFloat(6, cashAdvance);
        ps.setFloat(7, loan);
        ps.setInt(8, days);
        ps.setFloat(9, overTime);
        ps.setFloat(10, totalSalary);
        ps.setFloat(11, taxDeduction);
        ps.setInt(12, 1);
        ps.setInt(13, employeeID);
        
        int rows = ps.executeUpdate();

        if(rows > 0){
            PreparedStatement psHistory = c.prepareStatement("UPDATE payroll_history set rate = ?, sssDeduction = ?, pagibigDeduction = ?, philHealthDeduction = ?," +
            " bonus = ?, cashAdvance = ?, loan = ?, days = ?, overTime = ?, totalSalary = ?, taxDeduction = ?, claimed = ? where employeeID = ?");
            psHistory.setFloat(1, rate);
            psHistory.setFloat(2, sssDeduction);
            psHistory.setFloat(3, pagibigDeduction);
            psHistory.setFloat(4, philHealthDeduction);
            psHistory.setFloat(5, bonus);
            psHistory.setFloat(6, cashAdvance);
            psHistory.setFloat(7, loan);
            psHistory.setInt(8, days);
            psHistory.setFloat(9, overTime);
            psHistory.setFloat(10, totalSalary);
            psHistory.setFloat(11, taxDeduction);
            psHistory.setInt(12, 1);
            psHistory.setInt(13, employeeID);
        
            psHistory.executeUpdate();
            PreparedStatement ps1 = c.prepareStatement("UPDATE users set SSSDeduction = ?, pagibigDeduction = ?,"+
                    " philHealthDeduction = ?, taxDeduction = ?, rate = ? where employeeID = ?");
            ps1.setFloat(1, sssDeduction);
            ps1.setFloat(2, pagibigDeduction);
            ps1.setFloat(3, philHealthDeduction);
            ps1.setFloat(4, taxDeduction);
            ps1.setFloat(5, rate);
            ps1.setFloat(6, employeeID);
            
            int updateRows = ps1.executeUpdate();
            c.close();
            if(updateRows > 0){
                return "Successful";
            }
            else{
                return "Failed";
            }
        }
        else{
            return "Failed";
        }
    }
    public static void deleteAllTimeLogs() throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Delete from time_logs");
        ps.executeUpdate();
        
        c.close();
    }
    public static void getAllSalaryConditions() throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("SELECT salary_extra.employeeID as employeeID, salary_extra.type as salaryExtraType, salary_extra.amount as salaryExtraAmount," +
                        " salary_extra.appliedDate as salaryExtraDate, salary_condition.type as salaryConditionTypee, salary_condition.amount as salaryConditionAmount, salary_condition.appliedDate as salaryConditionDate FROM salary_extra " +
                        "INNER JOIN salary_condition ON salary_extra.employeeID = salary_condition.employeeID where month(salary_extra.appliedDate) = ? and year(salary_extra.appliedDate) = ? and month(salary_condition.appliedDate) = ? and year(salary_condition.appliedDate) = ?;");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.sql.Date(getCurrentCalendar()));
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int year = cal.get(Calendar.YEAR);
        
        ps.setInt(1, month);
        ps.setInt(2, year);
        ps.setInt(3, month);
        ps.setInt(4, year);
        
        
        ResultSet rs = ps.executeQuery();
        
    }
    public static List<SalaryItems> getAllSalaryItems() throws ClassNotFoundException, SQLException{
        Connection c = connect();
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.sql.Date(getCurrentCalendar()));
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int year = cal.get(Calendar.YEAR);
        List<SalaryItems> salaryItems = new ArrayList<SalaryItems>();
        
        PreparedStatement ps = c.prepareStatement("Select a.conditionID, CONCAT(b.firstName,' ', b.lastName) as 'Name', "+
                "a.type, a.employeeID, a.amount, a.appliedDate from salary_condition a, users b where a.employeeID = b.employeeID and month(appliedDate) = ? and year(appliedDate) = ?");
        ps.setInt(1, month);
        ps.setInt(2, year);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            SalaryItems items = new SalaryItems();
            items.setId(rs.getInt(1));
            items.setEmployeeName(rs.getString(2));
            items.setType(rs.getString(3));
            items.setEmployeeID(rs.getInt(4));
            items.setAmount(rs.getFloat(5));
            items.setClaimDate(rs.getDate(6));
            items.setConditionType("Deductables");
            salaryItems.add(items);
        }
        PreparedStatement ps1 = c.prepareStatement("Select a.extraID, CONCAT(b.firstName,' ', b.lastName) as 'Name', "+
                "a.type, a.employeeID, a.amount, a.appliedDate from salary_extra a, users b where a.employeeID = b.employeeID and month(appliedDate) = ? and year(appliedDate) = ?");
        
        ps1.setInt(1, month);
        ps1.setInt(2, year);
        
        ResultSet rs1 = ps1.executeQuery();
        while(rs1.next()){
            SalaryItems items = new SalaryItems();
            items.setId(rs1.getInt(1));
            items.setEmployeeName(rs1.getString(2));
            items.setType(rs1.getString(3));
            items.setEmployeeID(rs1.getInt(4));
            items.setAmount(rs1.getFloat(5));
            items.setClaimDate(rs1.getDate(6));
            items.setConditionType("Extra");
            salaryItems.add(items);
        }
        return salaryItems;
    }
    
    public static SalaryCondition getSalaryCondition(int conditionID, int employeeID, String table) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        SalaryCondition condition = new SalaryCondition();
        String id = null;
        if(table.equals("salary_condition")){
            id = "conditionID";
        }
        else{
            id = "extraID";
        }
        PreparedStatement ps = c.prepareStatement("Select a.employeeID, CONCAT(b.firstName,' ', b.lastName) as 'Name', a.type,"+
                " a.amount, a.appliedDate from "+ table+ " a, users b where "+ id +" = ? and a.employeeID = b.employeeID");
        ps.setInt(1, conditionID);

        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            condition.setEmployeeID(rs.getInt(1));
            condition.setEmployeeName(rs.getString(2));
            condition.setType(rs.getString(3));
            condition.setAmount(rs.getFloat(4));
            condition.setAppliedDate(rs.getDate(5));
        }
        return condition;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     

    public static Timestamp getCurrentTimeStamp(){
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        
        return timeStamp;
    }
    
    public static long getCurrentCalendar(){
        Calendar cal = Calendar.getInstance();
        return cal.getTimeInMillis();
    }
    
    public static long convertDate(Date holiday){
        Calendar cal = Calendar.getInstance();
        cal.setTime(holiday);
        return cal.getTimeInMillis();
    }
    
    public static Timestamp getDateToday(){
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());  
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return sq;
    }
    
    
    public static Notes getNote(int noteID) throws ClassNotFoundException, SQLException{
        Notes notes = new Notes();
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("SELECT noteID, employeeID, note from notes where noteID = ?");
        ps.setInt(1, noteID);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            notes.setNoteID(rs.getInt(1));
            notes.setEmployeeID(rs.getInt(2));
            notes.setNote(rs.getString(3));
        }
        c.close();
        return notes;
    }
    public static String updateNotes(int noteID, String notes) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("UPDATE notes set note = ? where noteID = ?");
        ps.setString(1, notes);
        ps.setInt(2, noteID);
        int rows = ps.executeUpdate();
        c.close();

        if(rows > 0){
            return "Successful";
        }
        else{
            return "Failed";
        }
    }
    public static String setUploadFile(int employeeID, String fileType, String fileLocation) throws ClassNotFoundException, SQLException, FileNotFoundException{
        Connection c = connect();
        
        PreparedStatement ps = c.prepareStatement("INSERT INTO uploads (employeeID, uploadType, filePath, uploadDate) VALUES (?,?,?,?)");
        ps.setInt(1, employeeID);
        ps.setString(2, fileType);
        ps.setString(3, fileLocation);
        ps.setTimestamp(4, getCurrentTimeStamp());
        int rows = ps.executeUpdate();
        c.close();

        if(rows > 0){
            return "Successful";
        }
        else{
            return "Failed";
        }
    }
    

    public static List<Files> getFiles(int employeeID) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
        String fileName = null;
        List<Files> filesList = new ArrayList<Files>();
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select a.uploadID, a.employeeID, b.firstName, b.lastName, a.filePath, a.uploadDate from " +
                "uploads a, users b where a.employeeID = ? and a.employeeID = b.employeeID;");
        ps.setInt(1, employeeID);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Files files = new Files();
            files.setFileID(rs.getInt(1));
            files.setEmployeeID(rs.getInt(2));
            files.setFirstName(rs.getString(3));
            files.setLastName(rs.getString(4));
            files.setFilePath(rs.getString(5));
            files.setUploadDate(rs.getDate(6));
            filesList.add(files);
        }
        c.close();
        return filesList;
    }
    public static List<PayrollDetails> getSalaryClaim(int employeeID) throws ClassNotFoundException, SQLException {
        Connection c = connect();
        String sqlSyntax = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.sql.Date(getCurrentCalendar()));
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int year = cal.get(Calendar.YEAR);
        int dayStart = 0;
        int dayEnd = 0;
        List<PayrollDetails> details = new ArrayList<>();
        if(day >= 1 && day <=15){
            dayStart = 1;
            dayEnd = 15;
        }
        else{
            dayStart = 16;
            dayEnd = 31;
        }
        if(employeeID == 0){
            sqlSyntax = "month(a.claimDate) = ? and (day(a.claimDate) >= ? and day(a.claimDate) <= ? )and year(a.claimDate) = ?";
        }
        else{
            sqlSyntax = "a.employeeID = ? and month(a.claimDate) = ? and (day(a.claimDate) >= ? and day(a.claimDate) <= ? ) and year(a.claimDate) = ?";
        }
        
        PreparedStatement ps = c.prepareStatement("SELECT a.employeeID, concat(b.firstName, ' ', b.lastName) as 'Name', a.rate, a.sssDeduction, a.pagibigDeduction, a.philHealthDeduction, a.bonus, a.cashAdvance, a.loan,"+
                " a.days, a.overtime, a.totalSalary, a.taxDeduction, a.isClaimed, a.claimDate from payroll a, users b where a.employeeID = b.employeeID and " + sqlSyntax);
        if(employeeID == 0){
            ps.setInt(1, month);
            ps.setInt(2, dayStart);
            ps.setInt(3, dayEnd);
            ps.setInt(4, year);
        }
        else{
            ps.setInt(1, employeeID);
            ps.setInt(2, month);
            ps.setInt(3, dayStart);
            ps.setInt(4, dayEnd);
            ps.setInt(5, year);
        }
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            PayrollDetails payroll = new PayrollDetails();
            
            payroll.setEmployeeID(rs.getInt(1));
            payroll.setEmployeeName(rs.getString(2));
            payroll.setRate(rs.getFloat(3));
            payroll.setSssDeduction(rs.getFloat(4));
            payroll.setPagibigDeduction(rs.getFloat(5));
            payroll.setPhilHealthDeduction(rs.getFloat(6));
            payroll.setBonus(rs.getFloat(7));
            payroll.setCashAdvance(rs.getFloat(8));
            payroll.setLoan(rs.getFloat(9));
            payroll.setDays(rs.getInt(10));
            payroll.setOverTime(rs.getFloat(11));
            payroll.setTotalSalary(rs.getInt(12));
            payroll.setTaxDeduction(rs.getFloat(13));
            payroll.setIsClaimed(rs.getString(14));
            payroll.setClaimDate(rs.getTimestamp(15));
            details.add(payroll);
        }
        c.close();
        return details;
    }
    
    public static List<PayrollDetails> getReportSalaryClaim(String startDate, String endDate, int employeeID) throws ClassNotFoundException, SQLException, ParseException {
        Connection c = connect();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        List<Attendance> attendanceList = new ArrayList<>();
        
//        int startMonth = 0;
//        int startDay = 0;
//        int startYear = 0;
//        int endMonth = 0;
//        int endDay = 0;
//        int endYear = 0;
//
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateStart = format.parse(startDate);
//        Date dateEnd = format.parse(endDate);
//        cal.setTime(dateStart);
//        cal2.setTime(dateEnd);
//        startMonth = cal.get(Calendar.MONTH) + 1;
//        startDay = cal.get(Calendar.DATE);
//        startYear = cal.get(Calendar.YEAR);
//        endMonth = cal2.get(Calendar.MONTH) + 1;
//        endDay = cal2.get(Calendar.DATE);
//        endYear = cal2.get(Calendar.YEAR);
        
        
        List<PayrollDetails> details = new ArrayList<>();
        
        
        PreparedStatement ps = c.prepareStatement("SELECT employeeID, rate, sssDeduction, pagibigDeduction, philHealthDeduction, bonus, cashAdvance, loan, days, overTime,"+
                "totalSalary, taxDeduction, claimDate, claimed from payroll_history a where employeeID = ? and claimed = 1 and a.claimDate BETWEEN CAST('"+startDate+"' AS DATE) AND CAST('"+endDate+"' AS DATE);");
        ps.setInt(1, employeeID);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            PayrollDetails payroll = new PayrollDetails();
            payroll.setEmployeeID(rs.getInt(1));
            payroll.setRate(rs.getFloat(2));
            payroll.setSssDeduction(rs.getFloat(3));
            payroll.setPagibigDeduction(rs.getFloat(4));
            payroll.setPhilHealthDeduction(rs.getFloat(5));
            payroll.setBonus(rs.getFloat(6));
            payroll.setCashAdvance(rs.getFloat(7));
            payroll.setLoan(rs.getFloat(8));
            payroll.setDays(rs.getInt(9));
            payroll.setOverTime(rs.getFloat(10));
            payroll.setTotalSalary(rs.getFloat(11));
            payroll.setTaxDeduction(rs.getFloat(12));
            payroll.setClaimDate(rs.getTimestamp(13));
            payroll.setIsClaimed(rs.getString(14));
            
            details.add(payroll);
        }
        c.close();
        return details;
    }
    
    public static List<Absence> getAbsentDate(String startDate, String endDate, int employeeID) throws ClassNotFoundException, SQLException, ParseException{
        Connection c = connect();
        Calendar calendar = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        List<Absence> absenceList = new ArrayList<>();
        int startMonth = 0;
        int startDay = 0;
        int startYear = 0;
        int endMonth = 0;
        int endDay = 0;
        int endYear = 0;

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateStart = format.parse(startDate);
        Date dateEnd = format.parse(endDate);
        cal.setTime(dateStart);
        cal2.setTime(dateEnd);
        startMonth = cal.get(Calendar.MONTH) + 1;
        startDay = cal.get(Calendar.DATE);
        startYear = cal.get(Calendar.YEAR);
        endMonth = cal2.get(Calendar.MONTH) + 1;
        endDay = cal2.get(Calendar.DATE);
        endYear = cal2.get(Calendar.YEAR);

        PreparedStatement ps = c.prepareStatement("SELECT a.employeeID, concat(b.firstName, ' ', b.lastName) as 'Name', a.absenceDate, a.reason"+
                " from absence a, users b where b.employeeID = a.employeeID and a.employeeID = ? and a.absenceDate BETWEEN CAST('"+startDate+"' AS DATE) AND CAST('"+endDate+"' AS DATE);");

        ps.setInt(1, employeeID);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Absence absence = new Absence();
            absence.setEmployeeID(rs.getInt(1));
            absence.setEmployeeName(rs.getString(2));
            absence.setAbsenceDate(rs.getDate(3));
            absence.setReason(rs.getString(4));

            absenceList.add(absence);
        }
        
        c.close();
        return absenceList;
    }
    public static List<Attendance> getReportAttendance(String startDate, String endDate, int employeeID) throws ClassNotFoundException, SQLException, ParseException{
        Connection c = connect();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        List<Attendance> attendanceList = new ArrayList<>();
        
//        int startMonth = 0;
//        int startDay = 0;
//        int startYear = 0;
//        int endMonth = 0;
//        int endDay = 0;
//        int endYear = 0;
//
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateStart = format.parse(startDate);
//        Date dateEnd = format.parse(endDate);
//        cal.setTime(dateStart);
//        cal2.setTime(dateEnd);
//        startMonth = cal.get(Calendar.MONTH) + 1;
//        startDay = cal.get(Calendar.DATE);
//        startYear = cal.get(Calendar.YEAR);
//        endMonth = cal2.get(Calendar.MONTH) + 1;
//        endDay = cal2.get(Calendar.DATE);
//        endYear = cal2.get(Calendar.YEAR);
    
        System.out.println(startDate);
        System.out.println(endDate);
        PreparedStatement ps = c.prepareStatement("SELECT a.employeeID, concat(b.firstName, ' ', b.lastName) as 'Name', a.logDate, a.timeIn,"+
                " a.timeOut from time_logs a, users b where b.employeeID = a.employeeID and a.employeeID = ? and a.logDate BETWEEN CAST('"+startDate+"' AS DATE) AND CAST('"+endDate+"' AS DATE);");
                //  + ""
//                + ""
//                + " '" + startDate + "' AND '" + endDate + "'");
//BETWEEN CAST('2017-05-01' AS DATE) AND CAST('2017-05-30' AS DATE);

        ps.setInt(1, employeeID);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getTimestamp(3));
            System.out.println(rs.getString(4));
            System.out.println(rs.getString(5));
            Attendance ul = new Attendance();
            ul.setEmployeeID(rs.getInt(1));
            ul.setEmployeeName(rs.getString(2));
            ul.setLogDate(rs.getTimestamp(3));
            ul.setTimeInDateString(rs.getString(4));
            ul.setTimeOutDateString(rs.getString(5));

            attendanceList.add(ul);
        }
        
        c.close();
        
        
        return attendanceList;
    }
   
//    public static List<UserLogs> getSystemLogs(int employeeID, String startDate, String endDate, String filter) throws ClassNotFoundException, SQLException, ParseException{
//        Connection c = connect();
//        Calendar cal = Calendar.getInstance();
//        Calendar cal2 = Calendar.getInstance();
//        List<UserLogs> logs = new ArrayList<UserLogs>();
//
//        if(employeeID != 0){
//            if(startDate.equals("") && endDate.equals("")){
//                cal.setTime(new java.sql.Date(getCurrentCalendar()));
//                int month = cal.get(Calendar.MONTH) + 1;
//                int day = cal.get(Calendar.DATE);
//                int year = cal.get(Calendar.YEAR);
//                PreparedStatement ps = c.prepareStatement("SELECT a.employeeID, concat(b.firstName, ' ', b.lastName) as 'Name', a.type, a.logDetails, a.logDate FROM user_logs a, users b" + 
//                        " where a.employeeID = b.employeeID and a.employeeID = ? and month(a.logDate) = ? and year(a.logDate) = ?  ORDER BY a.logDate DESC");
//                ps.setInt(1, employeeID);
//                ps.setInt(2, month);
//                ps.setInt(3, day);
//                ps.setInt(4, year);
//                ResultSet rs = ps.executeQuery();
//                while(rs.next()){
//                    UserLogs ul = new UserLogs();
//                    ul.setEmployeeID(rs.getInt(1));
//                    ul.setEmployeeName(rs.getString(2));
//                    ul.setType(rs.getString(3));
//                    ul.setLogDetails(rs.getString(4));
//                    ul.setLogDate(rs.getTimestamp(5).toString());
//                    logs.add(ul);
//                }
//            }
//            else if(startDate.equals("") || endDate.equals("")){
//                int month = 0;
//                int day = 0;
//                int year = 0;
//                if(!startDate.equals("")){
//                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                    Date date = format.parse(startDate);
//                    cal.setTime(date);
//                    month = cal.get(Calendar.MONTH) + 1;
//                    day = cal.get(Calendar.DATE);
//                    year = cal.get(Calendar.YEAR);
//                }
//                else if(!endDate.equals("")){
//                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                    Date date = format.parse(endDate);
//                    cal.setTime(date);
//                    month = cal.get(Calendar.MONTH) + 1;
//                    day = cal.get(Calendar.DATE);
//                    year = cal.get(Calendar.YEAR);
//                }
//                PreparedStatement ps = c.prepareStatement("SELECT a.employeeID, concat(b.firstName, ' ', b.lastName) as 'Name', a.type, a.logDetails, a.logDate FROM user_logs a, users b "+
//                         "where a.employeeID = b.employeeID and a.employeeID = ? and month(a.logDate) = ? and day(a.logDate) = ? and year(a.logDate)=?  ORDER BY a.logDate DESC"); 
//                ps.setInt(1, employeeID);
//                ps.setInt(2, month);
//                ps.setInt(3, day);
//                ps.setInt(4, year);
//                ResultSet rs = ps.executeQuery();
//                while(rs.next()){
//                    UserLogs ul = new UserLogs();
//                    ul.setEmployeeID(rs.getInt(1));
//                    ul.setEmployeeName(rs.getString(2));
//                    ul.setType(rs.getString(3));
//                    ul.setLogDetails(rs.getString(4));
//                    ul.setLogDate(rs.getTimestamp(5).toString());
//                    logs.add(ul);
//                }
//            }
//            else{
//                int startMonth = 0;
//                int startDay = 0;
//                int startYear = 0;
//                int endMonth = 0;
//                int endDay = 0;
//                int endYear = 0;
//
//                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                Date dateStart = format.parse(startDate);
//                Date dateEnd = format.parse(endDate);
//                cal.setTime(dateStart);
//                cal2.setTime(dateEnd);
//                startMonth = cal.get(Calendar.MONTH) + 1;
//                startDay = cal.get(Calendar.DATE);
//                startYear = cal.get(Calendar.YEAR);
//                endMonth = cal2.get(Calendar.MONTH) + 1;
//                endDay = cal2.get(Calendar.DATE);
//                endYear = cal2.get(Calendar.YEAR);
//
//                PreparedStatement ps = c.prepareStatement("SELECT a.employeeID, concat(b.firstName, ' ', b.lastName) as 'Name', a.type, a.logDetails, a.logDate"+
//                        " FROM user_logs a, users b where a.employeeID = b.employeeID and a.employeeID = ? and a.logDate BETWEEN '" + startDate + "' AND '" + endDate + "' ORDER BY a.logDate DESC");
//                ps.setInt(1, employeeID);
//                ResultSet rs = ps.executeQuery();
//                while(rs.next()){
//                    UserLogs ul = new UserLogs();
//                    ul.setEmployeeID(rs.getInt(1));
//                    ul.setEmployeeName(rs.getString(2));
//                    ul.setType(rs.getString(3));
//                    ul.setLogDetails(rs.getString(4));
//                    ul.setLogDate(rs.getTimestamp(5).toString());
//                    logs.add(ul);
//                }
//            }
//        }
//        else{
//            if(startDate.equals("") && endDate.equals("")){
//                cal.setTime(new java.sql.Date(getCurrentCalendar()));
//                int month = cal.get(Calendar.MONTH) + 1;
//                int day = cal.get(Calendar.DATE);
//                int year = cal.get(Calendar.YEAR);
//                PreparedStatement ps = c.prepareStatement("SELECT a.employeeID, concat(b.firstName, ' ', b.lastName) as 'Name', a.type, a.logDetails, a.logDate FROM user_logs a, users b" + 
//                        " where a.employeeID = b.employeeID and month(a.logDate) = ? and day(a.logDate) = ? and year(a.logDate) = ?  ORDER BY a.logDate DESC");
//                ps.setInt(1, month);
//                ps.setInt(2, day);
//                ps.setInt(3, year);
//                ResultSet rs = ps.executeQuery();
//                while(rs.next()){
//                    UserLogs ul = new UserLogs();
//                    ul.setEmployeeID(rs.getInt(1));
//                    ul.setEmployeeName(rs.getString(2));
//                    ul.setType(rs.getString(3));
//                    ul.setLogDetails(rs.getString(4));
//                    ul.setLogDate(rs.getTimestamp(5).toString());
//                    logs.add(ul);
//                }
//            }
//            else if(startDate.equals("") || endDate.equals("")){
//                int month = 0;
//                int day = 0;
//                int year = 0;
//                if(!startDate.equals("")){
//                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                    Date date = format.parse(startDate);
//                    cal.setTime(date);
//                    month = cal.get(Calendar.MONTH) + 1;
//                    day = cal.get(Calendar.DATE);
//                    year = cal.get(Calendar.YEAR);
//                }
//                else if(!endDate.equals("")){
//                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                    Date date = format.parse(endDate);
//                    cal.setTime(date);
//                    month = cal.get(Calendar.MONTH) + 1;
//                    day = cal.get(Calendar.DATE);
//                    year = cal.get(Calendar.YEAR);
//                }
//                PreparedStatement ps = c.prepareStatement("SELECT a.employeeID, concat(b.firstName, ' ', b.lastName) as 'Name', a.type, a.logDetails, a.logDate FROM user_logs a, users b "+
//                         "where a.employeeID = b.employeeID and month(a.logDate) = ? and day(a.logDate) = ? and year(a.logDate)=? ORDER BY a.logDate DESC");// 
//                ps.setInt(1, month);
//                ps.setInt(2, day);
//                ps.setInt(3, year);
//                ResultSet rs = ps.executeQuery();
//                while(rs.next()){
//                    UserLogs ul = new UserLogs();
//                    ul.setEmployeeID(rs.getInt(1));
//                    ul.setEmployeeName(rs.getString(2));
//                    ul.setType(rs.getString(3));
//                    ul.setLogDetails(rs.getString(4));
//                    ul.setLogDate(rs.getTimestamp(5).toString());
//                    logs.add(ul);
//                }
//            }
//            else{
//                int startMonth = 0;
//                int startDay = 0;
//                int startYear = 0;
//                int endMonth = 0;
//                int endDay = 0;
//                int endYear = 0;
//
//                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                Date dateStart = format.parse(startDate);
//                Date dateEnd = format.parse(endDate);
//                cal.setTime(dateStart);
//                cal2.setTime(dateEnd);
//                startMonth = cal.get(Calendar.MONTH) + 1;
//                startDay = cal.get(Calendar.DATE);
//                startYear = cal.get(Calendar.YEAR);
//                endMonth = cal2.get(Calendar.MONTH) + 1;
//                endDay = cal2.get(Calendar.DATE);
//                endYear = cal2.get(Calendar.YEAR);
//
//                PreparedStatement ps = c.prepareStatement("SELECT a.employeeID, concat(b.firstName, ' ', b.lastName) as 'Name', a.type, a.logDetails, a.logDate"+
//                        " FROM user_logs a, users b where a.employeeID = b.employeeID and a.logDate BETWEEN '" + startDate + "' AND '" + endDate + "'  ORDER BY a.logDate DESC");
//                
//                ResultSet rs = ps.executeQuery();
//                while(rs.next()){
//                    UserLogs ul = new UserLogs();
//                    ul.setEmployeeID(rs.getInt(1));
//                    ul.setEmployeeName(rs.getString(2));
//                    ul.setType(rs.getString(3));
//                    ul.setLogDetails(rs.getString(4));
//                    ul.setLogDate(rs.getTimestamp(5).toString());
//                    logs.add(ul);
//                }
//            }
//        }
//        c.close();
//        return logs;
//    }
    public static void setUserLogStatus(int employeeID, String type, String logDetails) throws ClassNotFoundException, SQLException, ParseException{
        Timestamp dateToday = DB.getDateToday();
        //java.sql.Date.
        //;
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("INSERT into user_logs(employeeID, type, logDetails, logDate) VALUES (?,?,?,?)");
        ps.setInt(1, employeeID);
        ps.setString(2, type);
        ps.setString(3, logDetails);
        ps.setTimestamp(4, dateToday);

        int rows = ps.executeUpdate();
        c.close();
    }
    
    public static String setHoliday(int employeeID, Date holiday) throws ClassNotFoundException, SQLException{
        //Calendar date = convertDate(holiday);
        Calendar calendar = Calendar.getInstance();

        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("INSERT INTO holiday (employeeID, holidayDate, appliedDate) VALUES (?,?,?)");
        ps.setInt(1, employeeID);
        ps.setDate(2, new java.sql.Date(convertDate(holiday)));
        ps.setDate(3, new java.sql.Date(getCurrentCalendar()));
        int rows = ps.executeUpdate();
        c.close();
       
        if(rows > 0){
            return "Successful";
        }
        else{
            return "Failed";
        }
    }
    
    public static String setAbsentDate(int employeeID, Date absenceDate, String reason) throws ClassNotFoundException, SQLException{
        //Calendar date = convertDate(holiday);
        Calendar calendar = Calendar.getInstance();
        
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("INSERT INTO absence (employeeID, absenceDate, reason, appliedDate) VALUES (?,?,?,?)");
        ps.setInt(1, employeeID);
        ps.setDate(2, new java.sql.Date(convertDate(absenceDate)));
        ps.setString(3, reason);
        ps.setDate(4, new java.sql.Date(getCurrentCalendar()));
        int rows = ps.executeUpdate();
        c.close();
       
        if(rows > 0){
            return "Successful";
        }
        else{
            return "Failed";
        }
    }
    public static String setExtraCashPerEmployee(int employeeID, float amount, String action) throws ClassNotFoundException, SQLException{
  
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("INSERT INTO salary_extra (employeeID, type, amount, appliedDate) VALUES (?,?,?,?)");
        ps.setInt(1, employeeID);
        ps.setString(2, action);
        ps.setFloat(3, amount);
        ps.setDate(4, new java.sql.Date(getCurrentCalendar()));
        // execute insert SQL stetement
        int rows = ps.executeUpdate();
        c.close();
       
        if(rows > 0){
            return "Successful";
        }
        else{
            return "Failed";
        }
    }
    
    public static String setCashAdvancePerEmployee(int employeeID, float amount, String action) throws ClassNotFoundException, SQLException{
  
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("INSERT INTO salary_condition (employeeID, type, amount, appliedDate) VALUES (?,?,?,?)");
        ps.setInt(1, employeeID);
        ps.setString(2, action);
        ps.setFloat(3, amount);
        ps.setDate(4, new java.sql.Date(getCurrentCalendar()));
        // execute insert SQL stetement
        int rows = ps.executeUpdate();
        c.close();
       
        if(rows > 0){
            return "Successful";
        }
        else{
            return "Failed";
        }
    }
    
    public static List<Attendance> getAttendance() throws ClassNotFoundException, SQLException, ParseException{
        int month = 0;
        int day = 0;
        int year = 0;
        String sqlDate = "";
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.sql.Date(getCurrentCalendar()));
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DATE);
        year = cal.get(Calendar.YEAR);

        sqlDate = "month(a.logDate) = ? and day(a.logDate) = ? and year(a.logDate) =?";
        List<Attendance> attendanceList = new ArrayList<Attendance>();
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select a.employeeID, b.firstName, b.lastName, a.logDate, a.timeIn, a.timeOut, TIMESTAMPDIFF(HOUR, a.timeIn, a.timeOut) as Duration from " +
                "logs a, users b where " +  sqlDate +" and a.employeeID = b.employeeID;");
        ps.setInt(1, month);
        ps.setInt(2, day);
        ps.setInt(3, year);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Attendance attendance = new Attendance();
            attendance.setEmployeeID(rs.getInt(1));
            attendance.setFirstName(rs.getString(2));
            attendance.setLastName(rs.getString(3));
            attendance.setLogDate(rs.getTimestamp(4));
            attendance.setTimeIn(rs.getTimestamp(5));
            attendance.setTimeOut(rs.getTimestamp(6));
            attendance.setDuration(rs.getInt(7));
            
            attendanceList.add(attendance);
        }
        c.close();
        return attendanceList;
        
    }
    public static int getAllowance(int employeeID) throws ClassNotFoundException, SQLException{
        int totalCashAdvance = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.sql.Date(getCurrentCalendar()));
        
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int year = cal.get(Calendar.YEAR);
        int startDay = 0;
        int endDay = 0;
        Timestamp today = DB.getCurrentTimeStamp();
        if(month == 0){
            month = 12;
        }
        if(day <=14){
            startDay = 1;
            endDay = 14;
        }
        else if(day > 14){
            startDay = 14;
            endDay = 31;
        }
        else;
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select employeeID, amount from salary_extra where month(appliedDate) = ? and day(appliedDate) > ? and day(appliedDate) <= ? and year(appliedDate) = ? and employeeID = ? and type='Allowance'");
        ps.setInt(1, month);
        ps.setInt(2, startDay);
        ps.setInt(3, endDay);
        ps.setInt(4, year);
        ps.setInt(5, employeeID);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            totalCashAdvance = totalCashAdvance + rs.getInt(2);
        }
        c.close();
        return totalCashAdvance;
    }
    public static int getLoan(int employeeID) throws ClassNotFoundException, SQLException{
        int totalLoan = 0;
        Connection c = connect();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.sql.Date(getCurrentCalendar()));
        
        int month = cal.get(Calendar.MONTH) + 1 ;
        
        if(month == 0){
            month = 12;
        }
        PreparedStatement ps = c.prepareStatement("Select employeeID, amount loan from loan where month(appliedDate) = ? and employeeID = ?");
        ps.setInt(1, month);
        ps.setInt(2, employeeID);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            totalLoan =  totalLoan + rs.getInt(2);
        }
        c.close();
        return  totalLoan;
        
    }
    public static int getBonus(int employeeID) throws ClassNotFoundException, SQLException{
        int totalBonus = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.sql.Date(getCurrentCalendar()));
        
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int year = cal.get(Calendar.YEAR);
        int startDay = 0;
        int endDay = 0;
        Timestamp today = DB.getCurrentTimeStamp();
        if(month == 0){
            month = 12;
        }
        if(day <=14){
            startDay = 1;
            endDay = 14;
        }
        else if(day > 14){
            startDay = 14;
            endDay = 31;
        }
        else;
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select employeeID, amount from salary_extra where month(appliedDate) = ? and day(appliedDate) > ? and day(appliedDate) <= ? and year(appliedDate) = ? and employeeID = ? and type='Bonus'");
        ps.setInt(1, month);
        ps.setInt(2, startDay);
        ps.setInt(3, endDay);
        ps.setInt(4, year);
        ps.setInt(5, employeeID);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            totalBonus = totalBonus + rs.getInt(2);
        }
        c.close();
        return totalBonus;
    }
    
    public static int getLogs(int employeeID, String dateStart, String dateEnd) throws ClassNotFoundException, SQLException, ParseException{
        Connection c = connect();
        int total = 0;
//        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
//        SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        //Wed Nov 30 00:00:00 CST 2016
//        Date startDate = formatter.parse(dateStart);
//        String parsedStartDate = outputFormatter.format(startDate);
//        
//        Date endDate = formatter.parse(dateEnd);
//        String parsedEndDate = outputFormatter.format(endDate);

        PreparedStatement ps = c.prepareStatement("SELECT count(logDate)"+
                " FROM dsl.time_logs where employeeID = ?;"); // and date BETWEEN '" + parsedStartDate +"' AND '"+ parsedEndDate +"'
         ps.setInt(1, employeeID);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             total = rs.getInt(1);
         }

        
        c.close();
        return total;
    }
    public static String determineUserRole(byte[] fingerPrintImage) throws ClassNotFoundException, SQLException, UareUException{
        String role = "";
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select employeeID as 'EmployeeID', firstName as 'FirstName', lastName as 'LastName', fingerPrint, role from users");
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            byte[] fingerPrint  = rs.getBytes(4);
            Engine engine = UareUGlobal.GetEngine();
        
            Fmd fmd1 = UareUGlobal.GetImporter().ImportFmd(fingerPrint, Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);
            Fmd fmd2 = UareUGlobal.GetImporter().ImportFmd(fingerPrintImage, Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);
            
            int falsematch_rate = engine.Compare(fmd1, 0, fmd2, 0);
            int target_falsematch_rate = Engine.PROBABILITY_ONE / 100000; //target rate is 0.00001
            
            if(falsematch_rate < target_falsematch_rate){
                role = rs.getString(5);
            }
        }
        c.close();
        return role;
    }
    
//    public static User getUserDetails(int employeeID) throws ClassNotFoundException, SQLException{
//        Connection c = connect();
//        PreparedStatement ps = c.prepareStatement("Select employeeID, firstName, lastName, address, telephoneNumber, mobileNumber, rate, timeIn, timeOut, role, fingerPrint," +  
//                "SSSNumber, philHealthNumber, tinNumber, pagibigNumber, SSSDeduction" + 
//                ", philHealthDeduction, pagibigDeduction, taxDeduction, pages, password, noLates, noMemos, noAbsences from users where employeeID = ?");
//        ps.setInt(1, employeeID);
//        ResultSet rs = ps.executeQuery();
//        User user = new User();
//        while(rs.next()){
//            user.setEmployeeID(rs.getInt(1));
//            user.setFirstName(rs.getString(2));
//            user.setLastName(rs.getString(3));
//            user.setAddress(rs.getString(4));
//            user.setMobileNumber(rs.getString(5));
//            user.setTelephoneNumber(rs.getString(6));
//            user.setRate(rs.getFloat(7));
//            user.setTimeIn(rs.getString(8));
//            user.setTimeOut(rs.getString(9));
//            user.setRole(rs.getString(10));
//            user.setFingerPrintImage(rs.getBytes(11));
//            user.setSSSNumber(rs.getString(12));
//            user.setPhilHealthNumber(rs.getString(13));
//            user.setTinNumber(rs.getString(14));
//            user.setPagibigNumber(rs.getString(15));
//            user.setSSSDeduction(rs.getFloat(16));
//            user.setPhilHealthDeduction(rs.getFloat(17));
//            user.setPagibigDeduction(rs.getFloat(18));
//            user.setTaxDeduction(rs.getFloat(19));
//            user.setPages(rs.getString(20));
//            user.setPassword(rs.getString(21));
//            user.setNoLates(rs.getInt(22));
//            user.setNoMemos(rs.getInt(23));
//            user.setNoAbsences(rs.getInt(24));
//        }
//        c.close();
//        return user;
//    }
    
    
    public static String determineDuplicateUser(byte[] fingerPrintImage) throws ClassNotFoundException, SQLException, UareUException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select * from users");
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            byte[] fingerPrint  = rs.getBytes(12);
            Engine engine = UareUGlobal.GetEngine();
            
            
            Fmd fmd1 = UareUGlobal.GetImporter().ImportFmd(fingerPrint, Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);
            Fmd fmd2 = UareUGlobal.GetImporter().ImportFmd(fingerPrintImage, Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);
            
            int falsematch_rate = engine.Compare(fmd1, 0, fmd2, 0);


            int target_falsematch_rate = Engine.PROBABILITY_ONE / 100000; //target rate is 0.00001
            
            if(falsematch_rate < target_falsematch_rate){
                return "Duplicate";
            }
        }
        return "Unique";
    }
    
    
    
    
    
    public static User alternativeLogin(int employeeID, String password) throws ClassNotFoundException, SQLException, UareUException, ParseException{
        String role = "";
        User user = new User();
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select employeeID as 'EmployeeID', firstName as 'FirstName', lastName as 'LastName'," + 
                " address as 'Address', telephoneNumber as 'TelephoneNumber', mobileNumber as 'MobileNumber', rate, timeIn, timeOut, role, fingerPrint,"+
                " password, pages, noLates, noMemos, noAbsences from users" + 
                " where employeeID = ? and password = ?");
        
        ps.setInt(1, employeeID);
        ps.setString(2, password);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
                user.setEmployeeID(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAddress(rs.getString(4));
                user.setTelephoneNumber(rs.getString(5));
                user.setMobileNumber(rs.getString(6));
                user.setRate(rs.getFloat(7));
                user.setTimeIn(rs.getString(8));
                user.setTimeOut(rs.getString(9));
                role = rs.getString(10);
                user.setRole(role);
                user.setFingerPrintImage(rs.getBytes(11));
                user.setPassword(rs.getString(12));
                user.setPages(rs.getString(13));
                user.setNoLates(rs.getInt(14));
                user.setNoMemos(rs.getInt(15));
                user.setNoAbsences(rs.getInt(16));
        }
        c.close();
        return user;
    }
}
