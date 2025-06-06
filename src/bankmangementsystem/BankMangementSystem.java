package bankmangementsystem;

import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class BankMangementSystem {

    public static void main(String[] args) {

        String[] emails = new String[100];
        String[] passwords = new String[100];
        boolean[] status = new boolean[100];
        double[] balances = new double[100];
        String[][] history = new String[100][200];
        String[] requestNote = new String[100];
        int size = 0;

        // emails[0] = "Alex";
        // passwords[0] = "11";
        // status[0] = true;
        // emails[1] = "Aldren";
        // passwords[1] = "12";
        // status[1] = true;
        // emails[2] = "Jelou";
        // passwords[2] = "13";
        // status[2] = true;
        Scanner num = new Scanner(System.in);
        Scanner text = new Scanner(System.in);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        Date date = new Date();

        while (true) {
            try {

                System.out.println("\n----------Bank Management-----------");

                System.out.println("1. Create account");
                System.out.println("2. Login");
                System.out.println("3. Enter as Admin");
                System.out.println("0. Exit");
                System.out.print("Choice: ");

                switch (num.nextInt()) {
                    case 1:
                        while (true) {
                            System.out.println("\n----------Account Creation-----------");
                            System.out.print("Enter Username: ");
                            String email = text.nextLine();
                            System.out.print("Enter password: ");
                            String password = text.nextLine();

                            if (email.trim() == "" || password.trim() == "") {
                                System.out.println("\nPlease put valid username or password :<\n");
                                break;
                            }

                            int emailExist = -1;

                            for (int i = 0; i < emails.length; i++) {
                                if (email.equals(emails[i])) {
                                    System.out.println("\nUsername Exist. Failed to Create account\n");
                                    System.out.println("1. Try Again");
                                    System.out.println("0. Return");
                                    System.out.print("choice: ");
                                    if (num.nextInt() == 1) {
                                        emailExist = i;
                                        break;
                                    } else {
                                        emailExist = -2;
                                        break;
                                    }

                                }
                            }

                            if (emailExist == -1) {
                                emails[size] = email;
                                passwords[size] = password;
                                status[size] = true;
                                balances[size] = 0;

                                size++;
                                System.out.println("Account created successfully!");
                                System.out.println("");
                                break;
                            } else if (emailExist == -2) {
                                System.out.println("Exiting.....\n");
                                break;
                            }

                        }
                        break;
                    case 2:
                        System.out.println("\n----------Account Login-----------");
                        System.out.print("Enter Username: ");
                        String email = text.nextLine();
                        System.out.print("Enter password: ");
                        String password = text.nextLine();

                        int index = -1;
                        for (int i = 0; i < size; i++) {
                            if (emails[i] != null) {
                                if (emails[i].equals(email) && passwords[i].equals(password)) {
                                    index = i;
                                    break;
                                }
                            }
                        }
                        if (index == -1) {
                            System.out.println("\nInvalid Username or password!\n");
                            continue;
                        }

                        System.out.println("\nLogged in Successfully\n");

                        if (status[index] == false) {
                            System.out.println("\nAccount is Currently Disabled");
                            System.out.println("1. Request Account Reactivation");
                            System.out.println("0. Return");
                            System.out.print("choice: ");
                            int schoice = num.nextInt();

                            if (schoice == 1) {
                                try {
                                    if (requestNote[index] != null) {
                                        System.out.println("\nAccount Already Sent Request");
                                        System.out.println("1. Send new (will lose the other)");
                                        System.out.println("0. return");
                                        System.out.print("choice: ");
                                        int choice = num.nextInt();

                                        if (choice == 1) {
                                            System.out.print("\nAdd Note: ");
                                            requestNote[index] = text.nextLine();
                                            System.out.println("Request sent..\n");
                                        }
                                    } else {
                                        System.out.print("\nAdd Note: ");
                                        requestNote[index] = text.nextLine();
                                        System.out.println("Request sent..\n");

                                    }
                                } catch (Exception e) {
                                }
                            } else {
                                break;
                            }

                        }

                        while (status[index]) {
                            try {
                                System.out.println("\n-----------" + emails[index] + "-----------");
                                System.out.println("Balance: " + balances[index]);
                                System.out.println("");
                                System.out.println(
                                        "1. Deposit\n2. Withdraw\n3. Delete account\n4. View History\n0. Logout");
                                System.out.print("Enter Choice: ");
                                int action = text.nextInt();

                                if (action == 1) {
                                    while (true) {
                                        System.out.println("\n--------Deposit--------");
                                        System.out.print("Enter amount to deposit: ");
                                        double amount = num.nextDouble();
                                        if (amount <= 0) {
                                            System.out.println("You Entered Invalid Amount");
                                            System.out.println("1. Try Again");
                                            System.out.println("0. Back");
                                            System.out.print("choice: ");
                                            if (num.nextInt() == 1) {
                                                continue;
                                            } else {
                                                break;
                                            }
                                        }

                                        balances[index] += amount;
                                        System.out.println("Deposit successful! New balance: " + balances[index]);
                                        int historyCount = 0;

                                        for (int i = 0; i < history[index].length; i++) {
                                            if (history[index][i] != null) {
                                                historyCount++;
                                            }
                                        }

                                        history[index][historyCount] = "Doposited:  " + amount + " \t\tDate: "
                                                + dateFormatter.format(date);

                                        System.out.println("");
                                        break;
                                    }

                                } else if (action == 2) {
                                    while (true) {
                                        System.out.println("\n--------Withdraw--------");
                                        System.out.println("Current Balance: " + balances[index]);
                                        System.out.print("Enter amount to withdraw: ");
                                        double amount = num.nextDouble();

                                        if (amount <= 0) {
                                            System.out.println("You Entered Invalid Amount");
                                            System.out.println("1. Try Again");
                                            System.out.println("0. Back");
                                            System.out.print("choice: ");
                                            if (num.nextInt() == 1) {
                                                continue;
                                            } else {
                                                break;
                                            }
                                        }

                                        if (amount > balances[index]) {
                                            System.out.println("Insufficient balance!");
                                            System.out.println("");
                                        } else {
                                            balances[index] -= amount;
                                            System.out
                                                    .println("Withdrawal successful! New balance: " + balances[index]);

                                            int historyCount = 0;

                                            for (int i = 0; i < history[index].length; i++) {
                                                if (history[index][i] != null) {
                                                    historyCount++;
                                                }
                                            }

                                            history[index][historyCount] = "Withdrawed: " + amount + " \t\tDate: "
                                                    + dateFormatter.format(date);

                                            System.out.println("");
                                        }
                                        break;
                                    }

                                } else if (action == 3) {// gi usab niyag rewrite per owala gi apil tung latest
                                    // add confirmation
                                    System.out.print("\nEnter Password to Continue: ");
                                    String testPassowrd = text.next();
                                    System.out.println("");

                                    if (testPassowrd.equals(passwords[index])) {
                                        for (int i = index; i < size; i++) {
                                            if (emails[i] != null) {

                                                if (i == size - 1) {
                                                    emails[i] = null;
                                                    passwords[i] = null;
                                                    status[i] = false;
                                                    balances[i] = 0;
                                                    requestNote[i] = null;
                                                    for (int j = 0; j < history[i].length; j++) {
                                                        history[i + 1][j] = null;
                                                    }

                                                    break;
                                                }

                                                emails[i] = emails[i + 1];
                                                passwords[i] = passwords[i + 1];
                                                status[i] = status[i + 1];
                                                balances[i] = balances[i + 1];
                                                requestNote[i] = requestNote[i + 1];

                                                for (int j = 0; j < history[i].length; j++) {
                                                    if (j == history[i].length - 1) {
                                                        history[i][j] = history[i + 1][j];
                                                        break;
                                                    }
                                                    history[i][j] = history[i + 1][j];
                                                }

                                            }
                                        }
                                        size--;
                                        System.out.println("\nAccount Deleted Successfully...\n\n");
                                        break;

                                    } else {
                                        System.out.println("Incorrect Password, Failed to Delete Account..");
                                    }

                                } else if (action == 4) {
                                    System.out.println("\n-------HISTORY-------");
                                    for (int j = 0; j < history[index].length; j++) {
                                        if (history[index][j] != null) {
                                            System.out.println(history[index][j]);
                                        }
                                    }
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println(
                                        "\nError Occur, Possible cause\n1. Wrong inputs\n2. Not Integers might inputted");
                                num.nextLine();
                            }
                        }
                        break;

                    case 3:
                        boolean admin = true;

                        System.out.println("\n---------ADMIN LOGIN-----------");
                        System.out.print("username: ");
                        String testAdminUsername = text.nextLine().trim();
                        System.out.print("password: ");
                        String testAdminPassword = text.nextLine().trim();

                        if (!(testAdminUsername.equals("Administrator") && testAdminPassword.equals("admin123"))) {
                            System.out.println("\nIncorrect username or Password");
                            break;
                        }
                        System.out.println("\nLogged In Successully");
                        while (admin) {
                            try {
                                System.out.println("\n---------ADMIN-----------");
                                System.out.println("1. View Accounts");
                                System.out.println("2. Edit Account");
                                System.out.println("3. View Reactivation Requests");
                                System.out.println("0. Log out");
                                System.out.print("Your choice is? ");

                                int choiceA = num.nextInt();

                                switch (choiceA) {
                                    case 1:
                                        System.out.println(
                                                "\n---------------------Accounts--------------------------------------------\n");
                                        System.out.printf("%-20s %-20s %-20s %-20s", "Status", "Username", "Balance",
                                                "Password");
                                        System.out.println(
                                                "\n_________________________________________________________________________\n");
                                        for (int i = 0; i < emails.length; i++) {
                                            if (emails[i] != null) {
                                                System.out.printf("%-20s %-20s %-20s %-20s",
                                                        (status[i] == true ? "Active" : "Deactivated"), emails[i],
                                                        balances[i], passwords[i]);
                                                System.out.println("");
                                            }
                                        }
                                        System.out.println("\nTotal accounts: " + size);
                                        System.out.println("");
                                        System.out.println("");

                                        break;

                                    case 2:
                                        System.out.println("\n-----------Edit Account-----------\n");
                                        System.out.print("Enter Username: ");
                                        String testUsername = text.nextLine();

                                        int accountIndex = -1;

                                        for (int i = 0; i < size; i++) {
                                            if (testUsername.equals(emails[i])) {
                                                accountIndex = i;
                                                break;
                                            }
                                        }

                                        if (accountIndex == -1) {
                                            System.out.println("Account not Found\n");
                                            continue;
                                        }
                                        while (true) {
                                            try {
                                                System.out.println("\n-----------Account - " + emails[accountIndex]
                                                        + "-----------\n");
                                                System.out.println("Status: "
                                                        + (status[accountIndex] == true ? "Active" : "Deactivated"));
                                                System.out.println("Balance: " + balances[accountIndex]);
                                                System.out.println("");
                                                System.out.println("1. Change Balance");
                                                System.out.println("2. Activate Account");
                                                System.out.println("3. Deactivate Account");
                                                System.out.println("4. Delete Account");
                                                System.out.println("5. View History");
                                                System.out.println("0. Cancel");
                                                System.out.print("choice: ");

                                                switch (num.nextInt()) {
                                                    case 1:
                                                        System.out.print("\nNew Balance: ");
                                                        balances[accountIndex] = num.nextInt();
                                                        System.out.println("Balance Updated....\n");
                                                        continue;
                                                    case 2:
                                                        if (status[accountIndex] == true) {
                                                            System.out.println("\nAccount is already active\n");
                                                            continue;
                                                        }

                                                        status[accountIndex] = true;
                                                        System.out.println("\nAccount activated :D\n");

                                                        continue;
                                                    case 3:
                                                        if (status[accountIndex] == false) {
                                                            System.out.println("\nAccount is already Not Active\n");
                                                            continue;
                                                        }

                                                        status[accountIndex] = false;
                                                        requestNote[accountIndex] = null;
                                                        System.out.println("\nAccount Deactivated :< \n");

                                                        continue;
                                                    case 4:
                                                        System.out.print("\nEnter Accounts Password to Proceed: ");
                                                        String testPassword = text.nextLine();

                                                        if (!testPassword.equals(passwords[accountIndex])) {
                                                            System.out.println(
                                                                    "\nIncorrect Password. Failed to Delete Account\n");
                                                            continue;
                                                        }

                                                        for (int i = accountIndex; i < size; i++) {
                                                            if (emails[i] != null) {

                                                                if (i == size - 1) {
                                                                    emails[i] = null;
                                                                    passwords[i] = null;
                                                                    status[i] = false;
                                                                    balances[i] = 0;
                                                                    requestNote[i] = null;
                                                                    for (int j = 0; j < history[i].length; j++) {
                                                                        history[i + 1][j] = null;
                                                                    }

                                                                    break;
                                                                }

                                                                emails[i] = emails[i + 1];
                                                                passwords[i] = passwords[i + 1];
                                                                status[i] = status[i + 1];
                                                                balances[i] = balances[i + 1];
                                                                requestNote[i] = requestNote[i + 1];

                                                                for (int j = 0; j < history[i].length; j++) {
                                                                    if (j == history[i].length - 1) {
                                                                        history[i][j] = history[i + 1][j];
                                                                        break;
                                                                    }
                                                                    history[i][j] = history[i + 1][j];
                                                                }

                                                            }
                                                        }
                                                        size--;
                                                        System.out.println("\nAccount Deleted Successfully...\n\n");
                                                        break;

                                                    case 5:
                                                        System.out.println("\n------------------HISTORY: "
                                                                + emails[accountIndex] + "------------------\n");
                                                        for (int i = 0; i < history[accountIndex].length; i++) {
                                                            if (history[accountIndex][i] != null) {
                                                                System.out.println(history[accountIndex][i]);
                                                            }
                                                        }
                                                        continue;
                                                    default:
                                                        break;
                                                }
                                                break;
                                            } catch (Exception e) {
                                                System.out.println(
                                                        "\nError Occur, Possible cause\n1. Wrong inputs\n2. Not Integers might inputted");
                                                num.nextLine();
                                            }
                                        }

                                        // for (int j = 0; j < emails.length; j++) {
                                        // if (emailEdit.equals(emails[j])) {
                                        // System.out.println("----------Current Balance-------------");
                                        // System.out.println("Current Balance: " + balances[j]);
                                        // System.out.print("Change the balance into: ");
                                        // balances[j] = num.nextDouble();
                                        // System.out.println("Balance Updated :D");
                                        //
                                        // break;
                                        // } else if (j == emails.length) {
                                        // System.out.println("No account have been found!");
                                        // }
                                        // }
                                        break;

                                    case 3:
                                        while (true) {
                                            try {
                                                System.out.println("\n-----------Activation Requests-----------\n");
                                                int requestFound = -1;

                                                boolean[] requestsIndices = new boolean[100];

                                                for (int i = 0; i < requestNote.length; i++) {
                                                    if (requestNote[i] != null) {

                                                        System.out.printf("%-20s %-20s %-20s", "Account Number",
                                                                "Username", "Request Note");
                                                        System.out
                                                                .println("\n-----------------------------------------");
                                                        System.out.printf("%-20s %-20s %-20s", i, emails[i],
                                                                requestNote[i]);
                                                        System.out.println("");
                                                        requestsIndices[i] = true;
                                                        requestFound++;
                                                    }
                                                }

                                                if (requestFound == -1) {
                                                    System.out.println("NO REQUESTS YET.\n");
                                                    break;
                                                }

                                                System.out.println("\n---------------------------------");
                                                System.out.println("1. Select the Accounts Number to Reactivate");
                                                System.out.println("0. Back");
                                                System.out.print("choice: ");
                                                int choice = num.nextInt();

                                                if (choice == 1) {
                                                    System.out.print("\nAccount Number: ");
                                                    int accountNumber = num.nextInt();
                                                    if (requestsIndices[accountNumber]) {
                                                        status[accountNumber] = true;
                                                        requestNote[accountNumber] = null;
                                                        requestsIndices[accountNumber] = false;
                                                        System.out.println("Account Reactivated Successfully\n");
                                                    } else {
                                                        System.out.println("Account did not Request");
                                                    }

                                                } else {
                                                    break;
                                                }
                                            } catch (Exception e) {

                                            }
                                        }

                                        break;
                                    default:
                                        admin = false;
                                }
                            } catch (Exception e) {
                                num.nextLine();
                            }
                        }
                        break;

                    case 0:
                        num.close();
                        text.close();
                        System.exit(0);
                    default:
                        System.out.println("Please input choice that from 1 to 4");
                        break;
                }
            } catch (Exception e) {
                System.out.println("\nError Occur, Possible cause\n1. Wrong inputs\n2. Not Integers might inputted");
                num.nextLine();
            }
        }
    }

}

/*
 * TO CONSIDER
 * 
 * [CANCELLED]if ma deactivate imong account kay mag bayad ka para ma reactivate
 * admin can reactivate and deactivate your account didtu sa view account
 * dapat dili ka delete if naay pay balance ang account
 * recover account
 * 
 * 
 * 
 */
