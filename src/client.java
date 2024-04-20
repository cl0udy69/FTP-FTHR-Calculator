 import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String selection = "";
        int power;
        int heartRate;
        int FTP;
        int FTHR;
        float unit = 0.95;

        System.out.println("Would you like to calcuate FTP or FTHR?: ");
        selection = scanner.nextLine();

        if (selection.equalsIgnoreCase("ftp") || selection.equalsIgnoreCase("functional threshold power") 
        || selection.equalsIgnoreCase("threshold power") || selection.equalsIgnoreCase("power")) {
            while (true) {
                System.out.println("Enter your current 20 minute power: ");
                power = scanner.nextInt();

                try {
                    power = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println(power + " is not a valid input");
                }
                
            }
            FTP = power * 0.95;
        }
    }
}