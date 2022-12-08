package screens;

import controllers.SetGoalController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetGoalTab extends JPanel implements ActionListener {

    String metricName;
    double upperBound;
    double lowerBound;
    JButton setGoalButton;
    JTextField goalInput;
    JButton  backButton;
    SetGoalController setGoalController;
    CardLayout cardLayout;
    JPanel screens;

    public SetGoalTab(String metricName, double upperBound, double lowerBound, SetGoalController setGoalController,
                      CardLayout cardLayout, JPanel screens){
        this.metricName = metricName;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.setGoalController = setGoalController;
        this.cardLayout = cardLayout;
        this.screens = screens;

        //Create text to be displayed. Contains metric-specific information.
        JLabel title = new JLabel("Set goal for " + metricName);
        JLabel bounds = new JLabel("Upper Bound: " + upperBound + " Lower Bound: " +
                lowerBound);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        bounds.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create buttons and textfields for user input
        setGoalButton = new JButton("Set Goal");
        setGoalButton.addActionListener(this);
        goalInput = new JTextField();
        goalInput.setPreferredSize(new Dimension(250,40));
        backButton = new JButton("Home");
        backButton.addActionListener(this);

        // Add buttons and textfields to panel
        this.add(title);
        JPanel main = new JPanel();
        main.add(bounds);
        main.add(goalInput);
        main.add(setGoalButton);
        main.add(backButton);
        this.add(main);


    }

    // React to button press of user
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == backButton) {
            cardLayout.show(screens, "home");
        }
        if (evt.getSource() == setGoalButton) {
            try {
                double goal = Double.parseDouble(goalInput.getText());
                String result = setGoalController.setGoal(goal, metricName);
                JOptionPane.showMessageDialog(this, result);
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this, "Invalid input! Please enter a number");
            }
        }
    }
}
