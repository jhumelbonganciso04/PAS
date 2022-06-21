/**
 * Java Course 4, Module 3
 * 
 * Norima Java Developer Course Capstone Project
   *
 * @author Jhumel M. Bonganciso
 */

import java.util.Scanner;

public class AccountOwner {

//    Account owner variables
     private String firstname, lastName, address;
     boolean isCancelled = false;

//   Scanner open
    Scanner scnnr = new Scanner(System.in);

    // Constructor
    public AccountOwner() {
    }

    // Get first name method
    public String getFirstname() {
        return firstname;
    }

    // Set first name method
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    // Get last name method
    public String getLastName() {
        return lastName;
    }

    // Set last name method
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Get address method
    public String getAddress() {
        return address;
    }

    // set address method
    public void setAddress(String address) {
        this.address = address;
    }

    // Load method that prompts the user for values
    public void load() {
        System.out.println("\nAdding customer account\n");
        System.out.print("First Name or enter 0(zero) to cancel ==> ");
        firstname = scnnr.nextLine();
        if(firstname.equals("0")) {
            isCancelled = true;
            return;
        }
        System.out.print("Last name or enter 0(zero) to cancel ==> ");
        
        lastName = scnnr.nextLine();
        if(lastName.equals("0")) {
            isCancelled = true;
            return;
        }
        System.out.print("Address or enter 0(zero) to cancel ==> ");
        address = scnnr.nextLine();
        if(address.equals("0")) {
            isCancelled = true;
            return;
        }
    }

    public boolean getIsCancelled(){
        return isCancelled;
    }

    // To string method
    @Override
    public String toString() {
        return  "\nFirst name: " + firstname  +
                "\nLast Name:" + lastName  +
                "\nAddress: " + address;
    }
}
