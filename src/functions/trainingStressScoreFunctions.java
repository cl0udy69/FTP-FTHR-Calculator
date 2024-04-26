package functions;
import java.util.Scanner;

public class trainingStressScoreFunctions {
    public void calculateTrainingStressScore() {
        Scanner scanner = new Scanner(System.in);
        powerFunctions powerFunc = new powerFunctions();
        heartRateFunctions heartRateFunc = new heartRateFunctions();
        trainingStressScoreFunctions tssFunc = new trainingStressScoreFunctions();

        String selection = "";
        int duration = 0;
        int normalizedPower = 0;
        int intensityFactor = 0;
        int functionalThresholdPower = 0;
        int trainingStressScore = 0;

        System.out.println("Enter the duration of your ride (in minutes): ");
        while (true) {
            try {
                duration = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        System.out.println("Enter your normalized power: ");
        while (true) {
            try {
                normalizedPower = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        System.out.println("Enter your intensity factor (round decimal up or down to nearest whole number): ");
        while (true) {
            try {
                intensityFactor = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        System.out.println("Enter your functional threshold power: ");
        while (true) {
            try {
                functionalThresholdPower = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        trainingStressScore = ((duration * normalizedPower * intensityFactor) / (functionalThresholdPower * 3600)) * 100;

        System.out.println("Your training stress score is: " + trainingStressScore);
        System.out.println("Would you like to do something else?");
        while (true) {
            selection = scanner.nextLine();
            if (selection.equalsIgnoreCase("yes") || selection.equalsIgnoreCase("y")) {
                System.out.println("What would you like to do?");
                selection = scanner.nextLine();
                if (selection.equalsIgnoreCase("calculate ftp") || (selection.equalsIgnoreCase("calculate functional threshold power")) ||
                        (selection.equalsIgnoreCase("ftp")) || (selection.equalsIgnoreCase("functional threshold power"))) {
                    powerFunc.calculateFTP();
                } else if (selection.equalsIgnoreCase("lthr") || selection.equalsIgnoreCase("lactate threshold heart rate")
                        || selection.equalsIgnoreCase("calculate lthr") || selection.equalsIgnoreCase("calculate lactate threshold heart rate")) {
                    heartRateFunc.calculateHeartRate();
                } else if (selection.equalsIgnoreCase("tss") || selection.equalsIgnoreCase("training stress score") ||
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
