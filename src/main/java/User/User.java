package User;

import java.util.ArrayList;
import java.util.Scanner;
// import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;

import Account.Accounts;
import Book.Books;

public class User {
    private static ArrayList<UID> Name = new ArrayList<UID>();
    static int vali = 0;
    static private int idd = 102;
    static int Show_duedt_var = -1;
    private Accounts user1_acc;
    private int user_id;
    private String UserName;
    private Scanner sc = new Scanner(System.in);

    public User() {
        // Name array initialization
        if (vali == 0) {
            UID us1 = new UID();
            us1.Name = "Nayan";
            us1.id = 100;
            Name.add(us1);
            UID us = new UID();
            us.Name = "Divyansh";
            us.id = 101;
            Name.add(us);
            vali++;
        }

    }

    public void Add_New_User(String U) {
        UID us3 = new UID();
        us3.Name = U;
        us3.id = idd;
        idd++;
        Name.add(us3);
        System.out.println("YOUR ID IS : " + us3.id);
    }

    // verification of user
    public boolean Verify(String Username) throws InterruptedException {
        // user1_acc=new Accounts();
        this.UserName = Username;
        int id_begger = 0;
        boolean uid_verify = true;
        // Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the id of user ");
        // if user enter wrong id for reattempts
        while (uid_verify) {
            if (id_begger >= 1) {
                int select_option_for_Verify;
                System.out.println("\n Chosse option :");
                System.out.println("\n 1. Reenter your ID :");
                System.out.println("\n 2. Exit ");
                select_option_for_Verify = sc.nextInt();
                if (select_option_for_Verify == 2) {
                    uid_verify = false;
                    return false;
                }

            }
            user_id = sc.nextInt();
            System.out.println("PLEASE WAIT WE ARE VERIFING YOU....WAIT");
            try {
                // TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println(Name.size() + " size is : ");
            for (int i = 0; i < Name.size(); i++) {

                // System.out.println("user ent : "+Username+" id: "+user_id);
                // System.out.println("user ent : "+Name.get(i).Name+" id: "+Name.get(i).id);
                if ((Name.get(i).Name).equalsIgnoreCase(Username) && Name.get(i).id == user_id) {
                    Show_duedt_var = i;
                    try {
                        if (Account.Accounts.indexfinder(Username, user_id, 0) == -1) {
                            user1_acc = new Accounts();
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println("SOME ERROR IS OCCURED ");
                    }

                    System.out.println("\nUSER IS VERIFIED ");

                    try {
                        uid_verify = false;
                        return true;
                    } finally {
                        int option;
                        boolean looping = true;
                        while (looping) {
                            try {
                                // TimeUnit.SECONDS.sleep(2);
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                            System.out.println("\n \n CHOSSE OPTION: ");
                            System.out.println("1.CHECK YOUR ACCOUNT INFORMATION Check : ");
                            System.out.println("2.GET BOOK INFORMATION : ");
                            System.out.println("3.EXIT : ");
                            option = sc.nextInt();
                            System.out.println("YOU ENTERED : " + option);
                            System.out.println("PLEASE WAIT WE ARE PROCESSING YOUR DATA...");
                            try {
                                // TimeUnit.SECONDS.sleep(2);

                                if (option == 1) {
                                    CheckAccount();
                                } else if (option == 2) {
                                    get_book_info();
                                } else if (option == 3) {
                                    looping = false;
                                    // uid_verify = false;

                                } else {

                                    System.out.println("\n!!!!  ENETER VALID INPUT !!! \n");
                                }
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                        }
                    }

                }
            }
            id_begger += 1;
        }
        System.out.println("\nUSER IS NOT VERIFIED ");
        return false;
    }

    // checking account user detail if it's verified
    private void CheckAccount() throws InterruptedException {
        System.out.println("\n\n NOW YOU R IN CHECKACCOUNT ");
        // System.out.println("ENTER CHOICE : ");
        // System.out.println("CALCULATE FINE : ");
        try {
            user1_acc.AccountDetails(UserName, user_id);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
    // private

    // getting information about book
    private void get_book_info() {
        int option_U;

        System.out.println("\n\n NOW YOU R IN GET_BOOK_INFO ");
        Books boookinfo = new Books();
        boolean looping = true;
        while (looping) {
            try {
                // TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println(" CHOOSE OPTION : ");
            System.out.println(" 1. DUE DATE INFORMATION : ");
            System.out.println(" 2. BOOK REQUEST : ");
            System.out.println(" 3. RENEW INFORMATION(RENEW YOUR BOOK ) :");
            System.out.println(" 4. EXIT  : ");
            option_U = sc.nextInt();
            System.out.println("YOU ENTERDED : " + option_U);
            System.out.println("WAIT ....");
            try {
                // TimeUnit.SECONDS.sleep(2);

                if (option_U == 1) {
                    // System.out.println(" i am in oprion 1"+(UserName)+" "+(user_id)+ "
                    // "+Account.Accounts.indexfinder(UserName, user_id,1));
                    // System.out.println(Account.Accounts.all_data_of_Accounts.get(0).u);
                    try {
                        if (Account.Accounts.indexfinder(UserName, user_id, 1) != -1) {

                            boookinfo.Show_duedt(Account.Accounts.indexfinder(UserName, user_id, 1), user1_acc);
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                } else if (option_U == 2) {
                    // System.out.println(" i am here \n");
                    boookinfo.Book_Request(UserName, user_id);

                } else if (option_U == 3) {
                    try {
                        if (Account.Accounts.indexfinder(UserName, user_id, 1) != -1) {
                            boookinfo.Renw_info(user1_acc);
                        } else {
                            // System.out.println("YOU DON'T HAVE BOOK");
                        }
                        //
                    } catch (Exception e) {
                        System.out.println("MAY BR DOES NOT HAVE ANY BOOK ");
                    }

                } else if (option_U == 4) {
                    System.out.println("\n THANK YOU HAVE A NICE DAY ......\n");
                    try {
                        // TimeUnit.SECONDS.sleep(2);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    looping = false;
                } else {
                    System.out.println("\n!!!!  ENETER VALID INPUT !!! \n");
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        // User u1 = new User();
        // System.out.println("afetr \n");
        User u2 = new User();

        int VALID = 1;
        Scanner sc1 = new Scanner(System.in);
        boolean h = true;
        while (h) {
            System.out.println(u2.Verify("DivyANSH"));
            System.out.println("\n \n \n NAYan is called ");
            System.out.println(u2.Verify("NAyan"));
            System.out.println("Press one for contine \n");
            VALID = sc1.nextInt();
            if (VALID != 1) {
                h = false;
            }

        }
        // u1.CheckAccount();;
        // u1.get_book_info();
    }
}