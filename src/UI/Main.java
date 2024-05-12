package UI; // Package declaration

import javax.swing.*;

public class Main { // Main class declaration
    public static void main(String[] args) { // Main method
        SwingUtilities.invokeLater(() -> { // Using SwingUtilities.invokeLater to ensure GUI is created on the Event Dispatch Thread
            GUI gui = new GUI(); // Creating an instance of the GUI class
            gui.setVisible(true); // Making the GUI visible
        });
    }
}
