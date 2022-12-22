package com.example.kemussit.Kitendawili;

public class CheckMode {
    String rep = null;
    String ses = null;
    String user_id = null;
    String username = null;
    String userphone = null;
    String term = null;
    String year = null;
    String report = null;
    String status = null;
    String entry_date = null;
    String ended = null;
    String ending = null;
    String end_date = null;

    public CheckMode(String rep, String ses, String user_id, String username, String userphone, String term, String year, String report, String status, String entry_date, String ended, String ending, String end_date) {
        this.rep = rep;
        this.ses = ses;
        this.user_id = user_id;
        this.username = username;
        this.userphone = userphone;
        this.term = term;
        this.year = year;
        this.report = report;
        this.status = status;
        this.entry_date = entry_date;
        this.ended = ended;
        this.ending = ending;
        this.end_date = end_date;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getSes() {
        return ses;
    }

    public void setSes(String ses) {
        this.ses = ses;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
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

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    public String getEnded() {
        return ended;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return rep + " " + ses + " " + user_id + " " + username + " " + userphone + " " + term + " " + year + " " + end_date + " " + entry_date + " " + report + " " + ended + " " + ending + " " + status;
    }
}
