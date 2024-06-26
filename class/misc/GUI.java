package test;

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
        buttonPanel.add(saveDataButton);x
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

    private void clearTextField() {
        outputTextArea.setText("");
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
    private JTextField durationField, distanceField, avgPowerField, avgHeartRateField, avgCadenceField,
            intervalZonesField, intervalWorkoutField, heartRateIntervalsField, cadenceIntervalWorkoutField,
            avgPaceField, dateField;
    private JButton createButton;
    private JComboBox<String> objectiveComboBox; // New JComboBox for key objective selection

    private GUI parentGUI;

    public CreateTrainingPlanDialog(GUI parentGUI) {
        super(parentGUI, "Create Training Plan", true);
        setSize(450, 500);
        setLocationRelativeTo(parentGUI);
        setLayout(new BorderLayout());

        this.parentGUI = parentGUI;

        JPanel inputPanel = new JPanel(new GridLayout(13, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

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

        createButton = new JButton("Create");
        createButton.addActionListener(e -> createTrainingRide());

        JScrollPane scrollPane = new JScrollPane(inputPanel);

        add(scrollPane, BorderLayout.CENTER);
        add(createButton, BorderLayout.SOUTH);
    }

    private void initializeCyclingInputPanel(JPanel inputPanel) {
        inputPanel.removeAll(); // Clear existing components
        inputPanel.setLayout(new GridLayout(14, 2, 10, 10));

        // Add cycling-specific input fields
        JLabel dateLabel = new JLabel("Date: ");
        dateField = new JTextField();
        JLabel durationLabel = new JLabel("Duration (minutes):");
        durationField = new JTextField();
        JLabel distanceLabel = new JLabel("Distance (if desired; enter units):");
        distanceField = new JTextField();
        JLabel avgPowerLabel = new JLabel("Average Estimated Power:");
        avgPowerField = new JTextField();
        JLabel avgHeartRateLabel = new JLabel("Average Estimated Heart Rate:");
        avgHeartRateField = new JTextField();
        JLabel avgCadenceLabel = new JLabel("Average Cadence:");
        avgCadenceField = new JTextField();
        JLabel intervalZonesLabel = new JLabel("Interval Zones:");
        intervalZonesField = new JTextField();
        JLabel intervalWorkoutLabel = new JLabel("Interval Workout:");
        intervalWorkoutField = new JTextField();
        JLabel heartRateIntervalsLabel = new JLabel("Heart Rate Intervals:");
        heartRateIntervalsField = new JTextField();
        JLabel cadenceIntervalWorkoutLabel = new JLabel("Cadence Interval Workout:");
        cadenceIntervalWorkoutField = new JTextField();

        // add expected weather
        // update interval system
        // add workout notes

        // Add JComboBox for key objective selection
        JLabel objectiveLabel = new JLabel("Key Objective:");
        String[] objectives = { "Base", "Tempo", "Other" };
        objectiveComboBox = new JComboBox<>(objectives);

        inputPanel.add(dateLabel);
        inputPanel.add(dateField);
        inputPanel.add(durationLabel);
        inputPanel.add(durationField);
        inputPanel.add(distanceLabel);
        inputPanel.add(distanceField);
        inputPanel.add(avgPowerLabel);
        inputPanel.add(avgPowerField);
        inputPanel.add(avgHeartRateLabel);
        inputPanel.add(avgHeartRateField);
        inputPanel.add(avgCadenceLabel);
        inputPanel.add(avgCadenceField);
        inputPanel.add(intervalZonesLabel);
        inputPanel.add(intervalZonesField);
        inputPanel.add(intervalWorkoutLabel);
        inputPanel.add(intervalWorkoutField);
        inputPanel.add(heartRateIntervalsLabel);
        inputPanel.add(heartRateIntervalsField);
        inputPanel.add(cadenceIntervalWorkoutLabel);
        inputPanel.add(cadenceIntervalWorkoutField);

        inputPanel.add(objectiveLabel);
        inputPanel.add(objectiveComboBox); // Add JComboBox to inputPanel
    }

    private void createTrainingRide() {
        try {
            int duration = Integer.parseInt(durationField.getText());
            int avgPower = Integer.parseInt(avgPowerField.getText());
            int avgHeartRate = Integer.parseInt(avgHeartRateField.getText());
            int avgCadence = Integer.parseInt(avgCadenceField.getText());

            String intervalZones = intervalZonesField.getText();
            String intervalWorkout = intervalWorkoutField.getText();
            String heartRateIntervals = heartRateIntervalsField.getText();
            String cadenceIntervalWorkout = cadenceIntervalWorkoutField.getText();
            String distance = distanceField.getText();
            String date = dateField.getText();

            // Get the selected item from the JComboBox
            String selectedObjective = (String) objectiveComboBox.getSelectedItem();

            StringBuilder planDetails = new StringBuilder();
            planDetails.append("Training Plan Details:\n");
            planDetails.append("Date: ").append(date).append("\n");
            planDetails.append("Duration (minutes): ").append(duration).append("\n");
            planDetails.append("Distance (if desired): ").append(distance).append("\n");
            planDetails.append("Average Estimated Power: ").append(avgPower + "W").append("\n");
            planDetails.append("Average Estimated Heart Rate: ").append(avgHeartRate + "BPM").append("\n");
            planDetails.append("Average Cadence: ").append(avgCadence + "RPM").append("\n");
            planDetails.append("Interval Zones: ").append(intervalZones).append("\n");
            planDetails.append("Interval Workout: ").append(intervalWorkout).append("\n");
            planDetails.append("Heart Rate Intervals: ").append(heartRateIntervals).append("\n");
            planDetails.append("Cadence Interval Workout: ").append(cadenceIntervalWorkout).append("\n");

            // Include the selected objective in the planDetails
            planDetails.append("Selected Objective: ").append(selectedObjective).append("\n");

            parentGUI.getOutputTextArea().append(planDetails.toString());
            JOptionPane.showMessageDialog(this, "Training plan created successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for all fields.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeRunningInputPanel(JPanel inputPanel) {

        // dateField = new JTextField();
        // durationField = new JTextField();
        // distanceField = new JTextField();
        // avgPaceField = new JTextField();
        // avgHeartRateField = new JTextField();
        // intervalZonesField = new JTextField();
        // intervalWorkoutField = new JTextField();
        // heartRateIntervalsField = new JTextField();

        // inputPanel.add(new JLabel("Date: "));
        // inputPanel.add(dateField);
        // inputPanel.add(new JLabel("Duration (minutes):"));
        // inputPanel.add(durationField);
        // inputPanel.add(new JLabel("Distance (if desired; enter units):"));
        // inputPanel.add(distanceField);
        // inputPanel.add(new JLabel("Average Estimated Pace:"));
        // inputPanel.add(avgPaceField);
        // inputPanel.add(new JLabel("Average Estimated Heart Rate:"));
        // inputPanel.add(avgHeartRateField);
        // inputPanel.add(new JLabel("Interval Zones:"));
        // inputPanel.add(intervalZonesField);
        // inputPanel.add(new JLabel("Interval Workout:"));
        // inputPanel.add(intervalWorkoutField);
        // inputPanel.add(new JLabel("Heart Rate Intervals:"));
        // inputPanel.add(heartRateIntervalsField);
    }

    private void createTrainingRun() {
        try {
            int duration = Integer.parseInt(durationField.getText());
            int avgHeartRate = Integer.parseInt(avgHeartRateField.getText());

            String intervalZones = intervalZonesField.getText();
            String intervalWorkout = intervalWorkoutField.getText();
            String heartRateIntervals = heartRateIntervalsField.getText();
            String distance = distanceField.getText();
            String avgPace = avgPaceField.getText();
            String date = dateField.getText();

            StringBuilder planDetails = new StringBuilder();
            planDetails.append("Training Plan Details:\n");
            planDetails.append("Date: ").append(date).append("\n");
            planDetails.append("Duration (minutes): ").append(duration).append("\n");
            planDetails.append("Distance (if desired): ").append(distance).append("\n");
            planDetails.append("Average Estimated Heart Rate: ").append(avgHeartRate + "BPM").append("\n");
            planDetails.append("Average Estimated Pace: ").append(avgPace).append("\n");
            planDetails.append("Interval Zones: ").append(intervalZones).append("\n");
            planDetails.append("Interval Workout: ").append(intervalWorkout).append("\n");
            planDetails.append("Heart Rate Intervals: ").append(heartRateIntervals).append("\n");

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

class NutritionTrackerDialog extends JDialog {
    private JTextField caloriesField, weightField, heightField, fatField,
            carbField, sugarField, sodiumField, ironField, dateField;

    // private JButton createButton;
    // private JComboBox<String> objectiveComboBox;

    private GUI parentGUI;

    public NutritionTrackerDialog(GUI parentGUI) {
        super(parentGUI, "Track Nutrition", true);
        setSize(450, 500);
        setLocationRelativeTo(parentGUI);
        setLayout(new BorderLayout());

        this.parentGUI = parentGUI;

        JPanel inputPanel = new JPanel(new GridLayout(13, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        String[] options = { "Cycling", "Running" };
        int choice = JOptionPane.showOptionDialog(null, "Choose either cycling or running?",
                "Choose Objective",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.CLOSED_OPTION) {
            dispose();
            return; // User closed the dialog
        }

        if (options[choice].equals("Cycling")) {
            initializeNutritionCyclingInputPanel(inputPanel);
            dispose(); // Close the dialog
        } else if (options[choice].equals("Running")) {
            JOptionPane.showMessageDialog("Running nutrition tracking is not yet available", "Error");
            dispose();
        }

        createButton = new JButton("Create");
        createButton.addActionListener(e -> createTrainingRide());

        JScrollPane scrollPane = new JScrollPane(inputPanel);

        add(scrollPane, BorderLayout.CENTER);
        add(createButton, BorderLayout.SOUTH);
    }

    private void initializeNutritionCyclingInputPanel(JPanel inputPanel) {
        inputPanel.removeAll();
        inputPanel.setLayout(new GridLayout(14, 2, 10, 10));

        JLabel dateLabel = new JLabel("Date: ");
        dateField = new JTextField();
        JLabel heightLabel = new JLabel("Height: ");
        heightField = new JTextField();
        JLabel weightLabel = new JLabel("Weight: ");
        weightField = new JTextField();
        JLabel caloriesLabel = new JLabel("Calories: ");
        caloriesField = new JTextField();
        JLabel fatLabel = new JLabel("Fat: ");
        fatField = new JTextField();
        JLabel carbLabel = new JLabel("Carbs: ");
        carbField = new JTextField();
        JLabel sugarLabel = new JLabel("Sugar: ");
        sugarField = new JTextField();
        JLabel sodiumLabel = new JLabel("Sodium: ");
        sodiumField = new JTextField();
        JLabel ironLabel = new JLabel("Iron: ");
        ironField = new JTextField();

        inputPanel.add(dateLabel);
        inputPanel.add(dateField);
        inputPanel.add(heightLabel);
        inputPanel.add(heightField);
        inputPanel.add(weightLabel);
        inputPanel.add(weightField);
        inputPanel.add(caloriesLabel);
        inputPanel.add(caloriesField);
        inputPanel.add(fatLabel);
        inputPanel.add(fatField);
        inputPanel.add(carbLabel);
        inputPanel.add(carbField);
        inputPanel.add(sugarLabel);
        inputPanel.add(sugarField);
        inputPanel.add(sodiumLabel);
        inputPanel.add(sodiumField);
        inputPanel.add(ironLabel);
        inputPanel.add(ironField);
    }

    private void CreateNutritionCycling() {
        try {
            int height = Integer.parseInt(heightField.getText());
            int weight = Integer.parseInt(weightField.getText());
            int calories = Integer.parseInt(caloriesField.getText());
            int fat = Integer.parseInt(fatField.getText());
            int carb = Integer.parseInt(carbField.getText());
            int sugar = Integer.parseInt(sugarField.getText());
            int sodium = Integer.parseInt(sodiumField.getText());
            int iron = Integer.parseInt(ironField.getText());

            String date = dateField.getText();

            StringBuilder planDetials = new StringBuilder();
            planDetials.append("Cycling nutrition tracker:\n");
            planDetials.append("Date").append(date).append("\n");
            planDetails.append("Height").append(height).append("\n");
            planDetails.append("Weight").append(weight).append("\n");
            planDetails.append("Calories").append(calories).append("\n");
            planDetails.append("Fat").append(fat).append("\n");
            planDetails.append("Carbs").append(carb).append("\n");
            planDetails.append("Sugar").append(sugar).append("\n");
            planDetails.append("Sodium").append(sodium).append("\n");
            planDetails.append("Iron").append(iron).append("\n");

            parentGUI.getOutputTextArea().append(planDetails.toString());
            JOptionPane.showMessagedialog(this, "Nutrition tracked successfully", "Success",
                    JOptionPane.ERROR_MESSAGE);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog("Please enter valid data", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}