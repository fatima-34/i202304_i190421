package com.ass2.i202304_i190421;

public class ChatList {
    private String contact, name, message, date, time;

    public ChatList(String contact, String name, String message, String date, String time) {
        this.contact = contact;
        this.name = name;
        this.message = message;
        this.date = date;
        this.time = time;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
}
