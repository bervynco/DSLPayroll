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
public class User {
    private int employeeID; 
    private String firstName;
    private String lastName;
    private String password;
    private String mobileNumber;
    private String telephoneNumber;
    private String address;
    private float rate;
    private String timeIn;
    private String timeOut;
    private String role;
    private String SSSNumber;
    private String philHealthNumber;
    private String pagibigNumber;
    private String tinNumber;
    private float SSSDeduction;
    private float pagibigDeduction;
    private float philHealthDeduction;
    private float taxDeduction;
    private static byte[] fingerPrintImage;
    private String pages;
    private int noLates;
    private int noMemos;
    private int noAbsences;
    
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSSSNumber() {
        return SSSNumber;
    }

    public void setSSSNumber(String SSSNumber) {
        this.SSSNumber = SSSNumber;
    }

    public String getPhilHealthNumber() {
        return philHealthNumber;
    }

    public String getPagibigNumber() {
        return pagibigNumber;
    }

    public void setPagibigNumber(String pagibigNumber) {
        this.pagibigNumber = pagibigNumber;
    }
    
    public void setPhilHealthNumber(String philHealthNumber) {
        this.philHealthNumber = philHealthNumber;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public void setTinNumber(String tinNumber) {
        this.tinNumber = tinNumber;
    }

    public float getSSSDeduction() {
        return SSSDeduction;
    }

    public void setSSSDeduction(float SSSDeduction) {
        this.SSSDeduction = SSSDeduction;
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

    public float getTaxDeduction() {
        return taxDeduction;
    }

    public void setTaxDeduction(float taxDeduction) {
        this.taxDeduction = taxDeduction;
    }
    
    public static byte[] getFingerPrintImage() {
        return fingerPrintImage;
    }

    public static void setFingerPrintImage(byte[] fingerPrintImage) {
        User.fingerPrintImage = fingerPrintImage;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public int getNoLates() {
        return noLates;
    }

    public void setNoLates(int noLates) {
        this.noLates = noLates;
    }

    public int getNoMemos() {
        return noMemos;
    }

    public void setNoMemos(int noMemos) {
        this.noMemos = noMemos;
    }

    public int getNoAbsences() {
        return noAbsences;
    }

    public void setNoAbsences(int noAbsences) {
        this.noAbsences = noAbsences;
    }
    
    
    
}
