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
    CardLayout cardLayout;
    JPanel screens;
    JButton backButton;

    public ChooseMetricSumScreen(MetricStorageInterface metricStorageInterface,
                                 MetricSumController metricSumController, CardLayout cardLayout, JPanel screens) {
        this.metricStorage = metricStorageInterface;
        this.metricSumController = metricSumController;
        this.cardLayout = cardLayout;
        this.screens = screens;


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

        backButton = new JButton("Home");
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
            try {
                MetricSumViewModel viewModel = metricSumController.getMetricSummary(evt.getActionCommand());
                MetricSummaryScreen summaryScreen = constructSummary(viewModel);
                summaryScreen.setVisible(true);
            } catch (NullPointerException | DataSummaryFailed e) {
                JOptionPane.showMessageDialog(this , e.getMessage());
            }
        }
    }

    /**
     * A helper method to extract data contained in the view model which is used to make the MetricSummaryScreen.
     * @param viewModel represents view model from which this method extracts information to construct
     *                  MetricSummaryScreen
     * @return a new MetricSummary screen containing the relevant metric information to be displayed
     */
    private MetricSummaryScreen constructSummary(MetricSumViewModel viewModel){
        String statistics = viewModel.getMetricStatistics();
        XYChart chart = viewModel.getChart();
        if (viewModel.getContainsGoalStat()){
            String goalStat = viewModel.getGoalStat();
            return new MetricSummaryScreen(statistics, chart, goalStat);
        }
        return new MetricSummaryScreen(statistics, chart);
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
        backButton = new JButton("Home");
        backButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(buttons);
        this.add(backButton);

        this.revalidate();
        this.repaint();
    }
}
