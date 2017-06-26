/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author L R E
 */
public class SalaryItems {
    //private int conditionID;
    //private int extraID;
    private int id;
    private int employeeID;
    private String employeeName;
    private String type;
    private float amount;
    private Date claimDate;
    private String conditionType;

//    public int getConditionID() {
//        return conditionID;
//    }
//
//    public void setConditionID(int conditionID) {
//        this.conditionID = conditionID;
//    }
//
//    public int getExtraID() {
//        return extraID;
//    }
//
//    public void setExtraID(int extraID) {
//        this.extraID = extraID;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }
    
    
    
}
