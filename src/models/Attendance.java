/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author L R E
 */
public class Attendance {
    private int employeeID;
    private String employeeName;
    private String firstName;
    private String lastName;
    private Timestamp logDate;
    private Timestamp timeIn;
    private Timestamp timeOut;
    private Timestamp timeInDate;
    private Timestamp timeOutDate;
    
    private String timeInDateString;
    private String timeOutDateString;
    
    private int duration;

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    /* REPORT Generating */
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public Timestamp getTimeInDate() {
        return timeInDate;
    }

    public void setTimeInDate(Timestamp timeInDate) {
        this.timeInDate = timeInDate;
    }

    public Timestamp getTimeOutDate() {
        return timeOutDate;
    }

    public void setTimeOutDate(Timestamp timeOutDate) {
        this.timeOutDate = timeOutDate;
    }
    
     /* REPORT Generating */
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getLogDate() {
        return logDate;
    }

    public void setLogDate(Timestamp logDate) {
        this.logDate = logDate;
    }
    
    public Timestamp getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Timestamp timeIn) {
        this.timeIn = timeIn;
    }

    public Timestamp getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Timestamp timeOut) {
        this.timeOut = timeOut;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTimeInDateString() {
        return timeInDateString;
    }

    public void setTimeInDateString(String timeInDateString) {
        this.timeInDateString = timeInDateString;
    }

    public String getTimeOutDateString() {
        return timeOutDateString;
    }

    public void setTimeOutDateString(String timeOutDateString) {
        this.timeOutDateString = timeOutDateString;
    }

    
    
    
}
