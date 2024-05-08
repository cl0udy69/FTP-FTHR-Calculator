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

package UI.dialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class CreateNutritionPlanDialog extends JDialog {
    private JTextField dateField, weightField, heightField, calorieField, carbField,
            sugarField, fatField, ironField, 
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

        String [] options  = {"Weight Loss", "Muscle Gain", "Improved Performance", "Cycling", "Running"};
        int choice = JOptionPane.showOptionDialog(null, "Options",
                "Choose an option", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.CLOSED_OPTION) {
            dispost();
            return;
        }
        if (options[choice].equals("Weight Loss")) {
            JOptionPane.showMessageDialog(this, "Weight Loss is not yet supported. Please select a different option.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        } else if (options[choice].equals("Muscle Gain")) {
            JOptionPane.showMessageDialog("Muscle Gain is not yet supported. Please select a different option", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        } else if (options[choice].equals("Improved Performance")) {
            JOptionPane.showMessageDialog(this, "Improved performance is not yet supported. Please select a different option.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        } else if (options[choice].equals("Cycling")) {
            JOptionPane.showMessageDialog(this, "Cycling is not yet supported. Please select a different option.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        } else if (options[choice].equals("Running")) {
            JOptionPane.showMessageDialog(this, "Running is not yet supported. Please select a different option.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
    }
}   
