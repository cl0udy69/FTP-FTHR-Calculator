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

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import UI.GUI;

import java.awt.*;
import java.awt.event.*;

public class CreateNutritionPlanDialog extends JDialog {
    private JTextField dateField, weightField, heightField, calorieField, carbField,
            sugarField, fatField, ironField;
    private JButton createButton;
    private JComboBox<String> objectiveComboBox;

    private GUI parentGUI;

    public CreateNutritionPlanDialog(GUI parentGUI) {
        super(parentGUI, "Create Nutrition Plan", true);
        setSize(450, 500);
        setLocationRelativeTo(parentGUI);
        setLayout(new BorderLayout());

        this.parentGUI = parentGUI;

        JPanel inputPanel = new JPanel(new GridLayout(13, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

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

        createButton = new JButton("Create");
        createButton.addActionListener(e -> createNutritionCycling());

        add(inputPanel, BorderLayout.CENTER);
        add(createButton, BorderLayout.SOUTH);
    }

    private void createNutritionCycling() {
        try {
            int calorie = Integer.parseInt(calorieField.getText());
            int carb = Integer.parseInt(carbField.getText());
            int sugar = Integer.parseInt(sugarField.getText());
            int fat = Integer.parseInt(fatField.getText());
            int iron = Integer.parseInt(ironField.getText());

            String date = dateField.getText();
            String weight = weightField.getText();
            String height = heightField.getText();

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
