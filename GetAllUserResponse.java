package com.example.riseandshine.model;

import java.util.List;

public class GetAllUserResponse {


    private boolean success;
    private List<User> users;

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    // Nested User class
    public static class User {

        private String _id;
        private String name;
        private String dob;
        private String mobileNo;
        private List<Task> tasks;
        private int version;

        // Getters and Setters
        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public List<Task> getTasks() {
            return tasks;
        }

        public void setTasks(List<Task> tasks) {
            this.tasks = tasks;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }


        // Nested Task class
        public static class Task {

            private String _id;
            private String taskName;
            private String taskTime;
            private boolean isMorningRoutine;

            // Getters and Setters
            public String getId() {
                return _id;
            }

            public void setId(String id) {
                this._id = id;
            }

            public String getTaskName() {
                return taskName;
            }

            public void setTaskName(String taskName) {
                this.taskName = taskName;
            }

            public String getTaskTime() {
                return taskTime;
            }

            public void setTaskTime(String taskTime) {
                this.taskTime = taskTime;
            }

            public boolean isMorningRoutine() {
                return isMorningRoutine;
            }

            public void setMorningRoutine(boolean morningRoutine) {
                isMorningRoutine = morningRoutine;
            }


        }
    }

}
