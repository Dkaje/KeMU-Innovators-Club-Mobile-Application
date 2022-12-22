package com.example.kemussit.Kitendawili;

public class ViewRegistry {
    String sm = null;
    String evt = null;
    String ses = null;
    String term = null;
    String theme = null;
    String venue = null;
    String land = null;
    String site = null;
    String date = null;
    String time = null;
    String money = null;
    String reg_no = null;
    String fullname = null;
    String phone = null;
    String status = null;
    String comment = null;
    String pay = null;
    String approval = null;
    String org = null;
    String org_no = null;
    String org_phone = null;
    String org_name = null;
    String club = null;
    String pat = null;
    String closure = null;
    String entry_date = null;


    public ViewRegistry(String sm, String evt, String ses, String term, String theme, String venue, String land, String site, String date, String time, String money, String reg_no, String fullname, String phone, String status, String comment, String pay, String approval, String org, String org_no, String org_phone, String org_name, String club, String pat, String closure, String entry_date) {
        this.sm = sm;
        this.evt = evt;
        this.ses = ses;
        this.term = term;
        this.theme = theme;
        this.venue = venue;
        this.land = land;
        this.site = site;
        this.date = date;
        this.time = time;
        this.money = money;
        this.reg_no = reg_no;
        this.fullname = fullname;
        this.phone = phone;
        this.status = status;
        this.comment = comment;
        this.pay = pay;
        this.approval = approval;
        this.org = org;
        this.org_no = org_no;
        this.org_phone = org_phone;
        this.org_name = org_name;
        this.club = club;
        this.pat = pat;
        this.closure = closure;
        this.entry_date = entry_date;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
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

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getOrg_no() {
        return org_no;
    }

    public void setOrg_no(String org_no) {
        this.org_no = org_no;
    }

    public String getOrg_phone() {
        return org_phone;
    }

    public void setOrg_phone(String org_phone) {
        this.org_phone = org_phone;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getPat() {
        return pat;
    }

    public void setPat(String pat) {
        this.pat = pat;
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
        return approval + " " + reg_no + " " + fullname + " " + phone + " " + closure + " " + money + " " + evt + " " + term + " " + ses + " " + theme + " " + entry_date + " " + venue + " " + land + " " + site + " " + date + " " + time + " " + status;
    }
}
