package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUI extends JFrame {
    private JButton calculateFTPButton, calculateTSSButton, calculateLTHRButton, createTrainingPlanButton,
            saveDataButton, loadDataButton;
    private JTextArea outputTextArea;

    // Array of options for action selection
    private String[] options = { "Calculate FTP", "Calculate TSS", "Calculate LTHR", "Create Training Plan",
            "Save Data", "Load Data" };
    // Index to keep track of the current action being processed
    private int currentActionIndex = 0;

    public GUI() {
        setTitle("Propel");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 5, 20, 20));
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Add icons to buttons
        calculateFTPButton = new JButton("Calculate FTP");
        calculateFTPButton.setIcon(new ImageIcon("ftp_icon.png"));
        calculateTSSButton = new JButton("Calculate TSS");
        calculateTSSButton.setIcon(new ImageIcon("tss_icon.png"));
        calculateLTHRButton = new JButton("Calculate LTHR");
        calculateLTHRButton.setIcon(new ImageIcon("lthr_icon.png"));
        createTrainingPlanButton = new JButton("Create Training Plan");
        createTrainingPlanButton.setIcon(new ImageIcon("training_plan_icon.png"));
        saveDataButton = new JButton("Save Data");
        loadDataButton = new JButton("Load Data");

        buttonPanel.add(calculateFTPButton);
        buttonPanel.add(calculateTSSButton);
        buttonPanel.add(calculateLTHRButton);
        buttonPanel.add(createTrainingPlanButton);
        buttonPanel.add(saveDataButton);
        buttonPanel.add(loadDataButton);

        // Text area for output
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font("Montserrat", Font.PLAIN, 17));
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        // Add components to the frame
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add action listeners
        calculateFTPButton.addActionListener(e -> calculateFTP());
        calculateTSSButton.addActionListener(e -> calculateTSS());
        calculateLTHRButton.addActionListener(e -> calculateLTHR());
        createTrainingPlanButton.addActionListener(e -> createTrainingPlan());
        saveDataButton.addActionListener(new SaveDataListener());
        loadDataButton.addActionListener(new LoadDataListener());

        // Prompt user to save data before closing the application
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(GUI.this,
                        "Do you want to save your data before exiting?", "Save Data",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    saveData();
                    dispose(); // Close the application after saving
                } else if (option == JOptionPane.NO_OPTION) {
                    dispose(); // Close the application without saving
                }
            }
        });
    }

    // Method to calculate FTP
    private void calculateFTP() {
        try {
            String input = JOptionPane
                    .showInputDialog("Enter your best 20 minute power: \n(Enter 'skip' to skip this step)");
            if (input == null || input.isEmpty())
                return; // Cancel button pressed
            if (input.equalsIgnoreCase("skip")) {
                currentActionIndex++;
                processNextAction();
                return;
            }
            int power = Integer.parseInt(input);
            if (power <= 0)
                throw new NumberFormatException();
            int FTP = (int) (power * 0.95);
            outputTextArea.setText("Your FTP is " + FTP + "\n");
            outputTextArea.append("Zone 1: " + (int) (FTP * 0) + " - " + (int) (FTP * 0.55) + "\n");
            outputTextArea.append("Zone 2: " + (int) (FTP * 0.55) + " - " + (int) (FTP * 0.75) + "\n");
            outputTextArea.append("Zone 3: " + (int) (FTP * 0.75) + " - " + (int) (FTP * 0.87) + "\n");
            outputTextArea.append("Zone 4: " + (int) (FTP * 0.87) + " - " + (int) (FTP * 0.94) + "\n");
            outputTextArea.append("Zone 5: " + (int) (FTP * 0.94) + " - " + (int) (FTP * 1.05) + "\n");
            outputTextArea.append("Zone 6: " + (int) (FTP * 1.05) + " - " + (int) (FTP * 1.20) + "\n");
            outputTextArea.append("Zone 7: " + (int) (FTP * 1.20) + " - " + (int) (FTP * 1.25) + "\n");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid positive number for Power.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            calculateFTP(); // Prompt again for valid data
        }
    }

    // Method to calculate TSS
    private void calculateTSS() {
        try {
            String input = JOptionPane.showInputDialog(
                    "Enter the duration of your ride (in minutes): \n(Enter 'skip' to skip this step)");
            if (input == null || input.isEmpty())
                return; // Cancel button pressed
            if (input.equalsIgnoreCase("skip")) {
                currentActionIndex++;
                processNextAction();
                return;
            }
            int duration = Integer.parseInt(input);
            if (duration <= 0)
                throw new NumberFormatException();
            int normalizedPower = Integer.parseInt(JOptionPane.showInputDialog("Enter your normalized power: "));
            float intensityFactor = Float.parseFloat(JOptionPane.showInputDialog("Enter your intensity factor: "));
            int functionalThresholdPower = Integer
                    .parseInt(JOptionPane.showInputDialog("Enter your functional threshold power: "));

            int tss = (int) (((duration * normalizedPower * intensityFactor) / (functionalThresholdPower * 3600))
                    * 100);
            outputTextArea.setText("Your training stress score is: " + tss);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Please enter valid positive numbers for Duration, Normalized Power, Intensity Factor, and FTP.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            calculateTSS(); // Prompt again for valid data
        }
    }

    // Method to calculate LTHR
    private void calculateLTHR() {
        String[] options = { "Cycling", "Running" };
        int choice = JOptionPane.showOptionDialog(null, "Do you want to calculate cycling or running zones?",
                "Choose Activity",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.CLOSED_OPTION)
            return; // User closed the dialog

        String activity = options[choice];

        try {
            String input = JOptionPane
                    .showInputDialog("Enter your best 20 minute heart rate: \n(Enter 'skip' to skip this step)");
            if (input == null || input.isEmpty())
                return; // Cancel button pressed
            if (input.equalsIgnoreCase("skip")) {
                currentActionIndex++;
                processNextAction();
                return;
            }
            int heartRate = Integer.parseInt(input);
            if (heartRate <= 0)
                throw new NumberFormatException();
            int LTHR = (int) (heartRate * 0.95);

            outputTextArea.setText("Your LTHR is " + LTHR + "\n");

            if (activity.equals("Cycling")) {
                outputTextArea.append("Zone 1: " + (int) (LTHR * 0) + " - " + (int) (LTHR * 0.81) + "\n");
                outputTextArea.append("Zone 2: " + (int) (LTHR * 0.81) + " - " + (int) (heartRate * 0.89) + "\n");
                outputTextArea.append("Zone 3: " + (int) (LTHR * 0.90) + " - " + (int) (LTHR * 0.93) + "\n");
                outputTextArea.append("Zone 4: " + (int) (LTHR * 0.94) + " - " + (int) (LTHR * 0.99) + "\n");
                outputTextArea.append("Zone 5a: " + (int) (LTHR * 1.00) + " - " + (int) (LTHR * 1.02) + "\n");
                outputTextArea.append("Zone 5b: " + (int) (LTHR * 1.03) + " - " + (int) (LTHR * 1.06) + "\n");
                outputTextArea.append("Zone 5c: " + (int) (LTHR * 1.06) + " <" + "\n");
            } else if (activity.equals("Running")) {
                outputTextArea.append("Zone 1: " + (int) (LTHR * 0) + " - " + (int) (LTHR * 0.85) + "\n");
                outputTextArea.append("Zone 2: " + (int) (LTHR * 0.85) + " - " + (int) (LTHR * 0.89) + "\n");
                outputTextArea.append("Zone 3: " + (int) (LTHR * 0.90) + " - " + (int) (LTHR * 0.94) + "\n");
                outputTextArea.append("Zone 4: " + (int) (LTHR * 0.95) + " - " + (int) (LTHR * 0.99) + "\n");
                outputTextArea.append("Zone 5a: " + (int) (LTHR * 1.00) + " - " + (int) (LTHR * 1.02) + "\n");
                outputTextArea.append("Zone 5b: " + (int) (LTHR * 1.03) + " - " + (int) (LTHR * 1.06) + "\n");
                outputTextArea.append("Zone 5c: " + (int) (LTHR * 1.06) + " <" + "\n");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid positive number for Heart Rate.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            calculateLTHR(); // Prompt again for valid data
        }
    }

    // Method to create training plan
    private void createTrainingPlan() {
        String[] options = { "Cycling", "Running" };
        int choice = JOptionPane.showOptionDialog(null, "What is the key object of the training ride?",
                "Choose Objective",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.CLOSED_OPTION)
            return; // User closed the dialog

        String objective = options[choice];

        try {
            String input = JOptionPane.showInputDialog("Enter the duration of your ride (in minutes): \n(Enter 'skip' to skip this step)");
            if (input == null || input.isEmpty())
                return; // Cancel button pressed
            if (input.equalsIgnoreCase("skip")) {
                currentActionIndex++;
                processNextAction();
                return;
            }
            int duration = Integer.parseInt(input);
            if (duration <= 0)
                throw new NumberFormatException();
            int estimatedAvgPower = Integer.parseInt(JOptionPane.showInputDialog("Enter your average estimated power: "));
            int estimatedAvgHeartRate = Integer.parseInt(JOptionPane.showInputDialog("Enter your average estimated heart rate: "));
            int estimatedAvgCadence = Integer.parseInt(JOptionPane.showInputDialog(""));
            int intervalZones = Integer.parseInt(JOptionPane.showInputDialog("Enter the specific training zones you are going to work in:"));
            int intervalWorkout = Integer.parseInt(JOptionPane.showInputDialog("Enter your intervals:"));
            // add the ability to combine cadence and interval zones
            // add hydration
            // add nutrition
            // add estimated TSS
            // add route upload
            // pacing
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid positive numbers.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            createTrainingPlan(); // Prompt again for valid data
        }
    }

    private class SaveDataListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            saveData();
        }
    }

    private class LoadDataListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            loadData();
        }
    }

    private void saveData() {
        String data = outputTextArea.getText();
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile())) {
                writer.write(data);
                JOptionPane.showMessageDialog(this, "Data saved successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadData() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                outputTextArea.read(reader, null);
                JOptionPane.showMessageDialog(this, "Data loaded successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void processNextAction() {
        if (currentActionIndex >= options.length) {
            JOptionPane.showMessageDialog(this, "No more actions to process.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String nextAction = options[currentActionIndex++];
        JOptionPane.showMessageDialog(this, "Processing next action: " + nextAction, "Info",
                JOptionPane.INFORMATION_MESSAGE);
        // Additional logic for processing the next action
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);
        });
    }
}
