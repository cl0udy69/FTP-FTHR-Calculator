package UI.methods;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import UI.GUI;

import java.awt.*;
import java.awt.event.*;

public class CalculateTSSDialog extends JDialog {
    private JTextField durationField, normalizedPowerField, intensityFactorField, functionalThresholdPowerField;
    private JButton calculateButton;

    private GUI parentGUI;

    public CalculateTSSDialog(GUI parentGUI) {
        super(parentGUI, "Calculate TSS", true);
        setSize(400, 300);
        setLocationRelativeTo(parentGUI);
        setLayout(new BorderLayout());

        this.parentGUI = parentGUI;

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        durationField = new JTextField();
        normalizedPowerField = new JTextField();
        intensityFactorField = new JTextField();
        functionalThresholdPowerField = new JTextField();

        inputPanel.add(new JLabel("Duration (minutes):"));
        inputPanel.add(durationField);
        inputPanel.add(new JLabel("Normalized Power:"));
        inputPanel.add(normalizedPowerField);
        inputPanel.add(new JLabel("Intensity Factor:"));
        inputPanel.add(intensityFactorField);
        inputPanel.add(new JLabel("Functional Threshold Power:"));
        inputPanel.add(functionalThresholdPowerField);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculateTSS());

        JScrollPane scrollPane = new JScrollPane(inputPanel);

        add(scrollPane, BorderLayout.CENTER);
        add(calculateButton, BorderLayout.SOUTH);
    }

    private void calculateTSS() {
        try {
            int duration = Integer.parseInt(durationField.getText());
            int normalizedPower = Integer.parseInt(normalizedPowerField.getText());
            float intensityFactor = Float.parseFloat(intensityFactorField.getText());
            int functionalThresholdPower = Integer.parseInt(functionalThresholdPowerField.getText());

            if (duration <= 0 || normalizedPower <= 0 || intensityFactor <= 0 || functionalThresholdPower <= 0)
                throw new NumberFormatException();

            int tss = (int) (((duration * normalizedPower * intensityFactor) / (functionalThresholdPower * 3600))
                    * 100);
            parentGUI.getOutputTextArea().append("Your training stress score is: " + tss);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid positive numbers for all fields.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
