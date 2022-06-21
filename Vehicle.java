/**
 * Java Course 4, Module 3
 * 
 * Norima Java Developer Course Capstone Project
   *
 * @author Jhumel M. Bonganciso
 */
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class Vehicle {
    // Scanner method
    Scanner sc = new Scanner(System.in);

    // Vehicle variables
    private String make;
    private double vpf;
    private String plateNumber;
    private String model;
    private double year;
    private String vehicleType;
    private String[] type = { "4-door Sedan", "2-door Sports Car", "SUV", "Truck" };
    private String vehicleFuelType;
    private String[] fuelType = { "Diesel", "Electric", "Petrol" };
    private double purchasePrice;
    private String color;
    private double premiumCharged = 0;
    private double vehicleAge;
    boolean isCancelled = false;
    // Policy holder object
    PolicyHolder policyHolder;

    // Currency method
    Locale locale = Locale.US;
    Currency currency = Currency.getInstance(locale);
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    // Constructor method
    public Vehicle() {

    }

    public Vehicle(PolicyHolder policyHolder) {
        this.policyHolder = policyHolder;
        loadMenu();
    }

    // Set Plate number method
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    // Get plate number method
    public String getPlateNumber() {
        return plateNumber;
    }

    // Get year method
    public double getYear() {
        return year;
    }

    // Load menu for user prompt
    public void loadMenu() {
        System.out.print("Car Maker or enter 0(zero) to go back to main menu ==> ");
        make = sc.next();

        if(make.equalsIgnoreCase("0")) {
            isCancelled = true;
            return;
        }
        System.out.print("Car Model ==> ");
        model = sc.next();
        boolean isCorrect = false;
        int choice = 0;
        do {
            try {
                System.out.print("Car year ==> ");
                year = sc.nextInt();
                if (year >= 1990 || year >= 2022) {
                    isCorrect = true;
                } else {
                    System.out.println("Please choose year value between 1990 and 2022 only");
                }

            } catch (Exception e) {
                sc.next();
                System.out.println("Incorrect input");
            }
        } while (!isCorrect);
        isCorrect = false;
        System.out.println("Type of Vehicle");
        for (int i = 0; i < type.length; i++) {
            System.out.println(i + ". " + type[i]);
        }
        do {
            try {
                System.out.print("Choice ==> ");
                choice = sc.nextInt();
                if (choice >= 0 && choice < 4) {
                    isCorrect = true;
                } else {
                    System.out.println("Incorrect input. Try again. Enter number from 0-3 only.");

                }
            } catch (Exception e) {
                sc.next();
                System.out.println("Incorrect input. Try again. Enter number from 0-3 only.");
            }
        } while (!isCorrect);
        vehicleType = type[choice];
        System.out.print("Plate number ==> ");
        plateNumber = sc.next();
        System.out.print("Color ==> ");
        sc.nextLine();
        color = sc.nextLine();

        System.out.println("Fuel type");
        for (int i = 0; i < fuelType.length; i++) {
            System.out.println(i + ". " + fuelType[i]);
        }

        isCorrect = false;
        do {
            try {
                System.out.print("Choice ==> ");
                choice = sc.nextInt();
                if (choice >= 0 && choice <= 2) {
                    isCorrect = true;
                } else {
                    System.out.println("Incorrect input. Please enter number from 0-2 only");
                    isCorrect = false;
                }
            } catch (Exception e) {
                sc.next();
                System.out.println("Incorrect input. Please enter number from 0-2 only");
            }
        } while (!isCorrect);
        vehicleFuelType = fuelType[choice];

        isCorrect = false;
        do {
            try {
                System.out.print("Vehicle Purchase Price ==> ");
                setPurchasePrice(sc.nextDouble());
                if (purchasePrice > 0) {
                    isCorrect = true;
                } else {
                    System.out.println("Incorrect input");
                }
            } catch (Exception e) {
                sc.next();
                System.out.println("Incorrect input. Please try again.");
            }
        } while (!isCorrect);
        setVehicleAge(2022 - getYear());
    }

    // Get premium charge method
    public double getPremiumCharged() {
        return premiumCharged;
    }

    // Set premium charge method
    public void setPremiumCharged(double premprem) {
        this.premiumCharged = premprem;
    }

    // Get vehicle price factor method
    public double getVpf() {
        return vpf;
    }

    // Set vehicle price factor method
    public void setVpf(double vpf) {
        this.vpf = vpf;
    }

    // Get purchase price method
    public double getPurchasePrice() {
        return purchasePrice;
    }

    // Set purchase price method
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    // set vehicle method
    public void setVehicleAge(double vehicleAge) {
        this.vehicleAge = vehicleAge;
    }

    // Get vehicle method
    public double getVehicleAge() {
        return vehicleAge;
    }

    // To string method
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0");
        return "\nMake: " + make +
                "\nModel: " + model +
                "\nYear: " + df.format(year) +
                "\nVehicle Type: " + vehicleType +
                "\nPurchase Price: " + currencyFormatter.format(purchasePrice) +
                "\nColor: " + color +
                "\nPremium Charged: " + currencyFormatter.format(premiumCharged);
    }
}
