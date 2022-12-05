package screens;

import controllers.DataImportController;
import models.ImportResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class StartScreen extends JPanel implements ActionListener {

    CardLayout cardlayout;
    JPanel screens;
    DataImportController dataImportController;

    public StartScreen(CardLayout cardLayout, JPanel screens, DataImportController dataImportController) {

        this.cardlayout = cardLayout;
        this.screens = screens;
        this.dataImportController = dataImportController;


        JLabel title = new JLabel("Mewdy");
        title.setFont(new Font("Calibri", Font.BOLD, 50));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("A configurable metric tracking program");
        subtitle.setFont(new Font("Calibri", Font.BOLD, 18));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        this.add(subtitle);
        this.add(buttons);
    }

    // React to button click
    public void actionPerformed(ActionEvent evt) {
        if (Objects.equals(evt.getActionCommand(), "New")) {
            cardlayout.show(screens, "home");
        }
        if (evt.getActionCommand().equals("Import")) {
            JFileChooser importFileChooser = new JFileChooser();
            importFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            //Select file to open
            int response = importFileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(importFileChooser.getSelectedFile().getAbsolutePath());
                ImportResponseModel responseModel = dataImportController.readFromNewFile(file.getAbsolutePath());
                if (responseModel.getErrorMsg().length() > 1) {
                    JOptionPane.showMessageDialog(this, responseModel.getErrorMsg());
                } else {
                    JOptionPane.showMessageDialog(this, "Success!");
                    cardlayout.show(screens, "home");
                }
            }
        }
    }
}

