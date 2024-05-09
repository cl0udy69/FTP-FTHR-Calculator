package UI.methods;

import UI.GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class CalculateLTHRDialog extends JDialog {
    private JTextField heartRateField;
    private JButton calculateButton;

    private GUI parentGUI;

    public CalculateLTHRDialog(GUI parentGUI) {
        super(parentGUI, "Calculate LTHR", true);
        setSize(400, 150);
        setLocationRelativeTo(parentGUI);
        setLayout(new BorderLayout());

        this.parentGUI = parentGUI;

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        String[] options = { "Cycling", "Running" };
        int choice = JOptionPane.showOptionDialog(null, "Choose either cycling or running?",
                "Choose Objective",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.CLOSED_OPTION) {
            dispose();
            return; // User closed the dialog
        }

        if (options[choice].equals("Cycling")) {
            initializeCyclingInputPanel(inputPanel);
        } else if (options[choice].equals("Running")) {
            initializeRunningInputPanel(inputPanel);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculateCyclingLTHR());
        JScrollPane scrollPane = new JScrollPane(inputPanel);

        add(scrollPane, BorderLayout.CENTER);
        add(calculateButton, BorderLayout.SOUTH);

        // heartRateField = new JTextField();

        // inputPanel.add(new JLabel("Heart Rate (bpm):"));
        // inputPanel.add(heartRateField);

        // calculateButton = new JButton("Calculate");
        // calculateButton.addActionListener(e -> calculateLTHR());

        // JScrollPane scrollPane = new JScrollPane(inputPanel);

        // add(scrollPane, BorderLayout.CENTER);
        // add(calculateButton, BorderLayout.SOUTH);
    }

    private void initializeCyclingInputPanel(JPanel inputPanel) {
        inputPanel.removeAll();
        inputPanel.setLayout(new GridLayout(1, 1, 5, 5));

        JLabel heartRateLabel = new JLabel("Best 20 minute heart rate: ");
        heartRateField = new JTextField();

        inputPanel.add(heartRateLabel);
        inputPanel.add(heartRateField);
    }

    private void calculateCyclingLTHR() {
        try {
            int heartRate = Integer.parseInt(heartRateField.getText());
            if (heartRate <= 0)
                throw new NumberFormatException();

            int lthr = (int) (heartRate * 0.95);
            StringBuilder calculateLTHR = new StringBuilder();
            calculateLTHR.append("Lactate Threshold Heartrate: " + lthr + "\n");
            calculateLTHR.append("Zone 1: " + (int) (lthr * 0) + " - " + (int) (lthr * 0.81) + "\n");
            calculateLTHR.append("Zone 2: " + (int) (lthr * 0.81) + " - " + (int) (lthr * 0.89) + "\n");
            calculateLTHR.append("Zone 3: " + (int) (lthr * 0.90) + " - " + (int) (lthr * 0.93) + "\n");
            calculateLTHR.append("Zone 4: " + (int) (lthr * 0.94) + " - " + (int) (lthr * 0.99) + "\n");
            calculateLTHR.append("Zone 5a: " + (int) (lthr * 1.00) + " - " + (int) (lthr * 1.02) + "\n");
            calculateLTHR.append("Zone 5b: " + (int) (lthr * 1.03) + " - " + (int) (lthr * 1.06) + "\n");
            calculateLTHR.append("Zone 5c: " + (int) (lthr * 1.06) + " <" + "\n");

            parentGUI.getOutputTextArea().append(calculateLTHR.toString());
            JOptionPane.showMessageDialog(this, "LTHR calculated successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number for Heart Rate.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeRunningInputPanel(JPanel inputPanel) {
        inputPanel.removeAll();
        inputPanel.setLayout(new GridLayout(1, 2, 5, 5));

        heartRateField = new JTextField();

        inputPanel.add(new JLabel("Best 20 minute heart rate: "));
        inputPanel.add(heartRateField);
    }

    private void calculateRunningLTHR() {
        try {
            int heartRate = Integer.parseInt(heartRateField.getText());
            if (heartRate <= 0)
                throw new NumberFormatException();

            int lthr = (int) (heartRate * 0.95);
            StringBuilder calculateLTHR = new StringBuilder();
            calculateLTHR.append("Lactate Threshold Heartrate: " + lthr);
            calculateLTHR.append("Zone 1: " + (int) (lthr * 0) + " - " + (int) (lthr * 0.85) + "\n");
            calculateLTHR.append("Zone 2: " + (int) (lthr * 0.85) + " - " + (int) (lthr * 0.89) + "\n");
            calculateLTHR.append("Zone 3: " + (int) (lthr * 0.90) + " - " + (int) (lthr * 0.94) + "\n");
            calculateLTHR.append("Zone 4: " + (int) (lthr * 0.95) + " - " + (int) (lthr * 0.99) + "\n");
            calculateLTHR.append("Zone 5a: " + (int) (lthr * 1.00) + " - " + (int) (lthr * 1.02) + "\n");
            calculateLTHR.append("Zone 5b: " + (int) (lthr * 1.03) + " - " + (int) (lthr * 1.06) + "\n");
            calculateLTHR.append("Zone 5c: " + (int) (lthr * 1.06) + " <" + "\n");

            parentGUI.getOutputTextArea().append(calculateLTHR.toString());
            JOptionPane.showMessageDialog(this, "LTHR calculated successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number for Heart Rate.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
