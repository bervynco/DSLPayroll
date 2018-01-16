/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;
import controllers.DB;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Pattern;
import models.User;
import models.UserLogs;
import models.Notes;
import models.PayrollDetails;
import models.Reports;
import models.SalaryItems;
import models.Uploads;
/**
 *
 * @author L R E
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private static String currentMenu = "Employees";
    private static User sessionUser = null;
    //private static String[] employeePages;
    private static ArrayList<String>employeePages = new ArrayList<String>();
    private static int currentEmployeeFilter = 0;
    public void FillEmployeesComboBox() throws SQLException, ClassNotFoundException{
        List<User> employees = new ArrayList<User>();
        employees = DB.getUsers();
        comboBoxEmployees.addItem("No Filter");
        for(int i = 0; i < employees.size(); i++){
            String fullName = employees.get(i).getFirstName()+ " " + employees.get(i).getLastName();
            comboBoxEmployees.addItem(fullName);
        }
    }
    public DefaultTableModel FillTable(String currentMenu) throws SQLException, ClassNotFoundException, ParseException{
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);
        if(currentMenu.equals("Employees") || currentMenu.equals("Claim Salary")){
            List<User> employees = new ArrayList<User>();
            if(this.currentEmployeeFilter == 0){
                employees = DB.getUsers();
            }
            else{
                employees = DB.getUserDetails(this.currentEmployeeFilter);
            }

            model.addColumn("Employee ID");
            model.addColumn("Name");
            model.addColumn("Time In");
            model.addColumn("Time Out");
            model.addColumn("Rate");
            model.addColumn("Address");
            model.addColumn("Mobile Number");
            model.addColumn("SSS Number");
            model.addColumn("PhilHealth Number");
            model.addColumn("Pagibig Number");
            model.addColumn("Tin Number");
            model.addColumn("Role");


            // userCount = employees.size();
            for(int i = 0; i < employees.size(); i++){
                Object [] rowData = {employees.get(i).getEmployeeID(), employees.get(i).getFirstName() +" " + employees.get(i).getLastName(), employees.get(i).getTimeIn(), employees.get(i).getTimeOut(), 
                    employees.get(i).getRate(), employees.get(i).getAddress(), employees.get(i).getMobileNumber(), employees.get(i).getSSSNumber(), employees.get(i).getPhilHealthNumber()
                    ,employees.get(i).getPagibigNumber(), employees.get(i).getTinNumber(), employees.get(i).getRole()};
                model.addRow(rowData);
            }
        }
        else if(currentMenu.equals("System Logs")){
            int i = 0;
            List<UserLogs> logs = new ArrayList<UserLogs>();
            logs = DB.getSystemLogs(this.currentEmployeeFilter);
            model.addColumn("Employee ID");
            model.addColumn("Employee Name");
            model.addColumn("Access Type");
            model.addColumn("Access Location");
            model.addColumn("Access Time");

            if(logs != null){
                for(i = 0; i < logs.size(); i++){
                    Object [] rowData = {logs.get(i).getEmployeeID(), logs.get(i).getEmployeeName(), logs.get(i).getType(), logs.get(i).getLogDetails(), 
                        logs.get(i).getLogDate()};
                    model.addRow(rowData);
                }
            }
        }
        else if(currentMenu.equals("Payroll")){
            List<PayrollDetails> payrollDetails = new ArrayList<PayrollDetails>();
            Calendar cal = Calendar.getInstance();
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            payrollDetails = DB.getAllPayroll(month, year, 0);
        
            model.addColumn("Employee ID");
            model.addColumn("Name");
            model.addColumn("Rate");
            model.addColumn("SSS Deduction");
            model.addColumn("Pagibig Deduction");
            model.addColumn("PhilHealth Deduction");
            model.addColumn("Bonus");
            model.addColumn("Cash Advance");
            model.addColumn("Loan");
            model.addColumn("Days");
            model.addColumn("Overtime");
            model.addColumn("Tax Deduction");
            model.addColumn("Total Salary");
            
            for(int i = 0; i < payrollDetails.size(); i++){
                Object [] rowData = {payrollDetails.get(i).getEmployeeID(), 
                    payrollDetails.get(i).getEmployeeName(), 
                    payrollDetails.get(i).getRate(), 
                    payrollDetails.get(i).getSssDeduction(), 
                    payrollDetails.get(i).getPagibigDeduction(),
                    payrollDetails.get(i).getPhilHealthDeduction(),
                    payrollDetails.get(i).getBonus(),
                    payrollDetails.get(i).getCashAdvance(), 
                    payrollDetails.get(i).getLoan(), 
                    payrollDetails.get(i).getDays(), 
                    payrollDetails.get(i).getOverTime(), 
                    payrollDetails.get(i).getTaxDeduction(), 
                    payrollDetails.get(i).getTotalSalary()};
                model.addRow(rowData);
            }
        }
        
        else if(currentMenu.equals("Notes")){
            List<Notes> notes = new ArrayList<Notes>();
            notes = DB.getAllNotes();

            model.addColumn("Note ID");
            model.addColumn("Employee ID");
            model.addColumn("Name");
            model.addColumn("Notes");
            model.addColumn("Note Date");
            int notesCount = notes.size();
            for(int i = 0; i < notesCount; i++){
                Object [] rowData = {notes.get(i).getNoteID(), notes.get(i).getEmployeeID(), notes.get(i).getFirstName() +" " + notes.get(i).getLastName(), notes.get(i).getNote(), notes.get(i).getNoteDate()};
                model.addRow(rowData);
            }
        }
       
        else if(currentMenu.equals("Reports")){
            List<Reports> reports = new ArrayList<Reports>();
            reports = DB.getAllReports();
            model.addColumn("Employee ID");
            model.addColumn("Employee Name");
            model.addColumn("File Name");
            model.addColumn("File Path");
            model.addColumn("Generated Date");
            
            for(int i = 0; i < reports.size(); i++){
                Object [] rowData = {
                    reports.get(i).getEmployeeID(), 
                    reports.get(i).getEmployeeName(), 
                    reports.get(i).getFileName(),
                    reports.get(i).getFilePath(), 
                    reports.get(i).getGeneratedDate()
                };
                model.addRow(rowData);
            }
        }
        
        else if(currentMenu.equals("Salary Conditions")){
            List<SalaryItems> items = new ArrayList<SalaryItems>();
            items = DB.getAllSalaryItems();
            model.addColumn("ID");
            model.addColumn("Employee ID");
            model.addColumn("Employee Name");
            model.addColumn("Type");
            model.addColumn("Amount");
            model.addColumn("Claim Date");
            model.addColumn("Condition Type");
            
            for(int i = 0; i < items.size(); i++){
                Object [] rowData = {
                    items.get(i).getId(),
                    items.get(i).getEmployeeID(),
                    items.get(i).getEmployeeName(),
                    items.get(i).getType(),
                    items.get(i).getAmount(),
                    items.get(i).getClaimDate(),
                    items.get(i).getConditionType()
                };
                model.addRow(rowData);
            }
        }
        else if(currentMenu.equals("Upload Document")){
            List<Uploads> uploads = new ArrayList<Uploads>();
            uploads = DB.getAllUploadedDocuments();
            model.addColumn("Employee ID");
            model.addColumn("Employee Name");
            model.addColumn("Upload Type");
            model.addColumn("File Path");
            model.addColumn("Upload Date");
            
            for(int i = 0; i < uploads.size(); i++){
                Object [] rowData = {uploads.get(i).getEmployeeID(), uploads.get(i).getFirstName() +" " + uploads.get(i).getLastName(), uploads.get(i).getUploadType(),
                    uploads.get(i).getFilePath(), uploads.get(i).getUploadDate()};
                model.addRow(rowData);
            }
        }
        return model;
    }
    
    public void filterView(int employeeID, User user) throws ClassNotFoundException, SQLException{
        menuEmployee.setVisible(true);
        menuClaimSalary.setVisible(false);
        menuReports.setVisible(false);
        menuPayrollOverview.setVisible(false);
        menuClaimSalary.setVisible(false);
        menuSystemLogs.setVisible(false);
        menuNotes.setVisible(false);
        if(user.getRole().equals("Administrator")){
            menuEmployee.setVisible(true);
            menuClaimSalary.setVisible(true);
            menuReports.setVisible(true);
            menuPayrollOverview.setVisible(true);
            menuClaimSalary.setVisible(true);
            menuSystemLogs.setVisible(true);
            menuNotes.setVisible(true);
        }
        else if(user.getRole().equals("Payroll")){
            menuPayrollOverview.setVisible(true);
        }
        else if(user.getRole().equals("Co-Administrator")){
            String[] pages = user.getPages().replaceFirst("^\\[", "").replaceFirst("\\]$", "").split(", ");
            for(int i = 0; i < pages.length; i++){
                if(pages[i].equals("Claim Salary") || pages[i].equals("Add Employee") || pages[i].equals("Edit Employee") || pages[i].equals("Delete Employee")){
                    
                    pages[i] = pages[i].replaceAll("\\s+","");
                    if(pages[i].equals("AddEmployee") ||pages[i].equals("EditEmployee") || pages[i].equals("DeleteEmployee")){
                        employeePages.add(pages[i]);
                    }
                }
                if(Pattern.compile(Pattern.quote(pages[i]), Pattern.CASE_INSENSITIVE).matcher("btnEmployee").find()){
                    menuEmployee.setVisible(true);
                }
                else if(Pattern.compile(Pattern.quote(pages[i]), Pattern.CASE_INSENSITIVE).matcher("btnClaimSalary").find()){
                    menuClaimSalary.setVisible(true);
                }
                else if(Pattern.compile(Pattern.quote(pages[i]), Pattern.CASE_INSENSITIVE).matcher("btnReports").find()){
                    menuReports.setVisible(true);
                }
                else if(Pattern.compile(Pattern.quote(pages[i]), Pattern.CASE_INSENSITIVE).matcher("btnPayroll").find()){
                    menuPayrollOverview.setVisible(true);
                }
                else if(Pattern.compile(Pattern.quote(pages[i]), Pattern.CASE_INSENSITIVE).matcher("btnNotes").find()){
                    menuNotes.setVisible(true);
                }
                else if(Pattern.compile(Pattern.quote(pages[i]), Pattern.CASE_INSENSITIVE).matcher("btnUpload").find()){
                    //btnUpload.setVisible(true);
                }
                else;

            }
        }
        else;
    }
    
    public Main(User user, String currentMenu) throws SQLException, ClassNotFoundException, ParseException{
        initComponents();
        this.sessionUser = user;
        this.currentMenu = currentMenu;
        DefaultTableModel model = this.FillTable(this.currentMenu);
        tableList.setModel(model);
        this.FillEmployeesComboBox();
        this.filterView(user.getEmployeeID(), user);
        employeeName.setText(this.sessionUser.getFirstName() + " " + this.sessionUser.getLastName());
        btnEdit.setVisible(false);
        btnAdd.setVisible(true);
        btnDelete.setVisible(false);
    }
    public Main(User user, ArrayList<String> employeePages, String currentMenu) throws SQLException, ClassNotFoundException, ParseException {
        initComponents();
        this.sessionUser = user;
        this.employeePages = employeePages;
        this.currentMenu = currentMenu;
        DefaultTableModel model = this.FillTable(this.currentMenu);
        tableList.setModel(model);
        this.FillEmployeesComboBox();
        employeeName.setText(this.sessionUser.getFirstName() + " " + this.sessionUser.getLastName());
        btnEdit.setVisible(false);
        btnAdd.setVisible(true);
        btnDelete.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAdd = new java.awt.Button();
        btnEdit = new java.awt.Button();
        btnDelete = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new javax.swing.JTable();
        lblTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        employeeName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        comboBoxEmployees = new javax.swing.JComboBox<>();
        btnExport = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuEmployee = new javax.swing.JMenu();
        menuPayrollOverview = new javax.swing.JMenu();
        menuClaimSalary = new javax.swing.JMenu();
        menuUpload = new javax.swing.JMenu();
        menuNotes = new javax.swing.JMenu();
        menuReports = new javax.swing.JMenu();
        menuSystemLogs = new javax.swing.JMenu();
        menuLogout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAdd.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnAdd.setLabel("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnEdit.setLabel("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 51, 51));
        btnDelete.setLabel("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tableList.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tableList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableList.setRowHeight(25);
        tableList.setRowMargin(5);
        tableList.setSelectionBackground(new java.awt.Color(173, 216, 230));
        tableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableList);

        lblTitle.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblTitle.setText("List of Employees");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel2.setText("Welcome, ");

        employeeName.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        employeeName.setForeground(new java.awt.Color(1, 169, 130));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Filter:");

        comboBoxEmployees.setEditable(true);
        comboBoxEmployees.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboBoxEmployees.setModel(new javax.swing.DefaultComboBoxModel<>());
        comboBoxEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxEmployeesActionPerformed(evt);
            }
        });

        btnExport.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnExport.setText("Export");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        menuEmployee.setText("Employee");
        menuEmployee.setToolTipText("");
        menuEmployee.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuEmployeeMouseClicked(evt);
            }
        });
        menuEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEmployeeActionPerformed(evt);
            }
        });
        jMenuBar1.add(menuEmployee);

        menuPayrollOverview.setText("Payroll");
        menuPayrollOverview.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuPayrollOverview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPayrollOverviewMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuPayrollOverview);

        menuClaimSalary.setText("Salary Conditions");
        menuClaimSalary.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuClaimSalary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuClaimSalaryMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuClaimSalary);

        menuUpload.setText("Upload Document");
        menuUpload.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuUpload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuUploadMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuUpload);

        menuNotes.setText("Notes");
        menuNotes.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuNotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuNotesMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuNotes);

        menuReports.setText("Reports");
        menuReports.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuReports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuReportsMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuReports);

        menuSystemLogs.setText("System Logs");
        menuSystemLogs.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuSystemLogs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSystemLogsMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuSystemLogs);

        menuLogout.setText("Log Out");
        menuLogout.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        menuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLogoutMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuLogout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(comboBoxEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(employeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(employeeName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(comboBoxEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        //ArrayList<String> employeePages = new ArrayList<String>();
        if(this.currentMenu.equals("Employees")){
            try {
                AddEmployee add = new AddEmployee(this.sessionUser, this.employeePages);
                add.setTitle("DSL Time Logging | Employees");
                add.pack();
                add.setLocationRelativeTo(null);
                add.setDefaultCloseOperation(0);
                add.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(this.currentMenu.equals("Salary Conditions")){
            try {
                SalaryConditionView condition = new SalaryConditionView(this.sessionUser, employeePages);
                condition.setTitle("DSL Time Logging | Salary Condition");
                condition.pack();
                condition.setLocationRelativeTo(null);
                condition.setDefaultCloseOperation(0);
                condition.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(this.currentMenu.equals("Upload Document")){
            try {
                
                UploadDocuments upload = new UploadDocuments(this.sessionUser, employeePages);
                upload.setTitle("DSL Time Logging | Upload Documents");
                upload.pack();
                upload.setLocationRelativeTo(null);
                upload.setDefaultCloseOperation(0);
                upload.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(this.currentMenu.equals("Notes")){
            try {
                AddNote add = new AddNote(this.sessionUser, this.employeePages);
                add.setTitle("DSL Time Logging | Add Notes");
                add.pack();
                add.setLocationRelativeTo(null);
                add.setDefaultCloseOperation(0);
                add.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(this.currentMenu.equals("Reports")){
            try {
                FilterReports add = new FilterReports(this.sessionUser, employeePages);
                add.setTitle("DSL Time Logging | Generate Report");
                add.pack();
                add.setLocationRelativeTo(null);
                add.setDefaultCloseOperation(0);
                add.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else if(this.currentMenu == "Payroll"){
            try {
                this.setVisible(true);
                File selectedFile = null;
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                }
                
                ExcelParser parser = new ExcelParser(this.sessionUser, employeePages);
                parser.parseExcel(selectedFile);
                DefaultTableModel model = this.FillTable(this.currentMenu);
                tableList.setModel(model);
                
                
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else;
    }//GEN-LAST:event_btnAddActionPerformed

    private void menuEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEmployeeActionPerformed
        
    }//GEN-LAST:event_menuEmployeeActionPerformed

    private void menuEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuEmployeeMouseClicked
        try {
            // TODO add your handling code here:
            this.currentMenu = "Employees";
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
            btnAdd.setVisible(true);
            btnAdd.setLabel("Add");
            btnExport.setVisible(true);
            lblTitle.setText("List of Employees");
            DefaultTableModel model = this.FillTable(this.currentMenu);
            tableList.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuEmployeeMouseClicked

    private void menuPayrollOverviewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPayrollOverviewMouseClicked
        try {
            // TODO add your handling code here:
            this.currentMenu = "Payroll";
            btnEdit.setVisible(true);
            btnDelete.setVisible(false);
            btnAdd.setVisible(true);
            btnExport.setVisible(true);
            btnAdd.setLabel("Generate Payout");
            lblTitle.setText("List of Payroll");
            btnEdit.setLabel("View Archives");
            DefaultTableModel model = this.FillTable(this.currentMenu);
            tableList.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuPayrollOverviewMouseClicked

    private void menuClaimSalaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuClaimSalaryMouseClicked
        try {
            // TODO add your handling code here:
            this.currentMenu = "Salary Conditions";
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
            btnAdd.setVisible(true);
            btnAdd.setLabel("Add");
            btnExport.setVisible(false);
            lblTitle.setText("List of Salary Conditions");
            DefaultTableModel model = this.FillTable(this.currentMenu);
            tableList.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuClaimSalaryMouseClicked

    private void menuUploadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUploadMouseClicked
        try {
            // TODO add your handling code here:
            this.currentMenu = "Upload Document";
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
            btnAdd.setVisible(true);
            btnExport.setVisible(false);
            btnAdd.setLabel("Upload");
            lblTitle.setText("List of Uploaded Documents");
            DefaultTableModel model = this.FillTable(this.currentMenu);
            tableList.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_menuUploadMouseClicked

    private void menuNotesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuNotesMouseClicked
        try {
            // TODO add your handling code here:
            this.currentMenu = "Notes";
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
            btnAdd.setVisible(true);
            btnExport.setVisible(false);
            btnAdd.setLabel("Add");
            lblTitle.setText("List of Notes");
            DefaultTableModel model = this.FillTable(this.currentMenu);
            tableList.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuNotesMouseClicked

    private void menuReportsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuReportsMouseClicked
        try {
            // TODO add your handling code here:
            this.currentMenu = "Reports";
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
            btnAdd.setVisible(true);
            btnAdd.setLabel("Generate");
            btnExport.setVisible(false);
            lblTitle.setText("List of Reports Generated");
            DefaultTableModel model = this.FillTable(this.currentMenu);
            tableList.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuReportsMouseClicked

    private void menuSystemLogsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSystemLogsMouseClicked
        try {
            // TODO add your handling code here:
            this.currentMenu = "System Logs";
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
            btnAdd.setVisible(false);
            btnExport.setVisible(false);
            lblTitle.setText("List of System Logs");
            DefaultTableModel model = this.FillTable(this.currentMenu);
            tableList.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuSystemLogsMouseClicked

    private void menuLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLogoutMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_menuLogoutMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if(this.currentMenu.equals("Employees")){
            try {
                ArrayList<String>employeePages = null;
                EditEmployee edit = new EditEmployee(this.sessionUser, employeePages);
                edit.setTitle("DSL Time Logging | Employees");
                edit.pack();
                edit.setLocationRelativeTo(null);
                edit.setDefaultCloseOperation(0);
                edit.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(this.currentMenu.equals("Claim Salary")){
            
        }
        else if(this.currentMenu.equals("Upload Document")){
            
        }
        else if(this.currentMenu.equals("Payroll")){
            try {
                ArrayList<String>employeePages = null;
                ViewArchives edit = new ViewArchives(this.sessionUser, employeePages);
                edit.setTitle("DSL Time Logging | View Archives");
                edit.pack();
                edit.setLocationRelativeTo(null);
                edit.setDefaultCloseOperation(0);
                edit.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(this.currentMenu.equals("Notes")){
//            try {
//                ViewNotes view = new ViewNotes(this.sessionUser, this.employeePages);
//                view.setTitle("DSL Time Logging | Add Notes");
//                view.pack();
//                view.setLocationRelativeTo(null);
//                view.setDefaultCloseOperation(0);
//                view.setVisible(true);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SQLException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ParseException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        
        else if(this.currentMenu.equals("Payroll")){
            
        }
        else;
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
//        if(this.currentMenu.equals("Employees")){
//            
//        }
//        else if(this.currentMenu.equals("Claim Salary")){
//            
//        }
//        else if(this.currentMenu.equals("Upload Document")){
//            
//        }
//        else if(this.currentMenu.equals("Notes")){
//            
//        }
//        else;
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void comboBoxEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxEmployeesActionPerformed
        try {
            // TODO add your handling code here:
            String employeeName = (String) comboBoxEmployees.getSelectedItem();
            if(employeeName.equals("No Filter")){
                this.currentEmployeeFilter = 0;
            }
            else{
                this.currentEmployeeFilter = DB.getEmployeeIDFromName(employeeName);
            }
            DefaultTableModel model = this.FillTable(this.currentMenu);
            tableList.setModel(model);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboBoxEmployeesActionPerformed

    private void tableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListMouseClicked
        // TODO add your handling code here:
        
        this.setVisible(false);
        ArrayList<String>employeePages = new ArrayList<String>();
        int row = tableList.rowAtPoint(evt.getPoint());
        if(this.currentMenu.equals("Employees")){
            try {
                
                EditEmployee editEmployee = new EditEmployee(this.sessionUser, employeePages);
                int employeeID = (int) tableList.getValueAt(row, 0);
                editEmployee.setEditableFields(employeeID);
                editEmployee.setTitle("DSL Time Logging | Edit Employee");
                editEmployee.pack();
                editEmployee.setLocationRelativeTo(null);
                editEmployee.setDefaultCloseOperation(0);
                editEmployee.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(this.currentMenu.equals("Notes")){
            try {
                int noteID = (int) tableList.getValueAt(row, 0);
                int employeeID = (int)tableList.getValueAt(row, 1);
                ViewNotes viewNotes = new ViewNotes(this.sessionUser, employeePages, employeeID, noteID);
                viewNotes.setNoteFields(employeeID, noteID);
                viewNotes.setTitle("DSL Time Logging | View Notes");
                viewNotes.pack();
                viewNotes.setLocationRelativeTo(null);
                viewNotes.setDefaultCloseOperation(0);
                viewNotes.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(this.currentMenu.equals("Payroll")){
            try {
                int employeeID = (int)tableList.getValueAt(row,0);
                String name = (String)tableList.getValueAt(row,1);
                
                SalaryClaim salary = new SalaryClaim(this.sessionUser, employeePages);
                salary.fillFields(employeeID, name);
                salary.setTitle("DSL Time Logging | Claim Salary");
                salary.pack();
                salary.setLocationRelativeTo(null);
                salary.setDefaultCloseOperation(0);
                salary.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(this.currentMenu.equals("Salary Conditions")){
            int id = (int) tableList.getValueAt(row, 0);
            int employeeID = (int) tableList.getValueAt(row,1);
            String type = (String) tableList.getValueAt(row,3);
            String name = (String) tableList.getValueAt(row,2);
            String conditionType = (String) tableList.getValueAt(row, 6);
            
            
            if(conditionType.equals("Deductables")){
                try {
                    Loan loan = new Loan(this.sessionUser, this.employeePages);
                    loan.fillFields(id, employeeID, name, type);
                    loan.setTitle("DSL Time Logging | Salary Condition");
                    loan.pack();
                    loan.setLocationRelativeTo(null);
                    loan.setDefaultCloseOperation(0);
                    loan.setVisible(true);
//                if(type.equals("Loan")){
//                    
//                }
//                else if(type.equals("Cash Advance")){
//                    
//                }
//                else;
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(conditionType.equals("Extra")){
                try {
                    Allowance allowance = new Allowance(this.sessionUser, this.employeePages);
                    allowance.fillFields(id, employeeID, name, type);
                    allowance.setTitle("DSL Time Logging | Allowance");
                    allowance.pack();
                    allowance.setLocationRelativeTo(null);
                    allowance.setDefaultCloseOperation(0);
                    allowance.setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            }
        }
        else;
    }//GEN-LAST:event_tableListMouseClicked

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        // TODO add your handling code here:
        if(this.currentMenu.equals("Employees") || this.currentMenu.equals("Payroll")){
            try {
                GenerateExcel generate = new GenerateExcel(this.sessionUser);
                generate.generateExcel(this.currentMenu);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else;
    }//GEN-LAST:event_btnExportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnAdd;
    private java.awt.Button btnDelete;
    private java.awt.Button btnEdit;
    private javax.swing.JButton btnExport;
    private javax.swing.JComboBox<String> comboBoxEmployees;
    private javax.swing.JLabel employeeName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JMenu menuClaimSalary;
    private javax.swing.JMenu menuEmployee;
    private javax.swing.JMenu menuLogout;
    private javax.swing.JMenu menuNotes;
    private javax.swing.JMenu menuPayrollOverview;
    private javax.swing.JMenu menuReports;
    private javax.swing.JMenu menuSystemLogs;
    private javax.swing.JMenu menuUpload;
    private javax.swing.JTable tableList;
    // End of variables declaration//GEN-END:variables
}
