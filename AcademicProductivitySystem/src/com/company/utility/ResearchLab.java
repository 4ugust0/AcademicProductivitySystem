package com.company.utility;

import com.company.models.*;
import com.company.models.state.Complete;
import com.company.models.state.InPreparation;
import com.company.models.state.InProgress;

import javax.management.monitor.CounterMonitor;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class ResearchLab {
    private static ResearchLab ourInstance = new ResearchLab();

    public static ResearchLab getInstance() {
        return ourInstance;
    }

    private ResearchLab() {
        studentList = new ArrayList<>();
        professorList = new ArrayList<>();
        contributorList = new ArrayList<>();

        researchProjectList = new ArrayList<>();

        orientationList = new ArrayList<>();
        publicationList = new ArrayList<>();
    }

    private List<Student> studentList;
    private List<Professor> professorList;
    private List<Contributor> contributorList;

    private List<ResearchProject> researchProjectList;

    private List<Orientation> orientationList;
    private List<Publication> publicationList;

    public void addStudent(Student newStudent) {
        for(Student s : studentList){
            if(s.getFullName().getFirstName().equals(newStudent.getFullName().getFirstName()) &&
                    s.getFullName().getLastName().equals(newStudent.getFullName().getLastName())
            ){
                return;
            }
        }
        studentList.add(newStudent);
    }

    public void addProfessor(Professor newProfessor) {
        for(Professor p : professorList){
            if(p.getFullName().getFirstName().equals(newProfessor.getFullName().getFirstName()) &&
                    p.getFullName().getLastName().equals(newProfessor.getFullName().getLastName())
            ){
                return;
            }
        }
        professorList.add(newProfessor);
    }

    public void addContributor(Contributor newContributor) {
        for(Contributor c : contributorList){
            if(c.getFullName().getFirstName().equals(newContributor.getFullName().getFirstName()) &&
                c.getFullName().getLastName().equals(newContributor.getFullName().getLastName())
            ){
                return;
            }
        }
        contributorList.add(newContributor);
    }

    public void addResearchProject(ResearchProject newResearchProject) {
        for(ResearchProject rp : researchProjectList){
            if(rp.getTheTittle().equals(newResearchProject.getTheTittle())){
                return;
            }
        }

        researchProjectList.add(newResearchProject);
    }

    public void addOrientation(Orientation newOrientation) {
        for(Orientation o : orientationList){
            String s1,s2,s11,s22, s3, s33;
            s1 = o.getOrientedStudent().getFullName().getFirstName();
            s11 = newOrientation.getOrientedStudent().getFullName().getFirstName();
            s2 = o.getOrientedStudent().getFullName().getLastName();
            s22 = newOrientation.getOrientedStudent().getFullName().getLastName();

            s3 = o.getAssociatedPublication().getTheTitle();
            s33 = newOrientation.getAssociatedPublication().getTheTitle();

            if(s1.equals(s11) && s2.equals(s22) && s3.equals(s33)){
                return;
            }
        }
        orientationList.add(newOrientation);
    }

    public void addPublication(Publication newPublication) {
        for(Publication p : publicationList){
            if(p.getTheTitle().equals(newPublication.getTheTitle())){
                return;
            }
        }
        publicationList.add(newPublication);
    }

    public ResearchProject getResearchProject(String theTitle) {
        ResearchProject foundResearchProject = null;

        for(ResearchProject rp : researchProjectList) {
            if(rp.getTheTittle().equals(theTitle)){
                foundResearchProject = rp;
                break;
            }
        }

        return foundResearchProject;
    }

    public Professor getProfessor(String firstName, String lastName) {
        Professor foundProfessor = null;

        for(Professor p : professorList) {
            if(p.getFullName().getFirstName().equals(firstName) && p.getFullName().getLastName().equals(lastName)){
                foundProfessor = p;
                break;
            }
        }

        return foundProfessor;
    }

    public Student getStudent(String firstName, String lastName) {
        Student foundStudent = null;

        for(Student s : studentList) {
            if(s.getFullName().getFirstName().equals(firstName) && s.getFullName().getLastName().equals(lastName)){
                foundStudent = s;
                break;
            }
        }

        return foundStudent;
    }

    public Contributor getContributor(String firstName, String lastName){
        Contributor foundContributor = null;

        for(Contributor c : contributorList) {
            if(c.getFullName().getFirstName().equals(firstName) && c.getFullName().getLastName().equals(lastName)) {
                foundContributor = c;
                break;
            }
        }

        return foundContributor;
    }

    public Publication getPublication(String theTitle) {
        Publication foundPublication = null;

        for(Publication p : publicationList) {
            if(p.getTheTitle().equals(theTitle)) {
                foundPublication = p;
                break;
            }
        }

        return foundPublication;
    }

    public void addProfessorInProject(String firstName, String lastName, String theTitle) {
        Professor foundProfessor = getProfessor(firstName, lastName);
        ResearchProject foundResearchProject = getResearchProject(theTitle);

        if(foundProfessor != null && foundResearchProject != null) {
            if(!(foundResearchProject.getTheStatus() instanceof InPreparation)) {
                System.out.println("System can only allocate professor in Preparation projects.");
                CommonUse.getInstance().getScanner().nextLine();
                return;
            }

            for(Professor p : foundResearchProject.getProfessorList()) {
                if(p.getFullName().getFirstName().equals(firstName) && p.getFullName().getLastName().equals(lastName)){
                    System.out.println("Professor already in the project");
                    CommonUse.getInstance().getScanner().nextLine();
                    return;
                }
            }

            foundResearchProject.getProfessorList().add(foundProfessor);

            System.out.println("Professor added in the project sucessfuly.");
            CommonUse.getInstance().getScanner().nextLine();
        } else {
            System.out.println("Professor or Project not found. Make sure that all inputs are valid.");
            CommonUse.getInstance().getScanner().nextLine();
        }
    }

    public void addStudentInProject(String firstName, String lastName, String theTitle) {
        Student foundStudent = getStudent(firstName, lastName);
        ResearchProject foundResearchProject = getResearchProject(theTitle);

        if(foundStudent != null && foundResearchProject != null) {
            if(!(foundResearchProject.getTheStatus() instanceof InPreparation)) {
                System.out.println("System can only allocate student in Preparation projects.");
                CommonUse.getInstance().getScanner().nextLine();
                return;
            }

            if(foundStudent.getTheType().matches("graduation")) {
                if(foundStudent.getProjectsCounter() < 2) {
                    for(Student s : foundResearchProject.getStudentList()) {
                        if(s.getFullName().getFirstName().equals(firstName) && s.getFullName().getLastName().equals(lastName)){
                            System.out.println("Student already in the project");
                            CommonUse.getInstance().getScanner().nextLine();

                            return;
                        }
                    }

                    foundStudent.setProjectsCounter(foundStudent.getProjectsCounter() + 1);
                    foundResearchProject.getStudentList().add(foundStudent);

                    System.out.println("Student added in the project sucessfuly.");
                    CommonUse.getInstance().getScanner().nextLine();
                } else {
                    System.out.println("Student are in 2 projects already.");
                    CommonUse.getInstance().getScanner().nextLine();
                }
            } else {
                for(Student s : foundResearchProject.getStudentList()) {
                    if(s.getFullName().getFirstName().equals(firstName) && s.getFullName().getLastName().equals(lastName)){
                        System.out.println("Student already in the project");
                        CommonUse.getInstance().getScanner().nextLine();

                        return;
                    }
                }

                foundResearchProject.getStudentList().add(foundStudent);

                System.out.println("Student added in the project sucessfuly.");
                CommonUse.getInstance().getScanner().nextLine();
            }
        } else {
            System.out.println("Student or Project not found. Make sure that all inputs are valid.");
            CommonUse.getInstance().getScanner().nextLine();
        }
    }

    public void addContributorInProject(String firstName, String lastName, String theTitle) {
        Contributor foundContributor = getContributor(firstName, lastName);
        ResearchProject foundResearchProject = getResearchProject(theTitle);

        if(foundContributor != null && foundResearchProject != null) {
            if(!(foundResearchProject.getTheStatus() instanceof InPreparation)) {
                System.out.println("System can only allocate contributor in Preparation projects.");
                CommonUse.getInstance().getScanner().nextLine();
                return;
            }

            for(Contributor c : foundResearchProject.getContributorList()) {
                if(c.getFullName().getFirstName().equals(firstName) && c.getFullName().getLastName().equals(lastName)){
                    System.out.println("Contributor alreadu in the project !");
                    CommonUse.getInstance().getScanner().nextLine();
                    CommonUse.getInstance().getScanner().nextLine();

                    return;
                }
            }

            foundResearchProject.getContributorList().add(foundContributor);

            System.out.println("Contributor added in the project sucessfuly.");
            CommonUse.getInstance().getScanner().nextLine();
        } else {
            System.out.println("Contributor or Project not found. Make sure that all inputs are valid.");
            CommonUse.getInstance().getScanner().nextLine();
        }
    }

    public int getCollaboratorNumber() {
        return studentList.size() + professorList.size() + contributorList.size();
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

    public List<ResearchProject> getResearchProjectList() {
        return researchProjectList;
    }

    public void setResearchProjectList(List<ResearchProject> researchProjectList) {
        this.researchProjectList = researchProjectList;
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

    public int getProjectsInPreparationNumber() {
        int number = 0;

        for(ResearchProject rp : researchProjectList) {
            if(rp.getTheStatus() instanceof InPreparation){
                number++;
            }
        }

        return number;
    }

    public int getProjectsInProgressNumber() {
        int number = 0;

        for(ResearchProject rp : researchProjectList) {
            if(rp.getTheStatus() instanceof InProgress){
                number++;
            }
        }

        return number;
    }

    public int getProjectsCompleteNumber() {
        int number = 0;

        for(ResearchProject rp : researchProjectList) {
            if(rp.getTheStatus() instanceof Complete){
                number++;
            }
        }

        return number;
    }

    public boolean isAbleToCreateResearcher(String firstName, String lastName) {
        boolean isAble = true;

        for(Student s : studentList){
            if(s.getFullName().getFirstName().equals(firstName) && s.getFullName().getLastName().equals(lastName)){
                isAble = false;
                break;
            }
        }

        for(Professor p : professorList) {
            if(p.getFullName().getFirstName().equals(firstName) && p.getFullName().getLastName().equals(lastName)){
                isAble = false;
                break;
            }
        }

        for(Contributor c : contributorList) {
            if(c.getFullName().getFirstName().equals(firstName) && c.getFullName().getLastName().equals(lastName)){
                isAble = false;
                break;
            }
        }

        return isAble;
    }

    public boolean isAbleToCreatePublication(String theTitle) {
        boolean isAble = true;

        for(Publication p : publicationList) {
            if(p.getTheTitle().equals(theTitle)){
                isAble = false;
                break;
            }
        }

        return isAble;
    }

    public boolean isAbleToCreateResearchProject(String theTitle) {
        boolean isAble = true;

        for(ResearchProject rp : researchProjectList) {
            if(rp.getTheTittle().equals(theTitle)) {
                isAble = false;
            }
        }

        return isAble;
    }
}
