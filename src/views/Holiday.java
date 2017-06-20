package views;

import controllers.DB;
import java.awt.Color;
import java.awt.Font;
import static java.awt.font.TextAttribute.FONT;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bendrhick
 */
public class Holiday extends javax.swing.JFrame {

    /**
     * Creates new form Holiday
     */
    private static User sessionUser = null;
    private final JPanel panel = new JPanel();
    private static ArrayList<String> employeePages = new ArrayList<String>();
    public Holiday(User user, ArrayList<String> employeePages) throws ClassNotFoundException, SQLException, ParseException {
        initComponents();
        this.sessionUser = user;
        this.employeePages = employeePages;
         DB.setUserLogStatus(user.getEmployeeID(),"Page Visit", "Holiday Page");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        holidayDate = new com.github.lgooddatepicker.components.DatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        label1.setText("Holdiay Date");

        label2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label2.setText("Date:");

        btnSave.setText("Save");
        btnSave.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(holidayDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(btnCancel)
                                .addGap(48, 48, 48)
                                .addComponent(btnSave)))
                        .addGap(0, 38, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(holidayDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnSave))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        Calendar cal = Calendar.getInstance();
        String holiday = holidayDate.getDateStringOrEmptyString();
        boolean error = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            //if not valid, it will throw ParseException
            Date date = sdf.parse(holiday);
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            System.out.println(year);
            if (year > 1950 && year < 2050){
                String status = DB.setHoliday(this.sessionUser.getEmployeeID(), date);
                
                if(status != "Successful"){
                    JOptionPane.showMessageDialog(panel, "System Error. Please contact Administrator", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    DB.setUserLogStatus(sessionUser.getEmployeeID(),"Save", "Add New Holiday");
                    JOptionPane.showMessageDialog(panel, "Add Holiday Completed", "Success", JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    SalaryCondition condition = new SalaryCondition(this.sessionUser, this.employeePages);
                    condition.setTitle("DSL Time Logging | Salary Condition");
                    condition.pack();
                    condition.setLocationRelativeTo(null);
                    condition.setDefaultCloseOperation(0);
                    condition.setVisible(true);
                }
            }else{
                error = true;
            }
            //System.out.println(date);

        } catch (ParseException e) {
            e.printStackTrace();
            error = true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Holiday.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Holiday.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(error == true){
            JOptionPane.showMessageDialog(panel, "Date Not parseable", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        try {
            // TODO add your handling code here:
            this.setVisible(false);
            SalaryCondition condition = new SalaryCondition(this.sessionUser, this.employeePages);
            condition.setTitle("DSL Time Logging | Salary Condition");
            condition.pack();
            condition.setLocationRelativeTo(null);
            condition.setDefaultCloseOperation(0);
            condition.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Holiday.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Holiday.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Holiday.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private com.github.lgooddatepicker.components.DatePicker holidayDate;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables
}