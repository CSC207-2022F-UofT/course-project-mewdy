package screens;

import entities.MetricStorageInterface;

import javax.swing.*;
import java.awt.*;

public class AddMetricScreen extends JPanel {

    MetricStorageInterface metricStorage;
    JButton sleepButton;
    JButton moodButton;
    JButton createButton;

    public AddMetricScreen(MetricStorageInterface metricStorage){
        this.metricStorage = metricStorage;

        JLabel title = new JLabel("Create a Metric to track");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel presetMetrics = new JPanel();
        JLabel presetTitle = new JLabel("Select from preset Metrics");
        presetTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        sleepButton = new JButton("Sleep");
        moodButton = new JButton("Mood");
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

        JTextField nameInput = new JTextField();
        JTextField upperBoundInput = new JTextField();
        JTextField lowerBoundInput = new JTextField();
        nameInput.setPreferredSize(new Dimension(100,40));
        upperBoundInput.setPreferredSize(new Dimension(100,40));
        lowerBoundInput.setPreferredSize(new Dimension(100,40));

        createButton = new JButton("Create");

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

    }
}
