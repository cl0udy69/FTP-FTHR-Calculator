package functions;
import java.util.Scanner;

public class powerFunctions {
    public void calculateFTP() {
        Scanner scanner = new Scanner(System.in);
        powerFunctions powerFunc = new powerFunctions();
        heartRateFunctions heartRateFunc = new heartRateFunctions();
        trainingStressScoreFunctions tssFunc = new trainingStressScoreFunctions();

        String selection = "";
        int power = 0;
        int FTP;

        System.out.println("Enter your best 20 minute power: ");
        while (true) {
            try {
                power = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
            FTP = (int) (power * 0.95);
            System.out.println("Your FTP is " + FTP);
            System.out.println("Zone 1: " + (int) (FTP * 0) + " - " + (int) (FTP * 0.55));
            System.out.println("Zone 2: " + (int) (FTP * 0.55) + " - " + (int) (FTP * 0.75));
            System.out.println("Zone 3: " + (int) (FTP * 0.75) + " - " + (int) (FTP * 0.87));
            System.out.println("Zone 4: " + (int) (FTP * 0.87) + " - " + (int) (FTP * 0.94));
            System.out.println("Zone 5: " + (int) (FTP * 0.94) + " - " + (int) (FTP * 1.05));
            System.out.println("Zone 6: " + (int) (FTP * 1.05) + " - " + (int) (FTP * 1.20));
            System.out.println("Zone 7: " + (int) (FTP * 1.20) + " - " + (int) (FTP * 1.25));
            System.out.println("Would you like to do something else?: ");
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
                    } else {
                        System.out.println("Thank you for using my program!");
                    }
                }
                scanner.close();
            }
        }
}
