package Screens;

import Presenters.MetricSumViewModel;
import org.jfree.chart.ChartPanel;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.*;

public class MetricSummaryScreen extends JFrame {

    final String metricAvgAndSize;
    final XYChart chart;

    public MetricSummaryScreen(String metricAvgAndSize, XYChart chart){
        this.metricAvgAndSize = metricAvgAndSize;
        this.chart = chart;

        JLabel title = new JLabel(metricAvgAndSize);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setSize(500,500);

        XChartPanel<XYChart> chartPanel = new XChartPanel<XYChart>(chart);

        this.add(title);
        this.add(chartPanel);

        this.setVisible(true);


    }
}