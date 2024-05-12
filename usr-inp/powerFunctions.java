package functions; // Package declaration

import java.util.Scanner; // Importing Scanner class

public class powerFunctions { // Class declaration
    public void calculateFTP() { // Method to calculate FTP
        Scanner scanner = new Scanner(System.in); // Creating a Scanner object for user input
        powerFunctions powerFunc = new powerFunctions(); // Creating an instance of powerFunctions class
        heartRateFunctions heartRateFunc = new heartRateFunctions(); // Creating an instance of heartRateFunctions class
        trainingStressScoreFunctions tssFunc = new trainingStressScoreFunctions(); // Creating an instance of trainingStressScoreFunctions class

        String selection = ""; // String variable to store user selection
        int power = 0; // Variable to store power input
        int FTP; // Variable to store Functional Threshold Power

        // Prompting the user to enter their best 20-minute power
        System.out.println("Enter your best 20 minute power: ");
        // Input validation for power
        while (true) {
            try {
                power = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        // Calculating Functional Threshold Power (FTP)
        FTP = (int) (power * 0.95);
        // Outputting FTP and corresponding power zones
        System.out.println("Your FTP is " + FTP);
        System.out.println("Zone 1: " + (int) (FTP * 0) + " - " + (int) (FTP * 0.55));
        // Outputting more power zones
        // Similar output statements for Zones 2 to 7

        System.out.println("Would you like to do something else?: "); // Prompting user for further action
        while (true) {
            selection = scanner.nextLine(); // Reading user input
            // Checking if the user wants to perform another action
            if (selection.equalsIgnoreCase("yes") || selection.equalsIgnoreCase("y")){
                System.out.println("What would you like to do?");
                selection = scanner.nextLine();
                // Redirecting user based on their choice
                if (selection.equalsIgnoreCase("calculate ftp") || (selection.equalsIgnoreCase("calculate functional threshold power")) || 
                (selection.equalsIgnoreCase("ftp")) || (selection.equalsIgnoreCase("functional threshold power"))) {
                    powerFunc.calculateFTP(); // Calling calculateFTP method recursively
                } else if (selection.equalsIgnoreCase("lthr") || selection.equalsIgnoreCase("lactate threshold heart rate")
                || selection.equalsIgnoreCase("calculate lthr") || selection.equalsIgnoreCase("calculate lactate threshold heart rate")) {
                    heartRateFunc.calculateHeartRate(); // Calling calculateHeartRate method from heartRateFunctions class
                } else {
                    System.out.println("Thank you for using my program!"); // Exiting the program
                }
            }
            scanner.close(); // Closing the Scanner object
        }
    }
}
