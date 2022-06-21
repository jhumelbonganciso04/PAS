
/**
 * Java Course 4, Module 3
 * 
 * Norima Java Developer Course Capstone Project
   *
 * @author Jhumel M. Bonganciso
 */

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Policy {
    // Scanner opening
    Scanner s = new Scanner(System.in);

    // Policy variables
    Calendar cal = Calendar.getInstance();
    Date today = new Date();
    Date effectiveDate;
    Date expiryDate;
    static int policyCounter = 100000;
    private int policyNumber = 0;
    private int effectiveMonth = 0;
    private int effectiveDay = 0;
    private int year = 0;
    private double totalPaymentForPolicy;

    // Premium payment formatter
    Locale locale = Locale.US;
    Currency currency = Currency.getInstance(locale);
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    // Arraylist of objections
    ArrayList<CustomerAccount> customerAccounts = new ArrayList<>();
    ArrayList<Vehicle> vehicles = new ArrayList<>();

    boolean isCarExisting = false;

    // Object creation
    CustomerAccount customerAccount1;
    PolicyHolder policyHolder;

    // Constructor methods
    Policy() {

    }

    Policy(PolicyHolder policyHolder, ArrayList customerAccounts) {
        this.policyHolder = policyHolder;
        this.customerAccounts = customerAccounts;
        load();
    }

    // Get policy number method
    public int getPolicyNumber() {
        return policyNumber;
    }

    // Set policy number method
    public void setPolicyNumber(int policyNumber) {
        this.policyNumber = policyNumber;
    }

    // Get effective month method
    public int getEffectiveMonth() {
        return effectiveMonth;
    }

    // Set effective month method
    public void setEffectiveMonth(int effectiveMonth) {
        this.effectiveMonth = effectiveMonth;
    }

    // Get effective day method
    public int getEffectiveDay() {
        return effectiveDay;
    }

    // Set effective day method
    public void setEffectiveDay(int effectiveDay) {
        this.effectiveDay = effectiveDay;
    }

    // get year method
    public int getYear() {
        return year;
    }

    // Set year method
    public void setYear(int year) {
        this.year = year;
    }

    // Load method for user prompt
    public void load() {
        addCarToPolicy();

        if (vehicles.isEmpty()) {
            System.out.println("Policy quotation cancelled.");
        } else {
            RatingEngine ratingEngine = new RatingEngine(vehicles, policyHolder);
            boolean isCorrect = false;
            cal.setTime(today);
            System.out.println("Policy Effective Date:");

            do {
                try {
                    System.out.print("Policy effective Year ==> ");
                    int yr = s.nextInt();

                    if (yr >= cal.get(Calendar.YEAR)) {
                        setYear(yr);
                        isCorrect = true;
                    } else {
                        System.out.println("Please put valid year. Valid year are years from this year onwards.");
                    }

                } catch (Exception e) {
                    s.next();
                    System.out.println("Invalid input");
                }
            } while (!isCorrect);

            isCorrect = false;

            do {
                try {
                    System.out.print("Policy effective month ==> ");
                    int month = s.nextInt();

                    if (getYear() == cal.get(Calendar.YEAR)) {
                        if (month >= (cal.get(Calendar.MONTH) + 1)) {
                            setEffectiveMonth(month);
                            isCorrect = true;
                        }
                    } else if (getYear() > cal.get(Calendar.YEAR)) {
                        setEffectiveMonth(month);
                        isCorrect = true;
                    } else {
                        System.out.println("Please put valid month. Valid month are months from today onwards.");
                    }

                } catch (Exception e) {
                    s.nextLine();
                    System.out.println("Invalid input");
                }
            } while (!isCorrect);

            isCorrect = false;

            do {
                try {
                    System.out.print("Policy effective day ==> ");
                    int day = s.nextInt();
                    if (getYear() == cal.get(Calendar.YEAR)) {
                        if (getEffectiveMonth() == (cal.get(Calendar.MONTH) + 1)) {
                            if (day >= (cal.get(Calendar.DAY_OF_MONTH))) {
                                setEffectiveDay(day);
                                isCorrect = true;
                            } else {
                                System.out.println("Please choose day from today onwards only");
                            }
                        } else if (getEffectiveMonth() > (cal.get(Calendar.MONTH) + 1)) {
                            setEffectiveDay(day);
                            isCorrect = true;
                        }
                    } else if (getYear() > cal.get(Calendar.YEAR)) {
                        if ((day > 0) && (day < 32)) {
                            setEffectiveDay(day);
                            isCorrect = true;
                        }
                    } else {
                        System.out.println("Please put valid day.");
                    }
                } catch (Exception e) {
                    s.nextLine();
                    System.out.println("Invalid input. Please Try again");
                }
            } while (!isCorrect);

            cal.set(getYear(), (getEffectiveMonth() - 1), getEffectiveDay());
            effectiveDate = cal.getTime();
            cal.add(cal.MONTH, 6);
            expiryDate = cal.getTime();
            setPolicyNumber(policyCounter);
            policyCounter++;
            double bal = 0;
            setTotalPaymentForPolicy(ratingEngine.getSubTotal());
            System.out
                    .println("\nYour Total Policy Premium is " + currencyFormatter.format(getTotalPaymentForPolicy()));
        }
    }

    // Add car to policy method
    public void addCarToPolicy() {
        setCarExisting(false);
        boolean isCorrect = false;
        double choice = 100000;
        do {
            try {
                System.out.println("How many cars do you wanted to add in the policy?");
                choice = s.nextDouble();
                isCorrect = true;
            } catch (Exception e) {
                // TODO: handle exception
                s.next();
                System.out.println("Invalid input.");
            }
        } while (!isCorrect);
        boolean isExisting = false;
        for (int i = 0; i < choice; i++) {
            vehicles.add(new Vehicle(policyHolder));
            if (vehicles.get(vehicles.size() - 1).isCancelled) {
                vehicles.remove(vehicles.size() - 1);
                return;
            }
            // Loop for customer accounts
            for (CustomerAccount customerAccount : customerAccounts) {

                // Policy Collection for customer accounts
                for (int j = 0; j < customerAccount.policyCollection.size(); j++) {

                    // Vehicle loop of policy collection
                    for (int k = 0; k < customerAccount.policyCollection.get(i).vehicles.size(); k++) {

                        // for each vehicle in customer account
                        for (Vehicle vehi : customerAccount.policyCollection.get(i).vehicles) {
                            if (vehi.getPlateNumber()
                                    .equalsIgnoreCase(vehicles.get(vehicles.size() - 1).getPlateNumber())) {
                                System.out.println(
                                        "Vehicle is already associated with policy. And will be remove with this policy.");
                                vehicles.remove(vehicles.size() - 1);
                                setCarExisting(true);
                            }
                        }
                    }

                }
            }

            if (!isCarExisting()) {
                System.out.println("\nCar added successfully.\n");
            }
        }

    }

    // Is car existing method
    public boolean isCarExisting() {
        return isCarExisting;
    }

    // Set car existing method
    public void setCarExisting(boolean carExisting) {
        isCarExisting = carExisting;
    }

    // Get total payment method
    public double getTotalPaymentForPolicy() {
        return totalPaymentForPolicy;
    }

    // Set total payment method
    public void setTotalPaymentForPolicy(double totalPaymentForPolicy) {
        this.totalPaymentForPolicy = totalPaymentForPolicy;
    }

    // To string method
    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM-dd-yyyy");
        return "\nPolicy Holder: " + policyHolder.toString() +
                "\nPolicy Number: " + policyNumber +
                "\nEffective Date: " + simpleDateFormat.format(effectiveDate) +
                "\nExpiry Date: " + simpleDateFormat.format(expiryDate) +
                "\nTotal Payment For Policy: " + currencyFormatter.format(totalPaymentForPolicy) +
                "\n\nVehicles enrolled";
    }

}
