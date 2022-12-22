package com.example.kemussit.Kitendawili;

public class PamperedMode {
    String pay = null;
    String mv = null;
    String ses = null;
    String term = null;
    String mpesa = null;
    String club_amt = null;
    String utility_token = null;
    String amount = null;
    String club = null;
    String pat_id = null;
    String pat_phone = null;
    String pat_name = null;
    String closure = null;
    String entry_date = null;

    public PamperedMode(String pay, String mv, String ses, String term, String mpesa, String club_amt, String utility_token, String amount, String club, String pat_id, String pat_phone, String pat_name, String closure, String entry_date) {
        this.pay = pay;
        this.mv = mv;
        this.ses = ses;
        this.term = term;
        this.mpesa = mpesa;
        this.club_amt = club_amt;
        this.utility_token = utility_token;
        this.amount = amount;
        this.club = club;
        this.pat_id = pat_id;
        this.pat_phone = pat_phone;
        this.pat_name = pat_name;
        this.closure = closure;
        this.entry_date = entry_date;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getMv() {
        return mv;
    }

    public void setMv(String mv) {
        this.mv = mv;
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

    public String getMpesa() {
        return mpesa;
    }

    public void setMpesa(String mpesa) {
        this.mpesa = mpesa;
    }

    public String getClub_amt() {
        return club_amt;
    }

    public void setClub_amt(String club_amt) {
        this.club_amt = club_amt;
    }

    public String getUtility_token() {
        return utility_token;
    }

    public void setUtility_token(String utility_token) {
        this.utility_token = utility_token;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getPat_id() {
        return pat_id;
    }

    public void setPat_id(String pat_id) {
        this.pat_id = pat_id;
    }

    public String getPat_phone() {
        return pat_phone;
    }

    public void setPat_phone(String pat_phone) {
        this.pat_phone = pat_phone;
    }

    public String getPat_name() {
        return pat_name;
    }

    public void setPat_name(String pat_name) {
        this.pat_name = pat_name;
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
        return pay + " " + mv + " " + ses + " " + term + " " + mpesa + " " + club_amt + " " + utility_token + " " + amount + " " + club + " " + pat_id + " " + pat_phone + " " + pat_name + " " + closure + " " + entry_date;
    }
}

