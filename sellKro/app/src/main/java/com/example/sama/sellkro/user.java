package com.example.sama.sellkro;

import org.w3c.dom.Text;

/**
  * Created by sama on 3/27/2021.
 * 
 */
public class user {

int id;
String fname,lname,address,town,pass,cpass,joiningdate;
double mobile,pin;

    public user(int id, String fname) {
        this.id = id;
        this.fname = fname;
    }

    public user(int id, String fname,String town, double mobile,String joiningdate)
    {
        this.id = id;
        this.fname = fname;
        this.town = town;
        this.joiningdate = joiningdate;
        this.mobile = mobile;
    }

    public user(int id, String fname, String lname, String address, String town, String pass, String cpass, String joiningdate, double mobile, double pin) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.town = town;
        this.pass = pass;
        this.cpass = cpass;
        this.joiningdate = joiningdate;
        this.mobile = mobile;
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }

    public String getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(String joiningdate) {
        this.joiningdate = joiningdate;
    }

    public double getMobile() {
        return mobile;
    }

    public void setMobile(double mobile) {
        this.mobile = mobile;
    }

    public double getPin() {
        return pin;
    }

    public void setPin(double pin) {
        this.pin = pin;
    }
}
