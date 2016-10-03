package com.example.kathyxu.googlesheetsapi;

/**
 * Created by kathy on 29/09/2016.
 */
public class Student {

    private String fname;
    private String lname;
    private int id;
    private String zmail;
    private String tut;

    public Student(){

    }

    public Student(String fname, String lname, int id, String zmail, String tut) {
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.zmail = zmail;
        this.tut = tut;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZmail() {
        return zmail;
    }

    public void setZmail(String zmail) {
        this.zmail = zmail;
    }

    public String getTut() {
        return tut;
    }

    public void setTut(String tut) {
        this.tut = tut;
    }
}
