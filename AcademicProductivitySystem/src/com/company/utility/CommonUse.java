package com.company.utility;

import java.util.Scanner;

public class CommonUse {
    private static CommonUse ourInstance = new CommonUse();

    public static CommonUse getInstance() {
        return ourInstance;
    }

    private CommonUse() {
        this.scanner = new Scanner(System.in);
    }

    private Scanner scanner;

    public void printHeader() {
        for(int i = 0; i < 10; ++i) {
            System.out.println();
        }

        System.out.println("\t Productivity System \t");
        System.out.println();
    }

    public Scanner getScanner() {
        return scanner;
    }
}
