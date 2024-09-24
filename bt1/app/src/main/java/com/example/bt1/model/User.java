package com.example.bt1.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private Date dateOfBirth;
    private String gender; // Thêm trường gender

    // Default constructor
    public User() {
    }

    public User(String username, String password, String fullname, String email, Date dateOfBirth, String gender) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender; // Khởi tạo trường gender
    }

    // Getter and Setter methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
