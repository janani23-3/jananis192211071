package com.example.riseandshine.model;
public class SignUpResponse {
    private boolean success;
    private String message;
    private User user;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public class User {
        private String Name;
        private String MobileNo;
        private String id;

        public String getName() {
            return Name;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public String getId() {
            return id;
        }
    }
}
