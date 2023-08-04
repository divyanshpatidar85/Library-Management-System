package Database;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Database {
    static public int validator=0,print_decider=0;
    private String title;
    private String author;
    private String ISBN;
    private String Publication;
    private int numOfBooks;
    public static ArrayList<DATABASE_FIELDS> databaseArrayList= new ArrayList<>();
    public boolean BookFoundOfNot;
// Here i am statyinh
    public Database(String title, String author, String nameOfYourClass, int Request){
        if(validator==0) {
            databaseArrayList.add(new DATABASE_FIELDS("To Kill a Mockingbird", "Harper Lee", "9780061120084", "Harper Perennial Modern Classics", 4));
            databaseArrayList.add(new DATABASE_FIELDS("1984", "George Orwell", "9780451524935", "Signet Classics", 2));
            databaseArrayList.add(new DATABASE_FIELDS("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", "Scribner", 3));
            databaseArrayList.add(new DATABASE_FIELDS("Harry Potter", "123", "12345", "JKR", 1));
            System.out.println(" i am inside ");
            validator++;
        }
        this.title = title;
        this.author = author;
        BookOrLibrarian(nameOfYourClass, Request);

    }

    public Database(){
        databaseArrayList.add(new DATABASE_FIELDS("To Kill a Mockingbird", "Harper Lee", "9780061120084", "Harper Perennial Modern Classics",4));
        databaseArrayList.add(new DATABASE_FIELDS("1984", "George Orwell", "9780451524935", "Signet Classics",2));
        databaseArrayList.add(new DATABASE_FIELDS( "The Great Gatsby","F. Scott Fitzgerald", "9780743273565", "Scribner",3));
        databaseArrayList.add(new DATABASE_FIELDS( "Harry Potter","123", "12345", "JKR",1));
        LibrarianAccess();
    }

    private void BookOrLibrarian(String bookOrLibrarianorAccount, int request){
        if (bookOrLibrarianorAccount == "BOOK" && request == 1){
           // This is for requesting
            BookAccess();
        }
        else if (bookOrLibrarianorAccount == "BOOK" && request == 0) {
            // This is for searching book
            try {
                animation();
//                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            BookFoundOfNot =
                    Search(title, author, null , null , "SEARCH");
        }
        else if (bookOrLibrarianorAccount == "LIBRARIAN") {
            LibrarianAccess();
        }
        else if (bookOrLibrarianorAccount == "ACCOUNT") {
            System.out.println("YOU ARE IN ACCOUNT");
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            AccountAccess();
        }
        else {
            System.out.println("ENTER CORRECT CLASS YOU CHOMU !!");
        }
    }

    private void IncreaseNumOfBooks(String title, String author) {
        System.out.print("ADDING YOUR BOOK ");
        try {
            animation();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (DATABASE_FIELDS databaseFields : databaseArrayList){

            if (databaseFields.getTitle().equalsIgnoreCase(title) && databaseFields.getAuthor().equalsIgnoreCase(author)){

                System.out.println();
                System.out.print(databaseFields.getTitle() + " ");
                System.out.print(databaseFields.getAuthor() + " ");
                System.out.print(databaseFields.getPublication() + " ");
                System.out.print(databaseFields.getISBN() + " ");
                System.out.print(databaseFields.getNumBooks() + " ");
                System.out.println();

                int currentNumBooks = databaseFields.getNumBooks();
                databaseFields.setNumBooks(currentNumBooks + 1);

                System.out.println();
                System.out.print(databaseFields.getTitle() + " ");
                System.out.print(databaseFields.getAuthor() + " ");
                System.out.print(databaseFields.getPublication() + " ");
                System.out.print(databaseFields.getISBN() + " ");
                System.out.print(databaseFields.getNumBooks() + " ");
                System.out.println();
            }
        }
    }
    private void AccountAccess() {
        System.out.print("RETURNING YOUR BOOK");
        try {
            animation();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        boolean BookPresentDB = true;
        if (BookPresentDB){
            IncreaseNumOfBooks(title, author);
        }
    }

    private void BookAccess(){
        System.out.print("SEARCHING YOUR BOOK");
        try {
            animation();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        BookFoundOfNot = Search(title, author);
    }

    private void LibrarianAccess(){
        int input ;
        boolean validInput = false;
        Scanner scn = new Scanner(System.in);

        while( (!validInput)){

            System.out.println("ENTER '1' TO ADD BOOK, '2' TO DELETE BOOK, '3' TO UPDATE," +
                    " '4' TO DISPLAY, '5' TO SEARCH | '6' TO EXIT");
            input = scn.nextInt();

            if (input == 1){
                LibrarianInput();
                if ( Search(title, author, ISBN, Publication, "SEARCH")) {
                    IncreaseNumOfBooks(title, author);
                }else {
                add(title, author, ISBN, Publication);
                }
                try {
                    System.out.print("WAIT");
                    animation();
//                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else if (input == 2) {
                LibrarianInput();
                delete(title, author, ISBN, Publication);
                try {
                    System.out.print("\nWAIT");
                    animation();
//                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else if (input == 3){
                LibrarianInput();
                if (Search(title, author, ISBN, Publication, "UPDATE")) {
                    update(title, author, ISBN, Publication);
                }else {
                    System.out.println("NO SUCH BOOK FOUND");
                }

                try {
                    System.out.print("\nWAIT");
                    animation();
//                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else if (input == 4) {
                display();
                try {
                    System.out.print("\nWAIT");
                    animation();
//                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else if (input == 5){
                Scanner scanner = new Scanner(System.in);
                System.out.println("ENTER SEARCH TITLE");
                String SearchTitle = scanner.nextLine();
                System.out.println("ENTER AUTHOR TO SEARCH");
                String SearchAuthor = scanner.nextLine();
                Search(SearchTitle, SearchAuthor, null,null,"SEARCH");


                try {
                    System.out.print("\nWAIT");
                    animation();
//                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }else if (input == 6) {
                validInput = true;
            }else {
                System.out.println("WRONG INPUT");
            }

        }
        scn.close();
    }


    private void add(String title, String author, String ISBN, String Publication){

        databaseArrayList.add(new DATABASE_FIELDS(title, ISBN, Publication, author, 1));
        System.out.println("BOOK ADDED !!!");
    }

    private void delete(String title, String author, String ISBN, String Publication) {
       // int indexToRemove = findIndexByFields(databaseArrayList, title, author);
       for(int i=0;i<databaseArrayList.size();i++){
           if(databaseArrayList.get(i).getTitle().equalsIgnoreCase(title) &&
                   databaseArrayList.get(i).getAuthor().equalsIgnoreCase(author)&&
           databaseArrayList.get(i).getISBN().equalsIgnoreCase(ISBN)&&
           databaseArrayList.get(i).getPublication().equalsIgnoreCase(Publication)&&
                   databaseArrayList.get(i).getNumBooks()>=1
           ){
               databaseArrayList.get(i).setNumBooks(databaseArrayList.get(i).getNumBooks()-1);
               System.out.println("BOOK IS DELETED .....");
           }
       }


    }

    private void delete(String title, String author) {

       for(int i=0;i<databaseArrayList.size();i++){
           if(databaseArrayList.get(i).getTitle().equalsIgnoreCase(title) && databaseArrayList.get(i).getAuthor().equalsIgnoreCase(author) &&databaseArrayList.get(i).getNumBooks()>=1){
//               System.out.println(databaseArrayList.get(i).getNumBooks());
               System.out.println("deleting "+databaseArrayList.get(i).getTitle()+"  "+databaseArrayList.get(i).getAuthor());
                  int a =databaseArrayList.get(i).getNumBooks() -1;
                  databaseArrayList.get(i).setNumBooks(a);
                  break;
           }
       }
    }


    private void update(String title, String author, String ISBN, String Publication){
        int index = findIndexByFields(databaseArrayList, title, author);

        System.out.println("ENTER THE BOOK DETAILS TO BE CHANGED\n");
        Scanner scn = new Scanner(System.in);

        System.out.println("ENTER TITLE");
        title = scn.nextLine();
        databaseArrayList.get(index).setTitle(title);

        System.out.println("ENTER AUTHOR");
        author = scn.nextLine();
        databaseArrayList.get(index).setAuthor(author);

        System.out.println("ENTER ISBN");
        ISBN = scn.nextLine();
        databaseArrayList.get(index).setISBN(ISBN);

        System.out.println("ENTER PUBLICATION");
        Publication = scn.nextLine();
        databaseArrayList.get(index).setPublication(Publication);

        System.out.println("CHANGES UPDATED !!!");
    }

    private void display(){
        for (DATABASE_FIELDS val : databaseArrayList){
            System.out.print(val.getTitle() + " ");
            System.out.print(val.getAuthor() + " ");
            System.out.print(val.getPublication() + " ");
            System.out.print(val.getISBN() + " ");
            System.out.print(val.getNumBooks() + " ");
            System.out.println();
        }
    }
    private boolean Search(String title, String author, String ISBN, String Publication, String REASON){
        //TODO keep it on check
        if (REASON == "UPDATE") {
            for (DATABASE_FIELDS val : databaseArrayList) {
                if (val.getTitle().equalsIgnoreCase(title) && val.getAuthor().equalsIgnoreCase(author)) {
                    System.out.println("BOOK IS PRESENT");
                    return true;
                }
            }
        } else if (REASON == "SEARCH") {
            System.out.println();
            for (DATABASE_FIELDS val : databaseArrayList) {
                if (val.getTitle().equalsIgnoreCase(title) && val.getAuthor().equalsIgnoreCase(author)&&val.getNumBooks()>=1) {
                    print_decider=1;
                    System.out.println("BOOK IS PRESENT");
                    return true;
                }
            }
        }
        System.out.println("BOOK IS NOT FOUND");
        return false;
    }

    private boolean Search(String title, String author){
        //TODO keep it on check
//        int rool=1;
        for (DATABASE_FIELDS val : databaseArrayList){
            if (val.getTitle().equalsIgnoreCase(title) && val.getAuthor().equalsIgnoreCase(author)&&  val.getNumBooks()>=1){
//                print_decider=1;
                System.out.println("if "+print_decider);
//                System.out.println("BOOK IS PRESENT AND HAS BEEN REQUESTED");
                System.out.println("deleting");
                delete(title, author);

                return true;

            }
        }
//        System.out.println("i aslo "+print_decider);
        if(print_decider!=1) {
            System.out.println("BOOK IS NOT FOUND !!!");
        }
        print_decider=0;
//        System.out.println("near to false "+print_decider);
        return false;
    }

    private void animation() throws InterruptedException {
        for (int i = 0; i < 3; i++){
//            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
        }
        System.out.println();
    }

    private void LibrarianInput(){
        boolean valid = false;
        do {
            try {
                Scanner scn = new Scanner(System.in);
                System.out.println("ENTER TITLE");
                 title = scn.nextLine();

                System.out.println("ENTER AUTHOR");
                 author = scn.nextLine();

                System.out.println("ENTER ISBN");
                 ISBN = scn.nextLine();

                System.out.println("ENTER PUBLICATION");
                 Publication = scn.nextLine();
                valid = true;
            }catch (InputMismatchException e){
                System.out.println("WRONG INPUT");
            }
        }while (!valid);
    }

    private int findIndexByFields(ArrayList<DATABASE_FIELDS> books, String title, String author) {
        for (int i = 0; i < books.size(); i++) {
            DATABASE_FIELDS book = books.get(i);
            if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {
                return i; // Match found, return the index
            }
        }
        return -1; // Match not found, return -1
    }

    public static void main(String[] args) {
        //"Harry Potter","123", "12345", "JKR",5
        Database obj = new Database("Harry Potter","JKR", "BOOK", 1);
//        System.out.println(obj.BookFoundOfNot);
//        Database obj = new Database("Harry Potter", "JKR", );
//        obj.delete("harry potter","jkr");

    }
}
