
/**
 * Java Course 4, Module 3
 * 
 * Norima Java Developer Course Capstone Project
   *
 * @author Jhumel M. Bonganciso
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;



public class PolicyAndClaimsAdministrationSystem {
    // Scanner
    Scanner scnr = new Scanner(System.in);

    // ArrayList of customer account
    ArrayList<CustomerAccount> customerAccounts;

    // Constructor method
    PolicyAndClaimsAdministrationSystem() {

    }

    PolicyAndClaimsAdministrationSystem(ArrayList<CustomerAccount> customerAccounts) {
        this.customerAccounts = customerAccounts;
    }

    // Menu method
    public void menu() {
        do {
            menuText();
            int choice = 1;
            boolean isCorrect = false;
            do {
                try {
                    System.out.print("Choice ==> ");
                    choice = scnr.nextInt();
                    isCorrect = true;
                } catch (Exception e) {
                    scnr.next();
                    clearScreen();
                    System.out.println("\nInvalid input. Please try again.\n");
                    menuText();
                }
            } while (!isCorrect);

            if (choice > 0 && choice < 9) {
                switch (choice) {
                    case 1 -> newAccount();
                    case 2 -> getQuoteAndBuyPolicy();
                    case 3 -> cancelPol();
                    case 4 -> fileClaim();
                    case 5 -> searchAccount();
                    case 6 -> searchAndDisplayPolicy();
                    case 7 -> searchAndDisplayClaim();
                    case 8 -> {

                        // Scanner closing
                        scnr.close();
                        for (CustomerAccount customerAccount : customerAccounts) {
                            customerAccount.scnnr.close();
                            for (int i = 0; i < customerAccount.claims.size(); i++) {
                                customerAccount.claims.get(i).scanner.close();
                            }
                            for (int i = 0; i < customerAccount.policyCollection.size(); i++) {
                                customerAccount.policyCollection.get(i).s.close();
                                for (int j = 0; j < customerAccount.policyCollection.get(i).vehicles.size(); j++) {
                                    customerAccount.policyCollection.get(i).vehicles.get(j).sc.close();
                                }
                            }
                            for (int i = 0; i < customerAccount.policyHolders.size(); i++) {
                                customerAccount.policyHolders.get(i).scanner.close();
                            }

                        }
                        System.exit(0);
                    }
                }

            } else {
                clearScreen();
                System.out.println("Invalid input");
            }
        } while (true);
    }// End of Main

    // Menu text
    private void menuText() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("\n\nDate today: " + calendar.getTime());
        System.out.println("\nAutomobile Insurance Policy and Claims Administration System (PAS)\n");
        System.out.println("[1] Create account");
        System.out.println("[2] Get a Policy quote and buy a policy");
        System.out.println("[3] Cancel a specific policy");
        System.out.println("[4] File an accident claim against a policy.");
        System.out.println("[5] Search for a Customer account ");
        System.out.println("[6] Search for and display a specific policy");
        System.out.println("[7] Search for and display a specific claim");
        System.out.println("[8] Exit the system");
    }

    // Clear screen method
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // New account method
    public void newAccount() {
        clearScreen();
        boolean isCorrect = false;
        int choice = 0;
        do {
            try {
                // User prompt for new account choice
                System.out.println("[1] Enter new customer account");
                System.out.println("[2] Enter Policy Holder for customer account");
                System.out.println("\n[0] Go back to main menu\n");
                System.out.print("Choice ==> ");
                choice = scnr.nextInt();

                // Choice validator
                if (choice >= 0 && choice < 3) {
                    isCorrect = true;
                    if (choice == 0) {
                        clearScreen();
                        break;
                    }
                } else {
                    System.out.println();
                }

            } catch (Exception e) {
                scnr.next();
                System.out.println("Invalid Input. Please try again");
            }
        } while (!isCorrect);

        // If valid go here
        switch (choice) {
            case 1 -> {
                boolean isExisting = false;
                clearScreen();
                // If there is no accounts existing, add here
                if (customerAccounts.isEmpty()) {
                    customerAccounts.add(new CustomerAccount());
                    if(!customerAccounts.get(customerAccounts.size() - 1).getIsCancelled()) {
                        System.out.println("\nAccount added successfully!\n");
                    } else {
                        System.out.println("Account adding cancelled.");
                        customerAccounts.remove(customerAccounts.get(customerAccounts.size() - 1));
                    }
                } else {
                    // If customer account is not empty, validate if user is already added here
                    customerAccounts.add(new CustomerAccount());
                    if(!customerAccounts.get(customerAccounts.size() - 1).getIsCancelled()) {
                        System.out.println("\nAccount added successfully!\n");
                    } else {
                        System.out.println("Account adding cancelled.");
                        customerAccounts.remove(customerAccounts.get(customerAccounts.size() - 1));
                        break;
                    }
                    for (CustomerAccount customerAccount : customerAccounts) {
                        if (customerAccount.getFirstname()
                                .equalsIgnoreCase(customerAccounts.get(customerAccounts.size() - 1).getFirstname())
                                && customerAccount.getLastName().equalsIgnoreCase(
                                        customerAccounts.get(customerAccounts.size() - 1).getLastName())) {
                            isExisting = true;
                        }
                    }
                }

                // If user is existing, they will be remove from the system
                if (isExisting) {
                    clearScreen();
                    System.out.println("\nCustomer is already existing. This account will be remove to the system.");
                    customerAccounts.remove(customerAccounts.get(customerAccounts.size() - 1));
                }

                isExisting = false;
                isCorrect = false;

                // Prompt for adding new customer account or going back to main menu
                do {

                    System.out.println("\nDo you want to enter another customer account?");
                    System.out.println("Enter Y for yes or N to go back to main menu ==> ");
                    String yOrN = scnr.next();

                    // If yes, add new one with validation again.
                    if (yOrN.equalsIgnoreCase("y")) {
                        clearScreen();
                        customerAccounts.add(new CustomerAccount());
                        if(!customerAccounts.get(customerAccounts.size() - 1).getIsCancelled()) {
                            System.out.println("\nAccount added successfully!\n");
                        } else {
                            System.out.println("Account adding cancelled.");
                            customerAccounts.remove(customerAccounts.get(customerAccounts.size() - 1));
                            break;
                        }

                        for (CustomerAccount customerAccount : customerAccounts) {
                            if (customerAccount.getFirstname()
                                    .equalsIgnoreCase(customerAccounts.get(customerAccounts.size() - 1).getFirstname())
                                    && customerAccount.getLastName().equalsIgnoreCase(
                                            customerAccounts.get(customerAccounts.size() - 1).getLastName())) {
                                isExisting = true;
                            }
                        }
                        if (isExisting) {
                            clearScreen();
                            System.out.println(
                                    "\nCustomer is already existing. This account will be remove to the system.");
                            customerAccounts.remove(customerAccounts.get(customerAccounts.size() - 1));
                        }
                        // If no, go back to main menu
                    } else if (yOrN.equalsIgnoreCase("n")) {
                        isCorrect = true;
                        clearScreen();
                        break;
                    }
                } while (!isCorrect);

            }

            // Adding of policy holder to existing account
            case 2 -> {
                isCorrect = false;
                do {
                    try {
                        // User prompt if account is existing
                        System.out.print("Enter 4 digit account number ==> ");
                        choice = scnr.nextInt();
                        isCorrect = true;
                    } catch (Exception e) {
                        scnr.nextLine();
                        clearScreen();
                        System.out.println("\nInvalid Input. Please try again\n");
                    }
                } while (!isCorrect);
                boolean isExisting = false;
                isCorrect = false;

                // Checker to see if account is existing
                for (CustomerAccount customerAccount : customerAccounts) {

                    // If account is existing
                    if (choice == customerAccount.getAccountNumber()) {
                        isExisting = true;
                        clearScreen();
                        // Prompt to add new policy holder
                        // If is cancelled is true, break. And remove the added policy holder from the array list is isCancelled == true;
                        customerAccount.policyHolders.add(new PolicyHolder());
                        if (customerAccount.policyHolders.get(0).isCancelled) {
                            customerAccount.policyHolders.remove(customerAccount.policyHolders.get(customerAccount.policyHolders.size() - 1));
                            break;
                        } 
                        // Otherwise, add it successfully
                        else {
                            System.out.println("\nPolicy Holder added successfully!\n");
                        }

                        // User prompt if they wanted to put another policy holder
                        do {
                            System.out.println("Do you wanted to put another policy holder?");
                            System.out.println("Enter Y for yes or N to go back to main menu");
                            String yOrN = scnr.next();
                            if (yOrN.equalsIgnoreCase("y")) {
                                customerAccount.policyHolders.add(new PolicyHolder());
                                System.out.println("\nPolicy Holder added successfully!\n");
                            } else if (yOrN.equalsIgnoreCase("n")) {
                                isCorrect = true;
                                clearScreen();
                                break;
                            }
                        } while (!isCorrect);
                        break;
                    }
                } // End of For each loop

                // If account is not existing this will be the user prompts
                if (!isExisting) {
                    clearScreen();
                    System.out.println("\nAccount do not exist\n");
                }
            }

        }
    }

    // Get quote method
    public void getQuoteAndBuyPolicy() {
        clearScreen();
        boolean isCorrect = false;
        boolean isExisting = false;
        int choice = 0;
        do {
            try {
                clearScreen();
                // User prompt for customer account
                System.out.println("Enter customer account (or enter 0(zero) to go back to main menu)");
                System.out.print("Choice ==> ");
                choice = scnr.nextInt();

                isCorrect = true;

            } catch (Exception e) {
                scnr.next();
                System.out.println("Invalid input. Please try again");
            }
        } while (!isCorrect);

        // For each loop to see if customer with user prompt is existing
        for (CustomerAccount customerAccount : customerAccounts) {

            // If customer account number is same with user input
            if (choice == customerAccount.getAccountNumber()) {
                isExisting = true;

                // if policy holder under account is empty
                if (customerAccount.policyHolders.isEmpty()) {
                    clearScreen();
                    System.out.println("Please enroll policy holder first");
                    customerAccount.policyHolders.add(new PolicyHolder());
                    if (customerAccount.policyHolders.get(0).isCancelled) {
                        clearScreen();
                        customerAccount.policyHolders.remove(customerAccount.policyHolders.get(customerAccount.policyHolders.size() - 1));
                        break;

                        // Otherwise, add it successfully
                    } else {
                        System.out.println("\nPolicy Holder added successfully!\n");
                        customerAccount.policyCollection
                                .add(new Policy(customerAccount.policyHolders.get(0), customerAccounts));

                        isCorrect = false;

                        // if vehicle under policy is not empty prompt if they will proceed with the policy
                        if (!customerAccount.policyCollection
                                .get(customerAccount.policyCollection.size() - 1).vehicles.isEmpty()) {
                            do {
                                try {
                                    System.out.println(
                                            "Press 1 to add this to your policy or Press 2 to cancel");

                                    choice = scnr.nextInt();
                                    if (choice == 1 || choice == 2) {
                                        isCorrect = true;
                                        break;
                                    }

                                } catch (Exception e) {
                                    scnr.nextLine();
                                    System.out.println("Invalid input");
                                }
                            } while (!isCorrect);
                            isCorrect = false;
                            if (choice == 1) {
                                // If choice is 1 add it to policy
                                System.out.println("\nPolicy Added Successfully!\n");
                                break;
                                // Else remove it to policy
                            } else if (choice == 2) {
                                System.out.println("\nPolicy is not added on account.\n");
                                customerAccount.policyCollection
                                        .remove(customerAccount.policyCollection.size() - 1);
                            }
                        } else {
                            customerAccount.policyCollection
                                    .remove(customerAccount.policyCollection.size() - 1);
                        }
                    }

                } else {
                    isCorrect = false;
                    do {
                        try {
                            clearScreen();
                            // If there is existing policy holder
                            System.out.println("Please choose policy holder to enroll");
                            System.out.println("#\tName");
                            for (int j = 0; j < customerAccount.policyHolders.size(); j++) {
                                System.out.println(
                                        j + "\t" + customerAccount.policyHolders.get(j).toString());
                            }
                            System.out.print("# ==> ");
                            choice = scnr.nextInt();
                            isCorrect = true;
                        } catch (Exception e) {
                            scnr.next();
                            System.out.println("Invalid input. Please try again");
                        }
                    } while (!isCorrect);
                    // Checker to see if user input is valid
                    if (choice >= 0 && choice < customerAccount.policyHolders.size()) {

                        // Add new policy
                        customerAccount.policyCollection.add(new Policy(
                                customerAccount.policyHolders.get(choice), customerAccounts));

                        isCorrect = false;
                        if (!customerAccount.policyCollection
                                .get(customerAccount.policyCollection.size() - 1).vehicles.isEmpty()) {
                            do {
                                try {
                                    System.out.println(
                                            "Press 1 to add this to your policy or Press 2 to cancel");
                                    System.out.println("Choice ==> ");
                                    choice = scnr.nextInt();
                                    if (choice == 1 || choice == 2) {
                                        isCorrect = true;
                                        break;
                                    }

                                } catch (Exception e) {
                                    scnr.next();
                                    System.out.println("Invalid input");
                                }
                            } while (!isCorrect);
                            if (choice == 1) {
                                isCorrect = false;
                                System.out.println("\nPolicy Added Successfully!\n");
                                break;
                            } else if (choice == 2) {
                                System.out.println("\nPolicy is not added on account.\n");
                                customerAccount.policyCollection
                                        .remove(customerAccount.policyCollection.size() - 1);
                            }
                        }
                        // If vehicle is empty, remove the added policy
                        else {
                            customerAccount.policyCollection
                                    .remove(customerAccount.policyCollection.size() - 1);
                        }
                    } else {
                        System.out.println("Invalid. Will go back to main menu.");
                    }
                }
            }

        }

        // If choice == 0, go back to main menu
        if (choice == 0) {
            clearScreen();
            return;
        }
        if (!isExisting) {
            System.out.println("\nCannot find customer account.\n");
        }
    }

    // Cancel policy method
    public void cancelPol() {
        clearScreen();
        boolean isExisting = false;
        boolean isCorrect = false;
        int choice = 0;
        do {
            try {
                // Enter policy number prompt
                System.out.println(
                        "\nEnter policy number to cancel or enter 0(zero) to go back to main menu.");
                System.out.print("Choice ==> ");
                choice = scnr.nextInt();
                isCorrect = true;
            } catch (Exception e) {
                scnr.next();
                clearScreen();
                System.out.println("\nInvalid input. Please try again\n");
            }
        } while (!isCorrect);

        // For each loop for customer account
        for (CustomerAccount customerAccount : customerAccounts) {
            for (int i = 0; i < customerAccount.policyCollection.size(); i++) {

                // if policy number == choice, remove the policy
                if (customerAccount.policyCollection.get(i).getPolicyNumber() == choice) {
                    customerAccount.policyCollection.remove(i);
                    System.out.println("\nPolicy Removed Successfully!\n");
                    isExisting = true;
                }
            }
        }
        if (choice == 0) {
            clearScreen();
            return;
        }
        if (!isExisting) {
            System.out.println("Policy do not exist");
        }
    }

    // File claim method
    public void fileClaim() {
        clearScreen();
        boolean isExisting = false;
        boolean isCorrect = false;
        int choice = 0;
        do {
            try {
                System.out.println(
                        "\nEnter policy number to cancel or enter 0(zero) to go back to main menu.");
                System.out.print("Choice ==> ");
                choice = scnr.nextInt();
                isCorrect = true;
            } catch (Exception e) {
                scnr.next();
                clearScreen();
                System.out.println("Invalid input. Please try again");
            }
        } while (!isCorrect);
        for (CustomerAccount customerAccount : customerAccounts) {
            for (int i = 0; i < customerAccount.policyCollection.size(); i++) {
                if (choice == customerAccount.policyCollection.get(i).getPolicyNumber()) {
                    clearScreen();
                    customerAccount.claims.add(new Claim(customerAccount.policyCollection.get(i)));
                    System.out.println("\nClaim added successfully!\n");
                    isExisting = true;
                }
            }
        }
        if (choice == 0) {
            clearScreen();
            return;
        }
        if (!isExisting) {
            System.out.println("Policy do not exist.");
        }
    }

    // Search account method
    public void searchAccount() {
        clearScreen();
        boolean isExisting = false;

        // User prompts
        System.out.print("First Name of the Customer or enter 0(zero) to go back to main menu ==> ");
        String name = scnr.next();

        // For each loop for customer account
        for (CustomerAccount customerAccount : customerAccounts) {

            // Checker if first name is existing. 
            if (name.equalsIgnoreCase(customerAccount.getFirstname())) {
                isExisting = true;

                // If existing prompt for last time 
                System.out.print("Enter Last name ==> ");
                name = scnr.next();

                // If last name exist that is associated with existing firstname
                if (name.equalsIgnoreCase(customerAccount.getLastName())) {

                    // To string for customer account
                    System.out.println(customerAccount.toString());
                    System.out.println("Policies owned");
                    for (int i = 0; i < customerAccount.policyCollection.size(); i++) {
                        System.out.println((i + 1) + ". Policy number: "
                                + customerAccount.policyCollection.get(i).getPolicyNumber());
                    }
                    System.out.println("Policy Holders enrolled: ");
                    for (int i = 0; i < customerAccount.policyHolders.size(); i++) {
                        System.out.println(
                                (i + 1) + " " + customerAccount.policyHolders.get(i).toString());
                    }
                } else {
                    System.out.println("Customer do not exist");
                }
            }
        }
        if (name.equals("0")) {
            clearScreen();
            return;
        }
        if (!isExisting) {
            System.out.println("Customer do not exist");
        }
    }

    // Search and display policy method
    public void searchAndDisplayPolicy() {
        boolean isExisting = false;
        boolean isCorrect = false;
        int choice = 0;
        do {
            try {
                clearScreen();
                System.out.println("Enter Policy number or enter 0(zero) to go back to main menu:");
                System.out.print("Choice ==> ");
                choice = scnr.nextInt();
                isCorrect = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again");
            }
        } while (!isCorrect);

        for (CustomerAccount customerAccount : customerAccounts) {
            for (int i = 0; i < customerAccount.policyCollection.size(); i++) {
                if (choice == customerAccount.policyCollection.get(i).getPolicyNumber()) {
                    System.out.println(customerAccount.policyCollection.get(i).toString());
                    for (int j = 0; j < customerAccount.policyCollection.get(i).vehicles.size(); j++) {
                        String vehicleString = customerAccount.policyCollection.get(i).vehicles
                                .toString().replace("[", "");
                        vehicleString = vehicleString.replace("]", "");
                        System.out.println(vehicleString);
                    }
                    isExisting = true;
                }
            }
        }

        if (choice == 0) {
            clearScreen();
            return;
        }
        if (!isExisting) {
            System.out.println("Policy do not exist.");
        }
    }

    // Search and display Claim method
    public void searchAndDisplayClaim() {
        clearScreen();
        boolean isExisting = false;
        String claimInput;
        System.out.println("Enter claim number or enter 0(zero) to go back to main menu");
        System.out.print("Choice ==> ");
        claimInput = scnr.next();
        for (CustomerAccount customerAccount : customerAccounts) {
            for (int i = 0; i < customerAccount.claims.size(); i++) {
                if (claimInput.equals(customerAccount.claims.get(i).getClaimNumber())) {
                    System.out.println(customerAccount.claims.get(i).toString());
                    isExisting = true;
                }
            }
        }
        if (claimInput.equals("0")) {
            clearScreen();
            return;
        }
        if (!isExisting) {
            System.out.println("Claim do not exist.");
        }
    }
}
