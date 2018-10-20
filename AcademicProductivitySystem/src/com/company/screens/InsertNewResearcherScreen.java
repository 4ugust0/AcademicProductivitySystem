package com.company.screens;

import com.company.models.*;
import com.company.utility.CommonUse;
import com.company.utility.ResearchLab;

public class InsertNewResearcherScreen extends Screen {
    private static InsertNewResearcherScreen ourInstance = new InsertNewResearcherScreen();

    public static InsertNewResearcherScreen getInstance() {
        return ourInstance;
    }

    private InsertNewResearcherScreen() {
    }

    private void showHeader() {
        CommonUse.getInstance().printHeader();

        System.out.println(":: Insert New Researcher in the System Screen ::");
        System.out.println();
    }

    private void showMenu() {
        showHeader();

        System.out.println("0 - Insert New Professor");
        System.out.println("1 - Insert New Student");
        System.out.println("2 - Insert New Contributor");
        System.out.println("3 - Exit");
    }

    private String readNewStudentType() {
        System.out.print("=:> Student Type(doctor|master|graduation): ");
        String studentType = CommonUse.getInstance().getScanner().nextLine();
        return studentType;
    }

    private void InsertNewResearcher(String researcherType) {
        try {
            showHeader();

            System.out.print("=:> First name: ");
            String firstName = CommonUse.getInstance().getScanner().nextLine();
            System.out.print("=:> Middle Name: ");
            String middleName = CommonUse.getInstance().getScanner().nextLine();
            System.out.print("=:> Last Name: ");
            String lastName = CommonUse.getInstance().getScanner().nextLine();
            System.out.print("=:> eMail: ");
            String neweMail = CommonUse.getInstance().getScanner().nextLine();

            if(!ResearchLab.getInstance().isAbleToCreateResearcher(firstName, lastName)){
                System.out.println("Researcher already exists in the lab.");
                CommonUse.getInstance().getScanner().nextLine();

                return;
            }

            Name newFullName = new Name(firstName, middleName, lastName);

            switch (researcherType) {
                case "Professor": {
                    Professor newProfessor = new Professor(newFullName, neweMail);
                    ResearchLab.getInstance().addProfessor(newProfessor);
                    break;
                }

                case "Student": {
                    String newStudentType = readNewStudentType();
                    if(newStudentType.matches("doctor|master|graduation")) {
                        Student newStudent = new Student(newFullName, neweMail, newStudentType);
                        ResearchLab.getInstance().addStudent(newStudent);
                    } else {
                        System.out.println("Student type incorrect, try again.");
                        CommonUse.getInstance().getScanner().nextLine();
                        return;
                    }
                    break;
                }

                case "Contributor": {
                    Contributor newContributor = new Contributor(newFullName, neweMail);
                    ResearchLab.getInstance().addContributor(newContributor);
                    break;
                }
            }

            System.out.println(researcherType + " added successfuly !");
            CommonUse.getInstance().getScanner().nextLine();

        } catch (Exception e) {
            e.printStackTrace();
            CommonUse.getInstance().getScanner().nextLine();
        }
    }

    private void chooseOption() {
        try {
            int chosenOption = CommonUse.getInstance().getScanner().nextInt();
            CommonUse.getInstance().getScanner().nextLine();

            switch (chosenOption) {
                case 0: {
                    InsertNewResearcher("Professor");
                    break;
                }

                case 1: {
                    InsertNewResearcher("Student");
                    break;
                }

                case 2: {
                    InsertNewResearcher("Contributor");
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
