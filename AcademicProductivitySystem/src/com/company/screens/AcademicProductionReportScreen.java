package com.company.screens;

import com.company.utility.CommonUse;
import com.company.utility.ResearchLab;

import java.util.Date;
import java.util.zip.CheckedOutputStream;

public class AcademicProductionReportScreen extends Screen {
    private static AcademicProductionReportScreen ourInstance = new AcademicProductionReportScreen();

    public static AcademicProductionReportScreen getInstance() {
        return ourInstance;
    }

    private AcademicProductionReportScreen() {
    }

    private void showHeader() {
        CommonUse.getInstance().printHeader();

        System.out.println(":: AcademicProductionReportScreen ::");
        System.out.println();
    }

    public void startScreen() {
        showHeader();

        int collaboratorNumber = ResearchLab.getInstance().getCollaboratorNumber();
        System.out.println("Number of Collaborators: " + (collaboratorNumber));

        int researchProjectsInPreparationNumber = ResearchLab.getInstance().getProjectsInPreparationNumber();
        System.out.println("Number of Projects in Preparation: " + (researchProjectsInPreparationNumber));

        int researchProjectsInProgressNumber = ResearchLab.getInstance().getProjectsInProgressNumber();
        System.out.println("Number of Projects in Progress: " + (researchProjectsInProgressNumber));

        int researchProjectsCompleteNumber = ResearchLab.getInstance().getProjectsCompleteNumber();
        System.out.println("Number of Projects Complete: " + (researchProjectsCompleteNumber));

        int totalProjectsNumber = researchProjectsInPreparationNumber + researchProjectsInProgressNumber + researchProjectsCompleteNumber;
        System.out.println("Total Number of Projects: " + (totalProjectsNumber));

        System.out.println("Number of Academic Production: ");
        int publicationsNumber = ResearchLab.getInstance().getPublicationList().size();
        System.out.println("\t Publications Number: " + (publicationsNumber));

        int orientationsNUmber = ResearchLab.getInstance().getOrientationList().size();
        System.out.println("\t Orientations Number: " + (orientationsNUmber));

        int totalNumberAcademicProduction = publicationsNumber + orientationsNUmber;
        System.out.println("\t Total Number of Academic Production: " + (totalNumberAcademicProduction));

        System.out.println();
        System.out.println("Reports generated in: " + (new Date()));
        CommonUse.getInstance().getScanner().nextLine();
        CommonUse.getInstance().getScanner().nextLine();
    }
}
