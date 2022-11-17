package Screens;

import Controllers.MetricSumController;
import Entities.MetricStorageInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class StartScreen extends JFrame implements ActionListener {

    MetricStorageInterface metricStorage;
    MetricSumController metricSumController;

    public StartScreen(MetricStorageInterface metricStorage, MetricSumController metricSumController){

        this.metricStorage = metricStorage;
        this.metricSumController = metricSumController;

        JLabel title = new JLabel("Mewdy");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton importButton = new JButton("Import");
        JButton newButton = new JButton("New");

        JPanel buttons = new JPanel();
        buttons.add(importButton);
        buttons.add(newButton);

        importButton.addActionListener(this);
        newButton.addActionListener(this);
        newButton.setActionCommand("New");

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(title);
        main.add(buttons);
        this.add(main);
    }

    // React to button click
    public void actionPerformed(ActionEvent evt){
        if (Objects.equals(evt.getActionCommand(), "New")){
            this.dispose();
            new HomeScreen(metricStorage, metricSumController);
        }
        System.out.println("Clicked");
    }
}
