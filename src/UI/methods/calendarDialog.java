import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class calendarDialog extends JDialog {

    private JCalendar calendar;
    private JButton addEventButton;
    private DefaultListModel<String> eventListModel;
    private JList<String> eventList;

    public calendarDialog(JFrame parent) {
        super(parent, "Training Calendar", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        // Create calendar component
        calendar = new JCalendar();
        calendar.setPreferredSize(new Dimension(400, 300));

        // Create event list
        eventListModel = new DefaultListModel<>();
        eventList = new JList<>(eventListModel);
        eventList.setPreferredSize(new Dimension(150, 300));

        // Add event button
        addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement event creation logic here
            }
        });

        // Layout components
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(calendar, BorderLayout.CENTER);
        mainPanel.add(eventList, BorderLayout.EAST);
        mainPanel.add(addEventButton, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    // Add methods for event creation, editing, deletion, and persistence
}
