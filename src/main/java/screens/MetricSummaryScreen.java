package screens;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.*;

/**
 * Subclass of JFrame that displays a chart of the metric summary
 */
public class MetricSummaryScreen extends JFrame {

    final String metricAvgAndSize;
    final XYChart chart;
    final String goalStat;

    /**
     * Constructor for the MetricSummaryScreen
     *
     * @param metricAvgAndSize represents the average and size of the metric
     * @param chart represents the chart of the metric summary
     */
    public MetricSummaryScreen(String metricAvgAndSize, XYChart chart) {
        this.metricAvgAndSize = metricAvgAndSize;
        this.chart = chart;
        this.goalStat = null;

        JLabel title = new JLabel(metricAvgAndSize);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(null, Font.PLAIN, 20));
        this.setSize(700, 700);

        XChartPanel<XYChart> chartPanel = new XChartPanel<XYChart>(chart);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
        main.add(title);
        main.add(chartPanel);
        this.add(main);

        this.setVisible(true);

    }

    public MetricSummaryScreen(String metricAvgAndSize, XYChart chart, String goalStat) {
        this.metricAvgAndSize = metricAvgAndSize;
        this.chart = chart;
        this.goalStat = goalStat;

        JLabel title = new JLabel(metricAvgAndSize);
        JLabel goalStatMessage = new JLabel(goalStat);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(null, Font.PLAIN, 20));
        goalStatMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        goalStatMessage.setFont(new Font(null, Font.PLAIN, 20));
        this.setSize(700, 700);

        XChartPanel<XYChart> chartPanel = new XChartPanel<XYChart>(chart);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
        main.add(title);
        main.add(goalStatMessage);
        main.add(chartPanel);
        this.add(main);

        this.setVisible(true);


    }
}
