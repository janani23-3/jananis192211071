package com.example.riseandshine;

public class WakeUpTimeRequest {
    private String wakeUpTime;

    public WakeUpTimeRequest(String wakeUpTime) {
        this.wakeUpTime = wakeUpTime;
    }

    public String getWakeUpTime() {
        return wakeUpTime;
    }

    public void setWakeUpTime(String wakeUpTime) {
        this.wakeUpTime = wakeUpTime;
    }
}
