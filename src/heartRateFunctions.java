import java.util.Scanner;

public class heartRateFunctions {
    public void calculateHeartRate(){
        Scanner scanner = new Scanner(System.in);
        powerFunctions powerFunc = new powerFunctions();
        heartRateFunctions heartRateFunc = new heartRateFunctions();

        String selection = "";
        int heartRate = 0;
        int LTHR;

        System.out.println("Do you want to calculate cycling or running zones?");
        selection = scanner.next();

        if (selection.equalsIgnoreCase("cycling") || selection.equalsIgnoreCase("biking")) {
            System.out.println("Enter your best 20 minute heart rate: ");
            try {
                heartRate = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input");
            }
            LTHR = (int) (heartRate * 0.95);
            System.out.println("Your LTHR is " + LTHR);
            System.out.println("Zone 1: " + (int) (LTHR * 0) + " - " + (int) (LTHR * 0.81));
            System.out.println("Zone 2: " + (int) (LTHR * 0.81) + " - " + (int) (heartRate * 0.89));
            System.out.println("Zone 3: " + (int) (LTHR * 0.90) + " - " + (int) (LTHR* 0.93));
            System.out.println("Zone 4: " + (int) (LTHR * 0.94) + " - " + (int) (LTHR * 0.99));
            System.out.println("Zone 5a: " + (int) (LTHR * 1.00) + " - " + (int) (LTHR * 1.02));
            System.out.println("Zone 5b: " + (int) (LTHR * 1.03) + " - " + (int) (LTHR * 1.06));
            System.out.println("Zone 5c: " + (int) (LTHR * 1.06) + " <" );
        } else if (selection.equalsIgnoreCase("running") || selection.equalsIgnoreCase("run")) {
            System.out.println("Enter your best 20 minute heart rate: ");
            try {
                heartRate = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input");
            }
            LTHR = (int) (heartRate * 0.95);
            System.out.println("Zone 1: " + (int) (LTHR * 0) + " - " + (int) (LTHR * 0.85));
            System.out.println("Zone 2: " + (int) (LTHR * 0.85) + " - " + (int) (LTHR * 0.89));
            System.out.println("Zone 3: " + (int) (LTHR * 0.90) + " - " + (int) (LTHR * 0.94));
            System.out.println("Zone 4: " + (int) (LTHR * 0.95) + " - " + (int) (LTHR * 0.99));
            System.out.println("Zone 5a: " + (int) (LTHR * 1.00) + " - " + (int) (LTHR * 1.02));
            System.out.println("Zone 5b: " + (int) (LTHR * 1.03) + " - " + (int) (LTHR * 1.06));
            System.out.println("Zone 5c: " + (int) (LTHR * 1.06) + " <" );
        }
        
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
            } else {
                System.out.println("Thank you for using my program!");
            }
            scanner.close();
        }
    }
}
