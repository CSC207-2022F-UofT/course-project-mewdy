package screens;

import entities.Metric;
import entities.MetricStorageInterface;
import controllers.MetricSumController;
import presenters.MetricSumViewModel;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class ChooseMetricSumScreen extends JPanel implements ActionListener, Refreshable{

    MetricStorageInterface metricStorage;
    MetricSumController metricSumController;
    CardLayout cardLayout;
    JPanel screens;
    JButton backButton;

    public ChooseMetricSumScreen(MetricStorageInterface metricStorageInterface,
                                 MetricSumController metricSumController, CardLayout cardLayout, JPanel screens){
        this.metricStorage = metricStorageInterface;
        this.metricSumController = metricSumController;
        this.cardLayout = cardLayout;
        this.screens = screens;

        this.setName("ChooseMetricSum");

        JLabel title = new JLabel("Metric Summary");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons for every Metric in MetricStorage.
        JPanel buttons = new JPanel();
        ArrayList<Metric> metricList = metricStorage.getMetricList();
        for (Metric m: metricList){
            JButton metricButton = new JButton(m.getName());
            buttons.add(metricButton);
            metricButton.addActionListener( this);
            metricButton.setActionCommand(m.getName());
        }

        backButton = new JButton("Back");
        backButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
        this.add(backButton);
    }

    //React to button press to summarize chosen Metric.
    public void actionPerformed(ActionEvent evt){
        if (evt.getSource() == backButton){
            cardLayout.show(screens, "home");
        }
        else {
            try {
                MetricSumViewModel viewModel = metricSumController.getMetricSummary(evt.getActionCommand());
                String averageAndTrend = viewModel.getMetricAverageAndSize();
                XYChart chart = viewModel.getChart();
                new MetricSummaryScreen(averageAndTrend, chart);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    @Override
    public void refresh(){
        this.removeAll();

        JLabel title = new JLabel("Metric Summary");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons for every Metric in MetricStorage.
        JPanel buttons = new JPanel();
        ArrayList<Metric> metricList = metricStorage.getMetricList();
        for (Metric m: metricList){
            JButton metricButton = new JButton(m.getName());
            buttons.add(metricButton);
            metricButton.addActionListener( this);
            metricButton.setActionCommand(m.getName());
        }
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
        this.add(backButton);

        this.revalidate();
        this.repaint();
    }
}