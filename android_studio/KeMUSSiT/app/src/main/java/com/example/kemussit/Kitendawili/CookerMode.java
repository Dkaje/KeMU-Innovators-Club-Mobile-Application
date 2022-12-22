package com.example.kemussit.Kitendawili;

public class CookerMode {
    String theme = null;
    String venue = null;
    String counter = null;

    public CookerMode(String theme, String venue, String counter) {
        this.theme = theme;
        this.venue = venue;
        this.counter = counter;
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

    @Override
    public String toString() {
        return theme + " " + venue;
    }
}