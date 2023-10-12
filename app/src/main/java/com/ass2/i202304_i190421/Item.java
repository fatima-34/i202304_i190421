package com.ass2.i202304_i190421;

public class Item {
    String name, rate, description, picture, city;

    public Item() {
    }

    public Item(String name, String rate, String description, String picture, String city) {
        this.name = name;
        this.rate = rate;
        this.description = description;
        this.picture = picture;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
