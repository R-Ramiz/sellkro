package com.example.sama.sellkro;

/**
 * Created by sama on 4/4/2021.
 */
public class admin
{
    int id;
    String fname;

    public admin(int id, String fname)
    {
        this.id = id;
        this.fname = fname;
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
}
