package com.example.kemussit.Kitendawili;

public class RelaxMode {
    String cs = null;
    String ses = null;
    String term = null;
    String code = null;
    String title = null;
    String credits = null;
    String department = null;
    String closure = null;
    String quote = null;
    String entry_date = null;

    public RelaxMode(String cs, String ses, String term, String code, String title, String credits, String department, String closure, String quote, String entry_date) {
        this.cs = cs;
        this.ses = ses;
        this.term = term;
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.department = department;
        this.closure = closure;
        this.quote = quote;
        this.entry_date = entry_date;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClosure() {
        return closure;
    }

    public void setClosure(String closure) {
        this.closure = closure;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    @Override
    public String toString() {
        return cs + " " + closure + " " + term + " " + ses + " " + code + " " + entry_date + " " + title + " " + credits + " " + department;
    }
}
