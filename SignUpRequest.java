package com.example.riseandshine.model;

public class SignUpRequest {
    private String Name;
    private String MobileNo;

    private String password;

    public SignUpRequest(String name, String mobileNo, String password) {
        Name = name;
        MobileNo = mobileNo;
        this.password = password;
    }

    // Constructor


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters and Setters
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getMobile() {
        return MobileNo;
    }

    public void setMobile(String mobile) {
        this.MobileNo = mobile;
    }
}
