package com.example.kemussit.Kitendawili;

public class BomoaMode {
    String idi = null;
    String ses = null;
    String ended = null;
    String pay = null;
    String deter = null;
    String category = null;
    String reg_no = null;
    String fullname = null;
    String phone = null;
    String profile = null;
    String signature = null;
    String department = null;
    String classification = null;
    String email = null;
    String finsta = null;
    String secsta = null;
    String lost = null;
    String date = null;

    public BomoaMode(String idi, String ses, String ended, String pay, String deter, String category, String reg_no, String fullname, String phone, String profile, String signature, String department, String classification, String email, String finsta, String secsta, String lost, String date) {
        this.idi = idi;
        this.ses = ses;
        this.ended = ended;
        this.pay = pay;
        this.deter = deter;
        this.category = category;
        this.reg_no = reg_no;
        this.fullname = fullname;
        this.phone = phone;
        this.profile = profile;
        this.signature = signature;
        this.department = department;
        this.classification = classification;
        this.email = email;
        this.finsta = finsta;
        this.secsta = secsta;
        this.lost = lost;
        this.date = date;
    }

    public String getIdi() {
        return idi;
    }

    public void setIdi(String idi) {
        this.idi = idi;
    }

    public String getSes() {
        return ses;
    }

    public void setSes(String ses) {
        this.ses = ses;
    }

    public String getEnded() {
        return ended;
    }

    public void setEnded(String ended) {
        this.ended = ended;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFinsta() {
        return finsta;
    }

    public void setFinsta(String finsta) {
        this.finsta = finsta;
    }

    public String getSecsta() {
        return secsta;
    }

    public void setSecsta(String secsta) {
        this.secsta = secsta;
    }

    public String getLost() {
        return lost;
    }

    public void setLost(String lost) {
        this.lost = lost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return idi + " " + deter + " " + secsta + " " + ses + " " + finsta + " " + reg_no + " " + fullname + " " + ended + " " + category + " " + phone + " " + department + " " + classification + " " + email + " " + pay;
    }
}
