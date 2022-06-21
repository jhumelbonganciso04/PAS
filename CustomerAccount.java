/**
 * Java Course 4, Module 3
 * 
 * Norima Java Developer Course Capstone Project
   *
 * @author Jhumel M. Bonganciso
 */
import java.util.ArrayList;

public class CustomerAccount extends AccountOwner {

    // Customer account variables
    static int accountCount = 1000;
    private int accountNumber = 0;
    ArrayList<Policy> policyCollection = new ArrayList<Policy>();
    ArrayList<PolicyHolder> policyHolders = new ArrayList<PolicyHolder>();
    ArrayList<Claim> claims = new ArrayList<Claim>();
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    boolean isCancelled = false;
    // Constructor method
    public CustomerAccount() {
        load();
    }

    // Load method for user prompt
    public void load() {
        isCancelled = false;
        super.load();
        if(!super.getIsCancelled()) {
            accountNumber = accountCount;
        System.out.println("\nYour account number is " + accountNumber + "\n");
        accountCount++;
        } else {
            isCancelled = true;
            return;
        }
    }

    public boolean getIsCancelled(){
        return isCancelled;
    }

    // Add policy holder method
    public void addPolicyHolder() {
        policyHolders.add(new PolicyHolder());
    }

    // Get account number method
    public int getAccountNumber() {
        return accountNumber;
    }

    // To string method
    @Override
    public String toString() {
        return "Customer Account: " +
                "\nAccount Number: " + accountNumber +
                super.toString();

    }
}
