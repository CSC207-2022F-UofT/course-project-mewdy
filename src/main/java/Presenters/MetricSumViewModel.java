package Presenters;

import org.jfree.chart.JFreeChart;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MetricSumViewModel {

    JFreeChart chart;
    String metricAverageAndSize;


    public MetricSumViewModel(JFreeChart chart, String metricAverageAndSize){
        this.chart = chart;
        this.metricAverageAndSize = metricAverageAndSize;
    }

    public JFreeChart getChart(){ return chart;}

    public String getMetricAverageAndSize(){ return  metricAverageAndSize;}
}
