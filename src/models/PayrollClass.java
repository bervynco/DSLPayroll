/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author L R E
 */
public class PayrollClass {
    private int employeeID;
    private String firstName;
    private String lastName;
    private int bonusAmount;
    private int workingDays;
    private float sssDeduction;
    private float pagibigDeduction;
    private float philHealthDeduction;
    private float salary;

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(int bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public float getSssDeduction() {
        return sssDeduction;
    }

    public void setSssDeduction(float sssDeduction) {
        this.sssDeduction = sssDeduction;
    }

    public float getPagibigDeduction() {
        return pagibigDeduction;
    }

    public void setPagibigDeduction(float pagibigDeduction) {
        this.pagibigDeduction = pagibigDeduction;
    }

    public float getPhilHealthDeduction() {
        return philHealthDeduction;
    }

    public void setPhilHealthDeduction(float philHealthDeduction) {
        this.philHealthDeduction = philHealthDeduction;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
    
    
    
}
