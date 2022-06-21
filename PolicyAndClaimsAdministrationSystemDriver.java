/**
 * Java Course 4, Module 3
 * 
 * Norima Java Developer Course Capstone Project
   *
 * @author Jhumel M. Bonganciso
 */
import java.util.ArrayList;

public class PolicyAndClaimsAdministrationSystemDriver {

// Main method
    public static void main(String[] args) {

        // Customer account arraylist
        ArrayList<CustomerAccount> customerAccounts = new ArrayList<>();

        // Policy creation
        PolicyAndClaimsAdministrationSystem pas = new PolicyAndClaimsAdministrationSystem(customerAccounts);
        pas.menu();
    }
}
