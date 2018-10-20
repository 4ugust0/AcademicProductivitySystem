package com.company.screens;

import com.company.models.*;
import com.company.models.state.InProgress;
import com.company.utility.CommonUse;
import com.company.utility.ResearchLab;

public class InsertAcademicProductionScreen extends Screen {
    private static InsertAcademicProductionScreen ourInstance = new InsertAcademicProductionScreen();

    public static InsertAcademicProductionScreen getInstance() {
        return ourInstance;
    }

    private InsertAcademicProductionScreen() {
    }

    private void showHeader() {
        CommonUse.getInstance().printHeader();

        System.out.println(":: Insert Academic Production Screen ::");
        System.out.println();
    }

    private void showMenu() {
        System.out.println("0 - Create a Publication");
        System.out.println("1 - Create a Orientation");
        System.out.println("2 - Associate Publication to a Reseacher");
        System.out.println("3 - Associate Publication to a Project");
        System.out.println("4 - Exit");
    }

    private void CreatePublication() {
        showHeader();

        System.out.print("=:> Publication Title: ");
        String theTitle = CommonUse.getInstance().getScanner().nextLine();

        if(!ResearchLab.getInstance().isAbleToCreatePublication(theTitle)) {
            System.out.print("Publication title already exists ! try again.");
            CommonUse.getInstance().getScanner().nextLine();

            return;
        }

        System.out.print("=:> Conference of Publication: ");
        String theConferenceName = CommonUse.getInstance().getScanner().nextLine();
        System.out.print("=:> Year of Publication: ");
        int publicationYear = CommonUse.getInstance().getScanner().nextInt();
        CommonUse.getInstance().getScanner().nextLine();
        System.out.print("=:> Has research project to associate?(yes|no): ");
        String hasProject = CommonUse.getInstance().getScanner().nextLine();

        Publication newPublication = null;

        if(hasProject.matches("no")) {
            newPublication = new Publication(theTitle, theConferenceName, publicationYear, null);
            ResearchLab.getInstance().addPublication(newPublication);

            System.out.print("Publication sucessfuly created !");
            CommonUse.getInstance().getScanner().nextLine();
        } else {
            System.out.print("=:> Project Title: ");
            String researchProjectTitle = CommonUse.getInstance().getScanner().nextLine();

            ResearchProject foundResearchProject = ResearchLab.getInstance().getResearchProject(researchProjectTitle);

            if(foundResearchProject == null) {
                System.out.print("Research project not found. Try again");
                CommonUse.getInstance().getScanner().nextLine();
                return;
            } else {
                newPublication = new Publication(theTitle, theConferenceName, publicationYear, foundResearchProject);
                ResearchLab.getInstance().addPublication(newPublication);
                if(!(foundResearchProject.getTheStatus() instanceof InProgress)) {
                    System.out.print("Can't associate with project because it's not in progress !");
                    CommonUse.getInstance().getScanner().nextLine();
                } else {
                    for(Publication p : foundResearchProject.getPublicationList()) {
                        if(p.getTheTitle().equals(newPublication.getTheTitle())){
                            System.out.print("Publication already in the Project, try again.");
                            CommonUse.getInstance().getScanner().nextLine();
                            return;
                        }
                    }

                    foundResearchProject.getPublicationList().add(newPublication);
                }

                System.out.print("Publication sucessfuly created !");
                CommonUse.getInstance().getScanner().nextLine();
            }
        }
    }

    private void CreateOrientation() {
        showHeader();

        System.out.print("=:> Professor First Name: ");
        String professorFirstName = CommonUse.getInstance().getScanner().nextLine();
        System.out.print("=:> Professor Last Name: ");
        String professorLastName = CommonUse.getInstance().getScanner().nextLine();
        System.out.print("=:> Student/Contributor First Name: ");
        String firstName = CommonUse.getInstance().getScanner().nextLine();
        System.out.print("=:> Student/Contributor Last Name: ");
        String lastName = CommonUse.getInstance().getScanner().nextLine();
        System.out.print("=:> Publication Title: ");
        String publicationTitle = CommonUse.getInstance().getScanner().nextLine();

        Professor professor = ResearchLab.getInstance().getProfessor(professorFirstName, professorLastName);
        Student student = ResearchLab.getInstance().getStudent(firstName, lastName);
        Contributor contributor = ResearchLab.getInstance().getContributor(firstName, lastName);
        Publication publication = ResearchLab.getInstance().getPublication(publicationTitle);

        if(professor == null) {
            System.out.print("Professor not found.");
            CommonUse.getInstance().getScanner().nextLine();

            return;
        } else if(student == null && contributor == null) {
            System.out.print("Student/Contributor not found.");
            CommonUse.getInstance().getScanner().nextLine();

            return;
        } else if(publication == null) {
            System.out.print("Publication not found.");
            CommonUse.getInstance().getScanner().nextLine();

            return;
        } else {
            Orientation newOrientation = null;
            if(student != null) {
                newOrientation = new Orientation(student, publication);
            } else {
                newOrientation = new Orientation(contributor, publication);
            }

            professor.getOrientationList().add(newOrientation);
            ResearchLab.getInstance().addOrientation(newOrientation);

            System.out.print("Orientation created sucessfuly !");
            CommonUse.getInstance().getScanner().nextLine();

            return;
        }
    }

