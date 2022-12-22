package com.example.kemussit.Kitendawili;

public class Keeper {
    String stud_id = null;
    String phone = null;
    String name = null;
    String message = null;
    String date = null;
    String time = null;

    public Keeper(String stud_id, String phone, String name, String message, String date, String time) {
        this.stud_id = stud_id;
        this.phone = phone;
        this.name = name;
        this.message = message;
        this.date = date;
        this.time = time;
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

    @Override
    public String toString() {
        return stud_id + " " + phone + " " + time + " " + name + " " + message + " " + date;
    }
}

