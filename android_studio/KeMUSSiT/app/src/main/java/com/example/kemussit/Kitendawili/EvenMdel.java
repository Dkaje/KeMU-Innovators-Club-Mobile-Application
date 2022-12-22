package com.example.kemussit.Kitendawili;

public class EvenMdel {
    String evt = null;
    String ses = null;
    String term = null;
    String theme = null;
    String venue = null;
    String land = null;
    String site = null;
    String date = null;
    String member = null;
    String opened = null;
    String money = null;
    String time = null;
    String status = null;
    String comment = null;
    String approval = null;
    String closure = null;
    String entry_date = null;

    public EvenMdel(String evt, String ses, String term, String theme, String venue, String land, String site, String date, String member, String opened, String money, String time, String status, String comment, String approval, String closure, String entry_date) {
        this.evt = evt;
        this.ses = ses;
        this.term = term;
        this.theme = theme;
        this.venue = venue;
        this.land = land;
        this.site = site;
        this.date = date;
        this.member = member;
        this.opened = opened;
        this.money = money;
        this.time = time;
        this.status = status;
        this.comment = comment;
        this.approval = approval;
        this.closure = closure;
        this.entry_date = entry_date;
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

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getOpened() {
        return opened;
    }

    public void setOpened(String opened) {
        this.opened = opened;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
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
        return approval + " " + member + " " + money + " " + evt + " " + term + " " + ses + " " + theme + " " + entry_date + " " + venue + " " + land + " " + site + " " + date + " " + time + " " + status;
    }
}
