package UI;

import UI.methods.CalculateLTHRDialog;
import UI.methods.CalculateTSSDialog;
import UI.methods.CreateNutritionPlanDialog;
import UI.methods.CreateTrainingPlanDialog;
import UI.methods.FontSelector;
import UI.methods.calculateFTPDialog;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame { // Define a class named GUI which extends JFrame
    // Declare buttons and text area
    private JButton calculateFTPButton, calculateTSSButton, calculateLTHRButton, createTrainingPlanButton,
            saveDataButton, loadDataButton, clearButton, createNutritionPlanButton;
    private JTextArea outputTextArea;

    // Constructor
    public GUI() {
        setTitle("Propel"); // Set window title
        setSize(800, 500); // Set window size
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Set default close operation
        setLayout(new BorderLayout()); // Set layout for the frame

        initializeComponents(); // Initialize UI components

        // Add window listener to prompt for saving before exit
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                promptToSaveBeforeExit();
            }
        });

        setVisible(true); // Set window visibility
    }

    // Method to initialize UI components
    private void initializeComponents() {
        // Create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 25, 15));
        buttonPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        // Initialize buttons
        calculateFTPButton = new JButton("Calculate FTP");
        calculateTSSButton = new JButton("Calculate TSS");
        calculateLTHRButton = new JButton("Calculate LTHR");
        createTrainingPlanButton = new JButton("Create Training Plan");
        createNutritionPlanButton = new JButton("Create Nutrition Plan");
        saveDataButton = new JButton("Save Data");
        loadDataButton = new JButton("Load Data");
        clearButton = new JButton("Clear Text Field");

        // Add buttons to button panel
        buttonPanel.add(calculateFTPButton);
        buttonPanel.add(calculateTSSButton);
        buttonPanel.add(calculateLTHRButton);
        buttonPanel.add(createTrainingPlanButton);
        buttonPanel.add(createNutritionPlanButton);
        buttonPanel.add(saveDataButton);
        buttonPanel.add(loadDataButton);
        buttonPanel.add(clearButton);

        // Initialize text area
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(true);
        outputTextArea.setFont(new Font("Montserrat", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        // Add components to frame
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add action listeners to buttons
        calculateFTPButton.addActionListener(e -> calculateFTP());
        calculateTSSButton.addActionListener(e -> calculateTSS());
        calculateLTHRButton.addActionListener(e -> calculateLTHR());
        createTrainingPlanButton.addActionListener(e -> createTrainingPlan());
        createNutritionPlanButton.addActionListener(e -> createNutritionPlan());
        saveDataButton.addActionListener(e -> saveData());
        loadDataButton.addActionListener(e -> loadData());
        clearButton.addActionListener(e -> clearTextArea());
    }

    // Method to handle Calculate FTP button click
    private void calculateFTP() {
        calculateFTPDialog dialog = new calculateFTPDialog(this);
        dialog.setVisible(true);
    }

    // Method to handle Calculate TSS button click
    private void calculateTSS() {
        CalculateTSSDialog dialog = new CalculateTSSDialog(this);
        dialog.setVisible(true);
    }

    // Method to handle Calculate LTHR button click
    private void calculateLTHR() {
        CalculateLTHRDialog dialog = new CalculateLTHRDialog(this);
        dialog.setVisible(true);
    }

    // Method to handle Create Training Plan button click
    private void createTrainingPlan() {
        CreateTrainingPlanDialog dialog = new CreateTrainingPlanDialog(this);
        dialog.setVisible(true);
    }

    // Method to handle Create Nutrition Plan button click
    private void createNutritionPlan() {
        CreateNutritionPlanDialog dialog = new CreateNutritionPlanDialog(this);
        dialog.setVisible(true);
    }

    FontSelector fontSelector = new FontSelector(outputTextArea);


    // Method to handle Save Data button click
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

    // Method to handle Load Data button click
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

    // Method to clear text area
    private void clearTextArea() {
        outputTextArea.setText("");
    }

    // Method to prompt user to save before exit
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

    // Getter for outputTextArea
    public JTextArea getOutputTextArea() {
        return outputTextArea;
    }

    // Main method to launch the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
