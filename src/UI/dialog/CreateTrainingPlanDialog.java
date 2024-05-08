package UI.dialog;

import UI.GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class CreateTrainingPlanDialog extends JDialog {
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