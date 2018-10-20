package com.company.screens;

import com.company.utility.CommonUse;

public class EditResearchLabScreen extends Screen {
    private static EditResearchLabScreen ourInstance = new EditResearchLabScreen();

    public static EditResearchLabScreen getInstance() {
        return ourInstance;
    }

    private EditResearchLabScreen() {
        super();
    }

    private void showHeader() {
        CommonUse.getInstance().printHeader();

        System.out.println(":: Edit Research Lab Screen ::");
        System.out.println();
    }

    private void showMenu() {
        System.out.println("0 - Create a project");
        System.out.println("1 - Insert New Researcher in the System");
        System.out.println("2 - Edit an existing project");
        System.out.println("3 - Query");
        System.out.println("4 - Insert academic production");
        System.out.println("5 - Reaseach Lab Academic Production Report");
        System.out.println("6 - Exit");
    }

    private void chooseOption() {
        try {
            int chosenOption = CommonUse.getInstance().getScanner().nextInt();

            switch (chosenOption) {
                case 0: {
                    CreateProjectScreen.getInstance().startScreen();
                    break;
                }

                case 1: {
                    InsertNewResearcherScreen.getInstance().startScreen();
                    break;
                }

                case 2: {
                    EditExistingProjectScreen.getInstance().startScreen();
                    break;
                }

                case 3: {
                    QueryScreen.getInstance().startScreen();
                    break;
                }

                case 4: {
                    InsertAcademicProductionScreen.getInstance().startScreen();
                    break;
                }

                case 5: {
                    AcademicProductionReportScreen.getInstance().startScreen();
                    break;
                }

                case 6: {
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
