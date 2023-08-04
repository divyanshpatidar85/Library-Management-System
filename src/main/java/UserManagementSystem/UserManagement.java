package UserManagementSystem;

import Database.Database;
import Librarian.Librarian;
import User.User;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserManagement {
    private static ArrayList<USERNAME_PWD> personList = new ArrayList<>();
    private String ValidName;
    User objOne;
    static int repeatValidation = 0, user_repeater = 0;
    // private static int idofuser=102;

    public void AskUserType() {
        System.out.println("STARTING USER MANAGEMENT SYSTEM ...\n\n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean ValidInput = false;
        int num = 0;
        boolean looping = true;
        while (looping) {
            while (!ValidInput) {
                try {
                    System.out.println("ENTER '1' FOR USER AND '2' FOR LIBRARIAN | TO EXIT ENTER '3'");
                    num = Integer.parseInt(reader.readLine());

                    if (num == 1) {
                        // TODO call user

                        VerifyUser();
                        // System.out.println("User");
                        ValidInput = true;
                    } else if (num == 2) {
                        // TODO call user
                        VerifyLibrarian();
                        System.out.println("Librarian");
                        ValidInput = true;
                    } else if (num == 3) {
                        System.out.println("Exit");
                        ValidInput = true;
                    } else {
                        System.out.println("Wrong Input");
                    }
                } catch (IOException e) {
                    System.out.println("Input Error");
                }
            }

        }
        int b = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER YOUR CHOICE ");
        System.out.println("1. FOR LOGIN/REGISETR WINDOW");
        System.out.println("2.EXIT ");
        b = sc.nextInt();
        if (b == 2) {
            looping = false;
        }
    }

    private void VerifyLibrarian() {
        ArrayList<USERNAME_PWD> LibrarianList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        LibrarianList.add(new USERNAME_PWD("Raj", "Kumar"));

        Scanner scn = new Scanner(System.in);
        boolean ValidInput = false;
        int EnteredNumber = 0;
        while (!ValidInput) {
            System.out.println("ENTER '1' FOR LOGIN | TO LOGOUT ENTER '3'");
            EnteredNumber = scn.nextInt();

            if (EnteredNumber == 1) {
                System.out.println("LOGIN");
                login(LibrarianList, "Librarian");
                ValidInput = true;
            } else if (EnteredNumber == 3) {
                System.out.println("LOGOUT");
                ValidInput = true;
            } else {
                System.out.println("WRONG INPUT !!!");
            }
        }
    }

    private void VerifyUser() {

        Scanner scanner = new Scanner(System.in);
        if (repeatValidation == 0) {
            personList.add(new USERNAME_PWD("Nayan", "Hello"));
            repeatValidation++;
        }

        Scanner scn = new Scanner(System.in);
        boolean ValidInput = false;
        int EnteredNumber = 0;

        while (!ValidInput) {
            System.out.println("ENTER '1' FOR LOGIN AND '2' FOR REGISTER | TO LOGOUT ENTER '3'");
            EnteredNumber = scn.nextInt();

            if (EnteredNumber == 1) {
                System.out.println("LOGIN");
                login(personList, "User");
                ValidInput = true;
            } else if (EnteredNumber == 2) {
                System.out.println("REGISTER");
                register(personList);
                ValidInput = true;
            } else if (EnteredNumber == 3) {
                System.out.println("LOGOUT");
                ValidInput = true;
            } else {
                System.out.println("WRONG INPUT !!!");
            }
        }
        scn.close();
    }

    private void register(ArrayList<USERNAME_PWD> arrayList) {
        Scanner scn = new Scanner(System.in);

        System.out.println("ENTER YOUR NAME");
        String name = scn.nextLine();
        System.out.println("ENTER YOUR PASSWORD");
        String password = scn.nextLine();

        arrayList.add(new USERNAME_PWD(name, password));
        // Add_New_User();
        if (user_repeater == 0) {
            objOne = new User();
        }
        objOne.Add_New_User(name);
        System.out.println("LOGGING IN ...");
        login(arrayList, "User");

    }

    private void login(ArrayList<USERNAME_PWD> arrayList, String UserOrLib) {
        Scanner scn = new Scanner(System.in);
        boolean UserPresent = false;
        String name;
        do {

            System.out.println("ENTER YOUR NAME");
            name = scn.nextLine();
            System.out.println("ENTER YOUR PASSWORD");
            String password = scn.nextLine();

            for (USERNAME_PWD val : arrayList) {
                if (val.getName().equals(name) && val.getPassword().equals(password)) {
                    System.out.println("Present");
                    ValidName = val.getName();
                    UserPresent = true;
                } else {
                    System.out.println("ENTER YOUR CREDENTIALS AGAIN !!!");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } while (!UserPresent);

        if (UserPresent && UserOrLib == "Librarian") {
            Librarian obj = new Librarian(name);
            // TODO can be changed
        } else if (UserPresent && UserOrLib == "User") {
            if (user_repeater == 0) {
                objOne = new User();
                user_repeater++;
            }
            try {
                System.out.println("enter ");
                objOne.Verify(ValidName);
                UserPresent = true;
                System.out.println("revert ");
                VerifyUser();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // TODO call librarian
        }
    }

    public static void main(String[] args) {
        UserManagement obj = new UserManagement();
        obj.AskUserType();
    }
}