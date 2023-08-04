package Book;

import java.util.ArrayList;
import java.util.Scanner;

import Account.Accounts;
import Database.Database;

public class Books {
    public static ArrayList<BookCustomData> All_InformationOf_Book = new ArrayList<BookCustomData>();
    private int renew_var;
    private int ele = 0;
    public static int req = 0;
    private String Title, Author, Publication;
    Database ObjOne;

    // this show due date
    public void Show_duedt(int i, Accounts obj) {

        renew_var = i;
       //this object call account class and show due date (i is index where the all the information is stored
        obj.due(i);
    }
    //This function return the book information to account class so that after submitting the book can add i database

    public static BookCustomDataReturn calling_book(String UsNa, int id) {
        BookCustomDataReturn returning = new BookCustomDataReturn();
        int val=0;
        for (int i = 0; i < All_InformationOf_Book.size(); i++) {
            if (All_InformationOf_Book.get(i).BookUName.equalsIgnoreCase(UsNa)
                    && All_InformationOf_Book.get(i).BookUID == id) {
                val=i;
                // BookCustomDataReturn returning=new BookCustomDataReturn();
                returning.Author = All_InformationOf_Book.get(i).Author;
                returning.Title = All_InformationOf_Book.get(i).Title;
                return returning;

        }
//        return returning;
            //for removing book infomation of book from user account
            try {

            }finally {
                All_InformationOf_Book.remove(val);
            }
            }
        return returning;
    }

    // Renw_info book information
    public void Renw_info(Accounts obj) {
        try {
            obj.Renew_data(renew_var);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    //by this method user can issue book by filling title, and author details
    public void Book_Request(String USERNAME, int USERID) {
        boolean looping = true;
        BookCustomData BCD = new BookCustomData();
        BCD.BookUName = USERNAME;
        BCD.BookUID = USERID;
        Scanner sc = new Scanner(System.in);
        while (looping) {

            System.out.println("ENTER THE \"TITLE\" OF BOOK");

            BCD.Title = sc.nextLine();

            System.out.println("ENTER THE \"AUTHOR\" OF BOOK");

            BCD.Author = sc.nextLine();

            System.out.println("ENTER THE \"PUBLICATION\" OF BOOK");

            BCD.Publication = sc.nextLine();
            //this is store all the filled book detalis in " All_InformationOf_Book "
            All_InformationOf_Book.add(BCD);
            //it's checking the book is present in library or not if it's present than user go further
            ObjOne = new Database(BCD.Title, BCD.Author, "BOOK", 0);
            // System.out.println(obj.BookFoundOfNot);
            try {
                Accounts uAccounts = new Accounts();
                if (ObjOne.BookFoundOfNot) {
                    req = 0;
//                   this is for if user have a book already than he caanot issue another book if user doesnot have any book than "req " will be 1
                    looping = uAccounts.AccUserBookInformation_Add(USERNAME, USERID, req);
                    // if req==1 than enter in
                    if (req == 1) {
                        // calling the database
                        System.out.println("---REQUESTING---");
                        System.out.println(BCD.Title + "   " + BCD.Author);
                        //this is for the book quantity will be decrement by 1
                        ObjOne = new Database(BCD.Title, BCD.Author, "BOOK", 1);
                        // System.out.println(ObjOne.BookFoundOfNot);
                    }

                } else {
                    //this will execute in case of if book is not available or user have already a bbok
                    int lool;
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("SELECT OPTION CHOICE : ");
                    System.out.println("1. AGAIN REENTER DETAILS ");
                    System.out.println("2. EXITS ");
                    lool = sc1.nextInt();
                    if (lool == 1) {
                        // continue;
                    } else {
                        looping = false;
                    }

                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }

}
