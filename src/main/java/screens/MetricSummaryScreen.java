package screens;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.*;

public class MetricSummaryScreen extends JFrame {

    final String metricAvgAndSize;
    final XYChart chart;

    public MetricSummaryScreen(String metricAvgAndSize, XYChart chart) {
        this.metricAvgAndSize = metricAvgAndSize;
        this.chart = chart;

        JLabel title = new JLabel(metricAvgAndSize);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setSize(700, 700);

        XChartPanel<XYChart> chartPanel = new XChartPanel<XYChart>(chart);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
        main.add(title);
        main.add(chartPanel);
        this.add(main);

        this.setVisible(true);


    }
}
