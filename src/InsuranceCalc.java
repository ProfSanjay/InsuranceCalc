/*
 * Title: InsuranceCalc
 * Author: Nic Samlal
 * Date of Last Revision: 4/28/2025
 */
package src;
import java.util.*;

public class InsuranceCalc {

    // TODO: Run as an executable for easy use + make into a pretty dialog box?
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
                
        System.out.println("Welcome to the Insurance Calculator!\nHow many cars will be insured?");
        
        int numCars = Integer.parseInt(input.nextLine());
        List<Car> cars = createList(numCars, input);

        // What is the total insurance listed at? (the price to pay)
        System.out.println("What is the total cost of the insurance owed?");
        double balanceDue = input.nextDouble(); 
        input.close();

        calcDiscount(cars, balanceDue);

        // Map function to print out who owes what
        Map<String, Double> balanceOwed = balance(cars);
        
        // Print that shit
        double check = 0.0;
        System.out.println("\nEach person owes: ");
        for (Map.Entry<String, Double> iter : balanceOwed.entrySet()) {
            System.out.printf("%s: $%.2f%n", iter.getKey(), iter.getValue());

            // Checks and Balances
            check += iter.getValue();
        }

        if (check == balanceDue) // TODO: Compare as strings formatted to 2 decimal values
            System.out.println("\nCongrats! Your insurance bill has been split fairly!");
        else
            System.out.println("\nSomeone fucked up somewhere!");

    }

    public static List<Car> createList(int numCars, Scanner scanner) {
        System.out.println("Please input the information for each vehicle!");
        
        ArrayList<Car> cars = new ArrayList<>();
        
        for (int i = 0; i < numCars; i++) {
            System.out.println("Please enter the make & model of the car: ");
            String carModel = scanner.nextLine();

            System.out.println("How many people own this car?");
            int ownerCount = scanner.nextInt();
            scanner.nextLine(); // Needed to clear input?
            
            System.out.println("Enter their name(s): ");
            List<String> carOwners = new ArrayList<>();
            boolean allOwners = false;

            while (!allOwners) {
                String carOwner = scanner.nextLine();
                carOwners.add(carOwner);

                if (carOwners.size() == ownerCount)
                    allOwners = true;
            }

            System.out.println("How much is owed on this vehicle?");
            double amountOwed = scanner.nextDouble();
            scanner.nextLine();
            System.out.println();
            
            Car addCar = new Car(carModel, carOwners, amountOwed);

            cars.add(addCar);
        }

        return cars;
    }

    public static void calcDiscount(List<Car> cars, double balanceDue) {
        // Get the total sum of each vehicle's insurance cost
        double vehicleSum = 0.0;

        for (int i = 0; i < cars.size(); i++)
            vehicleSum += cars.get(i).getAmount();

        // Calculate the total discount
        double totalDiscount = vehicleSum - balanceDue;
        double discountPerc = totalDiscount / vehicleSum;   // Fun lil stat
        System.out.printf("The total discount is $%.2f, which works out to an overall discount of %.2f%%%n", totalDiscount, discountPerc*100);

        
        // Set each vehicle's discount amount
        System.out.println("\nEach vehicle gets a discount of:");
        for (int i = 0; i < cars.size(); i++) {        
            System.out.print("Vehicle: " + cars.get(i).getModel());
            double carDiscount = (cars.get(i).getAmount() / vehicleSum) * totalDiscount;
            cars.get(i).setDiscountPortion(carDiscount);
            System.out.printf(" Discount Portion: $%.2f%n", carDiscount);
        }
    }

    public static Map<String, Double> balance(List<Car> cars) {
        Map<String, Double> balanceOwed = new HashMap<>();

        for (int i = 0; i < cars.size(); i++) {
            List<String> owners = cars.get(i).getOwners();  // Lists the names of the owners
            double carAmount = cars.get(i).getAmount();     // Lists the total value of the insurance owed

            for (int j = 0; j < owners.size(); j++) {
                if (balanceOwed.containsKey(owners.get(j))) {
                    // Add the value of cars[i] here (make sure to divide by owners.size())
                    balanceOwed.put(owners.get(j), balanceOwed.get(owners.get(j)) + (carAmount - cars.get(i).getDiscountPortion()) / owners.size()); 
                }  
                
                else {
                    // Should add a new key and value after subtracting the vehicle's discount amount
                    balanceOwed.put(owners.get(j), (carAmount - cars.get(i).getDiscountPortion()) / owners.size());    
                }
                
            }
        }

        return balanceOwed;
    }
}

class Car {
    private String model;   // Unique ID 
    private List<String> owners;    // List of owners i.e. responsible for paying insurance cost
    private double amount;  // Insurance cost for vehicle before discount calculation
    private double discountPortion; // Total amount to discount from vehicle's insurance cost 

    public Car(String model, List<String> owners, double amount) {
        this.model = model;
        this.owners = owners;
        this.amount = amount;
    }

    public String getModel() { return this.model; }
    public void setModel(String model) { this.model = model; }

    public List<String> getOwners() { return this.owners; }
    public void setOwners(List<String> owners) { this.owners = owners; }

    public double getAmount() { return this.amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getDiscountPortion() { return this.discountPortion; }
    public void setDiscountPortion(double discountPortion) { this.discountPortion = discountPortion; }
}