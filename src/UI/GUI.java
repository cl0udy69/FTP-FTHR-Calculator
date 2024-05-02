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
        calculateFTPButton.setPreferredSize(new Dimension(150, 50));
        calculateFTPButton.setIcon(new ImageIcon("ftp_icon.png"));
        calculateTSSButton = new JButton("Calculate TSS");
        calculateTSSButton.setPreferredSize(new Dimension(150, 50));
        calculateTSSButton.setIcon(new ImageIcon("tss_icon.png"));
        calculateLTHRButton = new JButton("Calculate LTHR");
        calculateLTHRButton.setPreferredSize(new Dimension(150, 50));
        calculateLTHRButton.setIcon(new ImageIcon("lthr_icon.png"));
        createTrainingPlanButton = new JButton("Create Training Plan");
        createTrainingPlanButton.setPreferredSize(new Dimension(150, 50));
        createTrainingPlanButton.setIcon(new ImageIcon("training_plan_icon.png"));
        saveDataButton = new JButton("Save Data");
        saveDataButton.setPreferredSize(new Dimension(150, 50));
        loadDataButton = new JButton("Load Data");
        loadDataButton.setPreferredSize(new Dimension(150, 50));

        buttonPanel.add(calculateFTPButton);
        buttonPanel.add(calculateTSSButton);
        buttonPanel.add(calculateLTHRButton);
        buttonPanel.add(createTrainingPlanButton);
        buttonPanel.add(saveDataButton);
        buttonPanel.add(loadDataButton);

        // Text area for output
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font("Montserrat", Font.PLAIN, 16));
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
            calculateFTP(); // Prompt user again for valid input
        }
    }

    // Method to calculate TSS
    private void calculateTSS() {
        CalculateTSSDialog dialog = new CalculateTSSDialog(this);
        dialog.setVisible(true);
    }

    // Method to calculate LTHR
    private void calculateLTHR() {
        CalculateLTHRDialog dialog = new CalculateLTHRDialog(this);
        dialog.setVisible(true);
    }

    // Method to create training plan
    private void createTrainingPlan() {
        CreateTrainingPlanDialog dialog = new CreateTrainingPlanDialog(this);
        dialog.setVisible(true);
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
    }

    public JTextArea getOutputTextArea() {
        return outputTextArea;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);
        });
    }
}

class CalculateTSSDialog extends JDialog {
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
            dispose(); // Close the dialog after calculation
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid positive numbers.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

class CalculateLTHRDialog extends JDialog {
    private JTextField heartRateField;
    private JButton calculateButton;

    private GUI parentGUI;

