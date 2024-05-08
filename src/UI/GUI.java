package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import UI.methods.CalculateLTHRDialog;
import UI.methods.CalculateTSSDialog;
import UI.methods.CreateNutritionPlanDialog;
import UI.methods.CreateTrainingPlanDialog;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUI extends JFrame {
    private JButton calculateFTPButton, calculateTSSButton, calculateLTHRButton, createTrainingPlanButton,
            saveDataButton, loadDataButton, clearButton, createNutritionPlanButton; // Added createNutritionPlanButton
    private JTextArea outputTextArea;

    public GUI() {
        setTitle("Propel");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeComponents();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                promptToSaveBeforeExit();
            }
        });

        setVisible(true);
    }

    private void initializeComponents() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 5, 20, 20));
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        calculateFTPButton = new JButton("Calculate FTP");
        calculateTSSButton = new JButton("Calculate TSS");
        calculateLTHRButton = new JButton("Calculate LTHR");
        createTrainingPlanButton = new JButton("Create Training Plan");
        createNutritionPlanButton = new JButton("Create Nutrition Plan"); // Added createNutritionPlanButton
        saveDataButton = new JButton("Save Data");
        loadDataButton = new JButton("Load Data");
        clearButton = new JButton("Clear Text Field");

        buttonPanel.add(calculateFTPButton);
        buttonPanel.add(calculateTSSButton);
        buttonPanel.add(calculateLTHRButton);
        buttonPanel.add(createTrainingPlanButton);
        buttonPanel.add(createNutritionPlanButton); // Added createNutritionPlanButton
        buttonPanel.add(saveDataButton);
        buttonPanel.add(loadDataButton);
        buttonPanel.add(clearButton);

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font("Montserrat", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        calculateFTPButton.addActionListener(e -> calculateFTP());
        calculateTSSButton.addActionListener(e -> calculateTSS());
        calculateLTHRButton.addActionListener(e -> calculateLTHR());
        createTrainingPlanButton.addActionListener(e -> createTrainingPlan());
        createNutritionPlanButton.addActionListener(e -> createNutritionPlan()); // Added action listener for
                                                                                 // createNutritionPlanButton
        saveDataButton.addActionListener(e -> saveData());
        loadDataButton.addActionListener(e -> loadData());
        clearButton.addActionListener(e -> clearTextArea());
    }

    private void calculateFTP() {
        // Implement FTP calculation logic here
        // For demonstration, let's just display a message
        JOptionPane.showMessageDialog(this, "FTP calculation will be implemented here.");
    }

    private void calculateTSS() {
        CalculateTSSDialog dialog = new CalculateTSSDialog(this);
        dialog.setVisible(true);
    }

    private void calculateLTHR() {
        CalculateLTHRDialog dialog = new CalculateLTHRDialog(this);
        dialog.setVisible(true);
    }

    private void createTrainingPlan() {
        CreateTrainingPlanDialog dialog = new CreateTrainingPlanDialog(this);
        dialog.setVisible(true);
    }

    private void createNutritionPlan() {
        CreateNutritionPlanDialog dialog = new CreateNutritionPlanDialog(this);
        dialog.setVisible(true);
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

    private void clearTextArea() {
        outputTextArea.setText("");
    }

    private void promptToSaveBeforeExit() {
        int option = JOptionPane.showConfirmDialog(this,
                "Do you want to save your data before exiting?", "Save Data",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            saveData();
            dispose();
        } else if (option == JOptionPane.NO_OPTION) {
            dispose();
        }
    }

    public JTextArea getOutputTextArea() {
        return outputTextArea;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
