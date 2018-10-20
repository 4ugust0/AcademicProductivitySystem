package com.company.screens;

import com.company.models.ResearchProject;
import com.company.utility.CommonUse;
import com.company.utility.ResearchLab;

public class CreateProjectScreen extends Screen {
    private static CreateProjectScreen ourInstance = new CreateProjectScreen();

    public static CreateProjectScreen getInstance() {
        return ourInstance;
    }

    private CreateProjectScreen() {
        super();
    }

    private void showHeader() {
        CommonUse.getInstance().printHeader();

        System.out.println(":: Create Project Screen ::");
        System.out.println();
    }

    public void startScreen() {

        try {
            showHeader();
            CommonUse.getInstance().getScanner().nextLine();

            System.out.print("=:> Title: ");
            String theTitle = CommonUse.getInstance().getScanner().nextLine();

            if(!ResearchLab.getInstance().isAbleToCreateResearchProject(theTitle)){
                System.out.println("Project title already exist !");
                CommonUse.getInstance().getScanner().nextLine();

                return;
            }

            System.out.print("=:> Funding Agency: ");
            String fundingAgency = CommonUse.getInstance().getScanner().nextLine();
            System.out.print("=:> Funding Value: ");
            double fundingValue = CommonUse.getInstance().getScanner().nextDouble();
            CommonUse.getInstance().getScanner().nextLine();
            System.out.print("=:> Objective: ");
            String theObjective = CommonUse.getInstance().getScanner().nextLine();
            System.out.print("=:> Description: ");
            String theDescription = CommonUse.getInstance().getScanner().nextLine();

            ResearchProject newResearchProject = new ResearchProject(
                    theTitle,
                    fundingAgency,
                    fundingValue,
                    theObjective,
                    theDescription);

            ResearchLab.getInstance().addResearchProject(newResearchProject);

            System.out.println("Project created sucessfuly !");
            CommonUse.getInstance().getScanner().nextLine();

        } catch (Exception e){
            e.printStackTrace();
            CommonUse.getInstance().getScanner().nextLine();
            CommonUse.getInstance().getScanner().nextLine();
        }
    }
 }
