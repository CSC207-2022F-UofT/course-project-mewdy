package Screens;

import Controllers.MetricSumController;
import Entities.MetricStorage;
import Entities.MetricStorageInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JPanel implements ActionListener {

    CardLayout cardLayout;
    JPanel screens;


    public HomeScreen(CardLayout cardLayout, JPanel screens) {

        this.cardLayout = cardLayout;
        this.screens = screens;

        JLabel title = new JLabel("Home");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);

    }

    //React to summary button click
    public void actionPerformed(ActionEvent evt){
        if (evt.getActionCommand().equals("Summary")){
            cardLayout.show(screens, "chooseMetricSum");
        }
        System.out.println("Clicked");

    }
}
