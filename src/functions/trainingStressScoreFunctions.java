package functions;
// TSS = (sec x NP x IF) / (FTP x 36000) x 100
// sec = Duration of the ride (seconds)
// NP = Normalized power
// IF = Intensity factor
// FTP = Functional threshold power
// 3600 = Total number of seconds in an hour

import java.util.Scanner;
public class trainingStressScoreFunctions {
    public void calculateTrainingStressScore (){
        Scanner scanner = new Scanner(System.in);
        powerFunctions powerFunc = new powerFunctions();
        heartRateFunctions heartRateFunc = new heartRateFunctions();
        trainingStressScoreFunctions tssFunc = new trainingStressScoreFunctions();

        
        String selection = "";
        int duration;
        int normalizedPower;
        int intensityFactor;
        int functionalThresholdPower;
        int trainingStressScore;
        
        System.out.println("Enter the duration of your ride (in minutes): ");
        duration = scanner.nextInt();
        try {
            duration = Integer.parseInt(scanner.nextLine()); 
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input");
        }

        System.out.println("Enter your normalized power: ");
        normalizedPower = scanner.nextInt();
        try {
            normalizedPower = Integer.parseInt(scanner.nextLine()); 
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input");
        }

        System.out.println("Enter your intensity factor: ");
        intensityFactor = scanner.nextInt();
        try {
            intensityFactor = Integer.parseInt(scanner.nextLine()); 
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input");
        }

        System.out.println("Enter your functional threshold power: ");
        functionalThresholdPower = scanner.nextInt();
        try {
            functionalThresholdPower = Integer.parseInt(scanner.nextLine()); 
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input");
        }

        trainingStressScore = ((duration * normalizedPower * intensityFactor) / (functionalThresholdPower * 3600)) * 100;

        System.out.println("Your training stress score is: " + trainingStressScore);
        while (true) {
            selection = scanner.nextLine();
            if (selection.equalsIgnoreCase("yes") || selection.equalsIgnoreCase("y")){
                System.out.println("What would you like to do?");
                selection = scanner.nextLine();
                if (selection.equalsIgnoreCase("calculate ftp") || (selection.equalsIgnoreCase("calculate functional threshold power")) || 
                (selection.equalsIgnoreCase("ftp")) || (selection.equalsIgnoreCase("functional threshold power"))) {
                    powerFunc.calculateFTP();
                } else if (selection.equalsIgnoreCase("lthr") || selection.equalsIgnoreCase("lactate threshold heart rate")
                || selection.equalsIgnoreCase("calculate lthr") || selection.equalsIgnoreCase("calculate lactate threshold heart rate")) {
                    heartRateFunc.calculateHeartRate();
                } 
                else if (selection.equalsIgnoreCase("tss") || selection.equalsIgnoreCase("training stress score") || 
                (selection.equalsIgnoreCase("calculate tss")) || (selection.equalsIgnoreCase("calculate training stress score"))) {
                    tssFunc.calculateTrainingStressScore();
                } else {
                    System.out.println("Thank you for using my program!");
                }
            }
            scanner.close();
        }
    }
}
