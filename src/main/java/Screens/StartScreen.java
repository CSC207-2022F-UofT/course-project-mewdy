package Screens;

import Controllers.MetricSumController;
import Entities.MetricStorageInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class StartScreen extends JPanel implements ActionListener {

    CardLayout cardlayout;
    JPanel screens;

    public StartScreen(CardLayout cardLayout, JPanel screens){

        this.cardlayout = cardLayout;
        this.screens = screens;


        JLabel title = new JLabel("Start screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton importButton = new JButton("Import");
        JButton newButton = new JButton("New");

        JPanel buttons = new JPanel();
        buttons.add(importButton);
        buttons.add(newButton);

        importButton.addActionListener(this);
        importButton.setActionCommand("Import");
        newButton.addActionListener(this);
        newButton.setActionCommand("New");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }

    // React to button click
    public void actionPerformed(ActionEvent evt){
        if (Objects.equals(evt.getActionCommand(), "New")){
            cardlayout.show(screens, "home");
        }
        if (evt.getActionCommand().equals("Import")){
            JFileChooser importFileChooser = new JFileChooser();

            //Select file to open
            int response = importFileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION){
                File file = new File(importFileChooser.getSelectedFile().getAbsolutePath());
            }

        }
    }
}
