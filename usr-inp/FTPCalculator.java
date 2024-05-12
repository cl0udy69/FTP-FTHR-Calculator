package client; // Package declaration

import java.util.Scanner; // Importing Scanner class
import functions.heartRateFunctions; // Importing heartRateFunctions class from functions package
import functions.powerFunctions; // Importing powerFunctions class from functions package
import functions.trainingStressScoreFunctions; // Importing trainingStressScoreFunctions class from functions package

public class FTPCalculator { // Class declaration
    public static void main(String[] args) { // Main method
        Scanner scanner = new Scanner(System.in); // Creating a Scanner object for user input
        powerFunctions powerFunc = new powerFunctions(); // Creating an instance of powerFunctions class
        heartRateFunctions heartRateFunc = new heartRateFunctions(); // Creating an instance of heartRateFunctions class
        trainingStressScoreFunctions tssFunc = new trainingStressScoreFunctions(); // Creating an instance of trainingStressScoreFunctions class

        String selection = ""; // String variable to store user selection

        // Prompting the user for input
        System.out.println("Would you like to calculate FTP, LTHR, or TSS?");
        selection = scanner.nextLine(); // Reading user input

        // Checking user's selection and calling respective methods
        if (selection.equalsIgnoreCase("ftp") || selection.equalsIgnoreCase("functional threshold power")
                || selection.equalsIgnoreCase("calculate ftp") || selection.equalsIgnoreCase("calculate functional threshold power")) {
                    powerFunc.calculateFTP(); // Calling calculateFTP method from powerFunctions class
        } else if (selection.equalsIgnoreCase("lthr") || selection.equalsIgnoreCase("lactate threshold power")
                || selection.equalsIgnoreCase("calculate lthr") || selection.equalsIgnoreCase("calculate lactate threshold power")) {
                    heartRateFunc.calculateHeartRate(); // Calling calculateHeartRate method from heartRateFunctions class
        } else if (selection.equalsIgnoreCase("tss") || selection.equalsIgnoreCase("training stress score")
                || selection.equalsIgnoreCase("calculate tss") || selection.equalsIgnoreCase("calculate training stress score")) {
                    tssFunc.calculateTrainingStressScore(); // Calling calculateTrainingStressScore method from trainingStressScoreFunctions class
        }
        scanner.close(); // Closing the Scanner object
    }
}
