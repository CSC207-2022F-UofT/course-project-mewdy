package screens;

import controllers.AddMetricController;
import entities.MetricStorageInterface;
import models.AddMetricResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Subclass of JPanel that displays the screen for adding a metric and implements the ActionListener interface
 */

public class AddMetricScreen extends JPanel implements ActionListener {

    MetricStorageInterface metricStorage;
    JButton sleepButton;
    JButton moodButton;
    JButton createButton;
    JTextField nameInput;
    JTextField upperBoundInput;
    JTextField lowerBoundInput;
    AddMetricController addMetricController;
    final Refreshable tabbedPane;
    JButton backButton;
    CardLayout cardLayout;
    JPanel screens;

    /**
     * Constructor for the AddMetricScreen class
     *
     * @param metricStorage represents the metric storage object
     * @param addMetricController represents the add metric controller object
     * @param tabbedPane represents the tabbed pane object
     */
    public AddMetricScreen(MetricStorageInterface metricStorage, AddMetricController addMetricController,
                           Refreshable tabbedPane, CardLayout cardLayout, JPanel screens){
        this.metricStorage = metricStorage;
        this.addMetricController = addMetricController;
        this.tabbedPane = tabbedPane;
        this.cardLayout = cardLayout;
        this.screens = screens;

        JLabel title = new JLabel("Create a Metric to track");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel presetMetrics = new JPanel();
        JLabel presetTitle = new JLabel("Select from preset Metrics");
        presetTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        sleepButton = new JButton("Sleep");
        sleepButton.addActionListener(this);
        moodButton = new JButton("Mood");
        moodButton.addActionListener(this);
        presetMetrics.add(presetTitle);
        presetMetrics.add(sleepButton);
        presetMetrics.add(moodButton);

        JPanel customMetrics = new JPanel();
        JLabel customMetricTitle = new JLabel("Create your own Metric to track");
        customMetricTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel customMetricName = new JLabel("Name");
        customMetricName.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel customUpperBound = new JLabel("Upper Bound");
        customUpperBound.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel customLowerBound = new JLabel("Lower Bound");
        customLowerBound.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameInput = new JTextField();
        upperBoundInput = new JTextField();
        lowerBoundInput = new JTextField();
        nameInput.setPreferredSize(new Dimension(100,40));
        upperBoundInput.setPreferredSize(new Dimension(100,40));
        lowerBoundInput.setPreferredSize(new Dimension(100,40));

        createButton = new JButton("Create");
        createButton.addActionListener(this);

        customMetrics.add(customMetricTitle);
        customMetrics.add(customMetricName);
        customMetrics.add(nameInput);
        customMetrics.add(customUpperBound);
        customMetrics.add(upperBoundInput);
        customMetrics.add(customLowerBound);
        customMetrics.add(lowerBoundInput);
        customMetrics.add(createButton);

        this.add(presetMetrics);
        this.add(customMetrics);

        backButton = new JButton("Home");
        backButton.addActionListener(this);
        this.add(backButton);

    }

    /**
     * actionPerformed handles the action events for the buttons on the screen
     *
     * @param evt represents the event to be processed
     */
    public void actionPerformed(ActionEvent evt){
        if (evt.getSource() == backButton){
            cardLayout.show(screens, "home");
        }
        if (evt.getSource() == createButton){
            try {
                AddMetricResponseModel responseModel = addMetricController.addMetric(nameInput.getText(),
                        Double.parseDouble(upperBoundInput.getText()), Double.parseDouble(lowerBoundInput.getText()));
                JOptionPane.showMessageDialog(this, responseModel.getMessage());
                this.tabbedPane.refresh();
            } catch (AddMetricFail error){
                JOptionPane.showMessageDialog(this, error.getMessage());
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this, "Invalid input! Please enter a number.");
            }
        }
        if (evt.getSource() == sleepButton){
            try {
                AddMetricResponseModel responseModel = addMetricController.addMetric("Sleep",
                        24.0, 0.0);
                JOptionPane.showMessageDialog(this, responseModel.getMessage());
                this.tabbedPane.refresh();
            } catch (AddMetricFail error){
                JOptionPane.showMessageDialog(this, error.getMessage());
            }
        }
        if (evt.getSource() == moodButton){
            try {
                AddMetricResponseModel responseModel = addMetricController.addMetric("Mood",
                        10.0, 0.0);
                JOptionPane.showMessageDialog(this, responseModel.getMessage());
                this.tabbedPane.refresh();
            } catch (AddMetricFail error){
                JOptionPane.showMessageDialog(this, error.getMessage());
            }
        }

    }
}
