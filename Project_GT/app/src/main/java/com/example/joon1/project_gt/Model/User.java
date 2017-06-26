package com.example.joon1.project_gt.Model;

/**
 * Created by joon1 on 2017-06-26.
 */

public class User {
    private String username;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private String gender;

    public User(String username, String name, String password, String email, String phoneNumber, String gender) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }
}
