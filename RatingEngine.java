/**
 * Java Course 4, Module 3
 * 
 * Norima Java Developer Course Capstone Project
   *
 * @author Jhumel M. Bonganciso
 */

import java.util.ArrayList;

public class RatingEngine {
    // Arraylist of vehicles
    ArrayList<Vehicle> vehicles = new ArrayList<>();

    // Policy holder object
    PolicyHolder policyHolder;

    // Subtotal 
    private double subTotal;

    // Constructor
    public RatingEngine() {

    }
    public RatingEngine(ArrayList<Vehicle> vehicles, PolicyHolder policyHolder) {
        this.vehicles = vehicles;
        this.policyHolder = policyHolder;
        load();
    }

    // Set subtotal method
    public void setSubTotal(double subTotal) {
        this.subTotal += subTotal;
    }

    // Get sub total method
    public double getSubTotal() {
        return subTotal;
    }

    // Load method
    public void load() {
        for (Vehicle vehicle: vehicles) {
            if(vehicle.getVehicleAge() < 1) {
                vehicle.setVpf(.01);
            } else if (vehicle.getVehicleAge() < 3) {
                vehicle.setVpf(.008);
            } else if (vehicle.getVehicleAge() < 5) {
                vehicle.setVpf(.007);
            } else if (vehicle.getVehicleAge() < 10) {
                vehicle.setVpf(.006);
            } else if (vehicle.getVehicleAge() < 15) {
                vehicle.setVpf(.004);
            } else if (vehicle.getVehicleAge() < 20) {
                vehicle.setVpf(.002);
            } else if (vehicle.getVehicleAge() < 40) {
                vehicle.setVpf(.001);
            }
            double premToPay = (vehicle.getPurchasePrice() * vehicle.getVpf()) + ((vehicle.getPurchasePrice()/100) / policyHolder.getDlx());
            vehicle.setPremiumCharged(premToPay);
            setSubTotal(premToPay);
        }
    }
}
