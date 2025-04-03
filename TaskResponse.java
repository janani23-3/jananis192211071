package com.example.riseandshine;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TaskResponse {

    private boolean success;
    private String message;
    private List<Task> tasks;

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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    // Inner Task Class to Represent Individual Tasks
    public static class Task {
        @SerializedName("_id")
        private String id;

        @SerializedName("taskName")
        private String taskName;

        @SerializedName("taskTime")
        private String taskTime;

        @SerializedName("isMorningRoutine")
        private boolean isMorningRoutine;

        // Getters and Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
