package functions; // Package declaration

import java.util.Scanner; // Importing Scanner class

public class trainingStressScoreFunctions { // Class declaration
    public void calculateTrainingStressScore() { // Method to calculate Training Stress Score
        Scanner scanner = new Scanner(System.in); // Creating a Scanner object for user input
        powerFunctions powerFunc = new powerFunctions(); // Creating an instance of powerFunctions class
        heartRateFunctions heartRateFunc = new heartRateFunctions(); // Creating an instance of heartRateFunctions class
        trainingStressScoreFunctions tssFunc = new trainingStressScoreFunctions(); // Creating an instance of trainingStressScoreFunctions class

        String selection = ""; // String variable to store user selection
        int duration = 0; // Variable to store duration of ride
        int normalizedPower = 0; // Variable to store normalized power
        float intensityFactor = 0; // Variable to store intensity factor
        int functionalThresholdPower = 0; // Variable to store functional threshold power
        int trainingStressScore = 0; // Variable to store training stress score

        // Prompting the user to enter the duration of their ride
        System.out.println("Enter the duration of your ride (in minutes): ");
        // Input validation for duration
        while (true) {
            try {
                duration = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Prompting the user to enter their normalized power
        System.out.println("Enter your normalized power: ");
        // Input validation for normalized power
        while (true) {
            try {
                normalizedPower = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Prompting the user to enter their intensity factor
        System.out.println("Enter your intensity factor (round decimal up or down to nearest whole number): ");
        // Input validation for intensity factor
        while (true) {
            try {
                intensityFactor = Float.parseFloat(scanner.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Prompting the user to enter their functional threshold power
        System.out.println("Enter your functional threshold power: ");
        // Input validation for functional threshold power
        while (true) {
            try {
                functionalThresholdPower = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Calculating Training Stress Score (TSS) using the provided formula
        trainingStressScore = (int) (((duration * normalizedPower * intensityFactor)
                / (functionalThresholdPower * 3600)) * 100);

        // Outputting the calculated Training Stress Score
        System.out.println("Your training stress score is: " + trainingStressScore);
        System.out.println("Would you like to do something else?");

        // Processing further actions based on user input
        while (true) {
            selection = scanner.nextLine();
            if (selection.equalsIgnoreCase("yes") || selection.equalsIgnoreCase("y")) {
                System.out.println("What would you like to do?");
                selection = scanner.nextLine();
                if (selection.equalsIgnoreCase("calculate ftp")
                        || (selection.equalsIgnoreCase("calculate functional threshold power")) ||
                        (selection.equalsIgnoreCase("ftp"))
                        || (selection.equalsIgnoreCase("functional threshold power"))) {
                    powerFunc.calculateFTP(); // Calling calculateFTP method from powerFunctions class
                } else if (selection.equalsIgnoreCase("lthr")
                        || selection.equalsIgnoreCase("lactate threshold heart rate")
                        || selection.equalsIgnoreCase("calculate lthr")
                        || selection.equalsIgnoreCase("calculate lactate threshold heart rate")) {
                    heartRateFunc.calculateHeartRate(); // Calling calculateHeartRate method from heartRateFunctions class
                } else if (selection.equalsIgnoreCase("tss") || selection.equalsIgnoreCase("training stress score") ||
                        (selection.equalsIgnoreCase("calculate tss"))
                        || (selection.equalsIgnoreCase("calculate training stress score"))) {
                    tssFunc.calculateTrainingStressScore(); // Recursively calling calculateTrainingStressScore method
                } else {
                    System.out.println("Thank you for using my program!"); // Exiting the program
                }
            }
            scanner.close(); // Closing the Scanner object
            break; // Exiting the while loop
        }
    }
}
