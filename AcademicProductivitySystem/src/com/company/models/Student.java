package com.company.models;

import com.company.models.state.Complete;
import com.company.utility.ResearchLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Student {
    public Student(Name fullName, String eMail, String theType) {
        this.fullName = fullName;
        this.eMail = eMail;
        this.theType = theType;
        this.publicationList = new ArrayList<>();
    }

    private Name fullName;
    private String eMail;
    private String theType;
    private List<Publication> publicationList;

    private int projectsCounter;

    public Name getFullName() {
        return fullName;
    }

    public void setFullName(Name fullName) {
        this.fullName = fullName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTheType() {
        return theType;
    }

    public void setTheType(String theType) {
        this.theType = theType;
    }

    public List<Publication> getPublicationList() {
        return publicationList;
    }

    public void setPublicationList(List<Publication> publicationList) {
        this.publicationList = publicationList;
    }

    public int getProjectsCounter() {
        return projectsCounter;
    }

    public void setProjectsCounter(int projectsCounter) {
        this.projectsCounter = projectsCounter;
    }

    @Override
    public String toString() {
        String newString = "";

        newString += "First Name: " + fullName.getFirstName() + "\n";
        newString += "Last Name: " + fullName.getLastName() + "\n";
        newString += "eMail: " + eMail + "\n";

        newString += "\n";
        newString += "In Preparation|In Progress projects: " + "\n";

        List<ResearchProject> rpList = new ArrayList<>();

        for(ResearchProject rp : ResearchLab.getInstance().getResearchProjectList()){
            for(Student s : rp.getStudentList()){
                if(s.getFullName().getFirstName().equals(fullName.getFirstName()) &&
                    s.getFullName().getLastName().equals(fullName.getLastName())
                ){
                    if(rp.getTheStatus() instanceof Complete) {
                        rpList.add(rp);
                    } else{
                        newString += "\tProject Title: " + rp.getTheTittle() + ", Description: " + rp.getTheDescription() + "\n";
                    }
                }
            }
        }

        newString += "\n";
        newString += "Complete projects: " + "\n";

        Collections.sort(rpList, new Comparator<ResearchProject>() {
            public int compare(ResearchProject o1, ResearchProject o2) {
                return o1.getEndDate().compareTo(o2.getEndDate());
            }
        });

        for(ResearchProject rp : rpList){
            newString += "\tProject Title: " + rp.getTheTittle() + ", Description: " + rp.getTheDescription() + "\n";
        }

        newString += "\n";
        newString += "Publications: " + "\n";

        Collections.sort(publicationList, new Comparator<Publication>() {
            public int compare(Publication o1, Publication o2) {
                return o2.getPublicationYear() - o1.getPublicationYear();
            }
        });

        for(Publication p : publicationList) {
            newString += "\tPublication Title: " + p.getTheTitle() +
                    ", Conference: " + p.getTheConferenceName() +
                    ", Year: " + p.getPublicationYear() +"\n";
        }

        return newString;
    }
}
