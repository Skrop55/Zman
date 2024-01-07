package com.example.zman.UserHelper;

public class User {
    String Email, Password;
    private byte[] picture;
    long id;

    public User(String email, String password, byte[] picture) {
        Email = email;
        Password = password;
        this.picture = picture;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
