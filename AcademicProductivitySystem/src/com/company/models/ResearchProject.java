package com.company.models;

import com.company.models.state.InPreparation;
import com.company.models.state.Status;

import java.util.*;

public class ResearchProject {
    public ResearchProject(String theTittle, String fundingAgency, double fundingValue, String theObjective, String theDescription) {
        this.theTittle = theTittle;
        this.beginDate = null;
        this.endDate = null;
        this.fundingAgency = fundingAgency;
        this.fundingValue = fundingValue;
        this.theObjective = theObjective;
        this.theDescription = theDescription;

        this.publicationList = new ArrayList<>();

        this.studentList = new ArrayList<>();
        this.professorList = new ArrayList<>();
        this.contributorList = new ArrayList<>();

        this.theStatus = new InPreparation();
    }

    private String theTittle;
    private Date beginDate;
    private Date endDate;
    private String fundingAgency;
    private double fundingValue;
    private String theObjective;
    private String theDescription;

    private List<Publication> publicationList;

    private List<Student> studentList;
    private List<Professor> professorList;
    private List<Contributor> contributorList;

    private Status theStatus;

    @Override
    public String toString() {
        String newString = "";
        newString += "Title: " + theTittle + "\n";

        if(beginDate == null) {
            newString += "Begin Date: " + "not started" + "\n";
        } else {
            newString += "Begin Date: " + beginDate.toString() + "\n";
        }

        if(endDate == null) {
            newString += "End Date: " + "not completed" + "\n";
        } else {
            newString += "End Date: " + endDate.toString() + "\n";
        }

        newString += "Funding Agency: " + fundingAgency + "\n";
        newString += "Funding Value: " + fundingValue + "\n";
        newString += "Objective: " + theObjective + "\n";
        newString += "Description: " + theDescription + "\n";

        newString += "\n";
        newString += "--- Publications ---";
        newString += "\n";

        Collections.sort(publicationList, new Comparator<Publication>() {
            public int compare(Publication o1, Publication o2) {
                return o2.getPublicationYear() - o1.getPublicationYear();
            }
        });

        for(Publication p : publicationList) {
            newString += p.getTheTitle() + ", " + p.getTheConferenceName() + ", " + p.getPublicationYear() + "\n";
        }

        newString += "\n";
        newString += "--- Collaborators ---";
        newString += "\n";

        newString += "Professor List: " + "\n";
        for(Professor p : professorList) {
            newString += "\t" + p.getFullName().getFirstName() + " " + p.getFullName().getLastName() + "\n";
        }

        newString += "Student List: " + "\n";
        for(Student s : studentList) {
            newString += "\t" + s.getFullName().getFirstName() + " " + s.getFullName().getLastName() + "\n";
        }

        newString += "Contributor List: " + "\n";
        for(Contributor c : contributorList) {
            newString += "\t" + c.getFullName().getFirstName() + " " + c.getFullName().getLastName() + "\n";
        }

        newString += "Project Status: " + theStatus.getStatus() + "\n";

        return newString;
    }

    public String getTheTittle() {
        return theTittle;
    }

    public void setTheTittle(String theTittle) {
        this.theTittle = theTittle;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFundingAgency() {
        return fundingAgency;
    }

    public void setFundingAgency(String fundingAgency) {
        this.fundingAgency = fundingAgency;
    }

    public Double getFundingValue() {
        return fundingValue;
    }

    public void setFundingValue(Double fundingValue) {
        this.fundingValue = fundingValue;
    }

    public String getTheObjective() {
        return theObjective;
    }

    public void setTheObjective(String theObjective) {
        this.theObjective = theObjective;
    }

    public String getTheDescription() {
        return theDescription;
    }

    public void setTheDescription(String theDescription) {
        this.theDescription = theDescription;
    }

    public List<Publication> getPublicationList() {
        return publicationList;
    }

    public void setPublicationList(List<Publication> publicationList) {
        this.publicationList = publicationList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    public List<Contributor> getContributorList() {
        return contributorList;
    }

    public void setContributorList(List<Contributor> contributorList) {
        this.contributorList = contributorList;
    }

    public Status getTheStatus() {
        return theStatus;
    }

    public void setTheStatus(Status theStatus) {
        this.theStatus = theStatus;
    }
}
