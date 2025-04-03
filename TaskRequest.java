package com.example.riseandshine;

public class TaskRequest {
    private String taskName;
    private String taskTime;

    // Constructor
    public TaskRequest(String taskName, String taskTime) {
        this.taskName = taskName;
        this.taskTime = taskTime;
    }

    // Getters
    public String getTaskName() {
        return taskName;
    }

    public String getTaskTime() {
        return taskTime;
    }

    // Setters
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }
}
