package com.company.models.state;

public class InPreparation implements Status {
    @Override
    public String getStatus() {
        return new String("Project in preparation");
    }
}
