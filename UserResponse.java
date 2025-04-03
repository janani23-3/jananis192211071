package com.example.riseandshine;

/*
public class UserResponse {

    private boolean success;
    private String message;
    private String Name;
    private String gender;
    private int age;

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
*/


import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("user")
    private User user;

    public boolean isSuccess() {
        return success;
    }

    public User getUser() {
        return user;
    }

    public static class User {
        @SerializedName("_id")
        private String id;

        @SerializedName("Name")
        private String name;

        @SerializedName("gender")
        private String gender;

        @SerializedName("age")
        private int age;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public int getAge() {
            return age;
        }
    }
}

