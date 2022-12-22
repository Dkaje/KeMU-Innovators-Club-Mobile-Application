package com.example.kemussit.Kitendawili;

public class ClubFC {
    String id = null;
    String club_name = null;
    String entry_date = null;

    public ClubFC(String id, String club_name, String entry_date) {
        this.id = id;
        this.club_name = club_name;
        this.entry_date = entry_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    @Override
    public String toString() {
        return id + " " + club_name + " " + entry_date;
    }
}
