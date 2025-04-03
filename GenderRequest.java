package com.example.riseandshine;

public class GenderRequest {
    private String userID;
    private String gender;

    public GenderRequest(String userID, String gender) {
        this.userID = userID;
        this.gender = gender;
    }
}
