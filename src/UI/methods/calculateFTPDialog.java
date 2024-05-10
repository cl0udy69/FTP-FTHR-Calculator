package UI.methods;

import UI.GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class calculateFTPDialog extends JDialog {
    private JTextField powerField;
    private JButton calculateButton;

    private GUI parentGUI;

    public calculateFTPDialog(GUI parentGUI) {
        super(parentGUI, "Calculate FTP", true);
        setSize(400, 150);
        setLocationRelativeTo(parentGUI);
        setLayout(new BorderLayout());
    
        this.parentGUI = parentGUI;
    
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    
        initializeFTPInputPanel(inputPanel);
    
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculateFTP());
        JScrollPane scrollPane = new JScrollPane(inputPanel);
    
        add(scrollPane, BorderLayout.CENTER);
        add(calculateButton, BorderLayout.SOUTH);
    }

    private void initializeFTPInputPanel(JPanel inputPanel) {
        inputPanel.removeAll();
        inputPanel.setLayout(new GridLayout(1, 1, 5, 5));

        JLabel powerLabel = new JLabel("Best 20 minute power: ");
        powerField = new JTextField();

        inputPanel.add(powerLabel);
        inputPanel.add(powerField);
    }

    private void calculateFTP() {
        try {
            int power = Integer.parseInt(powerField.getText());
            if (power <= 0)
                throw new NumberFormatException();

            int FTP = (int) (power * 0.95);
            StringBuilder calculateFTP = new StringBuilder();
            calculateFTP.append("Functional Threshold Power: " + power +"\n");
            calculateFTP.append("Zone 1: " + (int) (FTP * 0) + " - " + (int) (FTP * 0.55) + "\n");
            calculateFTP.append("Zone 2: " + (int) (FTP * 0.55) + " - " + (int) (FTP * 0.75) + "\n");
            calculateFTP.append("Zone 3: " + (int) (FTP * 0.75) + " - " + (int) (FTP * 0.87) + "\n");
            calculateFTP.append("Zone 4: " + (int) (FTP * 0.87) + " - " + (int) (FTP * 0.94) + "\n");
            calculateFTP.append("Zone 5a: " + (int) (FTP * 0.94) + " - " + (int) (FTP * 1.05) + "\n");
            calculateFTP.append("Zone 5b: " + (int) (FTP * 1.05) + " - " + (int) (FTP * 1.20) + "\n");
            calculateFTP.append("Zone 5c: " + (int) (FTP * 1.020) + " - " + (int) (FTP * 1.25) + "\n");

            parentGUI.getOutputTextArea().append(calculateFTP.toString());
            JOptionPane.showMessageDialog(this, "FTP calculated successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number for Heart Rate.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
