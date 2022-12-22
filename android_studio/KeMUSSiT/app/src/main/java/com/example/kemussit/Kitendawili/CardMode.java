package com.example.kemussit.Kitendawili;

public class CardMode {
    String id = null;
    String category = null;
    String fees = null;
    String description = null;
    String date = null;

    public CardMode(String id, String category, String fees, String description, String date) {
        this.id = id;
        this.category = category;
        this.fees = fees;
        this.description = description;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return id + " " + category + " " + fees + " " + description + " " + date;
    }
}
