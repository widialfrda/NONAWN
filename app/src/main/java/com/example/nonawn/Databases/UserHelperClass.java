package com.example.nonawn.Databases;

public class UserHelperClass {

    String fullname, email, password, PhoneNumber;
    public UserHelperClass(){}


    public UserHelperClass(String fullname, String email, String password, String phoneNumber) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.PhoneNumber = phoneNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }
}
