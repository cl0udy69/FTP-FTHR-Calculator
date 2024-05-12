package UI.methods;

import UI.GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Class definition for a dialog to calculate FTP
public class calculateFTPDialog extends JDialog {
    // Declare private variables for components
    private JTextField powerField;
    private JButton calculateButton;
    private GUI parentGUI;

    // Constructor
    public calculateFTPDialog(GUI parentGUI) {
        super(parentGUI, "Calculate FTP", true); // Call parent constructor with title and modality
        setSize(400, 150); // Set dialog size
        setLocationRelativeTo(parentGUI); // Set dialog location relative to parent GUI
        setLayout(new BorderLayout()); // Set layout for the dialog
        
        this.parentGUI = parentGUI; // Assign parent GUI
        
        // Create input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); // Add empty border for spacing
        
        // Initialize input panel
        initializeFTPInputPanel(inputPanel);
        
        // Create and configure Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculateFTP()); // Add action listener to calculate FTP
        
        // Add input panel and Calculate button to the dialog
        JScrollPane scrollPane = new JScrollPane(inputPanel); // Add scroll pane for input panel
        add(scrollPane, BorderLayout.CENTER);
        add(calculateButton, BorderLayout.SOUTH);
    }

    // Method to initialize FTP input panel
    private void initializeFTPInputPanel(JPanel inputPanel) {
        inputPanel.removeAll(); // Clear panel
        inputPanel.setLayout(new GridLayout(1, 1, 5, 5)); // Set layout
        
        // Create components for input
        JLabel powerLabel = new JLabel("Best 20 minute power: ");
        powerField = new JTextField();
        
        // Add components to input panel
        inputPanel.add(powerLabel);
        inputPanel.add(powerField);
    }

    // Method to calculate FTP
    private void calculateFTP() {
        try {
            // Get power input and convert to integer
            int power = Integer.parseInt(powerField.getText());
            if (power <= 0)
                throw new NumberFormatException(); // Throw exception for negative or zero power
            
            // Calculate FTP based on input power
            int FTP = (int) (power * 0.95);
            StringBuilder calculateFTP = new StringBuilder(); // Initialize string builder
            
            // Append FTP and zone information to string builder
            calculateFTP.append("Functional Threshold Power: " + FTP +"\n");
            calculateFTP.append("Zone 1: " + (int) (FTP * 0) + " - " + (int) (FTP * 0.55) + "\n");
            calculateFTP.append("Zone 2: " + (int) (FTP * 0.55) + " - " + (int) (FTP * 0.75) + "\n");
            calculateFTP.append("Zone 3: " + (int) (FTP * 0.75) + " - " + (int) (FTP * 0.87) + "\n");
            calculateFTP.append("Zone 4: " + (int) (FTP * 0.87) + " - " + (int) (FTP * 0.94) + "\n");
            calculateFTP.append("Zone 5a: " + (int) (FTP * 0.94) + " - " + (int) (FTP * 1.05) + "\n");
            calculateFTP.append("Zone 5b: " + (int) (FTP * 1.05) + " - " + (int) (FTP * 1.20) + "\n");
            calculateFTP.append("Zone 5c: " + (int) (FTP * 1.020) + " - " + (int) (FTP * 1.25) + "\n");
            
            // Display FTP information in parent GUI's output text area
            parentGUI.getOutputTextArea().append(calculateFTP.toString());
            
            // Show success message
            JOptionPane.showMessageDialog(this, "FTP calculated successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close the dialog

        } catch (NumberFormatException e) {
            // Show error message for invalid input
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number for Heart Rate.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
