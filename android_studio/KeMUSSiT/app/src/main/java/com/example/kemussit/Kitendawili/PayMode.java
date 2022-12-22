package com.example.kemussit.Kitendawili;

public class PayMode {
    String pay = null;
    String deter = null;
    String ses = null;
    String term = null;
    String year = null;
    String mpesa = null;
    String fees = null;
    String reg_no = null;
    String fullname = null;
    String phone = null;
    String status = null;
    String expiry = null;
    String remarks = null;
    String date = null;

    public PayMode(String pay, String deter, String ses, String term, String year, String mpesa, String fees, String reg_no, String fullname, String phone, String status, String expiry, String remarks, String date) {
        this.pay = pay;
        this.deter = deter;
        this.ses = ses;
        this.term = term;
        this.year = year;
        this.mpesa = mpesa;
        this.fees = fees;
        this.reg_no = reg_no;
        this.fullname = fullname;
        this.phone = phone;
        this.status = status;
        this.expiry = expiry;
        this.remarks = remarks;
        this.date = date;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getDeter() {
        return deter;
    }

    public void setDeter(String deter) {
        this.deter = deter;
    }

    public String getSes() {
        return ses;
    }

    public void setSes(String ses) {
        this.ses = ses;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMpesa() {
        return mpesa;
    }

    public void setMpesa(String mpesa) {
        this.mpesa = mpesa;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return ses + " " + deter + " " + term + " " + year + " " + pay + " " + fullname + " " + fees + " " + reg_no + " " + date + " " + status + " " + expiry + " " + phone + " " + mpesa;
    }
}
