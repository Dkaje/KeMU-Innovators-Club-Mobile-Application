package com.example.kemussit.Kitendawili;

public class SemaModel {
    String ann = null;
    String ses = null;
    String term = null;
    String subject = null;
    String notice = null;
    String closure = null;
    String entry_date = null;

    public SemaModel(String ann, String ses, String term, String subject, String notice, String closure, String entry_date) {
        this.ann = ann;
        this.ses = ses;
        this.term = term;
        this.subject = subject;
        this.notice = notice;
        this.closure = closure;
        this.entry_date = entry_date;
    }

    public String getAnn() {
        return ann;
    }

    public void setAnn(String ann) {
        this.ann = ann;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getClosure() {
        return closure;
    }

    public void setClosure(String closure) {
        this.closure = closure;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    @Override
    public String toString() {
        return ann + " " + term + " " + subject + " " + ses + " " + notice + " " + entry_date + " " + closure;
    }
}

