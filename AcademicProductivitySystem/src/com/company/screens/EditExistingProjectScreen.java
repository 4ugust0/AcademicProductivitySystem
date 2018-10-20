package com.company.screens;

import com.company.utility.CommonUse;

public class EditExistingProjectScreen extends Screen {
    private static EditExistingProjectScreen ourInstance = new EditExistingProjectScreen();

    public static EditExistingProjectScreen getInstance() {
        return ourInstance;
    }

    private EditExistingProjectScreen() {
    }

    private void showHeader() {
        CommonUse.getInstance().printHeader();

        System.out.println(":: Edit Existing Project Screen ::");
        System.out.println();
    }

    private void showMenu() {
        System.out.println("0 - Allocate Participants");
        System.out.println("1 - Change Status");
        System.out.println("2 - Exit");
    }

    private void chooseOption() {
        try {
            int chosenOption = CommonUse.getInstance().getScanner().nextInt();

            switch (chosenOption) {
                case 0: {
                    AllocateParticipantsScreen.getInstance().startScreen();
                    break;
                }

                case 1: {
                    ChangeStatusScreen.getInstance().startScreen();
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
