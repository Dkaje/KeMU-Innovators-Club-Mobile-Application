package com.example.kemussit.Kitendawili;

public class IssuedMode {
    String iss = null;
    String idi = null;
    String ses = null;
    String reg_no = null;
    String fullname = null;
    String phone = null;
    String profile = null;
    String signature = null;
    String department = null;
    String classification = null;
    String issue_date = null;
    String expiry_date = null;
    String category = null;
    String status = null;
    String ended = null;
    String expiry = null;
    String entry_date = null;

    public IssuedMode(String iss, String idi, String ses, String reg_no, String fullname, String phone, String profile, String signature, String department, String classification, String issue_date, String expiry_date, String category, String status, String ended, String expiry, String entry_date) {
        this.iss = iss;
        this.idi = idi;
        this.ses = ses;
        this.reg_no = reg_no;
        this.fullname = fullname;
        this.phone = phone;
        this.profile = profile;
        this.signature = signature;
        this.department = department;
        this.classification = classification;
        this.issue_date = issue_date;
        this.expiry_date = expiry_date;
        this.category = category;
        this.status = status;
        this.ended = ended;
        this.expiry = expiry;
        this.entry_date = entry_date;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
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

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnded() {
        return ended;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    @Override
    public String toString() {
        return status + " " + expiry + " " + idi + " " + entry_date + " " + ses + " " + expiry_date + " " + reg_no + " " + fullname + " " + ended + " " + category + " " + phone + " " + department + " " + classification + " " + issue_date + " " + iss;
    }
}
