package com.ass2.i202304_i190421;

public class User {
    String name, email, contact, profilePic, coverPic;

    public User() {}

    public User(String name, String email, String contact, String profilePic, String coverPic) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.profilePic = profilePic;
        this.coverPic = coverPic;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }
}
