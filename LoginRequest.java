package com.example.riseandshine.model;

public class LoginRequest {

    private String MobileNo;
    private String password;

    // Getters and Setters
    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.MobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

