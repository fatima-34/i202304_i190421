package com.ass2.i202304_i190421;

public class ProfileData {
    String name, email, contact, pp, cp;

    public ProfileData() {}

    public ProfileData(String name, String email, String contact, String pp, String cp) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.pp = pp;
        this.cp = cp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
}
