package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Random;


public class GUI extends JFrame{
    private JTextArea outputArea;
    private JTextField inputField;
    private JButton button;

    public GUI(){
        setTitle("Cycling Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        seetSize(400, 300);
        setLocationRelativeTo(null);

        //create output area
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setBackground(new Color(48, 48, 48));
        outputArea.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        //create text field for entering data
        dataField = JTextField(10);
        dataField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                button.doClick();
            }
        });

        //create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.add(new JLabel("Enter data: "));
        buttonPanel.add(dataField);
        buttonPanel.add(button);

        //add panels to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}


