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
        setSize(300, 200);
        setLocationRelativeTo(parentGUI);
        setLayout(new BorderLayout());

        this.parentGUI = parentGUI;

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        heartRateField = new JTextField();

        inputPanel.add(new JLabel("Heart Rate (bpm):"));
        inputPanel.add(heartRateField);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculateLTHR());

        JScrollPane scrollPane = new JScrollPane(inputPanel);

        add(scrollPane, BorderLayout.CENTER);
        add(calculateButton, BorderLayout.SOUTH);
    }

    private void calculateLTHR() {
        try {
            int heartRate = Integer.parseInt(heartRateField.getText());
            if (heartRate <= 0)
                throw new NumberFormatException();

            int lthr = (int) (heartRate * 0.95);
            parentGUI.getOutputTextArea().append("Your lactate threshold heart rate is: " + lthr);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number for Heart Rate.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
