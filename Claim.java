/**
 * Java Course 4, Module 3
 * 
 * Norima Java Developer Course Capstone Project
   *
 * @author Jhumel M. Bonganciso
 */

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Claim {
    // Scanner
    Scanner scanner = new Scanner(System.in);

    Date today = new Date();

    // Calendar class to set date
    Calendar calendar = Calendar.getInstance();
    // Claims variables
    private String claimNumber = "C";
    static int claimCounter = 10000;
    private String addressOfAccident;
    private String descriptionOfAccident;
    private String descriptionOfDamageToVehicle;
    private double estimatedCostOfRepairs;
    private Date date;
    Locale locale = Locale.US;
    Currency currency = Currency.getInstance(locale);
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);


    // Policy Object
    Policy policy;

    // Constructors
    public Claim() {

    }
    public Claim(Policy policy) {
        this.policy = policy;
        load();
    }

    // Get Claim number method
    public String getClaimNumber() {
        return claimNumber;
    }
    
    // Set Claim number method
    public void setClaimNumber(String claimNumber, int claimCounter) {
        String s = Integer.toString(claimCounter);
        this.claimNumber = claimNumber + s;
    }

    // Get claim counter
    public static int getClaimCounter() {
        return claimCounter;
    }

    // Set claim counter
    public static void setClaimCounter(int claimCounter) {
        Claim.claimCounter = claimCounter + 1;
    }

    // Get address of accident method
    public String getAddressOfAccident() {
        return addressOfAccident;
    }

    // Set address of accident method
    public void setAddressOfAccident(String addressOfAccident) {
        this.addressOfAccident = addressOfAccident;
    }

    // Get Description of accident method
    public String getDescriptionOfAccident() {
        return descriptionOfAccident;
    }

    // set description method
    public void setDescriptionOfAccident(String descriptionOfAccident) {
        this.descriptionOfAccident = descriptionOfAccident;
    }

    // get description method
    public String getDescriptionOfDamageToVehicle() {
        return descriptionOfDamageToVehicle;
    }

    // Set description method
    public void setDescriptionOfDamageToVehicle(String descriptionOfDamageToVehicle) {
        this.descriptionOfDamageToVehicle = descriptionOfDamageToVehicle;
    }

    // Get estimate cost of repairs method
    public double getEstimatedCostOfRepairs() {
        return estimatedCostOfRepairs;
    }

    // Set estimate cost of repairs
    public void setEstimatedCostOfRepairs(double estimatedCostOfRepairs) {
        this.estimatedCostOfRepairs = estimatedCostOfRepairs;
    }

    // Get date method
    public Date getDate() {
        return date;
    }

    // Set date method
    public void setDate(Date date) {
        this.date = date;
    }

    // Load method for user prompt
    public void load() {
        calendar.setTime(today);
        boolean isCorrect = false;
        int year = 0, month = 0, day = 0;
        String sc;
        System.out.println("\nFile a claim\n");
        System.out.println("Date of Accident:");
        do {
            try{
                System.out.print("Year of accident (press zero(0) to cancel) ==>  ");
                year = scanner.nextInt();
                if (year < policy.getYear()) {
                    System.out.println("Cannot process policy claim. Policy is only effective on " + policy.getYear());
                } else if(year == 0) {
                    return;
                } else if ((year == policy.getYear()) || (year > policy.getYear() && year <= calendar.get(Calendar.YEAR))) {
                    isCorrect = true;                    
                }
            } catch (Exception e) {
                scanner.next();
                System.out.println("Invalid input. Try again.");
            }
        } while(!isCorrect);
        isCorrect = false;
        do {
            try{
                System.out.print("Month (press zero(0) to cancel) ==>  ");
                month = scanner.nextInt();
                if(year == policy.getYear()) {
                    if (month < (policy.getEffectiveMonth() + 1)) {
                        System.out.println("Invalid. Please put date from effective month onwards");
                    } else if (month >= (policy.getEffectiveMonth()) && month <= 12) {
                        isCorrect = true;
                    }
                } else if(year > policy.getYear() && year <= calendar.get(Calendar.YEAR)) {
                    if(month > 0 && month < 12) {
                        isCorrect = true;
                    }
                } else {
                    System.out.println("Invalid month.");
                }
            } catch (Exception e) {
                scanner.next();
                System.out.println("Invalid input. Try again.");
            }
        } while (!isCorrect);

        isCorrect = false;
        do {
            try{
                System.out.print("Day of accident 1-31 only (press zero(0) to cancel) ==>  ");
                day = scanner.nextInt();
                
                if(year == policy.getYear()) {
                    if (month == (policy.getEffectiveMonth() + 1)) {
                        if (day < policy.getEffectiveDay()) {
                            System.out.println("Invalid input.");
                        } else if (day >= policy.getEffectiveDay() && day <= 31) {
                            isCorrect = true;
                        } 
                    }
                }
            } catch (Exception e) {
                scanner.next();
                System.out.println("Invalid input. Try again.");
            }
        } while(!isCorrect);

        isCorrect = false;
        
        calendar.set(year, month - 1, day);
        setDate(calendar.getTime());
        isCorrect = false;
        do {
            System.out.print("Address of accident (press zero(0) to cancel) ==> ");
            scanner.next();
            sc = scanner.nextLine();
            if(sc.endsWith("0")) {
                return;
            } else {
                setAddressOfAccident(sc);
                isCorrect = true;
            }
        } while(!isCorrect);

        isCorrect = false;
        do {
            System.out.print("Description of accident (press zero(0) to cancel) ==> ");
            sc = scanner.nextLine();
            if(sc.endsWith("0")) {
                return;
            } else {
                setDescriptionOfAccident(sc);
                isCorrect = true;
            }
        } while(!isCorrect);

        isCorrect = false;
        do {
            System.out.print("Description of damage to vehicle (press zero(0) to cancel) ==> ");
            sc = scanner.nextLine();
            if(sc.endsWith("0")) {
                return;
            } else {
                setDescriptionOfDamageToVehicle(sc);
                isCorrect = true;
            }
        } while(!isCorrect);


        isCorrect = false;
        do {
            System.out.print("Estimated cost of repair (press zero(0) to cancel) ==> ");
            double ch = scanner.nextDouble();

            if(ch > 0) {
                setEstimatedCostOfRepairs(ch);
                isCorrect = true;
            }
             else if(ch == 0) {
                return;
            }
        } while(!isCorrect);

        setClaimNumber(getClaimNumber(), getClaimCounter());
        System.out.println("Your claim number is " + getClaimNumber());
        claimCounter++;
    }



    // Tostring method
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        return "\nClaim number: " + claimNumber +
                "\nDate of accident: " + sdf.format(date) +
                "\nAddress of Accident: " + addressOfAccident +
                "\nDescription Of Accident: " + descriptionOfAccident +
                "\nDescription Of Damage To Vehicle: " + descriptionOfDamageToVehicle +
                "\nEstimated Cost Of Repairs: " + currencyFormatter.format(estimatedCostOfRepairs);
    }
}
