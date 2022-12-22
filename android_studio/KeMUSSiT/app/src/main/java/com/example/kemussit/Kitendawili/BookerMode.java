package com.example.kemussit.Kitendawili;

public class BookerMode {
    String theme = null;
    String venue = null;
    String counter = null;
    String count_2 = null;

    public BookerMode(String theme, String venue, String counter, String count_2) {
        this.theme = theme;
        this.venue = venue;
        this.counter = counter;
        this.count_2 = count_2;
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

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getCount_2() {
        return count_2;
    }

    public void setCount_2(String count_2) {
        this.count_2 = count_2;
    }

    @Override
    public String toString() {
        return theme + " " + venue;
    }
}

