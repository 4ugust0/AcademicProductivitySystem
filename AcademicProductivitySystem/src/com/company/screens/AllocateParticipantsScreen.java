package com.company.screens;

import com.company.models.Name;
import com.company.models.Professor;
import com.company.models.ResearchProject;
import com.company.utility.CommonUse;
import com.company.utility.ResearchLab;

public class AllocateParticipantsScreen extends Screen{
    private static AllocateParticipantsScreen ourInstance = new AllocateParticipantsScreen();

    public static AllocateParticipantsScreen getInstance() {
        return ourInstance;
    }

    private AllocateParticipantsScreen() {
    }

    private void showHeader() {
        CommonUse.getInstance().printHeader();

        System.out.println(":: Allocate Participants Screen ::");
        System.out.println();
    }

    private void showMenu() {
        System.out.println("0 - Allocate a Professor to a Project");
        System.out.println("1 - Allocate a Student to a Project");
        System.out.println("2 - Allocate a Contributor to a Project");
        System.out.println("3 - Exit");
    }

    private void AllocateProfessorProject() {
        try {
            showHeader();

            System.out.print("=:> Professor First Name: ");
            String firstName = CommonUse.getInstance().getScanner().nextLine();
            System.out.print("=:> Professor Last Name: ");
            String lastName = CommonUse.getInstance().getScanner().nextLine();
            System.out.print("=:> Project Title: ");
            String theTitle = CommonUse.getInstance().getScanner().nextLine();

            ResearchLab.getInstance().addProfessorInProject(firstName, lastName, theTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void AllocateStudentProject() {
        try {
            showHeader();

            System.out.print("=:> Student First Name: ");
            String firstName = CommonUse.getInstance().getScanner().nextLine();
            System.out.print("=:> Student Last Name: ");
            String lastName = CommonUse.getInstance().getScanner().nextLine();
            System.out.print("=:> Project Title: ");
            String theTitle = CommonUse.getInstance().getScanner().nextLine();

            ResearchLab.getInstance().addStudentInProject(firstName, lastName, theTitle);
        } catch (Exception e) {
            e.printStackTrace();
            CommonUse.getInstance().getScanner().nextLine();
        }
    }

    private void AllocateContributorProject() {
        try {
            showHeader();

            System.out.print("=:> Contributor First Name: ");
            String firstName = CommonUse.getInstance().getScanner().nextLine();
            System.out.print("=:> Contributor Last Name: ");
            String lastName = CommonUse.getInstance().getScanner().nextLine();
            System.out.print("=:> Project Title: ");
            String theTitle = CommonUse.getInstance().getScanner().nextLine();

            ResearchLab.getInstance().addContributorInProject(firstName, lastName, theTitle);
        } catch (Exception e) {
            e.printStackTrace();
            String theTitle = CommonUse.getInstance().getScanner().nextLine();
        }
    }

    private void chooseOption() {
        try {
            int chosenOption = CommonUse.getInstance().getScanner().nextInt();
            CommonUse.getInstance().getScanner().nextLine();

            switch (chosenOption) {
                case 0: {
                    AllocateProfessorProject();
                    break;
                }

                case 1: {
                    AllocateStudentProject();
                    break;
                }

                case 2: {
                    AllocateContributorProject();
                    break;
                }

                case 3: {
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
