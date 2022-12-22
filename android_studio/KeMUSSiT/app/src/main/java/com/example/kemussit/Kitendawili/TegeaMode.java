package com.example.kemussit.Kitendawili;

public class TegeaMode {
    String user = null;
    String alrt = null;
    String reg_date = null;

    public TegeaMode(String user, String alrt, String reg_date) {
        this.user = user;
        this.alrt = alrt;
        this.reg_date = reg_date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAlrt() {
        return alrt;
    }

    public void setAlrt(String alrt) {
        this.alrt = alrt;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return user + " " + alrt + " " + reg_date;
    }
}
