import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String selection = "";
        int power = 0; // Initialize power with a default value
        int FTP;
        double conversion = 0.95;

        System.out.println("Would you like to calculate FTP or FTHR?: ");
        selection = scanner.nextLine();

        if (selection.equalsIgnoreCase("ftp") || selection.equalsIgnoreCase("functional threshold power")
                || selection.equalsIgnoreCase("threshold power") || selection.equalsIgnoreCase("power")) {
            System.out.println("Enter your best 20 minute power: ");
            try {
                power = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input");
            }

            FTP = (int) (power * conversion);
            System.out.println("Your FTP is " + FTP + ". Would you like to do something else?");
            while (true) {
                selection = scanner.nextLine();
                if (selection.equalsIgnoreCase("yes") || selection.equalsIgnoreCase("y")){
                    System.out.println("What would you like to do?");
                    selection = scanner.nextLine();
                    if (selection.equalsIgnoreCase("calculate ftp") || (selection.equalsIgnoreCase("calculate functional threshold power")) || 
                    (selection.equalsIgnoreCase("ftp")) || (selection.equalsIgnoreCase("functional theshold power"))) {
                        functionalThresholdPower.FTP();
                    }
                }
            }
        } 
        scanner.close();
    }
}
