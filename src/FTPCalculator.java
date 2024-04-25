import java.util.Scanner;

public class FTPCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        powerFunctions powerFunc = new powerFunctions();
        heartRateFunctions heartRateFunc = new heartRateFunctions();

        String selection = "";

        System.out.println("Would you like to calculate FTP or LTHR");
        selection = scanner.nextLine();

        if (selection.equalsIgnoreCase("ftp") || selection.equalsIgnoreCase("functional threshold power")
                || selection.equalsIgnoreCase("calculate ftp") || selection.equalsIgnoreCase("calculate functional threshold power")) {
                    powerFunc.calculateFTP();
        } else if (selection.equalsIgnoreCase("lthr") || selection.equalsIgnoreCase("lactate threshold power")
                || selection.equalsIgnoreCase("calculate lthr") || selection.equalsIgnoreCase("calculate lactate threshold power")) {
                    heartRateFunc.calculateHeartRate();
        }
        scanner.close();
    }
}
