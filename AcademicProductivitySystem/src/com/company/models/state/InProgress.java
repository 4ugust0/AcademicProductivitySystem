package com.company.models.state;

public class InProgress implements Status {
    @Override
    public String getStatus() {
        return new String("Project in progress");
    }
}
