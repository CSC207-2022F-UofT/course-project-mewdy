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
    JButton backButton;


    public HomeScreen(CardLayout cardLayout, JPanel screens) {

        this.cardLayout = cardLayout;
        this.screens = screens;

        JLabel title = new JLabel("Home");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton recordButton = new JButton("Record");
        JButton summaryButton = new JButton("Summary");
        JButton saveButton = new JButton("Save");
        JButton exportButton = new JButton("Export");
        JButton entryUndoButton = new JButton("Undo Entry");
        JButton addMetricButton = new JButton("Add Metric");
        JButton deleteMetricButton = new JButton("Delete Metric");
        backButton = new JButton("Back");

        JPanel buttons = new JPanel();
        buttons.add(recordButton);
        buttons.add(summaryButton);
        buttons.add(saveButton);
        buttons.add(exportButton);
        buttons.add(entryUndoButton);
        buttons.add(addMetricButton);
        buttons.add(deleteMetricButton);
        buttons.add(backButton);

        summaryButton.addActionListener(this);
        summaryButton.setActionCommand("Summary");

        recordButton.addActionListener(this);
        recordButton.setActionCommand("dataLogChoose");

        backButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);

    }

    //React to summary button click
    public void actionPerformed(ActionEvent evt){
        if (evt.getActionCommand().equals("Summary")){
            cardLayout.show(screens, "chooseMetricSum");
        }
        if (evt.getActionCommand().equals("dataLogChoose")){
            cardLayout.show(screens, "dataLogChoose");
        }
        if (evt.getSource() == backButton){
            cardLayout.previous(screens);
        }

    }
}
