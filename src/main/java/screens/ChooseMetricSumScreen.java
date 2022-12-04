package screens;

import controllers.MetricSumController;
import entities.Metric;
import entities.MetricStorageInterface;
import org.knowm.xchart.XYChart;
import presenters.MetricSumViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChooseMetricSumScreen extends JPanel implements ActionListener, Refreshable {

    MetricStorageInterface metricStorage;
    MetricSumController metricSumController;
    MetricSumController metricSumWithGoalController;
    CardLayout cardLayout;
    JPanel screens;
    JButton backButton;

    public ChooseMetricSumScreen(MetricStorageInterface metricStorageInterface,
                                 MetricSumController metricSumController, CardLayout cardLayout, JPanel screens,
                                 MetricSumController metricSumWithGoalController) {
        this.metricStorage = metricStorageInterface;
        this.metricSumController = metricSumController;
        this.cardLayout = cardLayout;
        this.screens = screens;
        this.metricSumWithGoalController = metricSumWithGoalController;

        this.setName("ChooseMetricSum");

        JLabel title = new JLabel("Metric Summary");
        title.setFont(new Font(null, Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);

        if (metricStorage.getMetricList().size() == 0){
            JLabel noMetricMessage = new JLabel("No metrics are being tracked. Go add some!");
            noMetricMessage.setFont(new Font(null, Font.BOLD, 15));
            noMetricMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(noMetricMessage);
        }
        // Add buttons for every Metric in MetricStorage.
        JPanel buttons = new JPanel();
        ArrayList<Metric> metricList = metricStorage.getMetricList();
        for (Metric m : metricList) {
            JButton metricButton = new JButton(m.getName());
            buttons.add(metricButton);
            metricButton.addActionListener(this);
            metricButton.setActionCommand(m.getName());
        }

        backButton = new JButton("Back");
        backButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(buttons);
        this.add(backButton);
    }

    //React to button press to summarize chosen Metric.
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == backButton) {
            cardLayout.show(screens, "home");
        } else {
            if (checkHasGoal(evt.getActionCommand())){
                try {
                    MetricSumViewModel viewModel = metricSumWithGoalController.getMetricSummary(evt.getActionCommand());
                    String averageAndTrend = viewModel.getMetricStatistics();
                    String goalStat = viewModel.getGoalStat();
                    XYChart chart = viewModel.getChart();
                    new MetricSummaryScreen(averageAndTrend, chart, goalStat);
                } catch (NullPointerException | DataSummaryFailed e) {
                    JOptionPane.showMessageDialog(this , e.getMessage());
                }
            }
            else {
                try {
                    MetricSumViewModel viewModel = metricSumController.getMetricSummary(evt.getActionCommand());
                    String statistics = viewModel.getMetricStatistics();
                    XYChart chart = viewModel.getChart();
                    new MetricSummaryScreen(statistics, chart);
                } catch (NullPointerException | DataSummaryFailed e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
        }
    }

    private boolean checkHasGoal(String metricName){
        Metric metric = metricStorage.getMetric(metricName);
        return metric.getGoalStatus();
    }

    @Override
    public void refresh() {
        this.removeAll();

        JLabel title = new JLabel("Metric Summary");
        title.setFont(new Font(null, Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);

        if (metricStorage.getMetricList().size() == 0){
            JLabel noMetricMessage = new JLabel("No metrics are being tracked. Go add some!");
            noMetricMessage.setFont(new Font(null, Font.BOLD, 15));
            noMetricMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(noMetricMessage);
        }
        // Add buttons for every Metric in MetricStorage.
        JPanel buttons = new JPanel();
        ArrayList<Metric> metricList = metricStorage.getMetricList();
        for (Metric m : metricList) {
            JButton metricButton = new JButton(m.getName());
            buttons.add(metricButton);
            metricButton.addActionListener(this);
            metricButton.setActionCommand(m.getName());
        }
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(buttons);
        this.add(backButton);

        this.revalidate();
        this.repaint();
    }
}
