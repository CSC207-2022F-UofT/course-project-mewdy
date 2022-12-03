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

/**
 * Subclass of JPanel that displays the screen for choosing a metric to sum and implements
 * both the ActionListener and MetricStorageInterface interfaces.
 */
public class ChooseMetricSumScreen extends JPanel implements ActionListener, Refreshable {

    MetricStorageInterface metricStorage;
    MetricSumController metricSumController;
    CardLayout cardLayout;
    JPanel screens;
    JButton backButton;

    /**
     * Constructor for the ChooseMetricSumScreen
     *
     * @param metricStorageInterface represents the metric storage interface
     * @param metricSumController represents the metric sum controller
     * @param cardLayout represents the card layout
     * @param screens represents the screens
     */
    public ChooseMetricSumScreen(MetricStorageInterface metricStorageInterface,
                                 MetricSumController metricSumController, CardLayout cardLayout, JPanel screens) {
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
        for (Metric m : metricList) {
            JButton metricButton = new JButton(m.getName());
            buttons.add(metricButton);
            metricButton.addActionListener(this);
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

    /**
     * actionPerformed reacts to button press to summarize chosen Metric
     *
     * @param evt represents the event to be processed
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == backButton) {
            cardLayout.show(screens, "home");
        } else {
            try {
                MetricSumViewModel viewModel = metricSumController.getMetricSummary(evt.getActionCommand());
                String averageAndTrend = viewModel.getMetricAverageAndSize();
                XYChart chart = viewModel.getChart();
                new MetricSummaryScreen(averageAndTrend, chart);
            } catch (NullPointerException | DataSummaryFailed e) {
                JOptionPane.showMessageDialog(this , e.getMessage());
            }
        }
    }

    /**
     * refresh refreshes the screen
     */
    @Override
    public void refresh() {
        this.removeAll();

        JLabel title = new JLabel("Metric Summary");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        this.add(title);
        this.add(buttons);
        this.add(backButton);

        this.revalidate();
        this.repaint();
    }
}
