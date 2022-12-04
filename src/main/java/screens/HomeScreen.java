package screens;

import controllers.DataExportController;
import entities.MetricStorageInterface;
import models.ExportResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class HomeScreen extends JPanel implements ActionListener {

    CardLayout cardLayout;
    JPanel screens;
    DataExportController dataExportController;
    JButton recordButton;
    JButton summaryButton;
    JButton saveButton;
    JButton exportButton;
    JButton setGoalButton;
    MetricStorageInterface metricStorage;


    public HomeScreen(CardLayout cardLayout, JPanel screens, DataExportController dataExportController,
                      MetricStorageInterface metricStorage) {

        this.cardLayout = cardLayout;
        this.screens = screens;
        this.dataExportController = dataExportController;
        this.metricStorage = metricStorage;

        JLabel title = new JLabel("Home");
        title.setFont(new Font("Calibri", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        recordButton = new JButton("Record");
        summaryButton = new JButton("Summary");
        saveButton = new JButton("Save");
        exportButton = new JButton("Export");
        setGoalButton = new JButton("Set Goal");

        JPanel buttons = new JPanel();
        buttons.add(recordButton);
        buttons.add(summaryButton);
        buttons.add(setGoalButton);
        buttons.add(saveButton);
        buttons.add(exportButton);


        summaryButton.addActionListener(this);
        recordButton.addActionListener(this);
        exportButton.addActionListener(this);
        saveButton.addActionListener(this);
        setGoalButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);

    }

    //React to summary button click
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == summaryButton) {
            refreshScreen("ChooseMetricSum");
            cardLayout.show(screens, "chooseMetricSum");
        }
        if (evt.getSource() == recordButton) {
            refreshScreen("DataLogChoose");
            cardLayout.show(screens, "dataLogChoose");
        }
        if (evt.getSource() == setGoalButton){
            refreshScreen("SetGoal");
            cardLayout.show(screens, "setGoal");
        }
        if (evt.getSource() == exportButton) {
            //Set up file chooser
            JFileChooser exportFileChooser = new JFileChooser();
            exportFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            //Select location to export
            int response = exportFileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(exportFileChooser.getSelectedFile().getAbsolutePath());
                ExportResponseModel responseModel = dataExportController.writeToNewFile(file.getAbsolutePath());
                if (responseModel.getErrorMsg().length() > 1) {
                    JOptionPane.showMessageDialog(this, responseModel.getErrorMsg());
                } else {
                    JOptionPane.showMessageDialog(this, "Success!");
                }
            }
        }
        if (evt.getSource() == saveButton){
            ExportResponseModel responseModel = dataExportController.writeToNewFile(metricStorage.getPath().getAbsolutePath());
            if (responseModel.getErrorMsg().length() > 1) {
                JOptionPane.showMessageDialog(this, responseModel.getErrorMsg());
            } else {
                JOptionPane.showMessageDialog(this, "Success!");
            }
        }
    }

    private void refreshScreen(String screenName) {
        for (Component c : screens.getComponents()) {
            if (c instanceof Refreshable && Objects.equals(c.getName(), screenName)) {
                ((Refreshable) c).refresh();
            }
        }
    }

}
