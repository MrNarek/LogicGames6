package com.example.logicgames;

import java.util.HashMap;
import java.util.Map;

public class HelperClass {
    String name, email, password, id;
    int mathRec;


    public int getmathRec() {
        return mathRec;
    }

    public void setmathRec(int mathRec) {
        this.mathRec = mathRec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HelperClass(String name, String email, String password, String id, int mathRec) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.mathRec = mathRec;
    }

    public HelperClass() {

    }



}
