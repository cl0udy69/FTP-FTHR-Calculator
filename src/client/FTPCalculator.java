package client;
import java.util.Scanner;

import functions.heartRateFunctions;
import functions.powerFunctions;
import functions.trainingStressScoreFunctions;

public class FTPCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        powerFunctions powerFunc = new powerFunctions();
        heartRateFunctions heartRateFunc = new heartRateFunctions();
        trainingStressScoreFunctions tssFunc = new trainingStressScoreFunctions();

        String selection = "";

        System.out.println("Would you like to calculate FTP, LTHR, or TSS");
        selection = scanner.nextLine();

        if (selection.equalsIgnoreCase("ftp") || selection.equalsIgnoreCase("functional threshold power")
                || selection.equalsIgnoreCase("calculate ftp") || selection.equalsIgnoreCase("calculate functional threshold power")) {
                    powerFunc.calculateFTP();
        } else if (selection.equalsIgnoreCase("lthr") || selection.equalsIgnoreCase("lactate threshold power")
                || selection.equalsIgnoreCase("calculate lthr") || selection.equalsIgnoreCase("calculate lactate threshold power")) {
                    heartRateFunc.calculateHeartRate();
        } else if (selection.equalsIgnoreCase("tss") || selection.equalsIgnoreCase("training stress score")
                || selection.equalsIgnoreCase("calculate tss") || selection.equalsIgnoreCase("calculate training stress score")) {
                    tssFunc.calculateTrainingStressScore();
        }
        scanner.close();
    }
}
