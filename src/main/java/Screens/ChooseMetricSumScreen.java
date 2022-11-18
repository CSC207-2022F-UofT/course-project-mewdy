package Screens;

import Entities.Metric;
import Entities.MetricStorageInterface;
import Controllers.MetricSumController;
import Presenters.MetricSumViewModel;
import org.knowm.xchart.SwingWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

//import org.jfree.chart

public class ChooseMetricSumScreen extends JFrame implements ActionListener{

    MetricStorageInterface metricStorage;
    MetricSumController metricSumController;

    public ChooseMetricSumScreen(MetricStorageInterface metricStorageInterface,
                                 MetricSumController metricSumController){
        this.metricStorage = metricStorageInterface;
        this.metricSumController = metricSumController;

        JLabel title = new JLabel("Metric Summary");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setSize(500, 500);

        // Add buttons for every Metric in MetricStorage.
        JPanel buttons = new JPanel();
        ArrayList<Metric> metricList = metricStorage.getMetricList();
        for (Metric m: metricList){
            JButton metricButton = new JButton(m.getName());
            buttons.add(metricButton);
            metricButton.addActionListener( this);
            metricButton.setActionCommand(m.getName());
        }

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(buttons);
        
        this.add(title);
        this.add(main);
        this.setVisible(true);

    }

    //React to button press to summarize chosen Metric.
    public void actionPerformed(ActionEvent evt){
        try {
            MetricSumViewModel viewModel = metricSumController.getMetricSummary(evt.getActionCommand());
            String averageAndTrend = viewModel.getMetricAverageAndSize();
            JOptionPane.showMessageDialog(this, averageAndTrend);
            new SwingWrapper(viewModel.getChart()).displayChart();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
