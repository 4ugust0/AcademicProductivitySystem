package com.company.models.state;

public class Complete implements Status {
    @Override
    public String getStatus() {
        return new String("Project complete");
    }
}
