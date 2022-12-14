package screens;

import controllers.DataLoggerController;
import controllers.EntryUndoController;
import models.DataLoggerResponseModel;
import models.EntryUndoResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Subclass of JPanel that contains the input screen for the data logger and implements the ActionListener interface
 */
public class DataLogInputScreen extends JPanel implements ActionListener {

    String metricName;
    double upperBound;
    double lowerBound;
    JButton recordButton;
    JTextField dataInput;
    JButton  backButton;
    JButton undoButton;
    EntryUndoController entryUndoController;
    DataLoggerController dataLoggerController;
    CardLayout cardLayout;
    JPanel screens;

    /**
     * Constructor for the DataLogInputScreen class
     *
     * @param metricName represents the name of the metric
     * @param upperBound represents the upper bound of the metric
     * @param lowerBound represents the lower bound of the metric
     * @param entryUndoController represents the controller for the entry undo feature
     * @param dataLoggerController represents the controller for the data logger feature
     * @param cardLayout represents the card layout of the main frame
     * @param screens represents the JPanel that contains all the screens
     */

    public DataLogInputScreen(String metricName, double upperBound, double lowerBound,
                              EntryUndoController entryUndoController, DataLoggerController dataLoggerController,
                              CardLayout cardLayout, JPanel screens){
        this.metricName = metricName;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.dataLoggerController = dataLoggerController;
        this.entryUndoController = entryUndoController;
        this.cardLayout = cardLayout;
        this.screens = screens;

        JLabel title = new JLabel("Input data for " + metricName);
        JLabel bounds = new JLabel("Upper Bound: " + upperBound + " Lower Bound: " +
                lowerBound);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        bounds.setAlignmentX(Component.CENTER_ALIGNMENT);


        recordButton = new JButton("Record");
        recordButton.addActionListener(this);
        dataInput = new JTextField();
        dataInput.setPreferredSize(new Dimension(250,40));
        backButton = new JButton("Home");
        backButton.addActionListener(this);
        undoButton = new JButton("Undo");
        undoButton.addActionListener(this);

        this.add(title);
        this.add(bounds);
        this.add(dataInput);
        this.add(recordButton);
        this.add(backButton);
        this.add(undoButton);

    }

    /**
     * actionPerformed method that handles the actions of the buttons
     *
     * @param evt the event to be processed
     */
    public void actionPerformed(ActionEvent evt){
        if (evt.getSource() == backButton){
            cardLayout.show(screens, "home");
        }
        if (evt.getSource() == recordButton){
            try {
                double dataValue = Double.parseDouble(dataInput.getText());
                DataLoggerResponseModel responseModel = dataLoggerController.logDataPoint(dataValue, metricName);
                JOptionPane.showMessageDialog(this, responseModel.getMessage());
            } catch (DataLogFailed e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this, "Invalid input! Please enter a number");
            }
        }
        if (evt.getSource() == undoButton){
            try {
                EntryUndoResponseModel responseModel = entryUndoController.deleteLastEntry(metricName);
                JOptionPane.showMessageDialog(this, responseModel.getInformation());
            } catch (Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
}
