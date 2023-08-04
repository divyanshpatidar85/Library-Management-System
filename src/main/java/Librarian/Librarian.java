package Librarian;

import Database.Database;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Librarian {
    private String userName;

    public Librarian(String userName) {
        this.userName = userName;
        verifyLibrarian();
    }

    private void verifyLibrarian() {
        ArrayList<LIBRARIAN_ID> librarianIds = new ArrayList<>();
        librarianIds.add(new LIBRARIAN_ID("101", userName));
        boolean ValidLibrarian = false;

        Scanner scn = new Scanner(System.in);

        do {
            System.out.println("ENTER YOUR ID");
            String ID = scn.nextLine();
            System.out.println("Your password is in format id@name");

            for (LIBRARIAN_ID val : librarianIds) {
                if (val.getName().equals(userName) && val.getID().equals(ID)) {
                    System.out.println("Present");
                    ValidLibrarian = false;
                    AccessToDatabase();
                } else {
                    System.out.println("ENTER YOUR CREDENTIALS AGAIN !!! \n");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } while (!ValidLibrarian);
    }

    public void AccessToDatabase() {
        System.out.print("ACCESSING DATABASE");
        try {
            animation();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Database();

    }

    public static void animation() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Librarian obj = new Librarian("nayan");
    }

}
