package com.example.kemussit.Kitendawili;

public class AdaMess {
    String fd = null;
    String stud_id = null;
    String phone = null;
    String name = null;
    String message = null;
    String move = null;
    String date = null;
    String time = null;
    String current = null;

    public AdaMess(String fd, String stud_id, String phone, String name, String message, String move, String date, String time, String current) {
        this.fd = fd;
        this.stud_id = stud_id;
        this.phone = phone;
        this.name = name;
        this.message = message;
        this.move = move;
        this.date = date;
        this.time = time;
        this.current = current;
    }

    public String getFd() {
        return fd;
    }

    public void setFd(String fd) {
        this.fd = fd;
    }

    public String getStud_id() {
        return stud_id;
    }

    public void setStud_id(String stud_id) {
        this.stud_id = stud_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
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

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String toString() {
        return fd + " " + stud_id + " " + move + " " + phone + " " + name + " " + message + " " + time + " " + date + " " + current;
    }
}
