package com.company.models;

import com.company.models.state.Complete;
import com.company.utility.ResearchLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Professor {
    public Professor(Name fullName, String eMail) {
        this.fullName = fullName;
        this.eMail = eMail;
        this.orientationList = new ArrayList<>();
        this.publicationList = new ArrayList<>();
    }

    private Name fullName;
    private String eMail;
    private List<Orientation> orientationList;
    private List<Publication> publicationList;

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

    public List<Orientation> getOrientationList() {
        return orientationList;
    }

    public void setOrientationList(List<Orientation> orientationList) {
        this.orientationList = orientationList;
    }

    public List<Publication> getPublicationList() {
        return publicationList;
    }

    public void setPublicationList(List<Publication> publicationList) {
        this.publicationList = publicationList;
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
            for(Professor p : rp.getProfessorList()){
                if(p.getFullName().getFirstName().equals(fullName.getFirstName()) &&
                        p.getFullName().getLastName().equals(fullName.getLastName())
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

        newString += "\n";
        newString += "Orientations: " + "\n";

        for(Orientation o : orientationList) {
            newString += "First Name: " + o.getOrientedStudent().getFullName().getFirstName() + ", Last Name: " +
                o.getOrientedStudent().getFullName().getLastName() + ", Publication: " +
                o.getAssociatedPublication().getTheTitle() + "\n";
        }
        return newString;
    }
}
