package com.company.models;

import java.util.Date;

public class Publication {
    public Publication(String theTitle, String theConferenceName, int publicationYear, ResearchProject theResearchProject) {
        this.theTitle = theTitle;
        this.theConferenceName = theConferenceName;
        this.publicationYear = publicationYear;
        this.theResearchProject = theResearchProject;
    }

    private String theTitle;
    private String theConferenceName;
    private int publicationYear;
    private ResearchProject theResearchProject;

    public String getTheTitle() {
        return theTitle;
    }

    public void setTheTitle(String theTitle) {
        this.theTitle = theTitle;
    }

    public String getTheConferenceName() {
        return theConferenceName;
    }

    public void setTheConferenceName(String theConferenceName) {
        this.theConferenceName = theConferenceName;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public ResearchProject getTheResearchProject() {
        return theResearchProject;
    }

    public void setTheResearchProject(ResearchProject theResearchProject) {
        this.theResearchProject = theResearchProject;
    }
}
