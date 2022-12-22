package com.example.kemussit.Kitendawili;

public class SahauMode {
    String theme = null;
    String venue = null;
    String club = null;
    String counter = null;

    public SahauMode(String theme, String venue, String club, String counter) {
        this.theme = theme;
        this.venue = venue;
        this.club = club;
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

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return theme + " " + club + " " + venue;
    }
}