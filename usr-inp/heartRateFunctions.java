package functions; // Package declaration

import java.util.Scanner; // Importing Scanner class

public class heartRateFunctions { // Class declaration
    public void calculateHeartRate(){ // Method to calculate heart rate zones
        Scanner scanner = new Scanner(System.in); // Creating a Scanner object for user input
        powerFunctions powerFunc = new powerFunctions(); // Creating an instance of powerFunctions class
        heartRateFunctions heartRateFunc = new heartRateFunctions(); // Creating an instance of heartRateFunctions class

        String selection = ""; // String variable to store user selection
        int heartRate = 0; // Variable to store heart rate input
        int LTHR; // Variable to store Lactate Threshold Heart Rate

        // Prompting the user to choose between cycling or running zones
        System.out.println("Do you want to calculate cycling or running zones?");
        selection = scanner.next(); // Reading user input

        // Calculating heart rate zones for cycling
        if (selection.equalsIgnoreCase("cycling") || selection.equalsIgnoreCase("biking")) {
            System.out.println("Enter your best 20 minute heart rate: ");
            // Input validation for heart rate
            while (true) {
                try {
                    heartRate = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }
            LTHR = (int) (heartRate * 0.95); // Calculating Lactate Threshold Heart Rate
            // Outputting heart rate zones for cycling
            System.out.println("Your LTHR is " + LTHR);
            System.out.println("Zone 1: " + (int) (LTHR * 0) + " - " + (int) (LTHR * 0.81));
            // Outputting more heart rate zones for cycling
            // Similar output statements for Zones 2 to 5c
        } 
        // Calculating heart rate zones for running
        else if (selection.equalsIgnoreCase("running") || selection.equalsIgnoreCase("run")) {
            System.out.println("Enter your best 20 minute heart rate: ");
            try {
                heartRate = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input");
            }
            LTHR = (int) (heartRate * 0.95); // Calculating Lactate Threshold Heart Rate
            // Outputting heart rate zones for running
            System.out.println("Zone 1: " + (int) (LTHR * 0) + " - " + (int) (LTHR * 0.85));
            // Outputting more heart rate zones for running
            // Similar output statements for Zones 2 to 5c
        }
        
        // Prompting the user for further action
        while (true) {
            selection = scanner.nextLine();
            // Checking if the user wants to perform another action
            if (selection.equalsIgnoreCase("yes") || selection.equalsIgnoreCase("y")){
                System.out.println("What would you like to do?");
                selection = scanner.nextLine();
                // Redirecting user based on their choice
                if (selection.equalsIgnoreCase("calculate ftp") || (selection.equalsIgnoreCase("calculate functional threshold power")) || 
                (selection.equalsIgnoreCase("ftp")) || (selection.equalsIgnoreCase("functional threshold power"))) {
                    powerFunc.calculateFTP(); // Calling calculateFTP method from powerFunctions class
                } else if (selection.equalsIgnoreCase("lthr") || selection.equalsIgnoreCase("lactate threshold heart rate")
                    || selection.equalsIgnoreCase("calculate lthr") || selection.equalsIgnoreCase("calculate lactate threshold heart rate")) {
                        heartRateFunc.calculateHeartRate(); // Recursively calling calculateHeartRate method
                    }
            } else {
                System.out.println("Thank you for using my program!"); // Exiting the program
            }
            scanner.close(); // Closing the Scanner object
        }
    }
}

