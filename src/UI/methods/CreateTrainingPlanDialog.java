package UI.methods;

import UI.GUI; // Import necessary packages
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CreateTrainingPlanDialog extends JDialog { // Define a class named CreateTrainingPlanDialog which extends JDialog
    // Declare private variables for components
    private JTextField durationField, distanceField, avgPowerField, avgHeartRateField, avgCadenceField,
            avgPaceField, dateField; // Declare text fields
    private JButton createButton; // Declare button
    private JComboBox<String> objectiveComboBox; // Declare a combo box for selecting the key objective

    private GUI parentGUI; // Reference to the parent GUI

    // Constructor
    public CreateTrainingPlanDialog(GUI parentGUI) {
        super(parentGUI, "Create Training Plan", true); // Call parent constructor with title and modality
        setSize(450, 500); // Set dialog size
        setLocationRelativeTo(parentGUI); // Set dialog location relative to parent GUI
        setLayout(new BorderLayout()); // Set layout for the dialog

        this.parentGUI = parentGUI; // Assign parent GUI

        JPanel inputPanel = new JPanel(new GridLayout(13, 2, 10, 10)); // Create input panel
        inputPanel.setBorder(new EmptyBorder(30, 30, 30, 30)); // Add empty border for spacing

        // Display option dialog to choose activity (cycling or running)
        String[] options = { "Cycling", "Running" };
        int choice = JOptionPane.showOptionDialog(null, "Choose either cycling or running?", "Choose Objective",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        if (choice == JOptionPane.CLOSED_OPTION) {
            dispose();
            return; // User closed the dialog
        }

        // Check selected activity and initialize input panel accordingly
        if (options[choice].equals("Cycling")) {
            initializeCyclingInputPanel(inputPanel);
        } else if (options[choice].equals("Running")) {
            initializeRunningInputPanel(inputPanel);
        }

        createButton = new JButton("Create"); // Create create button
        createButton.addActionListener(e -> createTrainingRide()); // Add action listener to create button
        JScrollPane scrollPane = new JScrollPane(inputPanel); // Create scroll pane for input panel

        // Add components to the dialog
        add(scrollPane, BorderLayout.CENTER);
        add(createButton, BorderLayout.SOUTH);
    }

    // Method to initialize input panel for cycling
    private void initializeCyclingInputPanel(JPanel inputPanel) {
        inputPanel.removeAll(); // Clear previous components
        inputPanel.setLayout(new GridLayout(16, 2, 10, 10)); // Set layout for input panel

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

        // Add sliders for intensity levels
        JLabel enduranceIntensityLabel = new JLabel("Endurance Intensity:");
        JSlider enduranceIntensitySlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5); // Default value: 5
        enduranceIntensitySlider.setMajorTickSpacing(1);
        enduranceIntensitySlider.setPaintTicks(true);
        enduranceIntensitySlider.setPaintLabels(true);

        JLabel tempoIntensityLabel = new JLabel("Tempo Intensity:");
        JSlider tempoIntensitySlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5); // Default value: 5
        tempoIntensitySlider.setMajorTickSpacing(1);
        tempoIntensitySlider.setPaintTicks(true);
        tempoIntensitySlider.setPaintLabels(true);

        JLabel vo2MaxIntensityLabel = new JLabel("VO2Max Intensity: ");
        JSlider vo2MaxIntensitySlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5); // Default value: 5
        vo2MaxIntensitySlider.setMajorTickSpacing(1);
        vo2MaxIntensitySlider.setPaintTicks(true);
        vo2MaxIntensitySlider.setPaintLabels(true);

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

        inputPanel.add(enduranceIntensityLabel);
        inputPanel.add(enduranceIntensitySlider);
        inputPanel.add(tempoIntensityLabel);
        inputPanel.add(tempoIntensitySlider);
        inputPanel.add(vo2MaxIntensityLabel);
        inputPanel.add(vo2MaxIntensitySlider);

        inputPanel.add(objectiveLabel);
        inputPanel.add(objectiveComboBox); // Add JComboBox to inputPanel
    }

    // Method to create a training ride plan
    private void createTrainingRide() {
        try {
            int duration = Integer.parseInt(durationField.getText());
            int avgPower = Integer.parseInt(avgPowerField.getText());
            int avgHeartRate = Integer.parseInt(avgHeartRateField.getText());
            int avgCadence = Integer.parseInt(avgCadenceField.getText());

            String distance = distanceField.getText();
            String date = dateField.getText();

            // Get the selected item from the JComboBox
            String selectedObjective = (String) objectiveComboBox.getSelectedItem();

            StringBuilder planDetails = new StringBuilder();
            planDetails.append("Training Plan Details:\n");
            planDetails.append("Date: ").append(date).append("\n");
            planDetails.append("Duration (minutes): ").append(duration).append("\n");
            planDetails.append("Distance (if desired): ").append(distance).append("\n");
            planDetails.append("Average Estimated Power: ").append(avgPower).append("\n");
            planDetails.append("Average Estimated Heart Rate: ").append(avgHeartRate).append("\n");
            planDetails.append("Average Cadence: ").append(avgCadence).append("\n");
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

    // Method to initialize input panel for running
    private void initializeRunningInputPanel(JPanel inputPanel) {
        inputPanel.removeAll(); // Clear existing components
        inputPanel.setLayout(new GridLayout(16, 2, 10, 10)); // Set layout for input panel

        // Add running-specific input fields
        dateField = new JTextField();
        durationField = new JTextField();
        distanceField = new JTextField();
        avgPaceField = new JTextField();
        avgHeartRateField = new JTextField();

        inputPanel.add(new JLabel("Date: "));
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Duration (minutes):"));
        inputPanel.add(durationField);
        inputPanel.add(new JLabel("Distance (if desired; enter units):"));
        inputPanel.add(distanceField);
        inputPanel.add(new JLabel("Average Estimated Pace:"));
        inputPanel.add(avgPaceField);
        inputPanel.add(new JLabel("Average Estimated Heart Rate:"));
        inputPanel.add(avgHeartRateField);
    }

    // Method to create a training run plan
    private void createTrainingRun() {
        try {
            int duration = Integer.parseInt(durationField.getText());
            int avgHeartRate = Integer.parseInt(avgHeartRateField.getText());

            String distance = distanceField.getText();
            String avgPace = avgPaceField.getText();
            String date = dateField.getText();

            StringBuilder planDetails = new StringBuilder();
            planDetails.append("Training Plan Details:\n");
            planDetails.append("Date: ").append(date).append("\n");
            planDetails.append("Duration (minutes): ").append(duration).append("\n");
            planDetails.append("Distance (if desired): ").append(distance).append("\n");
            planDetails.append("Average Estimated Heart Rate: ").append(avgHeartRate).append("\n");
            planDetails.append("Average Estimated Pace: ").append(avgPace).append("\n");

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
