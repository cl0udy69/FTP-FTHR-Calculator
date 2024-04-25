import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String selection = "";

        System.out.println("Would you like to calculate FTP or FTHR?: ");
        selection = scanner.nextLine();

        if (selection.equalsIgnoreCase("ftp") || selection.equalsIgnoreCase("functional threshold power")
            || selection.equalsIgnoreCase("threshold power") || selection.equalsIgnoreCase("power")) {
                Power.FTP();
        }
    }
}
