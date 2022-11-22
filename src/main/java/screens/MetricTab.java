package screens;

import controllers.MetricDelController;
import entities.Metric;
import models.MetricDelResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MetricTab extends JPanel {
    private final JTabbedPane pane;
    private final MetricDelController metricDelController;
    private final String title;

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

    private class DeleteButton extends JButton implements ActionListener{

        public DeleteButton(){
            this.setText("x");
            setToolTipText("Delete this metric");
            addActionListener(this);
        }

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
