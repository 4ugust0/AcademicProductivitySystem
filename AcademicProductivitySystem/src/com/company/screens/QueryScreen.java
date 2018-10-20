package com.company.screens;

import com.company.models.Contributor;
import com.company.models.Professor;
import com.company.models.ResearchProject;
import com.company.models.Student;
import com.company.utility.CommonUse;
import com.company.utility.ResearchLab;

public class QueryScreen extends Screen {
    private static QueryScreen ourInstance = new QueryScreen();

    public static QueryScreen getInstance() {
        return ourInstance;
    }

    private QueryScreen() {
    }

    private void showHeader() {
        CommonUse.getInstance().printHeader();

        System.out.println(":: Query Screen ::");
        System.out.println();
    }

    private void showMenu() {
        System.out.println("0 - Query for collaborator");
        System.out.println("1 - Query for project");
        System.out.println("2 - Exit");
    }

    private void QueryForCollaborator() {
        showHeader();

        System.out.print("=:> Collaborator First Name: ");
        String firstName = CommonUse.getInstance().getScanner().nextLine();
        System.out.print("=:> Collaborator Last Name: ");
        String lastName = CommonUse.getInstance().getScanner().nextLine();

        Professor professor = ResearchLab.getInstance().getProfessor(firstName, lastName);
        Student student = ResearchLab.getInstance().getStudent(firstName, lastName);
        Contributor contributor = ResearchLab.getInstance().getContributor(firstName, lastName);

        if(professor == null && student == null && contributor == null) {
            System.out.print("Collaborator not found.");
            CommonUse.getInstance().getScanner().nextLine();
            return;
        } else {
            showHeader();

            if(professor != null){
                System.out.println(professor);
                System.out.println();
                CommonUse.getInstance().getScanner().nextLine();

                return;
            } else if(student != null) {
                System.out.println(student);
                System.out.println();
                CommonUse.getInstance().getScanner().nextLine();

                return;
            } else {
                System.out.println(contributor);
                System.out.println();
                CommonUse.getInstance().getScanner().nextLine();

                return;
            }
        }
    }

    private void QueryForProject() {
        showHeader();

        System.out.print("=:> Project title: ");
        String theTitle = CommonUse.getInstance().getScanner().nextLine();

        showHeader();

        ResearchProject researchProject = ResearchLab.getInstance().getResearchProject(theTitle);
        if(researchProject == null) {
            System.out.print("Project title doesn't exist !");
            CommonUse.getInstance().getScanner().nextLine();
        } else {
            System.out.println(researchProject);
            CommonUse.getInstance().getScanner().nextLine();
        }
    }

    private void chooseOption() {
        try {
            int chosenOption = CommonUse.getInstance().getScanner().nextInt();
            CommonUse.getInstance().getScanner().nextLine();

            switch (chosenOption) {
                case 0: {
                    QueryForCollaborator();
                    break;
                }

                case 1: {
                    QueryForProject();
                    break;
                }

                case 2: {
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
