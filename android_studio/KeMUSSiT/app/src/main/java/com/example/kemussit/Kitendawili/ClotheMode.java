package com.example.kemussit.Kitendawili;

public class ClotheMode {
    String mv = null;
    String evt = null;
    String ses = null;
    String term = null;
    String theme = null;
    String venue = null;
    String date = null;
    String club = null;
    String members = null;
    String money = null;
    String summ = null;
    String pat_id = null;
    String pat_phone = null;
    String pat_name = null;
    String fund = null;
    String closure = null;
    String entry_date = null;

    public ClotheMode(String mv, String evt, String ses, String term, String theme, String venue, String date, String club, String members, String money, String summ, String pat_id, String pat_phone, String pat_name, String fund, String closure, String entry_date) {
        this.mv = mv;
        this.evt = evt;
        this.ses = ses;
        this.term = term;
        this.theme = theme;
        this.venue = venue;
        this.date = date;
        this.club = club;
        this.members = members;
        this.money = money;
        this.summ = summ;
        this.pat_id = pat_id;
        this.pat_phone = pat_phone;
        this.pat_name = pat_name;
        this.fund = fund;
        this.closure = closure;
        this.entry_date = entry_date;
    }

    public String getMv() {
        return mv;
    }

    public void setMv(String mv) {
        this.mv = mv;
    }

    public String getEvt() {
        return evt;
    }

    public void setEvt(String evt) {
        this.evt = evt;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getSumm() {
        return summ;
    }

    public void setSumm(String summ) {
        this.summ = summ;
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

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
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
        return members + " " + club + " " + summ + " " + pat_id + " " + closure + " " + money + " " + evt + " " + term + " " + ses + " " + theme + " " + entry_date + " " + venue + " " + pat_name + " " + pat_phone + " " + date;
    }
}
