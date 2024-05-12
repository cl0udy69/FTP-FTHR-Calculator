package UI.methods;

import UI.GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Class definition for dialog to calculate Training Stress Score (TSS)
public class CalculateTSSDialog extends JDialog {
    // Declare private variables for components
    private JTextField durationField, normalizedPowerField, intensityFactorField, functionalThresholdPowerField;
    private JButton calculateButton;
    private GUI parentGUI;

    // Constructor
    public CalculateTSSDialog(GUI parentGUI) {
        super(parentGUI, "Calculate TSS", true); // Call parent constructor with title and modality
        setSize(400, 300); // Set dialog size
        setLocationRelativeTo(parentGUI); // Set dialog location relative to parent GUI
        setLayout(new BorderLayout()); // Set layout for the dialog

        this.parentGUI = parentGUI; // Assign parent GUI

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // Create input panel
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add empty border for spacing

        // Initialize text fields for input
        durationField = new JTextField();
        normalizedPowerField = new JTextField();
        intensityFactorField = new JTextField();
        functionalThresholdPowerField = new JTextField();

        // Add labels and text fields to input panel
        inputPanel.add(new JLabel("Duration (minutes):"));
        inputPanel.add(durationField);
        inputPanel.add(new JLabel("Normalized Power:"));
        inputPanel.add(normalizedPowerField);
        inputPanel.add(new JLabel("Intensity Factor:"));
        inputPanel.add(intensityFactorField);
        inputPanel.add(new JLabel("Functional Threshold Power:"));
        inputPanel.add(functionalThresholdPowerField);

        // Create and configure Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculateTSS()); // Add action listener to calculate TSS

        // Add input panel and Calculate button to the dialog
        JScrollPane scrollPane = new JScrollPane(inputPanel); // Add scroll pane for input panel
        add(scrollPane, BorderLayout.CENTER);
        add(calculateButton, BorderLayout.SOUTH);
    }

    // Method to calculate TSS
    private void calculateTSS() {
        try {
            // Get inputs and convert to appropriate data types
            int duration = Integer.parseInt(durationField.getText());
            int normalizedPower = Integer.parseInt(normalizedPowerField.getText());
            float intensityFactor = Float.parseFloat(intensityFactorField.getText());
            int functionalThresholdPower = Integer.parseInt(functionalThresholdPowerField.getText());

            // Check if any input is non-positive
            if (duration <= 0 || normalizedPower <= 0 || intensityFactor <= 0 || functionalThresholdPower <= 0)
                throw new NumberFormatException();

            // Calculate TSS using the formula
            int tss = (int) (((duration * normalizedPower * intensityFactor) / (functionalThresholdPower * 3600)) * 100);

            // Display TSS in parent GUI's output text area
            parentGUI.getOutputTextArea().append("Your training stress score is: " + tss);
            dispose(); // Close the dialog

        } catch (NumberFormatException e) {
            // Show error message for invalid input
            JOptionPane.showMessageDialog(this, "Please enter valid positive numbers for all fields.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