    public CalculateLTHRDialog(GUI parentGUI) {
        super(parentGUI, "Calculate LTHR", true);
        setSize(400, 300);
        setLocationRelativeTo(parentGUI);
        setLayout(new BorderLayout());

        this.parentGUI = parentGUI;

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        heartRateField = new JTextField();

        inputPanel.add(new JLabel("Heart Rate:"));
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

            int LTHR = (int) (heartRate * 0.95);

            parentGUI.getOutputTextArea().append("Your LTHR is " + LTHR + "\n");
            parentGUI.getOutputTextArea().append("Zone 1: " + (int) (LTHR * 0) + " - " + (int) (LTHR * 0.81) + "\n");
            parentGUI.getOutputTextArea()
                    .append("Zone 2: " + (int) (LTHR * 0.81) + " - " + (int) (heartRate * 0.89) + "\n");
            parentGUI.getOutputTextArea().append("Zone 3: " + (int) (LTHR * 0.90) + " - " + (int) (LTHR * 0.93) + "\n");
            parentGUI.getOutputTextArea().append("Zone 4: " + (int) (LTHR * 0.94) + " - " + (int) (LTHR * 0.99) + "\n");
            parentGUI.getOutputTextArea()
                    .append("Zone 5a: " + (int) (LTHR * 1.00) + " - " + (int) (LTHR * 1.02) + "\n");
            parentGUI.getOutputTextArea()
                    .append("Zone 5b: " + (int) (LTHR * 1.03) + " - " + (int) (LTHR * 1.06) + "\n");
            parentGUI.getOutputTextArea().append("Zone 5c: " + (int) (LTHR * 1.06) + " <" + "\n");
            dispose(); // Close the dialog after calculation
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number for Heart Rate.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

class CreateTrainingPlanDialog extends JDialog {
    private JTextField durationField, avgPowerField, avgHeartRateField, avgCadenceField,
            intervalZonesField, intervalWorkoutField, heartRateIntervalsField, cadenceIntervalWorkoutField;
    private JButton createButton;

    private GUI parentGUI;

    public CreateTrainingPlanDialog(GUI parentGUI) {
        super(parentGUI, "Create Training Plan", true);
        setSize(400, 400);
        setLocationRelativeTo(parentGUI);
        setLayout(new BorderLayout());

        this.parentGUI = parentGUI;

        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // String[] options = {"Cycling", "Running"};
        // int choice = JOptionPane.showOptionDialog(null, "What is the key object of the training ride?",
        //         "Choose Objective",
        //         JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        // if (choice == JOptionPane.CLOSED_OPTION)
        //     return; // User closed the dialog

        //     String objective = options[choice];

        durationField = new JTextField();
        avgPowerField = new JTextField();
        avgHeartRateField = new JTextField();
        avgCadenceField = new JTextField();
        intervalZonesField = new JTextField();
        intervalWorkoutField = new JTextField();
        heartRateIntervalsField = new JTextField();
        cadenceIntervalWorkoutField = new JTextField();

        inputPanel.add(new JLabel("Duration (minutes):"));
        inputPanel.add(durationField);
        inputPanel.add(new JLabel("Average Estimated Power:"));
        inputPanel.add(avgPowerField);
        inputPanel.add(new JLabel("Average Estimated Heart Rate:"));
        inputPanel.add(avgHeartRateField);
        inputPanel.add(new JLabel("Average Cadence:"));
        inputPanel.add(avgCadenceField);
        inputPanel.add(new JLabel("Interval Zones:"));
        inputPanel.add(intervalZonesField);
        inputPanel.add(new JLabel("Interval Workout:"));
        inputPanel.add(intervalWorkoutField);
        inputPanel.add(new JLabel("Heart Rate Intervals:"));
        inputPanel.add(heartRateIntervalsField);
        inputPanel.add(new JLabel("Cadence Interval Workout:"));
        inputPanel.add(cadenceIntervalWorkoutField);

        createButton = new JButton("Create");
        createButton.addActionListener(e -> createTrainingPlan());

        JScrollPane scrollPane = new JScrollPane(inputPanel);

        add(scrollPane, BorderLayout.CENTER);
        add(createButton, BorderLayout.SOUTH);
    }

    private void createTrainingPlan() {
            try {
                int duration = Integer.parseInt(durationField.getText());
                int avgPower = Integer.parseInt(avgPowerField.getText());
                int avgHeartRate = Integer.parseInt(avgHeartRateField.getText());
                int avgCadence = Integer.parseInt(avgCadenceField.getText());
    
                String intervalZones = intervalZonesField.getText();
                String intervalWorkout = intervalWorkoutField.getText();
                String heartRateIntervals = heartRateIntervalsField.getText();
                String cadenceIntervalWorkout = cadenceIntervalWorkoutField.getText();
    
                StringBuilder planDetails = new StringBuilder();
                planDetails.append("Training Plan Details:\n");
                planDetails.append("Duration (minutes): ").append(duration).append("\n");
                planDetails.append("Average Estimated Power: ").append(avgPower).append("\n");
                planDetails.append("Average Estimated Heart Rate: ").append(avgHeartRate).append("\n");
                planDetails.append("Average Cadence: ").append(avgCadence).append("\n");
                planDetails.append("Interval Zones: ").append(intervalZones).append("\n");
                planDetails.append("Interval Workout: ").append(intervalWorkout).append("\n");
                planDetails.append("Heart Rate Intervals: ").append(heartRateIntervals).append("\n");
                planDetails.append("Cadence Interval Workout: ").append(cadenceIntervalWorkout).append("\n");
    
                parentGUI.getOutputTextArea().append(planDetails.toString());
                JOptionPane.showMessageDialog(this, "Training plan created successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for all fields.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } 
    }
    
}
