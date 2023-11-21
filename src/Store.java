import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {
    private List<Bike> inventory = new ArrayList<>(); // List to store bike inventory
    private double totalPrice = 0; // Variable to track the total price of items in the cart
    private Customer currentCustomer; // Track the current customer
    private List<Customer> customers = new ArrayList<>(); // List to store customer information

    public Store() {
        // Hardcode products into the inventory
        inventory.add(new Bike("Kid's Bike", BikeCategory.MOUNTAIN_BIKE, 149.99));
        inventory.add(new Bike("Kid's Bike", BikeCategory.ROAD_BIKE, 129.99));
        inventory.add(new Bike("Kid's Bike", BikeCategory.BMX_BIKE, 89.99));
        inventory.add(new Bike("Kid's Bike", BikeCategory.CRUISER_BIKE, 109.99));
    }
// method to display message
    public void welcome() {
        System.out.println("Welcome to the Used Bikes for Kids Store!");
        System.out.println("Sign Up Below");
    }
// method for signup
    public void signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        currentCustomer = new Customer(name, email);
        customers.add(currentCustomer); // Add the new customer to the list
        System.out.println("Sign-up successful! Welcome, " + name + "!");
    }
//method to display available bike inventory
    public void displayInventory() {
        System.out.println("Available Bikes:");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i));
        }
    }
// method to handle shopping process
    public void shop() {
        Scanner scanner = new Scanner(System.in);
        boolean continueShopping = true;

        while (continueShopping) {
            displayInventory();
            System.out.print("Enter the number of the bike you want to purchase (0 to finish shopping, -1 to return): ");
            int choice = scanner.nextInt();

            if (choice >= 1 && choice <= inventory.size()) { // Check if the choice is a valid bike number
                Bike selectedBike = inventory.get(choice - 1);// Get the selected bike from the inventory
                totalPrice += selectedBike.getPrice(); // Add the price of the selected bike to the total price
                System.out.println("You've added " + selectedBike.getName() + " to your cart.");
            } else if (choice == 0) {
                continueShopping = false; // Set continueShopping to false to exit the shopping loop
            } else if (choice == -1) {
                returnPurchase(); // Call the returnPurchase() method to handle returning a bike
            } else {
                System.out.println("Invalid input. Please select a valid bike number."); //display error message
            }
        }
            //display thank you messages
        System.out.println("Thank you for shopping with us!");
        System.out.println("Total Price: $" + totalPrice);

        // Handle payment and change (assuming cash payment)
        System.out.print("Enter the amount of cash you're paying with: $");
        double payment = scanner.nextDouble(); // Call the returnPurchase() method to handle returning a bike
        double change = payment - totalPrice; // Calculate the change
        if (change >= 0) {
            System.out.println("Change: $" + change);
        } else {
            System.out.println("Insufficient payment. Please pay the full amount.");
        }
    }

    // Method to handle returning a purchased bike

    public void returnPurchase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of the bike you would like to return:");
        int returnChoice = scanner.nextInt();

        if (returnChoice >= 1 && returnChoice <= inventory.size()) {
            Bike returnedBike = inventory.get(returnChoice - 1);
            totalPrice -= returnedBike.getPrice();
            System.out.println("You've returned " + returnedBike.getName() + ". Refund amount: $" + returnedBike.getPrice());
        } else {
            System.out.println("Invalid input. Please select a valid bike number to return.");
        }
    }

    // Main method to execute the program

    public static void main(String[] args) {
        Store store = new Store();
        store.welcome();
        store.signUp();
        store.shop();
    }

    static class Customer {
        private String name;
        private String email;

        public Customer(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }
}
