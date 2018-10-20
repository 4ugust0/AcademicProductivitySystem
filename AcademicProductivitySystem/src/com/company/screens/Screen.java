package com.company.screens;

public class Screen {
    private boolean isRunning;

    public Screen() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
