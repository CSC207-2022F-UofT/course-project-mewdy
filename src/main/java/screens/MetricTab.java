package screens;

import controllers.MetricDelController;
import entities.Metric;
import models.MetricDelResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Subclass of JPanel that displays the Metric tab
 */

public class MetricTab extends JPanel {
    private final JTabbedPane pane;
    private final MetricDelController metricDelController;
    private final String title;

    /**
     * Constructor for the MetricTab class
     *
     * @param pane represents the JTabbedPane that the MetricTab is added to
     * @param title represents the title of the MetricTab
     * @param metricDelController represents the MetricDelController that is used to delete a metric
     */
    public MetricTab(final JTabbedPane pane, String title, MetricDelController metricDelController){
        this.pane = pane;
        this.metricDelController = metricDelController;
        this.title = title;

        JLabel label = new JLabel(title);
        this.add(label);

        //create tab button
        JButton deleteButton = new DeleteButton();
        this.add(deleteButton);
    }

    /**
     * Subclass of JButton that represents the delete button for the MetricTab and implements ActionListener
     */
    private class DeleteButton extends JButton implements ActionListener{

        /**
         * Constructor for the DeleteButton class
         */
        public DeleteButton(){
            this.setText("x");
            setToolTipText("Delete this metric");
            addActionListener(this);
        }

        /**
         * actionPerformed is called when the event is triggered
         *
         * @param evt the event to be processed
         */
        public void actionPerformed(ActionEvent evt){
            int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to " +
                    "delete this metric? Any data stored will be lost.","Select an Option",
                    JOptionPane.YES_NO_OPTION);
            if (input == JOptionPane.YES_OPTION) {
                MetricDelResponseModel responseModel = metricDelController.create(title);
                JOptionPane.showMessageDialog(this, responseModel.getMetricName() + " was deleted. " +
                        "It contained " + responseModel.getNumDataPoints() + " data points.");
                int i = pane.indexOfTabComponent(MetricTab.this);
                pane.remove(i);
            }
        }


    }


}
