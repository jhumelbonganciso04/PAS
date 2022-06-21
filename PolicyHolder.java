
/**
 * Java Course 4, Module 3
 * 
 * Norima Java Developer Course Capstone Project
   *
 * @author Jhumel M. Bonganciso
 */

import java.util.ArrayList;
import java.util.Scanner;

public class PolicyHolder {
    // Scanner class
    Scanner scanner = new Scanner(System.in);

    // Policy holder variables
     private String firstName;
     private String lastName;
     private String dob;
     private String address;
     private  String licenseNumber;
     private int licensedIssuedYear;
    boolean isCreated = false;
     double dlx;
    boolean isCancelled = false;
    //  Arraylist of vehicles
    ArrayList<Vehicle> vehicles = new ArrayList<>();

    // Policy Holder constructor
    public PolicyHolder() {
        load();
    }

    // Load method for user prompt.
    public void load(){
      
        boolean isCorrect = false;
        System.out.println("Policy Holder Information or enter 0(zero) to go back to main menu");
        System.out.print("First name ==> ");
        firstName = scanner.nextLine();
        if(firstName.equals("0")) {
            isCancelled = true;
            return;
        }
        System.out.print("Last name ==> ");
        lastName = scanner.nextLine();
        if(lastName.equals("0")) {
            isCancelled = true;
            return;
        }
        System.out.print("Date of Birth ==> ");
        dob = scanner.nextLine();
        if(dob.equals("0")) {
            isCancelled = true;
            return;
        }
        System.out.print("Address ==> ");
        address = scanner.nextLine();
        if(address.equals("0")) {
            isCancelled = true;
            return;
        }
        System.out.print("Licensed number ==> ");
        licenseNumber = scanner.nextLine();
        if(licenseNumber.equals("0")) {
            isCancelled = true;
            return;
        }
        do{
            try {
                System.out.print("Licensed issued year ==> ");
                licensedIssuedYear = scanner.nextInt();
                
                if(licensedIssuedYear >= 2000 & licensedIssuedYear <= 2022) {
                    isCorrect = true;
                } else if (licensedIssuedYear == 0) {
                    isCancelled = true;
                    return;
                }else {
                    System.out.println("Please enter year from 2000 - 2022 only.");
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Please enter year from 2000 - 2022 only.");
            }
        } while (!isCorrect);

        
        setDlx(2023 - licensedIssuedYear);
        
    }

    public boolean getIsCancelled(){
        return isCancelled;
    }
    // Get DLX method
    public double getDlx() {
        return dlx;
    }

    // Set dlx method
    public void setDlx(double dlx) {
        this.dlx = dlx;
    }

    // Get first name method
    public String getFirstName() {
        return firstName;
    }

    // Get last name method
    public String getLastName() {
        return lastName;
    }
    // Get date of birth method
    public String getDob() {
        return dob;
    }

    // Get address method
    public String getAddress() {
        return address;
    }

    // Get license number method
    public String getLicenseNumber() {
        return licenseNumber;
    }

    // To string method
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
