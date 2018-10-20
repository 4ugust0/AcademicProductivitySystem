package com.company.screens;

import com.company.models.ResearchProject;
import com.company.models.Student;
import com.company.models.state.Complete;
import com.company.models.state.InPreparation;
import com.company.models.state.InProgress;
import com.company.utility.CommonUse;
import com.company.utility.ResearchLab;

import java.util.Date;
import java.util.List;

public class ChangeStatusScreen extends Screen {
    private static ChangeStatusScreen ourInstance = new ChangeStatusScreen();

    public static ChangeStatusScreen getInstance() {
        return ourInstance;
    }

    private ChangeStatusScreen() {
    }

    private void showHeader() {
        CommonUse.getInstance().printHeader();

        System.out.println(":: Change Status Screen ::");
        System.out.println();
    }

    private void showMenu() {
        System.out.println("0 - Change from In Preparation to In Progress");
        System.out.println("1 - Change from In Progress to Complete");
        System.out.println("2 - Exit");
    }

    private ResearchProject lookForResearchProject() {
        showHeader();
        System.out.print("=:> Project title to change: ");
        String theTitle = CommonUse.getInstance().getScanner().nextLine();

        return ResearchLab.getInstance().getResearchProject(theTitle);
    }

    private void inPreparationToInProgress() {
        try {
            ResearchProject researchProject = lookForResearchProject();

            if(researchProject == null) {
                System.out.println("There is project with the given title.");
                CommonUse.getInstance().getScanner().nextLine();
            } else {
                if(researchProject.getTheStatus() instanceof InProgress || researchProject.getTheStatus() instanceof Complete){
                    System.out.print("Project must be in preparation state.");
                    CommonUse.getInstance().getScanner().nextLine();
                    return;
                }

                if(researchProject.getProfessorList().size() == 0) {
                    System.out.println("There is no professor associated with the project.");
                    CommonUse.getInstance().getScanner().nextLine();
                } else {
                    researchProject.setTheStatus(new InProgress());
                    researchProject.setBeginDate(new Date());

                    System.out.println("Status changed successfuly.");
                    CommonUse.getInstance().getScanner().nextLine();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inProgressToComplete() {
        try {
            ResearchProject researchProject = lookForResearchProject();

            if(researchProject == null) {
                System.out.print("There is no project with the given title.");
                CommonUse.getInstance().getScanner().nextLine();
            } else {
                if(researchProject.getTheStatus() instanceof InPreparation || researchProject.getTheStatus() instanceof Complete){
                    System.out.print("Project must be in progress state.");
                    CommonUse.getInstance().getScanner().nextLine();
                    return;
                }

                if(researchProject.getPublicationList().size() == 0) {
                    System.out.print("There is no publications associated with the project.");
                    CommonUse.getInstance().getScanner().nextLine();
                } else {
                    researchProject.setTheStatus(new Complete());
                    researchProject.setEndDate(new Date());

                    List<Student> studentList = researchProject.getStudentList();
                    for(Student s : studentList) {
                        if(s.getTheType().matches("graduation")){
                            s.setProjectsCounter(s.getProjectsCounter() - 1);
                        }
                    }

                    System.out.print("Status changed successfuly.");
                    CommonUse.getInstance().getScanner().nextLine();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void chooseOption() {
        try {
            int chosenOption = CommonUse.getInstance().getScanner().nextInt();
            CommonUse.getInstance().getScanner().nextLine();

            switch (chosenOption) {
                case 0: {
                    inPreparationToInProgress();
                    break;
                }

                case 1: {
                    inProgressToComplete();
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