    private void AssociatePublicationToResearcher() {
        showHeader();

        System.out.print("=:> Reseacher First Name: ");
        String firstName = CommonUse.getInstance().getScanner().nextLine();
        System.out.print("=:> Reseacher Last Name: ");
        String lastName = CommonUse.getInstance().getScanner().nextLine();
        System.out.print("=:> Publication Title: ");
        String theTitle = CommonUse.getInstance().getScanner().nextLine();

        Student student = ResearchLab.getInstance().getStudent(firstName, lastName);
        Professor professor = ResearchLab.getInstance().getProfessor(firstName, lastName);
        Contributor contributor = ResearchLab.getInstance().getContributor(firstName, lastName);
        Publication publication = ResearchLab.getInstance().getPublication(theTitle);

        if(publication == null) {
            System.out.print("Publication not found.");
            CommonUse.getInstance().getScanner().nextLine();

            return;
        }

        if(student == null && professor == null && contributor == null) {
            System.out.print("Researcher not found.");
            CommonUse.getInstance().getScanner().nextLine();

            return;
        } else {
            if(student != null) {
                for(Publication p : student.getPublicationList()) {
                    if(p.getTheTitle().equals(publication.getTheTitle())){
                        System.out.print("Already associated.");
                        CommonUse.getInstance().getScanner().nextLine();

                        return;
                    }
                }
                student.getPublicationList().add(publication);
            } else if(contributor != null) {
                for(Publication p : contributor.getPublicationList()) {
                    if(p.getTheTitle().equals(publication.getTheTitle())){
                        System.out.print("Already associated.");
                        CommonUse.getInstance().getScanner().nextLine();

                        return;
                    }
                }
                contributor.getPublicationList().add(publication);
            } else if(professor != null) {
                for(Publication p : professor.getPublicationList()) {
                    if(p.getTheTitle().equals(publication.getTheTitle())){
                        System.out.print("Already associated.");
                        CommonUse.getInstance().getScanner().nextLine();

                        return;
                    }
                }
                professor.getPublicationList().add(publication);
            }

            System.out.print("Sucessfuly associated !");
            CommonUse.getInstance().getScanner().nextLine();
        }

    }

    private void AssociatePublicationToProject() {
        showHeader();

        System.out.print("=:> Publication Title: ");
        String publicationTitle = CommonUse.getInstance().getScanner().nextLine();
        System.out.print("=:> Project Title: ");
        String projectTitle = CommonUse.getInstance().getScanner().nextLine();

        Publication publication = ResearchLab.getInstance().getPublication(publicationTitle);
        ResearchProject researchProject = ResearchLab.getInstance().getResearchProject(projectTitle);

        if(publication != null && researchProject != null) {
            if(!(researchProject.getTheStatus() instanceof InProgress)){
                System.out.println("Project must be in progress state !");
                CommonUse.getInstance().getScanner().nextLine();

                return;
            }

            for(Publication p : researchProject.getPublicationList()) {
                if(p.getTheTitle().equals(publication.getTheTitle())){
                    System.out.println("Publication is already associated with the project !");
                    CommonUse.getInstance().getScanner().nextLine();

                    return;
                }
            }

            researchProject.getPublicationList().add(publication);
            publication.setTheResearchProject(researchProject);

            System.out.println("Publication sucessfuly associated with the project !");
            CommonUse.getInstance().getScanner().nextLine();
        } else {
            System.out.println("Publication or Research Project not found. try again.");
            CommonUse.getInstance().getScanner().nextLine();
        }
    }

    private void chooseOption() {
        try {
            int chosenOption = CommonUse.getInstance().getScanner().nextInt();
            CommonUse.getInstance().getScanner().nextLine();

            switch (chosenOption) {
                case 0: {
                    CreatePublication();
                    break;
                }

                case 1: {
                    CreateOrientation();
                    break;
                }

                case 2: {
                    AssociatePublicationToResearcher();
                    break;
                }

                case 3: {
                    AssociatePublicationToProject();
                    break;
                }

                case 4: {
                    setRunning(false);
                    break;
                }

                default: {
                    setRunning(false);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            CommonUse.getInstance().getScanner().nextLine();
        }
    }

    public void startScreen() {
        setRunning(true);

        while(isRunning()) {
            showHeader();
            showMenu();
            chooseOption();
        }

        return;
    }
}
