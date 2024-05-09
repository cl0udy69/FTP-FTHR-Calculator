package UI.methods;

import UI.GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class calculateFTPDialog extends JDialog{
    private JTextField powerField;
    private JButton calculateButton;

    private GUI parentGUI;

    public CalculateFTPialog(GUI parentGUI) {
        super(parentGUI, "Calculate LTHR", true);
        setSize(300, 200);
        setLocationRelativeTo(parentGUI);
        setLayout(new BorderLayout());

        this.parentGUI = parentGUI;

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        heartRateField = new JTextField();

        inputPanel.add(new JLabel ("Power: "));
        inputPanel.add(powerField);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculateLTHR());

        JScrollPane scrollPane = new JScrollPane(inputPanel);

        add(scrollPane, BorderLayout.CENTER);
        add(calculateButton, BorderLayout.SOUTH);
    }
    
    private void calculateFTP() {
        int power;
        int ftp;

        ftp = (int) (power * 0.95);

        StringBuilder calculation = new StringBuilder();
        calculation.append("Your FTP is " + ftp + "\n");
        
    }
}
