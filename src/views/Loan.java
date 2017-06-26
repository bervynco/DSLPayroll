package views;

import controllers.DB;
import java.awt.Color;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import models.User;
import models.SalaryCondition;

public class Loan extends javax.swing.JFrame {

    /**
     * Creates new form Loan
     */
    private static User sessionUser = null;
    private static List<User> employees = new ArrayList<User>();
    private static ArrayList<String> employeePages = new ArrayList<String>();
    public void FillComboBox() throws SQLException, ClassNotFoundException{
        employees = DB.getUsers();
        String [] employeeNames = null;
        for(int i = 0; i < employees.size(); i++){
            String name = employees.get(i).getFirstName() + " " + employees.get(i).getLastName();
            jComboBox2.addItem(name);
        }

    }
    public Loan(User user, ArrayList<String> employeePages) throws ClassNotFoundException, SQLException, ParseException {
        initComponents();
        this.sessionUser = user;
        this.employeePages = employeePages;
        lblStatus.setHorizontalAlignment(JLabel.CENTER);
        lblStatus.setForeground(Color.red);
        FillComboBox();
        DB.setUserLogStatus(user.getEmployeeID(),"Page Visit", "Loan");
    }
    public void fillFields(int conditionID, int employeeID, String name, String type) throws ClassNotFoundException, SQLException{
        SalaryCondition condition = new SalaryCondition();
        condition = DB.getSalaryCondition(conditionID, employeeID, "salary_condition");
        //jComboBox2.
        String [] dropdown = {"Loan", "Cash Advance"};
        txtAmount.setText(Float.toString(condition.getAmount()));
        for(int i = 0; i < employees.size(); i++){
            if(name.equals(employees.get(i).getFirstName() + " " + employees.get(i).getLastName())){
                jComboBox2.setSelectedIndex(i);
            }
        }
        for(int k = 0; k < dropdown.length; k++){
            if(dropdown[k].equals(type)){
                jComboBox1.setSelectedIndex(k);
            }
        }
        
        //txtAmount.setText(Float.toString(amount));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnGo = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtAmount = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Loan", "Cash Advance" }));

        jLabel1.setText("Action:");

        jLabel2.setText("Amount:");

        btnGo.setText("Submit");
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel3.setText("Employee:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(btnGo)
                        .addGap(52, 52, 52)
                        .addComponent(btnCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGo)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed
        try {                                      
            // TODO add your handling code here:
            String action = null;
            float amount = 0;
            action = (String) jComboBox1.getSelectedItem();
            amount = Float.parseFloat(txtAmount.getText());
            String name = (String) jComboBox2.getSelectedItem();
            int employeeID = DB.getEmployeeIDFromName(name);
            try {
                String status = DB.setCashAdvancePerEmployee(employeeID, amount, action);
                
                if(status.equals("Successful")){
                    JOptionPane.showMessageDialog(null, "Add Loan Successful");
                    this.setVisible(false);
                    SalaryConditionView condition = new SalaryConditionView(this.sessionUser, this.employeePages);
                    condition.setTitle("DSL Time Logging | Salary Condition");
                    condition.pack();
                    condition.setLocationRelativeTo(null);
                    condition.setDefaultCloseOperation(0);
                    condition.setVisible(true);
                    DB.setUserLogStatus(sessionUser.getEmployeeID(),"Save", "Save new Loan");
                }
                else{
                    System.out.println("ERROR");
                    lblStatus.setText("Error. Please contact system administrator");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnGoActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        try {
            // TODO add your handling code here:
            this.setVisible(false);
            SalaryConditionView condition = new SalaryConditionView(this.sessionUser, this.employeePages);
            condition.setTitle("DSL Time Logging | Salary Condition");
            condition.pack();
            condition.setLocationRelativeTo(null);
            condition.setDefaultCloseOperation(0);
            condition.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Loan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGo;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextField txtAmount;
    // End of variables declaration//GEN-END:variables
}
