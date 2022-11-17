package Screens;

import Controllers.MetricSumController;
import Entities.MetricStorage;
import Entities.MetricStorageInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame implements ActionListener {

    MetricStorageInterface metricStorage;
    MetricSumController metricSumController;

    public HomeScreen(MetricStorageInterface metricStorage, MetricSumController metricSumController) {

        this.metricStorage = metricStorage;
        this.metricSumController = metricSumController;

        JLabel title = new JLabel("Home");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton importButton = new JButton("Record");
        JButton summaryButton = new JButton("Summary");
        JButton saveButton = new JButton("Save");
        JButton exportButton = new JButton("Export");

        JPanel buttons = new JPanel();
        buttons.add(importButton);
        buttons.add(summaryButton);
        buttons.add(saveButton);
        buttons.add(exportButton);

        summaryButton.addActionListener(this);
        summaryButton.setActionCommand("Summary");

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(title);
        main.add(buttons);
        this.add(main);
        this.setVisible(true);
    }

    //React to summary button click
    public void actionPerformed(ActionEvent evt){
        if (evt.getActionCommand().equals("Summary")){
            new ChooseMetricSumScreen(metricStorage, metricSumController);
        }
        System.out.println("Clicked");

    }
}
