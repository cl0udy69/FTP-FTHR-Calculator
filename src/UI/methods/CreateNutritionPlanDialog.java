// private JComboBox<String> goalComboBox;
// private String[] goals = { "Weight Loss", "Muscle Gain", "Improved Performance" };

// // Activity level
// private JComboBox<String> activityLevelComboBox;
// private String[] activityLevels = { "Sedentary", "Lightly Active", "Moderately Active", "Very Active" };

// // Dietary restrictions
// private JList<String> dietaryRestrictionsList;
// private String[] dietaryRestrictions = { "Dairy", "Eggs", "Gluten", "Nuts", "Soy" };

// // Calorie target
// private JTextField calorieTargetField;

// // Macronutrient targets
// private JTextField proteinTargetField, carbTargetField, fatTargetField;

// // Meal frequency
// private JComboBox<String> mealFrequencyComboBox;
// private String[] mealFrequencies = { "3 Meals per Day", "4 Meals per Day", "5 Meals per Day" };

// // Sample meal plan
// private JTextArea sampleMealPlanTextArea;

package UI.methods;

import UI.GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CreateNutritionPlanDialog extends JDialog {
    // Declare private variables for components
    private JTextField dateField, weightField, heightField, calorieField, carbField,
            sugarField, fatField, ironField, caloriesPerHourField, carbsPerHourField,
            sugarPerHourField, fatPerHourField, ironPerHourField, currentWeightField,
            goalWeightField, caloriesBurnedField, carbsBurnedField, dietaryRestrictionsField,
            proteinTargetField, carbTargetField, fatTargetField, ironTargetField, sugarTargetField,
            excerciseField, excerciseDurationField;
    private JButton createButton;
    private JComboBox<String> objectiveComboBox;

    private GUI parentGUI;

    // Constructor
    public CreateNutritionPlanDialog(GUI parentGUI) {
        super(parentGUI, "Create Nutrition Plan", true); // Call parent constructor with title and modality
        setSize(450, 500); // Set dialog size
        setLocationRelativeTo(parentGUI); // Set dialog location relative to parent GUI
        setLayout(new BorderLayout()); // Set layout for the dialog

        this.parentGUI = parentGUI; // Assign parent GUI

        JPanel inputPanel = new JPanel(new GridLayout(13, 2, 10, 10)); // Create input panel
        inputPanel.setBorder(new EmptyBorder(30, 30, 30, 30)); // Add empty border for spacing

        // Display option dialog to choose objective
        String[] options = { "Gain Weight", "Lose Weight", "Cycling", "Running" };
        int choice = JOptionPane.showOptionDialog(null, "Select One", "Choose Objective",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice == JOptionPane.CLOSED_OPTION) {
            dispose();
            return; // User closed the dialog
        }

        // Check selected objective and initialize input panel accordingly
        if (options[choice].equals("Cycling")) {
            // Display another option dialog to choose cycling type
            String[] cyclingOptions = { "General", "Training Ride" };
            
            int cyclingChoice = JOptionPane.showOptionDialog(null, "Select One", "Choose Cycling Type",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, cyclingOptions,
                    cyclingOptions[0]);

            if (cyclingChoice == JOptionPane.CLOSED_OPTION) {
                dispose();
                return; // User closed the dialog
            } else if (cyclingOptions[cyclingChoice].equals("General")) {
                initializeGeneralCyclingNutritionInputPanel(inputPanel);
            } else if (cyclingOptions[cyclingChoice].equals("Training Ride")) {
                initializeTrainingRideCyclingNutritionInputPanel(inputPanel);
            }
        
        } else if (cyclingOptions[cyclingChoice].equals("Gain Weight")){
            initializeGainWeightInputPanel(inputPanel);
        
        } else if (cyclingOptions[cyclingChoice].equals("Lose Weight")) {
            initializeGainWeightInputPanell(inputPanel);
        
        } if (options[choice.equals("Running")]) { 
            String[] runningOptions = {"General", "Training Run"};
            
            int runningChoice = JOptionPane.showOptionDialog(null, "Select One", "Choose Cycling Type",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, runningOptions,
                    cyclingOptions[0]);

            if (runningChoice == JOptionPane.CLOSED) {
                dispose();
                return;
            } else if (runningOptions[runningChoice].equals("General")) {
                initializeGeneralCyclingNutritionInputPanel(inputPanel);
            } else if (runningOptions[runningChoice].equals("Training Run")) {
                initializeGeneralCyclingNutritionInputPanel(inputPanel);
            }

        // Add input panel to the dialog
        add(inputPanel, BorderLayout.CENTER);
        setVisible(true); // Make the dialog visible after adding components
    }

    // Method to initialize input panel for general cycling nutrition plan
    private void initializeGeneralCyclingNutritionInputPanel(JPanel inputPanel) {
        inputPanel.removeAll(); // Clear previous components
        inputPanel.setLayout(new GridLayout(16, 2, 10, 10)); // Set layout for input panel

        // Create labels and text fields for input
        JLabel dateLabel = new JLabel("Date: ");
        dateField = new JTextField();
        JLabel weightLabel = new JLabel("Weight: ");
        weightField = new JTextField();
        JLabel heightLabel = new JLabel("Height: ");
        heightField = new JTextField();
        JLabel calorieLabel = new JLabel("Calories: ");
        calorieField = new JTextField();
        JLabel carbLabel = new JLabel("Carbs: ");
        carbField = new JTextField();
        JLabel sugarLabel = new JLabel("Sugar: ");
        sugarField = new JTextField();
        JLabel fatLabel = new JLabel("Fat: ");
        fatField = new JTextField();
        JLabel ironLabel = new JLabel("Iron: ");
        ironField = new JTextField();

        // Add labels and text fields to input panel
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);
        inputPanel.add(weightLabel);
        inputPanel.add(weightField);
        inputPanel.add(heightLabel);
        inputPanel.add(heightField);
        inputPanel.add(calorieLabel);
        inputPanel.add(calorieField);
        inputPanel.add(carbLabel);
        inputPanel.add(carbField);
        inputPanel.add(sugarLabel);
        inputPanel.add(sugarField);
        inputPanel.add(fatLabel);
        inputPanel.add(fatField);
        inputPanel.add(ironLabel);
        inputPanel.add(ironField);

        // Create and configure Create button
        createButton = new JButton("Create");
        createButton.addActionListener(e -> createNutritionPlan());

        // Add Create button to input panel
        add(inputPanel, BorderLayout.CENTER);
        add(createButton, BorderLayout.SOUTH);
    }

    // Method to create nutrition plan
    private void createNutritionPlan() {
        try {
            // Get inputs and convert to appropriate data types
            int calorie = Integer.parseInt(calorieField.getText());
            int carb = Integer.parseInt(carbField.getText());
            int sugar = Integer.parseInt(sugarField.getText());
            int fat = Integer.parseInt(fatField.getText());
            int iron = Integer.parseInt(ironField.getText());

            // Get text inputs
            String date = dateField.getText();
            String weight = weightField.getText();
            String height = heightField.getText();

            // Build plan details string
            StringBuilder planDetails = new StringBuilder();
            planDetails.append("Nutrition Plan: \n");
            planDetails.append("Date: ").append(date).append("\n");
            planDetails.append("Weight: ").append(weight).append("\n");
            planDetails.append("Height: ").append(height).append("\n");
            planDetails.append("Calories:").append(calorie).append("\n");
            planDetails.append("Carbs: ").append(carb).append("\n");
            planDetails.append("Sugar: ").append(sugar).append("\n");
            planDetails.append("Fat: ").append(fat).append("\n");
            planDetails.append("Iron: ").append(iron).append("\n");

            // Display plan details in parent GUI's output text area
            parentGUI.getOutputTextArea().append(planDetails.toString());
            // Show success message
            JOptionPane.showMessageDialog(parentGUI, "Nutrition plan created successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Dispose the dialog after creating the plan
        } catch (NumberFormatException e) {
            // Show error message for invalid input
            JOptionPane.showMessageDialog(parentGUI, "Please enter valid numbers for all fields.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

     private void initializeTrainingRideCyclingNutritionInputPanel(JPanel inputPanel) {
        inputPanel.removeAll();
        inputPanel.setLayout(new GridLayout(16, 2, 10, 10));

        JLabel dateLabel = new JLabel("Date: ");
        dateField = new JTextField();
        JLabel weightLabel = new JLabel("Weight: ");
        weightField = new JTextField();
        JLabel heightLabel = new JLabel("Height: ");
        heightField = new JTextField();
        JLabel caloriesPerHourLabel = new JLabel("Calories per Hour: ");
        caloriesPerHourField = new JTextField();
        JLabel carbsPerHourLabel = new JLabel("Carbs per Hour: ");
        carbsPerHourField = new JTextField();
        JLabel sugarPerHourLabel = new JLabel("Sugar per Hour: ");
        sugarPerHourField = new JTextField();
        JLabel fatPerHourLabel = new JLabel("Fat per Hour: ");
        fatPerHourField = new JTextField();
        JLabel ironPerHourLabel = new JLabel("Iron per Hour: ");
        ironPerHourField = new JTextField();

        inputPanel.add(dateLabel);
        inputPanel.add(dateField);
        inputPanel.add(weightLabel);
        inputPanel.add(weightField);
        inputPanel.add(heightLabel);
        inputPanel.add(heightField);
        inputPanel.add(caloriesPerHourLabel);
        inputPanel.add(caloriesPerHourField);
        inputPanel.add(carbsPerHourLabel);
        inputPanel.add(carbsPerHourField);
        inputPanel.add(sugarPerHourLabel);
        inputPanel.add(sugarPerHourField);
        inputPanel.add(fatPerHourLabel);
        inputPanel.add(fatPerHourField);
        inputPanel.add(ironPerHourLabel);
        inputPanel.add(ironPerHourField);

        createButton = new JButton("Create");
        createButton.addActionListener(e -> createNutritionPlan());

        add(inputPanel, BorderLayout.CENTER);
        add(createButton, BorderLayout.SOUTH);
    }

    private void createTrainingRideNutritionPlan() {
        try {
            int caloriesPerHour = Integer.parseInt(caloriesPerHourField.getText());
            int carbsPerHour = Integer.parseInt(carbsPerHourField.getText());
            int sugarPerHour = Integer.parseInt(sugarPerHourField.getText());
            int fatPerHour = Integer.parseInt(fatPerHourField.getText());
            int ironPerHour = Integer.parseInt(ironPerHourField.getText());

            String date = dateField.getText();
            String weight = weightField.getText();
            String height = heightField.getText();

            StringBuilder planDetails = new StringBuilder();
            planDetails.append("Nutrition Plan: \n");
            planDetails.append("Date: ").append(date).append("\n");
            planDetails.append("Weight: ").append(weight).append("\n");
            planDetails.append("Height: ").append(height).append("\n");
            planDetails.append("Calories per Hour:").append(caloriesPerHour).append("\n");
            planDetails.append("Carbs per Hour: ").append(carbsPerHour).append("\n");
            planDetails.append("Sugar per Hour: ").append(sugarPerHour).append("\n");
            planDetails.append("Fat per Hour: ").append(fatPerHour).append("\n");
            planDetails.append("Iron per Hour: ").append(ironPerHour).append("\n");

            parentGUI.getOutputTextArea().append(planDetails.toString());
            JOptionPane.showMessageDialog(parentGUI, "Nutrition plan created successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Dispose the dialog after creating the plan
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parentGUI, "Please enter valid numbers for all fields.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void initializeGainWeightInputPanel(JPanel inputPanel) {
        inputPanel.removeAll();
        inputPanel.setLayout(new GridLayout(16, 2, 10, 10));

        JLabel dateLabel = new JLabel("Date: ");
        dateField = new JTextField();
        JLabel heightLabel = new JLabel("Height: ");
        heightField = new JTextField();
        JLabel currentWeightLabel = new JLabel("Current Weight: ");
        currentWeightField = new JTextField();
        JLabel goalWeightLabel = new JLabel("Goal Weight: ");
        goalWeightField = new JTextField();
        JLabel excerciseLabel = new JLabel("Excercise: ");
        excerciseField = new JTextField();
        JLabel excerciseDurationLabel = new JLabel("Excercise Duration: ");
        excerciseDurationField = new JTextField(); 
        JLabel caloriesBurnedlabel = new JLabel("Calories Burned: ");
        caloriesBurnedField = new JTextField();
        JLabel carbsBurnedLabel = new JLabel("Carbs Burned: ");
        carbsBurnedField = new JTextField();
        JLabel proteinTargetlabel = new JLabel("Target Protein Consumption: ");
        proteinTargetField = new JTextField();
        JLabel carbTargetLabel = new JLabel("Target Carb Consumption: ");
        carbTargetField = new JTextField();
        JLabel fatTargetLabel = new JLabel("Target Fat Consumption: ");
        fatTargetField = new JTextField();
        JLabel ironTargetLabel = new JLabel("Target Iron Consumption: ");
        ironTargetField = new JTextField();
        JLabel sugarTargetLabel = new JLabel("Target Sugar Consumption: ");
        sugarTargetField = new JTextField();

        inputPanel.add(dateLabel);
        inputPanel.add(dateField);
        inputPanel.add(currentWeightLabel);
        inputPanel.add(currentWeightField);
        inputPanel.add(goalWeightLabel);
        inputPanel.add(goalWeightField);
        inputPanel.add(excerciseLabel);
        inputPanel.add(excerciseField);
        inputPanel.add(excerciseDurationLabel);
        inputPanel.add(excerciseDurationField);
        inputPanel.add(heightLabel);
        inputPanel.add(heightField);
        inputPanel.add(caloriesBurnedlabel);
        inputPanel.add(caloriesBurnedField);
        inputPanel.add(carbsBurnedLabel);
        inputPanel.add(carbsBurnedField);
        inputPanel.add(proteinTargetlabel);
        inputPanel.add(proteinTargetField);
        inputPanel.add(carbTargetLabel);
        inputPanel.add(carbTargetField);
        inputPanel.add(fatTargetLabel);
        inputPanel.add(fatTargetField);
        inputPanel.add(ironTargetLabel);
        inputPanel.add(ironTargetField);
        inputPanel.add(sugarTargetLabel);
        inputPanel.add(sugarTargetField);

        createButton = new JButton("Create");
        createButton.addActionListener(e -> createNutritionPlan());

        add(inputPanel, BorderLayout.CENTER);
        add(createButton, BorderLayout.SOUTH);
    }

    private void createGainWeightNutritionPlan() {
        try{
            int currentWeight = Integer.parseInt(currentWeightField.getText());
            int goalWeight = Integer.parseInt(goalWeightField.getText());
            int excerciseDuration = Integer.parseInt(excerciseDurationField.getText());
            int caloriesBurned = Integer.parseInt(caloriesBurnedField.getText());
            int carbsBurned = Integer.parseInt(carbsBurnedField.getText());
            int proteinTarget = Integer.parseInt(proteinTargetField.getText());
            int carbTarget = Integer.parseInt(carbTargetField.getText());
            int fatTarget = Integer.parseInt(fatTargetField.getText());
            int ironTarget = Integer.parseInt(ironTargetField.getText());
            int sugarTarget = Integer.parseInt(sugarTargetField.getText());

            String date = dateField.getText();
            String height = heightField.getText();

            StringBuilder planDetails = new StringBuilder();
            planDetails.append("Nutrition Plan: \n");
            planDetails.append("Date: ").append(date).append("\n");
            planDetails.append("Height: ").append(height).append("\n");
            planDetails.append("Current Weight: ").append(currentWeight).append("\n");
            planDetails.append("Goal Weight: ").append(goalWeight).append("\n");
            planDetails.append("Excercise: ").append(excerciseField.getText()).append("\n");
            planDetails.append("Excercise Duration: ").append(excerciseDuration).append("\n");
            planDetails.append("Calories Burned: ").append(caloriesBurned).append("\n");
            planDetails.append("Carbs Burned: ").append(carbsBurned).append("g").append("\n");
            planDetails.append("Protein Target: ").append(proteinTarget).append("g").append("\n");
            planDetails.append("Carb Target: ").append(carbTarget).append("g").append("\n");
            planDetails.append("Fat Target: ").append(fatTarget).append("g").append("\n");
            planDetails.append("Iron Target: ").append(ironTarget).append("g").append("\n");
            planDetails.append("Sugar Target: ").append(sugarTarget).append("g").append("\n");

            parentGUI.getOutputTextArea().append(planDetails.toString());
            JOptionPane.showMessageDialog(parentGUI, "Nutrition plan created successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Dispose the dialog after creating the plan
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parentGUI, "Please enter valid numbers for all fields.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
