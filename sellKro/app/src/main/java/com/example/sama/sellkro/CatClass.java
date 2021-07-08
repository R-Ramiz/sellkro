package com.example.sama.sellkro;

/**
 * Created by sama on 4/4/2021.
 */
public class CatClass
{
    int id;
    String Catname,Regdate;

    public CatClass(int id, String catname, String regdate) {
        this.id = id;
        this.Catname = catname;
        this.Regdate = regdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatname() {
        return Catname;
    }

    public void setCatname(String catname) {
        Catname = catname;
    }

    public String getRegdate() {
        return Regdate;
    }

    public void setRegdate(String regdate) {
        Regdate = regdate;
    }
}
