package com.example.riseandshine;

public class Task {
    private String userId;
    private String taskName;
    private String duration;

    // Constructor
    public Task(String userId, String taskName, String duration) {
        this.userId = userId;
        this.taskName = taskName;
        this.duration = duration;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDuration() {
        return duration;
    }

    // Setters
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    // Optional: toString method for easier debugging and printing
    @Override
    public String toString() {
        return "Task [userId=" + userId + ", taskName=" + taskName + ", duration=" + duration + "]";
    }
}
