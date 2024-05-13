package UI.methods;

import java.awt.*;
import javax.swing.*;

public class FontSelector {

    private JComboBox<String> fontComboBox;
    private JSpinner fontSizeSpinner;

    public FontSelector(JTextArea textArea) {
        initializeComponents(textArea);
    }

    private void initializeComponents(JTextArea textArea) {
        JPanel fontPanel = new JPanel();
        JLabel fontLabel = new JLabel("Font:");
        fontComboBox = new JComboBox<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        fontComboBox.addActionListener(e -> updateFont(textArea));

        JLabel sizeLabel = new JLabel("Size:");
        fontSizeSpinner = new JSpinner(new SpinnerNumberModel(16, 8, 72, 1));
        fontSizeSpinner.addChangeListener(e -> updateFont(textArea));

        fontPanel.add(fontLabel);
        fontPanel.add(fontComboBox);
        fontPanel.add(sizeLabel);
        fontPanel.add(fontSizeSpinner);

        // Optionally, you can add this panel to a container or frame
        // For example:
        // JFrame frame = new JFrame();
        // frame.add(fontPanel);
        // frame.pack();
        // frame.setVisible(true);
    }

    private void updateFont(JTextArea textArea) {
        String selectedFont = (String) fontComboBox.getSelectedItem();
        int fontSize = (int) fontSizeSpinner.getValue();
        Font font = new Font(selectedFont, Font.PLAIN, fontSize);
        textArea.setFont(font);
    }
}
