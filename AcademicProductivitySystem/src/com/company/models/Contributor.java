package com.company.models;

import com.company.models.state.Complete;
import com.company.utility.ResearchLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Contributor {
    public Contributor(Name fullName, String eMail) {
        this.fullName = fullName;
        this.eMail = eMail;
        this.publicationList = new ArrayList<>();
    }

    private Name fullName;
    private String eMail;
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
            for(Contributor c : rp.getContributorList()){
                if(c.getFullName().getFirstName().equals(fullName.getFirstName()) &&
                        c.getFullName().getLastName().equals(fullName.getLastName())
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
