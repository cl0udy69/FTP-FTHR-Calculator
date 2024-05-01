package methods;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CreateTrainingPlanDialog extends JDialog {
    private JTextField durationField, avgPowerField, avgHeartRateField, avgCadenceField,
            intervalZonesField, intervalWorkoutField, heartRateIntervalsField, cadenceIntervalWorkoutField;
    private JButton createButton, cancelButton;
    private JTextArea outputTextArea;

    private List<PlanCreationListener> planCreationListeners = new ArrayList<>();

    public CreateTrainingPlanDialog(JFrame parent) {
        super(parent, "Create Training Plan", true);
        setSize(800, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

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
        inputPanel.add(new JLabel("Average Estimated Cadence:"));
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
        cancelButton = new JButton("Cancel");
        createButton.addActionListener(e -> createTrainingPlan());
        cancelButton.addActionListener(e -> cancelTrainingPlan());

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(createButton);
        buttonPanel.add(cancelButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void createTrainingPlan() {
        try {
            int duration = Integer.parseInt(durationField.getText());
            int avgPower = Integer.parseInt(avgPowerField.getText());
            int avgHeartRate = Integer.parseInt(avgHeartRateField.getText());
            int avgCadence = Integer.parseInt(avgCadenceField.getText());

            if (duration <= 0 || avgPower <= 0 || avgHeartRate <= 0 || avgCadence <= 0)
                throw new NumberFormatException();

            String intervalZones = intervalZonesField.getText();
            String intervalWorkout = intervalWorkoutField.getText();
            String heartRateIntervals = heartRateIntervalsField.getText();
            String cadenceIntervalWorkout = cadenceIntervalWorkoutField.getText();

            TrainingPlan plan = new TrainingPlan(duration, avgPower, avgHeartRate, avgCadence,
                    intervalZones, intervalWorkout, heartRateIntervals, cadenceIntervalWorkout);

            // Notify listeners that the plan is created
            firePlanCreatedEvent(plan);

            dispose(); // Close the dialog after creating the plan
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid positive numbers.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelTrainingPlan() {
        // Notify listeners that plan creation is canceled
        firePlanCreationCanceledEvent();
        dispose(); // Close the dialog without creating the plan
    }

    public void addPlanCreationListener(PlanCreationListener listener) {
        planCreationListeners.add(listener);
    }

    private void firePlanCreatedEvent(TrainingPlan plan) {
        for (PlanCreationListener listener : planCreationListeners) {
            listener.planCreated(plan);
        }
    }

    private void firePlanCreationCanceledEvent() {
        for (PlanCreationListener listener : planCreationListeners) {
            listener.planCreationCanceled();
        }
    }
}
