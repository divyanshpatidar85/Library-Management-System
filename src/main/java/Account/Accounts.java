//Account
package Account;

import Book.BookCustomDataReturn;
import Book.Books;
import Database.Database;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Accounts {
    static ArrayList<CustomDataAccount> all_data_of_Accounts = new ArrayList<CustomDataAccount>();
    Scanner sc = new Scanner(System.in);
    long fine_amount = 0, payment_verifiaction;

    public static int indexfinder(String UserName, int id, int decider) {
        // if(all_data_of_Accounts.size()==0){ return -1;}
        for (int i = 0; i < all_data_of_Accounts.size(); i++) {
            // System.out.println(" i am called "+all_data_of_Accounts.get(i).Acc_UserName+"
            // "+all_data_of_Accounts.get(i).Acc_id+" "+UserName+" "+id);
            if ((all_data_of_Accounts.get(i).Acc_UserName).equalsIgnoreCase(UserName)
                    && all_data_of_Accounts.get(i).Acc_id == id) {
                // System.out.println(" i am called ");
                return i;
            }

        }
        if (decider == 1)
            System.out.println("\n USER DOES NOT HAVE ANY BOOK SO WE CAANOT GET BOOK INFORMATION \n ");
        return -1;
    }

    private void Calculate_fine(String UserName, int id) {
        int lost_book_info = -1;

        System.out.println("\n PLEASE WAIT  ...  ");
        for (int i = 0; i < all_data_of_Accounts.size(); i++) {
            if (all_data_of_Accounts.get(i).Acc_UserName == UserName && all_data_of_Accounts.get(i).Acc_id == id) {

                LocalDate currentDate = LocalDate.now();
                System.out.println(" CHOOSE OPTION  ");
                System.out.println(" 1. LOST BOOK(IF YOU LOST BOOK)");
                System.out.println(" 2. ONLY FOR CALCULATE FINE ");
                System.out.println(" 3. EXIT  ");
                lost_book_info = sc.nextInt();
                if (lost_book_info == 1 || lost_book_info == 2) {
                    if (lost_book_info == 1)
                        all_data_of_Accounts.get(i).Acc_lost_book = 1;

                    if (ChronoUnit.DAYS.between(all_data_of_Accounts.get(i).issuDate, currentDate) > 15) {

                        System.out.print("\n FINE FOR LATE BOOK SUBMISSSION : ");

                        // printing fine for late submission of a book

                        // this line is calculating day difference ===>
                        // (ChronoUnit.DAYS.between(all_data_of_Accounts.get(i).issuDate, currentDate))
                        // System.out.println("number of days
                        // :"+(ChronoUnit.DAYS.between(all_data_of_Accounts.get(i).issuDate,
                        // currentDate)));
                        System.out.println(
                                ((ChronoUnit.DAYS.between(all_data_of_Accounts.get(i).issuDate, currentDate)) - 15)
                                        * 5);

                        fine_amount = (((ChronoUnit.DAYS.between(all_data_of_Accounts.get(i).issuDate, currentDate))
                                - 15)
                                * 5);

                        System.out.print("\n FINE FOR LOST BOOK : ");

                        // printing fine for lost bbok

                        System.out.println((all_data_of_Accounts.get(i).Acc_lost_book) * 500);

                        fine_amount = fine_amount + (all_data_of_Accounts.get(i).Acc_lost_book) * 500;
                    } else {
                        fine_amount = fine_amount + (all_data_of_Accounts.get(i).Acc_lost_book) * 500;
                    }
                }
            }
            // Scanner sc = new Scanner(System.in);
            System.out.println("\n\nTOTAL FINE IS : " + fine_amount);
            System.out.println(" CHOOSE OPTION :  ");
            if (fine_amount != 0)
                System.out.println(" 1. FOR FINE PAYMENT && RETURNING BOOK  ");
            System.out.println(" 2. EXIT ");
            payment_verifiaction = sc.nextInt();
            if (payment_verifiaction == 1) {
                BookCustomDataReturn val = new BookCustomDataReturn();
                val = Books.calling_book(UserName, id);
                System.out.println(val.Author);
                System.out.println(val.Title);
                Database obj = new Database(val.Title, val.Author, "ACCOUNT", 1);

                fine_status(UserName, id);
            } else if (payment_verifiaction > 2 && payment_verifiaction < 0) {
                System.out.println("\n!!!!!   WONG INPUT  !!!!!!!!\n ");

            }
        }
    }

    private void fine_status(String UName, int id) {
        for (int i = 0; i < all_data_of_Accounts.size(); i++) {
            if (all_data_of_Accounts.get(i).Acc_UserName == UName && all_data_of_Accounts.get(i).Acc_id == id) {
                all_data_of_Accounts.get(i).Acc_returned_book = 1;
                System.out.println("\n YOU RETURNED BOOK AND SUBMITTED FINE \n ");
                System.out.println("\n  THANK YOU\n ");
                all_data_of_Accounts.remove(i);

                Calculate_fine(UName, id);

                break;
            }
        }
    }

    // showing account details
    public void AccountDetails(String USName, int US_ID) throws Exception {
        int choice = 0, flag = 0;
        try {

            for (int i = 0; i < all_data_of_Accounts.size(); i++) {
                if (all_data_of_Accounts.get(i).Acc_UserName == USName && all_data_of_Accounts.get(i).Acc_id == US_ID) {
                    flag = 1;
                    System.out.println("BORROWED BOOK : " + all_data_of_Accounts.get(i).Acc_borrowed_book);
                    System.out.println("LOST BOOK : " + all_data_of_Accounts.get(i).Acc_lost_book);
                    System.out.println("RETURNED BOOK : " + all_data_of_Accounts.get(i).Acc_returned_book);
                    try {
                        // TimeUnit.SECONDS.sleep(2);
                        System.out.println(" \n ENTER YOUR CHOICE : ");
                        System.out.println("1. CALCULATE FINE ");
                        System.out.println("2. EXIT :");
                        choice = sc.nextInt();
                        if (choice == 1) {
                            Calculate_fine(USName, US_ID);

                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                }
            }
            if (flag == 0) {
                System.out.println("YOU DOES NOT HAVE BOOK ");
                // TimeUnit.SECONDS.sleep(2);
            }

        } catch (Exception e) {
            System.out.println("YOU DOES NOT HAVE BOOK ");
            // TimeUnit.SECONDS.sleep(2);
        }

    }

    // adding all information of the user related to book

    public boolean AccUserBookInformation_Add(String username, int uuid, int req) throws InterruptedException {
        boolean verification = true;
        // System.out.println("my size is now "+all_data_of_Accounts.size());
        System.out.println("WAIT .......");
        // TimeUnit.SECONDS.sleep(2);

        for (int i = 0; i < all_data_of_Accounts.size(); i++) {
            if (all_data_of_Accounts.get(i).Acc_UserName == username && all_data_of_Accounts.get(i).Acc_id == uuid) {
                verification = false;
            }
        }
        if (verification) {
            System.out.println(" YOU DOES NOT HAVE ANY BOOK  ");
            int choice;
            System.out.println(" CHOOSE OPTION  ");
            System.out.println(" 1. FOR ISSUE BOOK   ");
            System.out.println(" 2 EXIT  ");
            choice = sc.nextInt();
            System.out.println("YOU ENTERED : " + choice);
            System.out.println("WAIT..... : ");
            try {
                // TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                // TODO: handle exception
            }

            if (choice == 1) {
                // Database obj = new Database()
                Books.req = 1;
                CustomDataAccount cda = new CustomDataAccount();

                cda.Acc_UserName = username;

                cda.Acc_borrowed_book = 1;

                cda.Acc_id = uuid;

                cda.Acc_lost_book = 0;

                cda.Acc_returned_book = 0;

                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                cda.issuDate = LocalDate.parse("2023-07-16", dateFormatter);

                all_data_of_Accounts.add(cda);
                // System.out.println("\n WAIT......... ");
                // TimeUnit.SECONDS.sleep(2);
                System.out.println(" NOW YOU ISSUED ONE BOOK  ");
            }
        } else {
            // System.out.println("!!! INAVAID OPTION !!!!");
            try {
                // TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println("!!!!!! USER CANNOT BORROW MORE THAN ONE BOOK !!!! ");
        }
        return false;

    }

    // public static void main(String[] args) {
    // Accounts obj = new Accounts();
    // // obj.AccUserBookInformation_Add();
    // // obj.Calculate_fine("divyansh", 111);

    // // System.out.println(" main " +obj.all_data_of_Accounts.size());

    // }

    public void due(int i) {
        System.out.println("i is == > " + i);
        // System.out.println("due i is "+all_data_of_Accounts.size());
        System.out.println("DUE DATE IS :" + all_data_of_Accounts.get(i).issuDate.plusDays(15));

    }

    public void Renew_data(int renew_var) throws InterruptedException {
        // System.out.println("Renew var "+renew_var);
        try {

            if (ChronoUnit.DAYS.between(all_data_of_Accounts.get(renew_var).issuDate, LocalDate.now()) < 15) {

                // System.out.println("renew var is : " + );
                all_data_of_Accounts.get(renew_var).issuDate = LocalDate.now();
                System.out.println(
                        "YOUR BOOK IS RENEWED : " + (all_data_of_Accounts.get(renew_var).issuDate = LocalDate.now()));
            } else {
                System.out
                        .println("\n YOUR DUE DATE IS : " + all_data_of_Accounts.get(renew_var).issuDate.plusDays(15));
                System.out.println(" FIRST PAY YOUR FINE ");
            }
        } catch (Exception e) {
            // TimeUnit.SECONDS.sleep(1);
            System.out.println("YOU DOES NOT HAVE ANY BOOK ");
        }
    }
}
