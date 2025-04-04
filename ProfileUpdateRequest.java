package com.example.riseandshine;

public class ProfileUpdateRequest {
    private String userId;
    private String name;
    private String gender;
    private String age;

    public ProfileUpdateRequest(String userId, String name, String gender, String age) {
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
