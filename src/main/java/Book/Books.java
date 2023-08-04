package Book;

import java.util.ArrayList;
import java.util.Scanner;

import Account.Accounts;
import Database.Database;

public class Books {
  public  static ArrayList<BookCustomData> All_InformationOf_Book = new ArrayList<BookCustomData>();
    private int renew_var;
    private int ele=0;
    public  static int req=0;
    private String Title, Author, Publication;
    Database ObjOne;

    // show due date
    public void Show_duedt(int i, Accounts obj) {

        renew_var = i;

        obj.due(i);
    }
   public static BookCustomDataReturn calling_book(String UsNa, int id){
       BookCustomDataReturn returning=new BookCustomDataReturn();
        for(int i=0;i< All_InformationOf_Book.size();i++){
            if(All_InformationOf_Book.get(i).BookUName.equalsIgnoreCase(UsNa) && All_InformationOf_Book.get(i).BookUID==id){

//           BookCustomDataReturn returning=new BookCustomDataReturn();
           returning.Author=All_InformationOf_Book.get(i).Author;
           returning.Title= All_InformationOf_Book.get(i).Title;
           return  returning;
//                break;
            }
        }
            return  returning;
    }

    // Renw_info
    public void Renw_info(Accounts obj) {
        try {
            obj.Renew_data(renew_var);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void Book_Request(String USERNAME,int USERID) {
        boolean looping=true;
        BookCustomData BCD=new BookCustomData();
        BCD.BookUName=USERNAME;
        BCD.BookUID=USERID;
         Scanner sc = new Scanner(System.in);
         while(looping) {

             System.out.println("ENTER THE \"TITLE\" OF BOOK");

             BCD.Title = sc.nextLine();

             System.out.println("ENTER THE \"AUTHOR\" OF BOOK");

             BCD.Author = sc.nextLine();

             System.out.println("ENTER THE \"PUBLICATION\" OF BOOK");

             BCD.Publication = sc.nextLine();
             All_InformationOf_Book.add(BCD);
             System.out.println("This is just for searching");
             ObjOne= new Database(BCD.Title, BCD.Author, "BOOK", 0);
//        System.out.println(obj.BookFoundOfNot);
             try {
                 Accounts uAccounts = new Accounts();
                 if (ObjOne.BookFoundOfNot) {
                      req=0;
                     looping=uAccounts.AccUserBookInformation_Add(USERNAME, USERID,req);
                     if(req==1){
                         //calling the database
                         System.out.println("---REQUESTING---");
                         System.out.println(BCD.Title+"   "+BCD.Author);
                          ObjOne = new Database(BCD.Title, BCD.Author,"BOOK", 1 );
//                         System.out.println(ObjOne.BookFoundOfNot);
                     }

                 } else {
                     int lool;
                     Scanner sc1=new Scanner(System.in);
                     System.out.println("SELECT OPTION CHOICE : ");
                     System.out.println("1. AGAIN REENTER DETAILS ");
                     System.out.println("2. EXITS ");
                     lool=sc1.nextInt();
                     if(lool==1){
//                         continue;
                     }else{
                         looping=false;
                     }

                 }
             } catch (Exception e) {
                 // TODO: handle exception
             }
         }


    }

    public static void main(String[] args) {
        // Accounts acc = new Accounts();
        // acc.AccUserBookInformation();
//         Books b1 = new Books();
        // b1.Show_duedt(0, acc);
        // b1.Renw_info(acc);
//         b1.Book_Request("Divyansh",111);
//        Books b2 = new Books();
//        b2.Book_Request("Nayan",123);
    }
}
