package methods;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class CalculateTSSDialog extends JDialog {
    private JTextField durationField, normalizedPowerField, intensityFactorField, functionalThresholdPowerField;
    private JButton calculateButton;
    private JTextArea outputTextArea;

    public CalculateTSSDialog(JFrame parent) {
        super(parent, "Calculate TSS", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

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

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        add(inputPanel, BorderLayout.NORTH);
        add(calculateButton, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
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
            outputTextArea.setText("Your training stress score is: " + tss);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid positive numbers.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
