package com.company;

import com.company.screens.EditResearchLabScreen;
import com.company.utility.CommonUse;

import java.util.Scanner;

public class Main {

    static final String admUsername = "jose";
    static final String admPassword = "augusto";
    static int attemptsCounter = 0;

    public static void main(String[] args) {
        while(attemptsCounter++ < 3) {
            CommonUse.getInstance().printHeader();

            System.out.print("=:> Username: ");
            String attemptUsername = new Scanner(System.in).nextLine();
            System.out.print("=:> Password: ");
            String attemptPassword = new Scanner(System.in).nextLine();

            if( attemptUsername.equals(admUsername) && attemptPassword.equals(admPassword)) {
                System.out.println("Logged successfuly !");
                CommonUse.getInstance().getScanner().nextLine();
                EditResearchLabScreen.getInstance().startScreen();
            } else {
                System.out.println("Username or password incorrect.");
                CommonUse.getInstance().getScanner().nextLine();
            }
        }

        System.out.println("More than 3 attempts, exiting the system...");
        CommonUse.getInstance().getScanner().nextLine();
    }
}
