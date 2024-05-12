package UI.methods;

import UI.GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Class definition for dialog to calculate Lactate Threshold Heart Rate (LTHR)
public class CalculateLTHRDialog extends JDialog {
    // Declare private variables for components
    private JTextField heartRateField;
    private JButton calculateButton;
    private GUI parentGUI;

    // Constructor
    public CalculateLTHRDialog(GUI parentGUI) {
        super(parentGUI, "Calculate LTHR", true); // Call parent constructor with title and modality
        setSize(400, 150); // Set dialog size
        setLocationRelativeTo(parentGUI); // Set dialog location relative to parent GUI
        setLayout(new BorderLayout()); // Set layout for the dialog

        this.parentGUI = parentGUI; // Assign parent GUI

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5)); // Create input panel
        inputPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); // Add empty border for spacing

        // Prompt user to choose between cycling or running
        String[] options = { "Cycling", "Running" };
        int choice = JOptionPane.showOptionDialog(null, "Choose either cycling or running?", "Choose Objective",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.CLOSED_OPTION) {
            dispose();
            return; // User closed the dialog
        }

        // Initialize input panel based on user's choice
        if (options[choice].equals("Cycling")) {
            initializeCyclingInputPanel(inputPanel);
        } else if (options[choice].equals("Running")) {
            initializeRunningInputPanel(inputPanel);
        }

        // Create and configure Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculateCyclingLTHR()); // Add action listener to calculate LTHR

        // Add input panel and Calculate button to the dialog
        JScrollPane scrollPane = new JScrollPane(inputPanel); // Add scroll pane for input panel
        add(scrollPane, BorderLayout.CENTER);
        add(calculateButton, BorderLayout.SOUTH);
    }

    // Method to initialize input panel for cycling
    private void initializeCyclingInputPanel(JPanel inputPanel) {
        inputPanel.removeAll(); // Clear panel
        inputPanel.setLayout(new GridLayout(1, 1, 5, 5)); // Set layout

        // Create components for input
        JLabel heartRateLabel = new JLabel("Best 20 minute heart rate: ");
        heartRateField = new JTextField();

        // Add components to input panel
        inputPanel.add(heartRateLabel);
        inputPanel.add(heartRateField);
    }

    // Method to calculate LTHR for cycling
    private void calculateCyclingLTHR() {
        try {
            // Get heart rate input and convert to integer
            int heartRate = Integer.parseInt(heartRateField.getText());
            if (heartRate <= 0)
                throw new NumberFormatException(); // Throw exception for negative or zero heart rate

            // Calculate LTHR based on input heart rate
            int lthr = (int) (heartRate * 0.95);
            StringBuilder calculateLTHR = new StringBuilder(); // Initialize string builder

            // Append LTHR and zone information to string builder
            calculateLTHR.append("Lactate Threshold Heartrate: " + lthr + "\n");
            calculateLTHR.append("Zone 1: " + (int) (lthr * 0) + " - " + (int) (lthr * 0.81) + "\n");
            calculateLTHR.append("Zone 2: " + (int) (lthr * 0.81) + " - " + (int) (lthr * 0.89) + "\n");
            calculateLTHR.append("Zone 3: " + (int) (lthr * 0.90) + " - " + (int) (lthr * 0.93) + "\n");
            calculateLTHR.append("Zone 4: " + (int) (lthr * 0.94) + " - " + (int) (lthr * 0.99) + "\n");
            calculateLTHR.append("Zone 5a: " + (int) (lthr * 1.00) + " - " + (int) (lthr * 1.02) + "\n");
            calculateLTHR.append("Zone 5b: " + (int) (lthr * 1.03) + " - " + (int) (lthr * 1.06) + "\n");
            calculateLTHR.append("Zone 5c: " + (int) (lthr * 1.06) + " <" + "\n");

            // Display LTHR information in parent GUI's output text area
            parentGUI.getOutputTextArea().append(calculateLTHR.toString());

            // Show success message
            JOptionPane.showMessageDialog(this, "LTHR calculated successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close the dialog

        } catch (NumberFormatException e) {
            // Show error message for invalid input
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number for Heart Rate.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to initialize input panel for running
    private void initializeRunningInputPanel(JPanel inputPanel) {
        inputPanel.removeAll(); // Clear panel
        inputPanel.setLayout(new GridLayout(1, 2, 5, 5)); // Set layout

        // Create components for input
        heartRateField = new JTextField();

        // Add components to input panel
        inputPanel.add(new JLabel("Best 20 minute heart rate: "));
        inputPanel.add(heartRateField);
    }

    // Method to calculate LTHR for running
    private void calculateRunningLTHR() {
        try {
            // Get heart rate input and convert to integer
            int heartRate = Integer.parseInt(heartRateField.getText());
            if (heartRate <= 0)
                throw new NumberFormatException(); // Throw exception for negative or zero heart rate

            // Calculate LTHR based on input heart rate
            int lthr = (int) (heartRate * 0.95);
            StringBuilder calculateLTHR = new StringBuilder(); // Initialize string builder

            // Append LTHR and zone information to string builder
            calculateLTHR.append("Lactate Threshold Heartrate: " + lthr);
            calculateLTHR.append("Zone 1: " + (int) (lthr * 0) + " - " + (int) (lthr * 0.85) + "\n");
            calculateLTHR.append("Zone 2: " + (int) (lthr * 0.85) + " - " + (int) (lthr * 0.89) + "\n");
            calculateLTHR.append("Zone 3: " + (int) (lthr * 0.90) + " - " + (int) (lthr * 0.94) + "\n");
            calculateLTHR.append("Zone 4: " + (int) (lthr * 0.95) + " - " + (int) (lthr * 0.99) + "\n");
            calculateLTHR.append("Zone 5a: " + (int) (lthr * 1.00) + " - " + (int) (lthr * 1.02) + "\n");
            calculateLTHR.append("Zone 5b: " + (int) (lthr * 1.03) + " - " + (int) (lthr * 1.06) + "\n");
            calculateLTHR.append("Zone 5c: " + (int) (lthr * 1.06) + " <" + "\n");

            // Display LTHR information in parent GUI's output text area
            parentGUI.getOutputTextArea().append(calculateLTHR.toString());

            // Show success message
            JOptionPane.showMessageDialog(this, "LTHR calculated successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close the dialog

        } catch (NumberFormatException e) {
            // Show error message for invalid input
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number for Heart Rate.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
